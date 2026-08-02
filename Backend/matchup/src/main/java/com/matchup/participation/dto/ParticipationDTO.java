package com.matchup.participation.dto;

import com.matchup.common.dto.BaseDto;
import com.matchup.event.dto.EventDTO;
import com.matchup.participation.enums.ParticipationStatus;
import com.matchup.user.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDTO extends BaseDto {

    @NotNull(message = "User is required")
    private UserDTO user;

    @NotNull(message = "Event is required")
    private EventDTO event;

    private ParticipationStatus status;
}
