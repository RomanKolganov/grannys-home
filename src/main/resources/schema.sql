drop view if exists user_accepted_orders_view;
drop view if exists messages_view;
drop view if exists dialogs_view;
drop table if exists comments;
drop table if exists user_accepted_orders;
drop table if exists dialogs;
drop table if exists messages;
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
CREATE TABLE IF NOT EXISTS messages (
    id bigserial,
    dialog_id bigint,
    "text" varchar(500),
    creation_date timestamp,
    user_id_to bigint references users (id),
    user_id_from bigint references users (id),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS dialogs (
    id bigserial,
    user_id_to bigint references users (id),
    user_id_from bigint references users (id),
    primary key (id)
);
CREATE OR REPLACE view user_accepted_orders_view as
select uao.id, ua.id as accepted_user_id, ua.login as accepted_user_login, ua.name as accepted_user_name,
o.id as order_id, o.title, o.description,
uc.id as created_user_id, uc.name as created_user_name,
a.id as animal_id, a.name as animal_name
from user_accepted_orders uao
left join users ua on ua.id = uao.accepted_user_id
left join orders o on o.id = uao.order_id
left join users uc on uc.id = o.user_id
left join animals a on a.id = o.animal_id;

create or replace view messages_view as
select m.id, m.text, m.creation_date, d.id as dialog_id,
ut.id as user_id_to, ut.name as user_name_to, ut.login as user_login_to,
uf.id as user_id_from, uf.name as user_name_from, uf.login as user_login_from
from messages m
left join dialogs d on d.id = m.dialog_id
left join users ut on ut.id = m.user_id_to
left join users uf on uf.id = m.user_id_from;

create or replace view dialogs_view as
select d.id, d.user_id_to, ut.name as user_to_name,
d.user_id_from, uf.name as user_from_name
from dialogs d
left join users ut on ut.id = d.user_id_to
left join users uf on uf.id = d.user_id_from;