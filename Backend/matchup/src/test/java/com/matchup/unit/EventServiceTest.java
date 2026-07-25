package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.event.repository.EventRepository;
import com.matchup.event.service.EventService;
import com.matchup.location.mapper.LocationMapper;
import com.matchup.location.repository.LocationRepository;
import com.matchup.sport.repository.SportRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest extends GenericCrudServiceTest<Event>{

    @Mock
    private EventRepository repository;

    @Mock
    private SportRepository sportRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private LocationMapper locationMapper;


    @Override
    protected JpaRepository<Event, Long> getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Event> createService() {
        return new EventService(
                repository,
                sportRepository,
                locationRepository,
                locationMapper
        );
    }

    @Override
    protected Event createEntity() {
        return Event.builder().build();
    }
}
