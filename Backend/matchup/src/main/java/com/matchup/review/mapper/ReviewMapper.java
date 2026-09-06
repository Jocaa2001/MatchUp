package com.matchup.review.mapper;

import com.matchup.common.mapper.BaseMapper;
import com.matchup.review.dto.ReviewDTO;
import com.matchup.review.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends BaseMapper<Review, ReviewDTO> {
}