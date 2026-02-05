package com.matchup.review.mapper;

import com.matchup.common.mapper.BaseMapper;
import com.matchup.event.entity.Event;
import com.matchup.event.repository.EventRepository;

import com.matchup.review.dto.ReviewDTO;
import com.matchup.review.entity.Review;
import com.matchup.user.entity.User;
import com.matchup.user.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper implements BaseMapper<Review, ReviewDTO> {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected EventRepository eventRepository;


    @Override
    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUser")
    @Mapping(source = "eventId", target = "event", qualifiedByName = "mapEvent")
    public abstract Review toEntity(ReviewDTO dto);


    @Override
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    public abstract ReviewDTO toDto(Review entity);


    @Named("mapUser")
    protected User mapUser(Long id) {
        return id == null ? null : userRepository.getReferenceById(id);
    }

    @Named("mapEvent")
    protected Event mapEvent(Long id) {
        return id == null ? null : eventRepository.getReferenceById(id);
    }
}