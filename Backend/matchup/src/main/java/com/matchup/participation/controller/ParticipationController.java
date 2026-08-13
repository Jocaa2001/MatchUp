package com.matchup.participation.controller;

import com.matchup.common.controller.CrudController;

import com.matchup.event.dto.EventDTO;
import com.matchup.event.mapper.EventMapper;
import com.matchup.participation.dto.ParticipationDTO;
import com.matchup.participation.entity.Participation;
import com.matchup.participation.mapper.ParticipationMapper;
import com.matchup.participation.service.ParticipationService;
import com.matchup.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/participations")
public class ParticipationController extends CrudController<Participation, ParticipationDTO, ParticipationService, ParticipationMapper> {

    public final EventMapper eventMapper;

    public ParticipationController(ParticipationService service, ParticipationMapper mapper, EventMapper eventMapper) {
        super(service, mapper);
        this.eventMapper = eventMapper;
    }

    @PostMapping("/events/{eventId}")
    public ResponseEntity<ParticipationDTO> joinEvent(
            @AuthenticationPrincipal User user,
            @PathVariable Long eventId) {
        ParticipationDTO response = mapper.toDto(service.joinEvent(user, eventId));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/for-user")
    public ResponseEntity<List<EventDTO>> getEventsForUser(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
                service.getEventsForUser(user.getId())
                        .stream()
                        .map(eventMapper::toDto)
                        .toList()
        );
    }
}
