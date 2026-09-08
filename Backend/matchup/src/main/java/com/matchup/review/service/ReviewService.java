package com.matchup.review.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.event.enums.EventStatus;
import com.matchup.event.repository.EventRepository;
import com.matchup.participation.entity.Participation;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.review.dto.CreateReviewRequest;
import com.matchup.review.dto.ReviewDTO;
import com.matchup.review.dto.ReviewEventResponse;
import com.matchup.review.entity.Review;
import com.matchup.review.mapper.ReviewMapper;
import com.matchup.review.repository.ReviewRepository;
import com.matchup.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService extends CrudServiceImpl<Review, ReviewRepository> {
    private final ReviewMapper mapper;
    private final ParticipationRepository participationRepository;
    private final EventRepository eventRepository;

    public ReviewService(ReviewRepository repository, ReviewMapper mapper, ParticipationRepository participationRepository, EventRepository eventRepository) {
        super(repository);
        this.mapper = mapper;
        this.participationRepository = participationRepository;
        this.eventRepository = eventRepository;
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

        return new ReviewEventResponse(reviewDTOs, avgRating, reviewCount, userJoined);
    }

    public ReviewDTO createReview(User user, CreateReviewRequest request) {

        Optional<Participation> userJoined = participationRepository
                .findByUserIdAndEventId(user.getId(), request.getEventId());

        if (userJoined.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User must join the event before leaving a review"
            );
        }

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Event not found"
                ));

        if (event.getStatus() != EventStatus.FINISHED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Review can only be left for finished events"
            );
        }

        Review review = Review.builder()
                .user(user)
                .event(event)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        Review savedReview = repository.save(review);
        return mapper.toDto(savedReview);
    }
}
