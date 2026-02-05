package com.matchup.event.controller;

import com.matchup.common.controller.CrudController;
import com.matchup.event.dto.EventDTO;
import com.matchup.event.entity.Event;
import com.matchup.event.mapper.EventMapper;
import com.matchup.event.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/events")
public class EventController extends CrudController<Event, EventDTO, EventService, EventMapper> {
    public EventController(EventService service, EventMapper mapper) {
        super(service, mapper);
    }
}
