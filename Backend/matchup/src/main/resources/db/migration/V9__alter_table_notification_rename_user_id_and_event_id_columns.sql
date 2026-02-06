ALTER TABLE mc_notification
    RENAME COLUMN user_id TO mc_user_id;

ALTER TABLE mc_notification
    RENAME COLUMN event_id TO mc_event_id;

-- FK constraint rename
ALTER TABLE mc_notification
    RENAME CONSTRAINT fk_notification_user TO fk_notification_mc_user;

ALTER TABLE mc_notification
    RENAME CONSTRAINT fk_notification_event TO fk_notification_mc_event;