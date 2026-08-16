package com.matchup.notification.util;

import com.matchup.event.entity.Event;
import com.matchup.notification.enums.NotificationRecipient;
import com.matchup.notification.enums.NotificationType;

public class NotificationMessageUtil {

    public static String getMessage(NotificationType notificationType, NotificationRecipient recipient, Event event) {

        return switch (notificationType) {

            case EVENT_JOINED -> switch (recipient) {
                case ORGANIZER ->
                        "A new user has joined your event.";

                case JOINED_USER ->
                        "You have joined the event.";

                default ->
                        "A new user has joined an event.";
            };

            case EVENT_CREATED ->
                    "A new event has been created.";

            case EVENT_CANCELLED ->
                    switch (recipient) {
                        case ADMIN ->
                                "An event has been cancelled.";

                        default ->
                                "The event you joined has been cancelled.";
                    };

            case INFO ->
                    "You have a new information.";

            case ALERT ->
                    "You have a new alert.";

            case REMINDER ->
                    "You have a reminder.";
        };
    }

    public static String getSubject(
            NotificationType notificationType,
            NotificationRecipient recipient) {

        return switch (notificationType) {

            case EVENT_CREATED ->
                    "New event created";

            case EVENT_JOINED ->
                    switch (recipient) {
                        case ORGANIZER ->
                                "New participant joined your event";

                        case JOINED_USER ->
                                "You joined an event";

                        default ->
                                "New participant joined an event";
                    };

            case EVENT_CANCELLED ->
                    switch (recipient) {
                        case ADMIN ->
                                "Event cancelled";

                        case ORGANIZER ->
                                "Your event has been cancelled";

                        default ->
                                "Event you joined has been cancelled";
                    };

            case INFO ->
                    "Information";

            case ALERT ->
                    "Alert";

            case REMINDER ->
                    "Reminder";
        };
    }
}
