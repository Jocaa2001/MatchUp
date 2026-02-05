package com.matchup.notification.mapper;

import com.matchup.common.mapper.BaseMapper;
import com.matchup.event.entity.Event;
import com.matchup.event.repository.EventRepository;

import com.matchup.notification.dto.NotificationDTO;
import com.matchup.notification.entity.Notification;
import com.matchup.user.entity.User;
import com.matchup.user.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class NotificationMapper implements BaseMapper<Notification, NotificationDTO> {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected EventRepository eventRepository;


    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUser")
    @Mapping(source = "eventId", target = "event", qualifiedByName = "mapEvent")
    public abstract Notification toEntity(NotificationDTO dto);


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "event.id", target = "eventId")
    public abstract NotificationDTO toDto(Notification entity);

    // === Mapping methods ===
    @Named("mapUser")
    protected User mapUser(Long id) {
        return id == null ? null : userRepository.getReferenceById(id);
    }

    @Named("mapEvent")
    protected Event mapEvent(Long id) {
        return id == null ? null : eventRepository.getReferenceById(id);
    }
}
