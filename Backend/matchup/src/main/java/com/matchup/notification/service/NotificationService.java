package com.matchup.notification.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends CrudServiceImpl<Notification> {
    protected NotificationService(JpaRepository<Notification, Long> repository) {
        super(repository);
    }
}
