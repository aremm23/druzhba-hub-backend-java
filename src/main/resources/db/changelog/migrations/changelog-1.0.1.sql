--liquibase formatted sql

--changeset artsem:1
--comment initial db

create table profile
(
    id           bigserial
        constraint profile_pk
            primary key,
    username     varchar      not null
        constraint profile_pk_2
            unique,
    rate         integer,
    self_summary varchar(350) not null
);

alter table profile
    owner to postgres;

create table account
(
    id                 bigserial
        constraint account_pk
            primary key,
    email              varchar   not null
        constraint account_pk_2
            unique,
    password           varchar   not null,
    role               varchar   not null,
    created_at         timestamp not null,
    updated_at         timestamp not null,
    is_email_confirmed boolean,
    profile_id         bigint
        constraint account_profile_id_fk
            references profile
);

alter table account
    owner to postgres;

create table profile_image
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
            references profile
            on update cascade on delete cascade
);

alter table profile_image
    owner to postgres;

create table friends
(
    id                bigserial
        constraint friends_pk
            primary key,
    first_profile_id  bigint    not null
        constraint friends_profile_id_fk
            references profile
            on update cascade on delete cascade,
    second_profile_id bigint    not null
        constraint friends_profile_id_fk_2
            references profile
            on update cascade on delete cascade,
    created_at        timestamp not null
);

alter table friends
    owner to postgres;

create table category
(
    id         bigserial
        constraint category_pk
            primary key,
    name       varchar   not null,
    created_at timestamp not null
);

alter table category
    owner to postgres;

create table event
(
    id          bigserial
        constraint event_pk
            primary key,
    summary     varchar(350) not null,
    category_id bigint       not null
        constraint event_category_id_fk
            references category
            on delete set null,
    location    varchar      not null,
    start_at    timestamp    not null,
    created_at  timestamp    not null,
    updated_at  timestamp    not null
);

alter table event
    owner to postgres;

create table event_image
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
            references event
            on update cascade on delete cascade
);

alter table event_image
    owner to postgres;

create table post
(
    id         bigserial
        constraint post_pk
            primary key,
    profile_id bigint
        constraint post_profile_id_fk
            references profile
            on delete set null,
    event_id   bigint
        constraint post_event_id_fk
            references event
            on delete set null,
    summary    varchar(350) not null,
    created_at timestamp    not null,
    updated_at timestamp    not null
);

alter table post
    owner to postgres;

create table likes
(
    id         bigserial
        constraint like_pk
            primary key,
    profile_id bigint
        constraint like_profile_id_fk
            references profile
            on delete set null,
    post_id    bigint
        constraint like_post_id_fk
            references post
            on delete set null,
    created_at timestamp not null
);

alter table likes
    owner to postgres;

create table confirm_token
(
    id         bigserial
        constraint confirm_token_pk
            primary key,
    token      varchar   not null,
    account_id bigint    not null
        constraint confirm_token_account_id_fk
            references account
            on update cascade on delete cascade,
    created_at timestamp not null,
    expire_at  timestamp not null
);

alter table confirm_token
    owner to postgres;

