package com.matchup.userprofile.service;


import com.matchup.common.service.CrudServiceImpl;
import com.matchup.userprofile.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends CrudServiceImpl<UserProfile> {
    public UserProfileService(JpaRepository<UserProfile, Long> repository) {
        super(repository);
    }
}
