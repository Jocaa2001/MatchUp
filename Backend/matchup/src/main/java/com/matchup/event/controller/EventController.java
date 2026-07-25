package com.matchup.event.controller;

import com.matchup.common.controller.CrudController;
import com.matchup.event.dto.CreateEventDTO;
import com.matchup.event.dto.EventDTO;
import com.matchup.event.entity.Event;
import com.matchup.event.mapper.EventMapper;
import com.matchup.event.service.EventService;
import com.matchup.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/events")
public class EventController extends CrudController<Event, EventDTO, EventService, EventMapper> {
    public EventController(EventService service, EventMapper mapper) {
        super(service, mapper);
    }

    @PostMapping("/create-event")
    public ResponseEntity<EventDTO> create(@AuthenticationPrincipal User user, @Valid @RequestBody CreateEventDTO request) {

        Event savedEvent = service.create(user, request);

        return ResponseEntity.ok(mapper.toDto(savedEvent));
    }

}
