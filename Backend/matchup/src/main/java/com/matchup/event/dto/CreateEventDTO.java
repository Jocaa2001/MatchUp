package com.matchup.event.dto;

import com.matchup.location.dto.LocationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateEventDTO {

    @NotNull(message = "Sport id is required")
    private Long sportId;

    @Valid
    @NotNull(message = "Location is required")
    private LocationDTO location;

    @NotNull(message = "Start time is required")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @Future(message = "End time must be in the future")
    private LocalDateTime endTime;

    @NotNull(message = "Maximum players is required")
    @Min(value = 2, message = "Minimum players is 2")
    private Integer maxPlayers;

}
