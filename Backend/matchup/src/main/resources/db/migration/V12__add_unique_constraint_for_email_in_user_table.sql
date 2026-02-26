ALTER TABLE mc_user
ADD CONSTRAINT uq_mc_user_email UNIQUE (email);