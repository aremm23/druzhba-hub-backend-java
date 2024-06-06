--liquibase formatted sql

--changeset artsem:2
--comment add column is_email_confirmed

alter table public.account
    add is_email_confirmed boolean;

