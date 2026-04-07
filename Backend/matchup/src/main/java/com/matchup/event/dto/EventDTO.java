package com.matchup.event.dto;

import com.matchup.common.dto.BaseDto;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class EventDTO extends BaseDto {
    @NotNull(message = "Sport ID is required")
    private Long sportId;

    @NotNull(message = "Location ID is required")
    private Long locationId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Start time is required")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;

    @NotNull(message = "Max players is required")
    @Min(value = 2, message = "At least 2 players are required")
    @Max(value = 100, message = "Max players cannot exceed 100")
    private Integer maxPlayers;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "CREATED|ACTIVE|CANCELLED|FINISHED",
            message = "Status must be one of: CREATED, ACTIVE, CANCELLED, FINISHED"
    )
    private String status;
}
