package com.matchup.userprofile.repository;

import com.matchup.userprofile.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
}
