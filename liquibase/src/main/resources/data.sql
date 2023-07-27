DROP TABLE IF EXISTS db_example_person;

CREATE TABLE person (
                        id bigint not null auto_increment,
                        age integer,
                        name varchar(255),
                        address varchar(255),
                        primary key (id)
) engine=InnoDB
