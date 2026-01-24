CREATE TABLE mc_participation (
    id BIGSERIAL PRIMARY KEY,
    mc_user_id BIGINT NOT NULL,
    mc_event_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_participation_user
    FOREIGN KEY (mc_user_id)
    REFERENCES mc_user(id),

    CONSTRAINT fk_participation_event
    FOREIGN KEY (mc_event_id)
    REFERENCES mc_event(id)
);