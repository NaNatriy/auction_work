-- liquibase formatted sql

-- changeset natriy:1
CREATE TABLE lot (
                     id BIGSERIAL PRIMARY KEY,
                     status VARCHAR NOT NULL CHECK (status IN ('CREATED', 'ACTION', 'SOLD')),
                     lot_name VARCHAR(255) NOT NULL,
                     description VARCHAR(255) NOT NULL,
                     start_price INTEGER NOT NULL,
                     bid_price INTEGER NOT NULL
);