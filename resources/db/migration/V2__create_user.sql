create table users
(
    id           bigserial primary key,
    phone_number varchar(20) not null unique,
    first_name   varchar(25),
    last_name    varchar(30),
    password     varchar not null
)