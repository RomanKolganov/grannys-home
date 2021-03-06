insert into users (name, login, password) values ('Чуваков Чувак', 'dude', '$2y$12$QrQNuzVjeEticey3pGfLo.p3twDxzV9vqX2ceTjCXlRX7yAgVTmPa');--dude_password
insert into users (name, login, password) values ('Принимающий Заказ', 'acceptor', '$2y$12$HTznFQKzouAjoWlPyL0lhuw/9GxP.gDqqoRhtUFFHJT9L/joV8v2S');--acceptor_password
insert into users (name, login, password) values ('Нормально Исполняющий', 'cool_dude', '$2y$12$NynmXCG3xwSXp78SxjMX4e.quZ3z3ClGNtYoZqzZWGAmcqZJQ/GkW');--cool_dude_password
insert into users (name, login, password) values ('Тестовый', 'test', '$2y$12$.gmpkQEFN962fbUPvoQAveFHOpd8/PP4RJbhjTUU/k6XKIIkfLKSO');--test
insert into users (name, login, password) values ('Вера', 'vera', '$2y$12$U8gcfTZkv1zTcd9WtJfFwO85rN21MvSRdEN04d61oq3oxm/G5Y/Iu');

insert into animals (name, type, user_id) values ('Нана', 'Собака', 4);
insert into animals (name, type, user_id) values ('Дракоша', 'Домашний дракон', 4);
insert into animals (name, type, user_id) values ('Фландерс', 'Аквариумная рыбка', 1);

insert into orders (title, description, status, animal_id, user_id)
values ('Выгул собаки', 'Гулять 2 раза в день. Кормить после каждой прогулки', 'NEW', 1, 4);
insert into orders (title, description, status, animal_id, user_id)
values ('Отдам рыбок', 'Ничего не надо, просто сыпь корм иногда.', 'NEW', 3, 3);
insert into orders (title, description, status, animal_id, user_id)
values ('Дракон', 'Обязательно заставлять летать хотя бы раз в день. Еду он найдет сам. Ни в коем случае не пытаться украсть его сокровища!!!', 'NEW', 2, 4);

insert into user_accepted_orders (accepted_user_id, order_id) values (2, 1);
insert into user_accepted_orders (accepted_user_id, order_id) values (1, 1);

insert into comments (text, creation_date, user_id_to, user_id_from) values ('Сам жрет собачий корм и не дает его собаке', to_timestamp('22.01.2019 23:50:47', 'DD.MM.YYYY HH24:MI:SS'), 2, 1);
insert into comments (text, creation_date, user_id_to, user_id_from) values ('Исполняет на высшем уровне', to_timestamp('22.01.2019 23:50:47', 'DD.MM.YYYY HH24:MI:SS'), 3, 2);
insert into comments (text, creation_date, user_id_to, user_id_from) values ('Погулял. Помыл. Покормил. И кота тоже.', to_timestamp('22.01.2019 23:50:47', 'DD.MM.YYYY HH24:MI:SS'), 3, 4);

insert into roles (role) values ('USER');
insert into roles (role) values ('ADMIN');

insert into user_roles (user_id, role_id) values (1, 1);
insert into user_roles (user_id, role_id) values (2, 1);
insert into user_roles (user_id, role_id) values (3, 1);
insert into user_roles (user_id, role_id) values (4, 1);
insert into user_roles (user_id, role_id) values (5, 1);

insert into dialogs (user_id_to, user_id_from) values (1, 4);

insert into messages (dialog_id, text, creation_date, user_id) values (1, 'Привет, Чувак', to_timestamp('03.02.2019 19:28:47', 'DD.MM.YYYY HH24:MI:SS'), 4);
insert into messages (dialog_id, text, creation_date, user_id) values (1, 'Привет, Тестовый', to_timestamp('03.02.2019 19:28:53', 'DD.MM.YYYY HH24:MI:SS'), 1);
insert into messages (dialog_id, text, creation_date, user_id) values (1, 'Как дела?', to_timestamp('03.02.2019 19:29:02', 'DD.MM.YYYY HH24:MI:SS'), 4);
insert into messages (dialog_id, text, creation_date, user_id) values (1, 'Нормалек, а у тебя?', to_timestamp('03.02.2019 19:29:07', 'DD.MM.YYYY HH24:MI:SS'), 1);