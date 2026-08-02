package com.matchup.event.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.dto.CreateEventDTO;
import com.matchup.event.entity.Event;
import com.matchup.event.enums.EventStatus;
import com.matchup.event.repository.EventRepository;
import com.matchup.exception.EntityNotFoundException;
import com.matchup.location.entity.Location;
import com.matchup.location.mapper.LocationMapper;
import com.matchup.location.repository.LocationRepository;
import com.matchup.participation.dto.ParticipationDTO;
import com.matchup.participation.entity.Participation;
import com.matchup.participation.enums.ParticipationStatus;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.participation.service.ParticipationService;
import com.matchup.sport.entity.Sport;
import com.matchup.sport.repository.SportRepository;
import com.matchup.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService extends CrudServiceImpl<Event, EventRepository> {

    private final SportRepository sportRepository;
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final ParticipationRepository participationRepository;

    public EventService(EventRepository repository, SportRepository sportRepository, LocationRepository locationRepository, LocationMapper locationMapper, ParticipationRepository participationRepository) {
        super(repository);
        this.sportRepository = sportRepository;
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.participationRepository = participationRepository;
    }

    public Event create(User user, CreateEventDTO request) {

        Sport sport = sportRepository.findById(request.getSportId())
                .orElseThrow(() ->
                        new EntityNotFoundException(request.getSportId()));


        Location location = locationMapper.toEntity(request.getLocation());
        Location savedLocation = locationRepository.save(location);


        Event event = Event.builder()
                .sport(sport)
                .location(savedLocation)
                .user(user)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .maxPlayers(request.getMaxPlayers())
                .status(EventStatus.OPEN)
                .build();

        event = repository.save(event);

        Participation participation = Participation.builder()
                .event(event)
                .user(user)
                .status(ParticipationStatus.CONFIRMED).build();

        participationRepository.save(participation);

        return event;
    }

    public List<Participation> getParticipantsByEventId(Long id) {
        return participationRepository.findByEventId(id)
                .stream()
                .toList();
    }
}
