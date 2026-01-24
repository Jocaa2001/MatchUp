CREATE TABLE mc_review (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    rating INT,
    comment TEXT,
    created_at TIMESTAMP,

    CONSTRAINT fk_review_user
    FOREIGN KEY (user_id)
    REFERENCES mc_user(id),

    CONSTRAINT fk_review_event
    FOREIGN KEY (event_id)
    REFERENCES mc_event(id)
);