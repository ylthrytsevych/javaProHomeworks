-- створити бд
CREATE DATABASE IF NOT EXISTS my_database; --підсвічується червоним бо в comunity версії idea не розуміє синтаксис (так каже ші)
-- після цього обов'язково натиснути на Schemas і оновити - бо не показує оновлення в irl

-- переключитись на цю нову бд
USE my_database; --або зверху в db browser є випадаючий список - там обираю my_db

-- створеняня таблиці
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    email VARCHAR(150),
    PRIMARY KEY (id)
);