package com.matchup.review.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends CrudServiceImpl<Review> {
    public ReviewService(JpaRepository<Review, Long> repository) {
        super(repository);
    }
}
