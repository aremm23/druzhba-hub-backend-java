--liquibase formatted sql

--changeset artsem:8
--comment update profile table

alter table public.profile
    add age integer;

alter table public.profile
    add place varchar;
