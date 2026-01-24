CREATE TABLE mc_event (
    id BIGSERIAL PRIMARY KEY,
    mc_sport_id BIGINT NOT NULL,
    mc_location_id BIGINT NOT NULL,
    mc_user_id BIGINT NOT NULL,
    start_time TIMESTAMP,
    max_players INT,

    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_event_sport
    FOREIGN KEY (mc_sport_id)
    REFERENCES mc_sport(id),

    CONSTRAINT fk_event_location
    FOREIGN KEY (mc_location_id)
    REFERENCES mc_location(id),

    CONSTRAINT fk_event_user
    FOREIGN KEY (mc_user_id)
    REFERENCES mc_user(id)
);