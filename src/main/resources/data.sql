insert into users (name, login, password) values ('Чуваков Чувак', 'dude', '$2y$12$QrQNuzVjeEticey3pGfLo.p3twDxzV9vqX2ceTjCXlRX7yAgVTmPa');--dude_password
insert into users (name, login, password) values ('Принимающий Заказ', 'acceptor', '$2y$12$HTznFQKzouAjoWlPyL0lhuw/9GxP.gDqqoRhtUFFHJT9L/joV8v2S');--acceptor_password
insert into users (name, login, password) values ('Нормально Исполняющий', 'cool_dude', '$2y$12$NynmXCG3xwSXp78SxjMX4e.quZ3z3ClGNtYoZqzZWGAmcqZJQ/GkW');--cool_dude_password
insert into users (name, login, password) values ('Тестовый', 'test', '$2y$12$.gmpkQEFN962fbUPvoQAveFHOpd8/PP4RJbhjTUU/k6XKIIkfLKSO');--test

insert into animals (name, quantity) values ('Нана', 1);
insert into animals (name, quantity) values ('Дракоша', 1);
insert into animals (name, quantity) values ('Фландерс', 1);

insert into orders (title, description, animal_id, user_id)
values ('Выгул собаки', 'Гулять 2 раза в день. Кормить после каждой прогулки', 1, 1);
insert into orders (title, description, animal_id, user_id)
values ('Отдам рыбок', 'Ничего не надо, просто сыпь корм иногда.', 3, 3);
insert into orders (title, description, animal_id, user_id)
values ('Дракон', 'Обязательно заставлять летать хотя бы раз в день. Еду он найдет сам. Ни в коем случае не пытаться украсть его сокровища!!!', 2, 2);

insert into user_accepted_orders (user_id, order_id) values (2, 1);
insert into user_accepted_orders (user_id, order_id) values (3, 1);

insert into comments (text, user_id_to, user_id_from) values ('Сам жрет собачий корм и не дает его собаке', 2, 1);
insert into comments (text, user_id_to, user_id_from) values ('Исполняет на высшем уровне', 3, 2);
insert into comments (text, user_id_to, user_id_from) values ('Погулял. Помыл. Покормил. И кота тоже.', 3, 4);

insert into roles (role) values ('USER');
insert into roles (role) values ('ADMIN');

insert into user_roles (user_id, role_id) values (4, 1);
