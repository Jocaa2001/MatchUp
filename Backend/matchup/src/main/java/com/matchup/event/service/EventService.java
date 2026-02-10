package com.matchup.event.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService extends CrudServiceImpl<Event> {
    public EventService(JpaRepository<Event, Long> repository) {
        super(repository);
    }
}
