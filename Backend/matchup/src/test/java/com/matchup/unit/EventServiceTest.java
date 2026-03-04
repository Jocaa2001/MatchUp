package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.event.repository.EventRepository;
import com.matchup.event.service.EventService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class EventServiceTest extends GenericCrudServiceTest<Event>{

    @Mock
    private EventRepository repository;

    @Override
    protected JpaRepository<Event, Long> getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Event> createService() {
        return new EventService(repository);
    }

    @Override
    protected Event createEntity() {
        return Event.builder().build();
    }
}
