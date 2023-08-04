create table person (
    id bigint not null auto_increment,
    city varchar(255),
    name varchar(255),
    primary key (id)) engine=InnoDB;

insert into person (city,name) values ("Blumenau-SC","John Green");
insert into person (city,name) values ("Criciuma-SC", "Peter Black");
insert into person (city,name) values ("Joinville-SC","Adam Red");