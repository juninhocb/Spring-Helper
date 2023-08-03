create table beer
(
    id bigint not null auto_increment,
    description varchar(255),
    name varchar(255),
    primary key (id)
) engine=InnoDB;

insert into beer (description,name) values ('Skol','A good lagger');
insert into beer (description,name) values ('Brahma','Good option for summer');
insert into beer (description,name) values ('Heineken','The green one');
