DROP TABLE IF EXISTS car;
CREATE TABLE car
(
    id INTEGER NOT NULL auto_increment,
    oil BIT,
    velocity INTEGER,
    created_time DATETIME(6),
    color VARCHAR(255),
    model VARCHAR(255),
    name VARCHAR(255) UNIQUE,
    PRIMARY KEY (id)
) engine=InnoDB