-- liquibase formatted sql

-- changeset natriy:2
CREATE TABLE bid (
                     id BIGSERIAL PRIMARY KEY,
                     bid_name VARCHAR(255) NOT NULL,
                     bid_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                     lot_id BIGINT NOT NULL REFERENCES lot(id)
);