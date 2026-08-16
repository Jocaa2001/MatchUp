package com.matchup.participation.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.event.enums.EventStatus;
import com.matchup.event.repository.EventRepository;
import com.matchup.exception.EntityNotFoundException;
import com.matchup.notification.service.NotificationService;
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
    private final NotificationService notificationService;

    public ParticipationService(ParticipationRepository repository, EventRepository eventRepository, NotificationService notificationService) {
        super(repository);
        this.eventRepository = eventRepository;
        this.notificationService = notificationService;
    }

    public Participation joinEvent(User user, Long eventId) {

        Event e = eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException(eventId));

        long participantsCount = repository.countParticipantsByEventId(eventId);

        if (participantsCount >= e.getMaxPlayers()) {
            throw new RuntimeException("Event is full");
        }

        if(e.getStatus() != EventStatus.OPEN){
            throw new RuntimeException("Can not join event that is not in status OPEN");
        }

        Participation p = Participation.builder()
                .user(user)
                .event(e)
                .status(ParticipationStatus.CONFIRMED).build();

        notificationService.notifyEventJoined(e,user);

        return repository.save(p);
    }

    public List<Event> getEventsForUser(Long userId) {
        return repository.findEventsByUserId(userId);
    }

    public void leaveEvent(User user, Long eventId) {
        Participation participation = repository
                .findByUserIdAndEventId(user.getId(), eventId)
                .orElseThrow(() -> new RuntimeException("User is not participating in this event"));

        repository.delete(participation);
    }

}
