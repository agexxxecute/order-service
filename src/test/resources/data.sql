DROP TABLE IF EXISTS books;

DROP TABLE IF EXISTS authors;

DROP TABLE IF EXISTS orders;

CREATE TABLE authors(
    id bigserial primary key,
    first_name varchar(255),
    last_name varchar(255)
);

CREATE TABLE orders(
    id bigserial primary key,
    order_number varchar(255),
    order_date timestamp(6),
    customer_name varchar(255),
    total_price numeric(38,2)
);

CREATE TABLE books(
    id bigserial primary key,
    price numeric(38,2),
    title varchar(255),
    author_id bigserial,
    orders_id bigserial,
    foreign key (author_id) references authors(id),
    foreign key (orders_id) references orders(id)
);

INSERT INTO authors (first_name, last_name) VALUES ('Александр Сергеевич', 'Пушкин'),
                                                ('Михаил Юрьевич', 'Лермонтов');

INSERT INTO orders (order_number, order_date, customer_name, total_price) VALUES ('11111120210101', '2021-01-01', 'Покупатель-1', 100.0),
                                                                                 ('22222220220101', '2022-01-01', 'Покупатель-2', 50.0);

INSERT INTO books (price, title, author_id, orders_id) VALUES (75.0, 'Евгений Онегин', 1, 1),
                                                               (25.0, 'Герой нашего времени', 2, 1),
                                                               (50.0, 'Руслан и Людмила', 1, 2);