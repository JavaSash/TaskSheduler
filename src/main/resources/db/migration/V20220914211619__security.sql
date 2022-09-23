CREATE TABLE if not exists task
(
    id          BIGSERIAL PRIMARY KEY,
    date        DATE    NOT NULL,
    description VARCHAR(250),
    done        BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX if not exists task_date_idx ON task (date);

CREATE INDEX if not exists task_done_idx ON task (done);

CREATE TABLE if not exists users
(
    id          BIGSERIAL PRIMARY KEY,
    login       VARCHAR(15) NOT NULL,
    password    VARCHAR(15) NOT NULL
);