package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.event.repository.EventRepository;
import com.matchup.event.service.EventService;
import com.matchup.location.mapper.LocationMapper;
import com.matchup.location.repository.LocationRepository;
import com.matchup.notification.service.NotificationService;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.participation.service.ParticipationService;
import com.matchup.sport.repository.SportRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest extends GenericCrudServiceTest<Event, EventRepository>{

    @Mock
    private EventRepository repository;

    @Mock
    private SportRepository sportRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    ParticipationRepository participationRepository;

    @Mock
    NotificationService notificationService;

    @Mock
    private LocationMapper locationMapper;


    @Override
    protected EventRepository getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Event, EventRepository> createService() {
        return new EventService(
                repository,
                sportRepository,
                locationRepository,
                locationMapper,
                participationRepository,
                notificationService
        );
    }

    @Override
    protected Event createEntity() {
        return Event.builder().build();
    }
}
