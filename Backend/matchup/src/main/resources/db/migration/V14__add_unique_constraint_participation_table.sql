ALTER TABLE mc_participation
ADD CONSTRAINT uk_participation_user_event
UNIQUE (mc_user_id, mc_event_id);