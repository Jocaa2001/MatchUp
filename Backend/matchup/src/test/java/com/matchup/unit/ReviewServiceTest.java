package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.review.entity.Review;
import com.matchup.review.repository.ReviewRepository;
import com.matchup.review.service.ReviewService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class ReviewServiceTest extends GenericCrudServiceTest<Review>{

    @Mock
    private ReviewRepository repository;

    @Override
    protected JpaRepository<Review, Long> getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Review> createService() {
        return new ReviewService(repository);
    }

    @Override
    protected Review createEntity() {
        return Review.builder().build();
    }
}
