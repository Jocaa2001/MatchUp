package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.repository.EventRepository;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.review.entity.Review;
import com.matchup.review.mapper.ReviewMapper;
import com.matchup.review.repository.ReviewRepository;
import com.matchup.review.service.ReviewService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class ReviewServiceTest extends GenericCrudServiceTest<Review, ReviewRepository>{

    @Mock
    private ReviewRepository repository;

    @Mock
    private ReviewMapper reviewMapper;

    @Mock
    private ParticipationRepository participationRepository;

    @Mock
    private EventRepository eventRepository;

    @Override
    protected ReviewRepository getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Review,ReviewRepository> createService() {
        return new ReviewService(repository, reviewMapper,participationRepository, eventRepository);
    }

    @Override
    protected Review createEntity() {
        return Review.builder().build();
    }
}
