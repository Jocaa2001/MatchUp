package com.matchup.event.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.exception.EntityNotFoundException;
import com.matchup.location.entity.Location;
import com.matchup.location.repository.LocationRepository;
import com.matchup.sport.entity.Sport;
import com.matchup.sport.repository.SportRepository;
import com.matchup.user.entity.User;
import com.matchup.user.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService extends CrudServiceImpl<Event> {

    public EventService(JpaRepository<Event, Long> repository,
                        SportRepository sportRepository,
                        LocationRepository locationRepository,
                        UserRepository userRepository) {
        super(repository);
    }
}
