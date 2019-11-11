create table courts
(
    id bigserial primary key ,
    name varchar(200) not null,
    is_hall boolean not null
);