--liquibase formatted sql

--changeset artsem:1
--comment initial db

create table public.account
(
    id         bigserial
        constraint account_pk
            primary key,
    email      varchar   not null
        constraint account_pk_2
            unique,
    password   varchar   not null,
    role       varchar   not null,
    created_at timestamp not null,
    updated_at timestamp not null
);

alter table public.account
    owner to postgres;

create table public.profile
(
    id           bigserial
        constraint profile_pk
            primary key,
    account_id   bigint
        constraint profile_account_id_fk
            references public.account
            on update cascade on delete cascade,
    username     integer      not null
        constraint profile_pk_2
            unique,
    rate         integer,
    self_summary varchar(350) not null
);

alter table public.profile
    owner to postgres;

create table public.profile_image
(
    id         bigserial
        constraint profile_image_pk
            primary key,
    image_url  varchar not null,
    image_name varchar not null
        constraint profile_image_pk_2
            unique,
    profile_id bigint  not null
        constraint profile_image_profile_id_fk
            references public.profile
            on update cascade on delete cascade
);

alter table public.profile_image
    owner to postgres;

create table public.friends
(
    id                bigserial
        constraint friends_pk
            primary key,
    first_profile_id  bigint    not null
        constraint friends_profile_id_fk
            references public.profile
            on update cascade on delete cascade,
    second_profile_id bigint    not null
        constraint friends_profile_id_fk_2
            references public.profile
            on update cascade on delete cascade,
    created_at        timestamp not null
);

alter table public.friends
    owner to postgres;

create table public.category
(
    id         bigserial
        constraint category_pk
            primary key,
    name       varchar   not null,
    created_at timestamp not null
);

alter table public.category
    owner to postgres;

create table public.event
(
    id          bigserial
        constraint event_pk
            primary key,
    summary     varchar(350) not null,
    category_id bigint       not null
        constraint event_category_id_fk
            references public.category
            on delete set null,
    location    varchar      not null,
    start_at    timestamp    not null,
    created_at  timestamp    not null,
    updated_at  timestamp    not null
);

alter table public.event
    owner to postgres;

create table public.event_image
(
    id         bigserial
        constraint event_image_pk
            primary key,
    image_url  varchar not null,
    image_name varchar not null
        constraint event_image_pk_2
            unique,
    event_id   bigint  not null
        constraint event_image_event_id_fk
            references public.event
            on update cascade on delete cascade
);

alter table public.event_image
    owner to postgres;

create table public.post
(
    id         bigserial
        constraint post_pk
            primary key,
    profile_id bigint
        constraint post_profile_id_fk
            references public.profile
            on delete set null,
    event_id   bigint
        constraint post_event_id_fk
            references public.event
            on delete set null,
    summary    varchar(350) not null,
    created_at timestamp    not null,
    updated_at timestamp    not null
);

alter table public.post
    owner to postgres;

create table public.confirm_token
(
    id         bigserial
        constraint confirm_token_pk
            primary key,
    token      varchar   not null,
    account_id bigint    not null
        constraint confirm_token_account_id_fk
            references public.account
            on update cascade on delete cascade,
    created_at timestamp not null,
    expire_at  timestamp not null
);

alter table public.confirm_token
    owner to postgres;



create table public.likes
(
    id         bigserial
        constraint like_pk
            primary key,
    profile_id bigint
        constraint like_profile_id_fk
            references public.profile
            on delete set null,
    post_id    bigint
        constraint like_post_id_fk
            references public.post
            on delete set null,
    created_at timestamp not null
);

alter table public.likes
    owner to postgres;
