--файл виконувати одразу не виходить, чомусь в comunity версії воно не перемикає вручну на інші бд і видає  помилку
-- треба виконувати кожну команду окремо

CREATE DATABASE IF NOT EXISTS sales_database;

USE sales_database;

CREATE TABLE IF NOT EXISTS sales (
    id INT AUTO_INCREMENT,
    product VARCHAR(255),
    price DECIMAL(10, 2),      -- числовий тип з 2 знаками після коми
    quantity INT,
    PRIMARY KEY (id)
);

-- старотові дані
INSERT INTO sales (product, price, quantity)
VALUES
    ('Laptop', 1000.00, 5),
    ('Phone', 700.00, 3),
    ('Tablet', 500.00, 2),
    ('Printer', 300.00, 4);

SELECT * FROM sales;