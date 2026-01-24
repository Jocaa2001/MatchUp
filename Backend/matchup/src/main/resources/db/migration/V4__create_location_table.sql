CREATE TABLE mc_location (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    city VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    indoor BOOLEAN,
    description TEXT
);