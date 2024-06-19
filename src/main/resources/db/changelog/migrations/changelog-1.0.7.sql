--liquibase formatted sql

--changeset artsem:7
--comment update image tables

alter table public.event_image
    rename column image_url to gcs_file_name;

alter table public.event_image
    drop column image_name;

alter table public.event_image
    add upload_time timestamp not null;

alter table public.profile_image
    rename column image_url to gcs_file_name;

alter table public.profile_image
    drop column image_name;

alter table public.profile_image
    add upload_time timestamp not null;



