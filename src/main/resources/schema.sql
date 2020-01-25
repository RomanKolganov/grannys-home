drop table if exists comments;
drop table if exists user_accepted_orders;
drop table if exists orders;
drop table if exists animals;
drop table if exists user_roles;
drop table if exists users;
drop table if exists roles;

CREATE TABLE IF NOT EXISTS users (
    id bigserial,
    name varchar(250),
    login varchar(100),
    password varchar(150),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS animals (
    id bigserial,
    name varchar(250),
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
    user_id bigint references users (id),
    order_id bigint references orders (id)
);
CREATE TABLE IF NOT EXISTS comments (
    id bigserial,
    text varchar(500),
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