package com.matchup.review.controller;


import com.matchup.common.controller.CrudController;
import com.matchup.review.dto.CreateReviewRequest;
import com.matchup.review.dto.ReviewDTO;
import com.matchup.review.dto.ReviewEventResponse;
import com.matchup.review.entity.Review;
import com.matchup.review.mapper.ReviewMapper;
import com.matchup.review.service.ReviewService;
import com.matchup.user.entity.User;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/reviews")
public class ReviewController extends CrudController<Review, ReviewDTO, ReviewService, ReviewMapper> {
    public ReviewController(ReviewService service, ReviewMapper mapper) {
        super(service, mapper);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<ReviewEventResponse> getAllReviewsByEventId(
            @AuthenticationPrincipal User user,
            @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(service.getAllReviewsByEventId(user,eventId));
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewDTO> createReview(@AuthenticationPrincipal User user, @RequestBody @Valid CreateReviewRequest request) {
        return ResponseEntity.ok(service.createReview(user, request));
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @AuthenticationPrincipal User user,
            @PathVariable Long reviewId
    ) {
        service.deleteReview(user, reviewId);

        return ResponseEntity.noContent().build();
    }

}
