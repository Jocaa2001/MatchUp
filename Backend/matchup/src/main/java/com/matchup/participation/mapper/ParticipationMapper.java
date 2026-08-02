package com.matchup.participation.mapper;


import com.matchup.common.mapper.BaseMapper;
import com.matchup.event.dto.EventDTO;
import com.matchup.event.entity.Event;
import com.matchup.event.repository.EventRepository;
import com.matchup.participation.dto.ParticipationDTO;
import com.matchup.participation.entity.Participation;
import com.matchup.user.entity.User;
import com.matchup.user.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface ParticipationMapper extends BaseMapper<Participation, ParticipationDTO> {

    @Override
    @Mapping(source = "user", target = "user")
    @Mapping(source = "event", target = "event")
    ParticipationDTO toDto(Participation participation);

    @Override
    @Mapping(source = "user", target = "user")
    @Mapping(source = "event", target = "event")
    Participation toEntity(ParticipationDTO dto);

}
