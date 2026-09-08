package com.matchup.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {
    Long eventId;

    @Min(1)
    @Max(5)
    Integer rating;

    @Size(max = 500)
    String comment;
}
