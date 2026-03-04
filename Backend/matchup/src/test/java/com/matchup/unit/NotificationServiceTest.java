package com.matchup.unit;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.notification.entity.Notification;
import com.matchup.notification.repository.NotificationRepository;
import com.matchup.notification.service.NotificationService;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class NotificationServiceTest extends GenericCrudServiceTest<Notification>{

    @Mock
    private NotificationRepository repository;

    @Override
    protected JpaRepository<Notification, Long> getRepository() {
        return repository;
    }

    @Override
    protected CrudServiceImpl<Notification> createService() {
        return new NotificationService(repository);
    }

    @Override
    protected Notification createEntity() {
        return Notification.builder().build();
    }
}
