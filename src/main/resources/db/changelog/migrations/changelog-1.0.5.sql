--liquibase formatted sql

--changeset artsem:5
--comment create new table subscriptions

create table public.subscriptions
(
    id               bigserial
        constraint subscriptions_pk
            primary key,
    subscriber_id    bigint
        constraint subscriptions_profile_id_fk
            references public.profile
            on update cascade on delete cascade,
    subscribed_to_id bigint
        constraint subscriptions_profile_id_fk_2
            references public.profile
            on update cascade on delete cascade,
    created_at       timestamp not null
);

