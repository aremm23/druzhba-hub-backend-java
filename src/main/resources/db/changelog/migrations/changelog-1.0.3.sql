--liquibase formatted sql

--changeset artsem:3
--comment add updated_at column in profile table


alter table public.profile
    add updated_at timestamp not null;

