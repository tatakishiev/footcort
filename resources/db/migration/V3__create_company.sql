create table companies
(
    id      bigserial primary key,
    name    varchar(255) not null,
    address varchar(255) not null
);

create table companies_phone_numbers
(
    id           bigserial primary key,
    phone_number varchar(255) not null,
    company_id   bigint references companies (id)
);