package com.matchup.userprofile.service;


import com.matchup.common.service.CrudServiceImpl;
import com.matchup.exception.EntityNotFoundException;
import com.matchup.filestorage.FileStorageService;
import com.matchup.user.entity.User;
import com.matchup.userprofile.entity.UserProfile;
import com.matchup.userprofile.repository.UserProfileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.util.Optional;

@Service
public class UserProfileService extends CrudServiceImpl<UserProfile, UserProfileRepository> {

    private final FileStorageService fileStorageService;

    public UserProfileService(UserProfileRepository repository, FileStorageService fileStorageService) {
        super(repository);
        this.fileStorageService = fileStorageService;
    }

    public String uploadAvatar(User user, MultipartFile multipartFile){
        UserProfile profile = user.getProfile();
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Complete your profile before uploading an avatar");
        }
        String uploadedFileName = fileStorageService.upload(multipartFile);
        profile.setAvatarUrl(uploadedFileName);
        repository.save(profile);
        return uploadedFileName;
    }

    public InputStream getAvatar(User user) {
        UserProfile profile = user.getProfile();
        if (profile == null || profile.getAvatarUrl() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User has no avatar");
        }
        return fileStorageService.get(profile.getAvatarUrl());
    }
    public InputStream getAvatarByAvatarUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar not found");
        }
        return fileStorageService.get(url);
    }

    public void deleteAvatar(User user) {
        UserProfile profile = user.getProfile();
        if (profile == null) return;

        String avatarKey = profile.getAvatarUrl();
        if (avatarKey == null) return;

        fileStorageService.delete(avatarKey);

        profile.setAvatarUrl(null);
        repository.save(profile);
    }
}
