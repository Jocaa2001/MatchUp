package com.matchup.participation.controller;

import com.matchup.common.controller.CrudController;

import com.matchup.participation.dto.ParticipationDTO;
import com.matchup.participation.entity.Participation;
import com.matchup.participation.mapper.ParticipationMapper;
import com.matchup.participation.service.ParticipationService;
import com.matchup.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/participations")
public class ParticipationController extends CrudController<Participation, ParticipationDTO, ParticipationService, ParticipationMapper> {
    public ParticipationController(ParticipationService service, ParticipationMapper mapper) {
        super(service, mapper);
    }

    @PostMapping("/events/{eventId}")
    public ResponseEntity<ParticipationDTO> joinEvent(
            @AuthenticationPrincipal User user,
            @PathVariable Long eventId) {
        ParticipationDTO response = mapper.toDto(service.joinEvent(user, eventId));
        return ResponseEntity.ok().body(response);
    }
}
