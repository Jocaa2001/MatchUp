package com.matchup.userprofile.service;


import com.matchup.common.service.CrudServiceImpl;
import com.matchup.exception.EntityNotFoundException;
import com.matchup.filestorage.FileStorageService;
import com.matchup.user.entity.User;
import com.matchup.userprofile.entity.UserProfile;
import com.matchup.userprofile.repository.UserProfileRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        String uploadedFileName = fileStorageService.upload(multipartFile);
        user.getProfile().setAvatarUrl(uploadedFileName);
        repository.save(user.getProfile());
        return uploadedFileName;
    }

    public InputStream getAvatar(User user) {
        return fileStorageService.get(user.getProfile().getAvatarUrl());
    }
    public InputStream getAvatarByAvatarUrl(String url) {
        if(url == null || url.isEmpty()){
            return null;
        }
        return fileStorageService.get(url);
    }


    public void deleteAvatar(User user) {
        String avatarKey = user.getProfile().getAvatarUrl();

        if (avatarKey == null) return;

        fileStorageService.delete(avatarKey);

        user.getProfile().setAvatarUrl(null);
        repository.save(user.getProfile());
    }
}
