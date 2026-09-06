package com.matchup.review.dto;

import com.matchup.common.dto.BaseDto;
import com.matchup.event.dto.EventDTO;
import com.matchup.user.dto.UserDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class ReviewDTO extends BaseDto {

    @NotNull(message = "User is required")
    private UserDTO user;

    @NotNull(message = "Event is required")
    private EventDTO event;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be greater than 5")
    private Integer rating;

    @Size(max = 500, message = "Comment can have up to 500 characters")
    private String comment;

    private LocalDateTime createdAt;

}
