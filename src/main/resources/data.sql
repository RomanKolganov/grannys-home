insert into users (name, login, password) values ('Чуваков Чувак', 'dude', 'dude_password');
insert into users (name, login, password) values ('Принимающий Заказ', 'acceptor', 'acceptor_password');
insert into users (name, login, password) values ('Нормально Исполняющий', 'cool_dude', 'cool_dude_password');

insert into animals (name, quantity) values ('Нана', 1);

insert into orders (title, description, expiration_date, animal_id, user_id)
values ('Выгул собаки', 'Гулять 2 раза в день. Кормить после каждой прогулки', parsedatetime('14.02.2020 19:27:53', 'dd.mm.yyyy hh:mm:ss'), 1, 1);

insert into user_accepted_orders (user_id, order_id) values (2, 1);
insert into user_accepted_orders (user_id, order_id) values (3, 1);

insert into comments (title, text, user_id) values ('Очень плохо кормит', 'Сам жрет собачий корм и не дает его собаке', 2);
insert into comments (title, text, user_id) values ('Просто лучший', 'Исполняет на высшем уровне', 3);
