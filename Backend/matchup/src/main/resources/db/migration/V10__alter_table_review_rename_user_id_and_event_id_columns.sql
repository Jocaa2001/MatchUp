ALTER TABLE mc_review
    RENAME COLUMN user_id TO mc_user_id;

ALTER TABLE mc_review
    RENAME COLUMN event_id TO mc_event_id;

-- FK constraint rename (nije obavezno ali preporučuje se)
ALTER TABLE mc_review
    RENAME CONSTRAINT fk_review_user TO fk_review_mc_user;

ALTER TABLE mc_review
    RENAME CONSTRAINT fk_review_event TO fk_review_mc_event;