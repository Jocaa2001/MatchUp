package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.participation.entity.Participation;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.participation.service.ParticipationService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class ParticipationServiceTest extends GenericCrudServiceTest<Participation>{

    @Mock
    private ParticipationRepository repository;

    @Override
    protected JpaRepository<Participation, Long> getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Participation> createService() {
        return new ParticipationService(repository);
    }

    @Override
    protected Participation createEntity() {
        return Participation.builder().build();
    }
}
