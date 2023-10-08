create table one.instrument (
    id   binary(16) not null,
    name varchar(255),
    type varchar(255),
    primary key (id)
) engine=InnoDB;

create table two.instrument (
    id   binary(16) not null,
    name varchar(255),
    type varchar(255),
    primary key (id)
) engine=InnoDB;