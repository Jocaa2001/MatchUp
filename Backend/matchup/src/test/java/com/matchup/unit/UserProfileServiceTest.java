package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.userprofile.entity.UserProfile;
import com.matchup.userprofile.repository.UserProfileRepository;
import com.matchup.userprofile.service.UserProfileService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class UserProfileServiceTest extends GenericCrudServiceTest<UserProfile, UserProfileRepository>{

    @Mock
    private UserProfileRepository repository;

    @Override
    protected UserProfileRepository getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<UserProfile, UserProfileRepository> createService() {
        return new UserProfileService(repository);
    }

    @Override
    protected UserProfile createEntity() {
        return UserProfile.builder().build();
    }
}
