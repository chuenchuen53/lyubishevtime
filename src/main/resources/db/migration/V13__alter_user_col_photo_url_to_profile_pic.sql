ALTER TABLE app_user
    ALTER COLUMN photo_url TYPE TEXT;

ALTER TABLE app_user
    RENAME COLUMN photo_url TO profile_pic;