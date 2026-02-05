package com.matchup.event.mapper;

import com.matchup.common.mapper.BaseMapper;
import com.matchup.event.dto.EventDTO;
import com.matchup.event.entity.Event;
import com.matchup.location.entity.Location;
import com.matchup.location.repository.LocationRepository;
import com.matchup.sport.entity.Sport;
import com.matchup.sport.repository.SportRepository;
import com.matchup.user.entity.User;
import com.matchup.user.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EventMapper implements BaseMapper<Event, EventDTO> {

    @Autowired
    protected SportRepository sportRepository;

    @Autowired
    protected LocationRepository locationRepository;

    @Autowired
    protected UserRepository userRepository;

    @Mapping(source = "sportId", target = "sport", qualifiedByName = "mapSportById")
    @Mapping(source = "locationId", target = "location", qualifiedByName = "mapLocationById")
    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUserById")
    public abstract Event toEntity(EventDTO dto);

    @Mapping(source = "sport.id", target = "sportId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "user.id", target = "userId")
    public abstract EventDTO toDto(Event event);

    @Named("mapSportById")
    protected Sport mapSport(Long id) {
        return id == null ? null : sportRepository.getReferenceById(id);
    }

    @Named("mapLocationById")
    protected Location mapLocation(Long id) {
        return id == null ? null : locationRepository.getReferenceById(id);
    }

    @Named("mapUserById")
    protected User mapUser(Long id) {
        return id == null ? null : userRepository.getReferenceById(id);
    }
}