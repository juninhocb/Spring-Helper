CREATE TABLE IF NOT EXISTS person
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    age INTEGER,
    is_tall BIT,
    created_date timestamp
);