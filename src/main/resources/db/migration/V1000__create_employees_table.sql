CREATE TABLE employees (
                           id SERIAL PRIMARY KEY,
                           surname VARCHAR(128) NOT NULL,
                           name VARCHAR(128) NOT NULL,
                           patronymic VARCHAR(128),
                           date_of_birth DATE NOT NULL,
                           date_of_hiring DATE NOT NULL,
                           position VARCHAR(10) NOT NULL DEFAULT 'WORKER',
                           manager_id INT
);