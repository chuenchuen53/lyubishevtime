BEGIN;

ALTER TABLE time_event_tag_order
    ALTER COLUMN tag_ids SET DEFAULT '{}';

COMMIT;