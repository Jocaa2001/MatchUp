package com.matchup.notification.controller;

import com.matchup.common.controller.CrudController;
import com.matchup.notification.dto.NotificationDTO;
import com.matchup.notification.entity.Notification;
import com.matchup.notification.mapper.NotificationMapper;
import com.matchup.notification.service.NotificationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/notifications")
public class NotificationController extends CrudController<Notification, NotificationDTO, NotificationService, NotificationMapper> {
    public NotificationController(NotificationService service, NotificationMapper mapper) {
        super(service, mapper);
    }
}
