CREATE TYPE time_event_tag_color AS ENUM ('red', 'orange', 'yellow', 'green', 'cyan', 'blue', 'purple', 'grey');

ALTER TABLE time_event_tag
    ADD COLUMN color time_event_tag_color;
