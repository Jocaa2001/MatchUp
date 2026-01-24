CREATE TABLE mc_user_profile (
    id BIGSERIAL PRIMARY KEY,
    mc_user_id BIGINT NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    city VARCHAR(50),
    phone VARCHAR(20),
    birth_date DATE,
    avatar_url VARCHAR(255),

    CONSTRAINT fk_user_profile_user
    FOREIGN KEY (mc_user_id)
    REFERENCES mc_user(id)
);