CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(20) NOT NULL,
    role VARCHAR(10) NOT NULL DEFAULT 'ROLE_USER',
    password VARCHAR(128) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);