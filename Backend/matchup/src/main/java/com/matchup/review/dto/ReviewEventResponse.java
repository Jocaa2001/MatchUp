package com.matchup.review.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEventResponse {

    List<ReviewDTO> reviews;
    Double averageRating;
    Integer reviewCount;
    boolean userJoined;

}
