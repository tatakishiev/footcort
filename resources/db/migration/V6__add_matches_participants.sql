create table matches_participants
(
    id       bigserial primary key,
    user_id  bigint references users (id)   not null,
    match_id bigint references matches (id) not null
);

alter table matches
    add column creator_id bigint references users (id) not null;