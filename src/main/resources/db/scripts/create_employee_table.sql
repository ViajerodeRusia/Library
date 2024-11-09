-- liquibase formatted sql

-- changeset dsiliukov:${changeset.id.sequence}

CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);