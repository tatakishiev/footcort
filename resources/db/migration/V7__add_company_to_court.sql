alter table courts
    add column company_id bigint references companies (id);