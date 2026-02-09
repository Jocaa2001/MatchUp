ALTER TABLE mc_user_profile
DROP CONSTRAINT IF EXISTS fk_user_profile_mc_user;

ALTER TABLE mc_user_profile
DROP COLUMN IF EXISTS mc_user_id;

ALTER TABLE mc_user
ADD COLUMN mc_user_profile_id BIGINT;

ALTER TABLE mc_user
ADD CONSTRAINT fk_user_user_profile
FOREIGN KEY (mc_user_profile_id)
REFERENCES mc_user_profile(id);

ALTER TABLE mc_user
ADD CONSTRAINT uq_user_profile UNIQUE (mc_user_profile_id);