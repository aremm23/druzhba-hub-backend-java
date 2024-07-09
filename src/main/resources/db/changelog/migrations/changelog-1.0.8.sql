--liquibase formatted sql

--changeset artsem:8
--comment update profile and event table

alter table public.profile
    add age integer;

alter table public.profile
    add place varchar;

alter table public.event
    add is_created_by_admin boolean;