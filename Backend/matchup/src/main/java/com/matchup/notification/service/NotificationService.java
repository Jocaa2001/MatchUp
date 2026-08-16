package com.matchup.notification.service;

import com.matchup.common.service.CrudServiceImpl;
import com.matchup.event.entity.Event;
import com.matchup.mail.service.MailService;
import com.matchup.notification.entity.Notification;
import com.matchup.notification.enums.NotificationRecipient;
import com.matchup.notification.enums.NotificationStatus;
import com.matchup.notification.enums.NotificationType;
import com.matchup.notification.repository.NotificationRepository;
import com.matchup.notification.util.NotificationMessageUtil;
import com.matchup.participation.repository.ParticipationRepository;
import com.matchup.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService extends CrudServiceImpl<Notification, NotificationRepository> {

    private final MailService mailService;
    private final ParticipationRepository participationRepository;
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationRepository repository, MailService mailService, ParticipationRepository participationRepository) {
        super(repository);
        this.mailService = mailService;
        this.participationRepository = participationRepository;
    }

    private void sendNotification(User user, Event event, NotificationType notificationType, NotificationRecipient recipient){

        String message = NotificationMessageUtil.getMessage(notificationType, recipient, event);
        String subject = NotificationMessageUtil.getSubject(notificationType, recipient);

        Notification notification = Notification.builder()
                .user(user)
                .event(event)
                .type(NotificationType.EVENT_CANCELLED)
                .message(message)
                .status(NotificationStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(notification);

        try {
            mailService.send(user.getEmail(), subject, message
            );

            notification.setStatus(NotificationStatus.SENT);

        } catch (MailException e) {
            log.error("Error sending a mail: ", e);
            notification.setStatus(NotificationStatus.FAILED);
        }
        repository.save(notification);
    }

    public void notifyEventJoined(Event event, User joinedUser) {

        // Organiser
        sendNotification(
                event.getUser(),
                event,
                NotificationType.EVENT_JOINED,
                NotificationRecipient.ORGANIZER
        );

        // To user who joined
        sendNotification(
                joinedUser,
                event,
                NotificationType.EVENT_JOINED,
                NotificationRecipient.JOINED_USER
        );
    }

    public void notifyEventCreated(Event event, User admin) {

        sendNotification(
                admin,
                event,
                NotificationType.EVENT_CREATED,
                NotificationRecipient.ORGANIZER
        );
    }

    public void notifyEventCancelled(Event event, User admin) {

        // Adminu
        sendNotification(
                admin,
                event,
                NotificationType.EVENT_CANCELLED,
                NotificationRecipient.ADMIN
        );

        // Organizeru i svim učesnicima
        participationRepository.findByEventId(event.getId())
                .forEach(participation -> {

                    NotificationRecipient recipient =
                            participation.getUser().getId().equals(event.getUser().getId())
                                    ? NotificationRecipient.ORGANIZER
                                    : NotificationRecipient.JOINED_USER;

                    sendNotification(
                            participation.getUser(),
                            event,
                            NotificationType.EVENT_CANCELLED,
                            recipient
                    );
                });
    }

}
