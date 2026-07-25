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
import com.matchup.sport.entity.Sport;
import com.matchup.sport.repository.SportRepository;
import com.matchup.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService extends CrudServiceImpl<Event> {

    private final SportRepository sportRepository;
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public EventService(JpaRepository<Event, Long> repository, SportRepository sportRepository, LocationRepository locationRepository, LocationMapper locationMapper) {
        super(repository);
        this.sportRepository = sportRepository;
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public Event create(User user, CreateEventDTO request) {

        Sport sport = sportRepository.findById(request.getSportId())
                .orElseThrow(() ->
                        new EntityNotFoundException(request.getSportId()));


        Location location = locationMapper.toEntity(request.getLocation());
        Location savedLocation = locationRepository.save(location);


        Event event = new Event();
        event.setSport(sport);
        event.setLocation(savedLocation);
        event.setUser(user);
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setMaxPlayers(request.getMaxPlayers());
        event.setStatus(EventStatus.OPEN);
        return repository.save(event);
    }
}
