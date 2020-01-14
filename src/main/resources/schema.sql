drop table if exists comments;
drop table if exists user_accepted_orders;
drop table if exists orders;
drop table if exists animals;
drop table if exists users;

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
    quantity bigint,
    primary key (id)
);
CREATE TABLE IF NOT EXISTS orders (
    id bigserial,
    title varchar(150),
    description varchar(1000),
    expiration_date date,
    user_id bigint references users (id),
    animal_id bigint references animals (id),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS user_accepted_orders (
    user_id bigint references users (id),
    animal_id bigint references animals (id)
);
CREATE TABLE IF NOT EXISTS comments (
    id bigserial,
    title varchar(50),
    text varchar(500),
    user_id bigint references users (id),
    primary key (id)
);