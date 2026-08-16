package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.repository.EventRepository;
import com.matchup.notification.service.NotificationService;
import com.matchup.participation.entity.Participation;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.participation.service.ParticipationService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class ParticipationServiceTest extends GenericCrudServiceTest<Participation, ParticipationRepository>{

    @Mock
    private ParticipationRepository repository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private NotificationService notificationService;

    @Override
    protected ParticipationRepository getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Participation, ParticipationRepository> createService() {
        return new ParticipationService(repository, eventRepository,notificationService);
    }

    @Override
    protected Participation createEntity() {
        return Participation.builder().build();
    }
}
