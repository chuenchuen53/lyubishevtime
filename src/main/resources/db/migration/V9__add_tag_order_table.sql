CREATE TABLE time_event_tag_order
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER   NOT NULL UNIQUE,
    tag_ids    INTEGER[] NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_user_id foreign key (user_id) references app_user (id)
);