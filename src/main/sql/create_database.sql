DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS supplier;

CREATE TABLE product_category
(
id serial PRIMARY KEY,
name varchar(40),
description text,
department varchar(40)

);

CREATE TABLE supplier
(
    id serial PRIMARY KEY,
    name varchar(40),
    description text
);

CREATE TABLE product
(
    id serial PRIMARY KEY,
    name varchar(40),
    description text,
    default_price float,
    default_currency varchar(3),
    product_category varchar(40),
    supplier_id integer REFERENCES supplier(id) not null,
    product_category_id integer REFERENCES product_category(id) not null

);

INSERT INTO supplier (name, description)
VALUES ('Super Flowers Kft.', 'Small family business with quality plants'),
('King Of Cactus Kft.', 'Main priority is cacti, but also sell other plants'),
('Big Flower Business Zrt.', 'Industry leader big company'),
('Green Future Kft.', 'Selling wide range of plants grown in the country');




