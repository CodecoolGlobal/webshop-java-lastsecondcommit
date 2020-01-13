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

INSERT INTO product_category (name, description, department )
VALUES  ('Cactus', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Plant'),
        ('Flower','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Plant'),
        ('Little plant','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Plant'),
        ('Palm','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Plant');

