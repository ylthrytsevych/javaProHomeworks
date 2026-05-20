--4. Вибірка даних:
--  - Виберіть всі записи з таблиці sales та виведіть їх.

select * from sales;


--5. Вибірка з обмеженням (LIMIT):
--  - Виберіть перші два записи з таблиці sales та виведіть їх.

-- select top 2 * from sales -- прикол, не працює в mysql
select * from sales limit 2;


--6. Обчислення суми (SUM):
----  - Обчисліть загальну вартість всіх продуктів у таблиці sales та виведіть її.
select sum(price) as SumOfAll from sales;


-- 7. Групування даних (GROUP BY):
-- Згрупуйте дані у таблиці sales за назвою продукту (product), обчисліть загальну кількість продуктів кожного типу
-- та середню ціну за одиницю продукту.

--count = це кілкість рядків а не значень, тому краще sum
select product as ProductType,
sum(quantity) as TotalQuantity,
avg(price) as PriceAverage from sales group by product;

--нонсенс, показує ті ж дані що і в таблиці, бо немає що групувати







--додам ще варіант де більше спільних записів, бо нема сенсу групувати ніщо
INSERT INTO sales (product, price, quantity)
VALUES
    ('Laptop', 3200.00, 3),
    ('Phone', 1350.00, 5),
    ('Tablet', 2000.00, 4),
    ('Printer', 158.00, 9),
    ('Lamp', 50.00, 2),
    ('Ac Adapter', 220.00, 4),
    ('Phone', 600.00, 2),
    ('Tablet', 400.00, 4),
    ('Laptop', 1100.00, 1);
select * from sales order by product ASC;;


select product as ProductType,
sum(quantity) as TotalQuantity,
avg(price) as PriceAverage from sales group by product;



--8. Перевірка правильності виконання:
--  - Переконайтеся, що дані були вставлені правильно та виведені після вибірки.
--  - Переконайтеся, що обчислення суми та групування даних відбулися коректно.