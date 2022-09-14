CREATE TABLE if not exists task
(
    id          BIGSERIAL PRIMARY KEY,
    date        DATE    NOT NULL,
    description TEXT,
    done        BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX if not exists task_date_idx ON task (date);

CREATE INDEX if not exists task_done_idx ON task (done);