package com.matchup.notification.dto;


import com.matchup.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO extends BaseDto {

    private Long userId;
    private Long eventId;
    private String type;
    private String message;
    private LocalDateTime createdAt;
}
