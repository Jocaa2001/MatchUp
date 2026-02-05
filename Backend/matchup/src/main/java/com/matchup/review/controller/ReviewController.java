package com.matchup.review.controller;


import com.matchup.common.controller.CrudController;
import com.matchup.review.dto.ReviewDTO;
import com.matchup.review.entity.Review;
import com.matchup.review.mapper.ReviewMapper;
import com.matchup.review.service.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/reviews")
public class ReviewController extends CrudController<Review, ReviewDTO, ReviewService, ReviewMapper> {
    public ReviewController(ReviewService service, ReviewMapper mapper) {
        super(service, mapper);
    }
}
