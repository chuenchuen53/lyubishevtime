CREATE TABLE app_user
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR   NOT NULL UNIQUE,
    nickname   VARCHAR   NOT NULL,
    photo_url  VARCHAR,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE color
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR   NOT NULL UNIQUE,
    hexcode    VARCHAR   NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE time_event_tag
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER   NOT NULL,
    color_id   INTEGER   NOT NULL,
    name       VARCHAR   NOT NULL,
    tag_order  INTEGER   NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_time_event_tags_colors FOREIGN KEY (color_id) REFERENCES color (id),
    CONSTRAINT fk_time_event_tags_users FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE TABLE time_event
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER   NOT NULL,
    tag_id      INTEGER   NOT NULL,
    name        VARCHAR   NOT NULL,
    minute      INTEGER   NOT NULL,
    date        DATE      NOT NULL,
    event_order INTEGER   NOT NULL DEFAULT 0,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_time_events_users FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT fk_time_events_tags FOREIGN KEY (tag_id) REFERENCES time_event_tag (id)
);