drop view if exists dialogs_view;
drop table if exists comments;
drop table if exists user_accepted_orders;
drop table if exists messages;
drop table if exists dialogs;
drop table if exists orders;
drop table if exists animals;
drop table if exists user_roles;
drop table if exists users;
drop table if exists roles;

CREATE TABLE users (
    id bigserial,
    name varchar(250),
    login varchar(100),
    password varchar(150),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS animals (
    id bigserial,
    name varchar(250),
    type varchar(250),
    birthday date,
    user_id bigint references users (id),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS orders (
    id bigserial,
    title varchar(150),
    description varchar(1000),
    status varchar (150),
    user_id bigint references users (id),
    animal_id bigint references animals (id),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS user_accepted_orders (
    id bigserial,
    accepted_user_id bigint references users (id),
    order_id bigint references orders (id),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS comments (
    id bigserial,
    text varchar(500),
    creation_date timestamp,
    user_id_to bigint references users (id),
    user_id_from bigint references users (id),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS roles (
    id bigserial,
    role varchar(500),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS user_roles (
    user_id bigint,
    role_id bigint
);
CREATE TABLE IF NOT EXISTS dialogs (
    id bigserial,
    user_id_to bigint references users (id),
    user_id_from bigint references users (id),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS messages (
    id bigserial,
    dialog_id bigint references dialogs(id),
    text varchar(500),
    creation_date timestamp,
    user_id bigint references users (id),
    primary key (id)
);
create or replace view dialogs_view as
select d.id, d.user_id_to, ut.name as user_to_name,
d.user_id_from, uf.name as user_from_name
from dialogs d
left join users ut on ut.id = d.user_id_to
left join users uf on uf.id = d.user_id_from;