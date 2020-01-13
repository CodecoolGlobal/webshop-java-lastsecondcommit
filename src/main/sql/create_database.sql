DROP TABLE IF EXISTS product;
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

DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category
(
    id serial PRIMARY KEY,
    name varchar(40),
    description text,
    department varchar(40)

);

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier
(
    id serial PRIMARY KEY,
    name varchar(40),
    description text
);

INSERT INTO product (name, description, default_price, default_currency, product_category, supplier_id, product_category_id)
VALUES ("")



