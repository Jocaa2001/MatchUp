package com.matchup.event.mapper;

import com.matchup.common.mapper.BaseMapper;
import com.matchup.event.dto.EventDTO;
import com.matchup.event.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper extends BaseMapper<Event, EventDTO> {

    @Override
    @Mapping(source = "sport", target = "sport")
    @Mapping(source = "location", target = "location")
    @Mapping(source = "user", target = "user")
    EventDTO toDto(Event event);
}