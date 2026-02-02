package com.matchup.userprofile.controller;


import com.matchup.common.controller.CrudController;
import com.matchup.userprofile.dto.UserProfileDTO;
import com.matchup.userprofile.entity.UserProfile;
import com.matchup.userprofile.mapper.UserProfileMapper;
import com.matchup.userprofile.service.UserProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user-profiles")
public class UserProfileController extends CrudController<UserProfile, UserProfileDTO, UserProfileService, UserProfileMapper> {
    public UserProfileController(UserProfileService service, UserProfileMapper mapper) {
        super(service, mapper);
    }
}
