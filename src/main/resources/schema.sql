drop table if exists comments;

CREATE TABLE IF NOT EXISTS comments (
    id bigserial,
    title varchar(50),
    text varchar(500),
    primary key (id)
);