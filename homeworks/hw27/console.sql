

-- name: "John", age: 30, email: "john@example.com"
--name:"Alice", age: 25, email: "alice@example.com"
--name:"Bob", age: 35, email: "bob@example.com"
insert into users (name, age, email)
values  ('John', 30, 'john@example.com'),
('Alice', 25, 'alice@example.com'),
('Bob', 35,'bob@example.com');

-- Вибірка даних:Виберіть всі записи з таблиці users та виведіть їх.
SELECT * FROM users;

--Видалення даних:Видаліть користувача з ім'ям"Bob" з таблиці users.
delete from users where name = 'Bob'
SELECT * FROM users;

--Перевірка правильності виконання:Переконайтеся, що дані були вставлені правильно та виведені після вибірки.
--Переконайтеся, що користувач з ім'ям "Bob" був успішно видалений.
select * from users where name like '%Bob%'
select * from users where name = 'Bob'