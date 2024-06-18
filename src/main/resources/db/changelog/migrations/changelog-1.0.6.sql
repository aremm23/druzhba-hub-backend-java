--liquibase formatted sql

--changeset artsem:6
--comment update type of rate field in profile table

alter table public.profile
    alter column rate type double precision using rate::double precision;

