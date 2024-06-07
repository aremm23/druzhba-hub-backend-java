--liquibase formatted sql

--changeset artsem:2
--comment add new table

create table public.review
(
    id           bigserial
        constraint review_pk
            primary key,
    comment      varchar(300),
    profile_from bigint
        constraint review_profile_id_fk
            references public.profile,
    profile_to   bigint
        constraint review_profile_id_fk_2
            references public.profile,
    grade        integer not null
);