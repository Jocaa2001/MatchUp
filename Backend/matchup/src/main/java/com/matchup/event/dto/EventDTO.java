package com.matchup.event.dto;

import com.matchup.common.dto.BaseDto;
import com.matchup.location.dto.LocationDTO;
import com.matchup.sport.dto.SportDTO;
import com.matchup.user.dto.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventDTO extends BaseDto {

    private SportDTO sport;

    private LocationDTO location;

    private UserDTO user;

    private LocalDateTime startTime;

    private Integer maxPlayers;

    private String status;
}