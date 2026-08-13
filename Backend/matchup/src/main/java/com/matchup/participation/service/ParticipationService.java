package com.matchup.participation.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.event.repository.EventRepository;
import com.matchup.exception.EntityNotFoundException;
import com.matchup.participation.entity.Participation;
import com.matchup.participation.enums.ParticipationStatus;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParticipationService extends CrudServiceImpl<Participation, ParticipationRepository> {

    private final EventRepository eventRepository;

    public ParticipationService(ParticipationRepository repository, EventRepository eventRepository) {
        super(repository);
        this.eventRepository = eventRepository;
    }

    public Participation joinEvent(User user, Long eventId) {

        Event e = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException(eventId));

        long participantsCount = repository.countParticipantsByEventId(eventId);

        if (participantsCount >= e.getMaxPlayers()) {
            throw new RuntimeException("Event is full");
        }

        Participation p = Participation.builder()
                .user(user)
                .event(e)
                .status(ParticipationStatus.CONFIRMED).build();

        return repository.save(p);
    }

    public List<Event> getEventsForUser(Long userId) {
        return repository.findEventsByUserId(userId);
    }

}
