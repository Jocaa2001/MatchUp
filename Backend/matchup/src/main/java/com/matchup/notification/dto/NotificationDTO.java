package com.matchup.notification.dto;


import com.matchup.common.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Event ID is required")
    private Long eventId;

    @NotBlank(message = "Type is required")

    private String type;

    @NotBlank(message = "Message is required")
    @Size(min = 3, max = 255, message = "Message must be between 3 and 255 characters")
    private String message;

    private LocalDateTime createdAt;
}
