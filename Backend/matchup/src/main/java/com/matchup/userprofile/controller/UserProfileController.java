package com.matchup.userprofile.controller;


import com.matchup.common.controller.CrudController;
import com.matchup.user.entity.User;
import com.matchup.userprofile.dto.AvatarUploadResponse;
import com.matchup.userprofile.dto.UserProfileDTO;
import com.matchup.userprofile.entity.UserProfile;
import com.matchup.userprofile.mapper.UserProfileMapper;
import com.matchup.userprofile.service.UserProfileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController()
@RequestMapping("/user-profiles")
public class UserProfileController extends CrudController<UserProfile, UserProfileDTO, UserProfileService, UserProfileMapper> {
    public UserProfileController(UserProfileService service, UserProfileMapper mapper) {
        super(service, mapper);
    }

    @PostMapping(path = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AvatarUploadResponse> uploadAvatar(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file){
        return ResponseEntity.ok().body(AvatarUploadResponse.builder().avatarUrl(service.uploadAvatar(user, file)).build());
    }

    @GetMapping(path ="/avatar")
    public ResponseEntity<InputStreamResource> getAvatar(
            @AuthenticationPrincipal User user) {

        InputStream inputStream = service.getAvatar(user);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(inputStream));
    }

    @DeleteMapping("/avatar")
    public ResponseEntity<Void> deleteAvatar(
            @AuthenticationPrincipal User user) {

        service.deleteAvatar(user);

        return ResponseEntity.noContent().build();
    }

}
