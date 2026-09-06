package com.matchup.review.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.review.dto.ReviewDTO;
import com.matchup.review.dto.ReviewEventResponse;
import com.matchup.review.entity.Review;
import com.matchup.review.mapper.ReviewMapper;
import com.matchup.review.repository.ReviewRepository;
import com.matchup.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ReviewService extends CrudServiceImpl<Review, ReviewRepository> {
    private final ReviewMapper mapper;
    private final ParticipationRepository participationRepository;

    public ReviewService(ReviewRepository repository, ReviewMapper mapper, ParticipationRepository participationRepository) {
        super(repository);
        this.mapper = mapper;
        this.participationRepository = participationRepository;
    }

    public ReviewEventResponse getAllReviewsByEventId(User user , Long eventId){
        List<Review> reviews = repository.findAllByEventId(eventId);

        List<ReviewDTO> reviewDTOs = reviews.stream()
                .map(mapper::toDto)
                .toList();

        double avgRating = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);

        int reviewCount = reviews.size();

        boolean userJoined = false;

        if (user != null) {
            userJoined = participationRepository
                    .findByUserIdAndEventId(user.getId(), eventId)
                    .isPresent();
        }

        return new ReviewEventResponse(
                reviewDTOs,
                avgRating,
                reviewCount,
                userJoined
        );
    }

}
