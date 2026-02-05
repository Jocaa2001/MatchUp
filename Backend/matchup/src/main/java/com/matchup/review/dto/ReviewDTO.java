package com.matchup.review.dto;

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
public class ReviewDTO extends BaseDto {

    private Long userId;
    private Long eventId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

}
