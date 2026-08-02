package com.matchup.participation.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.event.repository.EventRepository;
import com.matchup.exception.EntityNotFoundException;
import com.matchup.participation.entity.Participation;
import com.matchup.participation.enums.ParticipationStatus;
import com.matchup.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class ParticipationService extends CrudServiceImpl<Participation> {

    private final EventRepository eventRepository;

    public ParticipationService(JpaRepository<Participation, Long> repository, EventRepository eventRepository) {
        super(repository);
        this.eventRepository = eventRepository;
    }

    public Participation joinEvent(User user, Long eventId) {

        Event e = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException(eventId));

        Participation p = Participation.builder()
                .user(user)
                .event(e)
                .status(ParticipationStatus.CONFIRMED).build();

        return repository.save(p);
    }
}
