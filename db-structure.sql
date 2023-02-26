create table public.roles
(
    id      bigserial
        constraint roles_pk
            primary key,
    name    text not null,
    user_id bigint
        constraint roles_users_id_fk
            references public.users
);
create table public.tasks
(
    id            bigserial
        constraint tasks_pk
            primary key,
    name          text                                not null,
    is_done       boolean   default true              not null,
    creation_date timestamp default CURRENT_TIMESTAMP not null,
    owner_id      bigint                              not null
        constraint tasks__fk
            references public.users
);
create table public.users
(
    id            bigserial
        constraint users_pk
            primary key,
    username      text                                not null
        constraint unique_constraint
            unique,
    password      varchar(60)                         not null,
    creation_date timestamp default CURRENT_TIMESTAMP not null
);




