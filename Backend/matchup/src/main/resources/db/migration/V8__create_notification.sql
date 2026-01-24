CREATE TABLE mc_notification (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    event_id BIGINT,
    type VARCHAR(50) NOT NULL,
    message TEXT,
    created_at TIMESTAMP,

    CONSTRAINT fk_notification_user
    FOREIGN KEY (user_id)
    REFERENCES mc_user(id),

    CONSTRAINT fk_notification_event
    FOREIGN KEY (event_id)
    REFERENCES mc_event(id)
);