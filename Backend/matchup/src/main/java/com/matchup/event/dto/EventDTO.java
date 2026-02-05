package com.matchup.event.dto;

import com.matchup.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventDTO extends BaseDto {
    private Long sportId;
    private Long locationId;
    private Long userId;
    private LocalDateTime startTime;
    private Integer maxPlayers;
    private String status;
}
