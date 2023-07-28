drop table if exists person;
create table person (
    age integer,
    is_alive bit,
    id bigint not null auto_increment,
    name varchar(255),
    primary key (id)) engine=InnoDB