package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.sport.entity.Sport;
import com.matchup.sport.repository.SportRepository;
import com.matchup.sport.service.SportService;
import com.matchup.userprofile.entity.UserProfile;
import com.matchup.userprofile.repository.UserProfileRepository;
import com.matchup.userprofile.service.UserProfileService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class SportServiceTest extends GenericCrudServiceTest<Sport>{

    @Mock
    private SportRepository repository;

    @Override
    protected JpaRepository<Sport, Long> getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Sport> createService() {
        return new SportService(repository);
    }

    @Override
    protected Sport createEntity() {
        return Sport.builder().build();
    }
}
