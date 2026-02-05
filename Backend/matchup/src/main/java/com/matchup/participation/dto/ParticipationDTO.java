package com.matchup.participation.dto;

import com.matchup.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDTO extends BaseDto {

    private Long userId;
    private Long eventId;
    private String status;
}
