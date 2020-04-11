create table matches
(
    id          bigserial primary key,
    start       timestamp,
    "end"       timestamp,
    court_id    bigint references courts (id),
    description varchar(200)
);