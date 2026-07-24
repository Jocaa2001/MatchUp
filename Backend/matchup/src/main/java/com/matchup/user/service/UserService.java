package com.matchup.user.service;


import com.matchup.common.service.CrudServiceImpl;
import com.matchup.user.entity.User;
import com.matchup.userprofile.dto.UserProfileDTO;
import com.matchup.userprofile.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudServiceImpl<User> {

    public UserService(JpaRepository<User, Long> repository) {
        super(repository);
    }

    public User createUserProfileForAuthenticatedUser(User user, UserProfile userProfile) {
        //check if user already has profile assigned
        UserProfile existingProfile = user.getProfile();
        if (existingProfile == null) {
            user.setProfile(userProfile);
        } else {

            existingProfile.setFirstName(userProfile.getFirstName());
            existingProfile.setLastName(userProfile.getLastName());
            existingProfile.setCity(userProfile.getCity());
            existingProfile.setPhone(userProfile.getPhone());
            existingProfile.setBirthDate(userProfile.getBirthDate());
            existingProfile.setAvatarUrl(userProfile.getAvatarUrl());
        }
        return repository.save(user);
    }
}
