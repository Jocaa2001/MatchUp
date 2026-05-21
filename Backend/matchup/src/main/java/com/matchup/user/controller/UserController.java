package com.matchup.user.controller;



import com.matchup.common.controller.CrudController;
import com.matchup.user.dto.UserDTO;
import com.matchup.user.entity.User;
import com.matchup.user.mapper.UserMapper;
import com.matchup.user.service.UserService;
import com.matchup.userprofile.dto.UserProfileDTO;
import com.matchup.userprofile.mapper.UserProfileMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController extends CrudController<User, UserDTO, UserService, UserMapper> {
    private final UserProfileMapper userProfileMapper;
    public UserController(UserService service, UserMapper mapper, UserProfileMapper userProfileMapper) {
        super(service, mapper);
        this.userProfileMapper = userProfileMapper;
    }

    @PutMapping("/create-profile")
    public ResponseEntity<UserDTO> createUserProfileForAuthenticatedUser(@AuthenticationPrincipal User user, @RequestBody UserProfileDTO userProfileDTO){
        User savedUser = service.createUserProfileForAuthenticatedUser(user,userProfileMapper.toEntity(userProfileDTO));
        return ResponseEntity.ok(mapper.toDto(savedUser));
    }

}
