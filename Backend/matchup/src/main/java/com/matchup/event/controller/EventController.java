package com.matchup.event.controller;

import com.matchup.common.controller.CrudController;
import com.matchup.event.dto.CreateEventDTO;
import com.matchup.event.dto.EventDTO;
import com.matchup.event.entity.Event;
import com.matchup.event.enums.EventStatus;
import com.matchup.event.mapper.EventMapper;
import com.matchup.event.service.EventService;
import com.matchup.participation.dto.ParticipationDTO;
import com.matchup.participation.mapper.ParticipationMapper;
import com.matchup.user.dto.UserDTO;
import com.matchup.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/events")
public class EventController extends CrudController<Event, EventDTO, EventService, EventMapper> {

    ParticipationMapper participationMapper;

    public EventController(EventService service, EventMapper mapper, ParticipationMapper participationMapper) {
        super(service, mapper);
        this.participationMapper = participationMapper;
    }

    @PostMapping("/create-event")
    public ResponseEntity<EventDTO> create(@AuthenticationPrincipal User user, @Valid @RequestBody CreateEventDTO request) {

        Event savedEvent = service.create(user, request);

        return ResponseEntity.ok(mapper.toDto(savedEvent));
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipationDTO>> getParticipantsByEventId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getParticipantsByEventId(id).stream().map(participationMapper::toDto).toList());
    }

    @GetMapping("/for-user")
    public ResponseEntity<List<EventDTO>> getEventsForUser(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                service.getEventsForUser(user.getId())
                        .stream()
                        .map(mapper::toDto)
                        .toList()
        );
    }

    @PatchMapping("/{id}")
    public EventDTO updatePartial(@AuthenticationPrincipal User user, @PathVariable Long id, @RequestBody EventDTO dto) {

        Event event = service.getById(id);
        mapper.updateFromDto(dto, event);
        event = service.update(id, event);
        if(event.getStatus() == EventStatus.CANCELLED) service.notifyCancelled(event,user);
        return mapper.toDto(event);
    }
}
