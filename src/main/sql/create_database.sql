DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS product_category CASCADE;
DROP TABLE IF EXISTS supplier CASCADE;
DROP TABLE IF EXISTS location CASCADE;
DROP TABLE IF EXISTS user_order CASCADE;
DROP TABLE IF EXISTS ordered_products CASCADE;
DROP TABLE IF EXISTS line_item CASCADE;

CREATE TABLE users
(
    id                serial PRIMARY KEY,
    username          varchar(40) UNIQUE,
    password          text,
    email             text UNIQUE,
    registration_date timestamp without time zone
);

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
    supplier_id integer REFERENCES supplier(id) not null,
    product_category_id integer REFERENCES product_category(id) not null
);

CREATE TABLE location
(
    id serial PRIMARY KEY,
    country varchar(50),
    city varchar(50),
    zip_code varchar(9),
    address varchar(100)
);

CREATE TABLE user_order
(
    id serial PRIMARY KEY,
    name varchar(50),
    email varchar(50),
    phone_number varchar(15),
    billing_address_id integer REFERENCES location(id),
    shipping_address_id integer  REFERENCES location(id),
    order_status varchar(9)
);

CREATE TABLE line_item(
    order_id integer REFERENCES user_order(id),
    product_id integer REFERENCES product(id),
    quantity integer

);


INSERT INTO product_category (name, description, department )
VALUES  ('Cactus', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Plant'),
        ('Flower','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Plant'),
        ('Little plant','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Plant'),
        ('Palm','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Plant');

INSERT INTO supplier (name, description)
VALUES ('Super Flowers Kft.', 'Small family business with quality plants'),
('King Of Cactus Kft.', 'Main priority is cacti, but also sell other plants'),
('Big Flower Business Zrt.', 'Industry leader big company'),
('Green Future Kft.', 'Selling wide range of plants grown in the country');

INSERT INTO product (name, description, default_price, default_currency, supplier_id, product_category_id)
VALUES ('Bunch of smol Cacti', 'Stellar collection of little cacti. If you take good care of them, they protect you from ' ||
 'robbers and bad men by throwing themselves on the floor before thus immobilizing the intruder.', 100, 'USD', 2, 1),
 ('Highfive Cactus', 'Big Brother cactus. He watches your daily life and comforts you when in need.', 20, 'USD', 2, 1),
 ('Spikealousless Cactus', 'Smol cactus', 10, 'USD', 2, 1),
 ('Spikasaurus Rex', 'Pack of StoneRose. They are siblings to cacti.', 30, 'USD', 2, 1),
 ('Candelabra Cactus', 'Piercing cactus', 5, 'USD', 2, 1),
 ('Josephs Coat', 'Bonsai cactus. Bedroom size version of his successor who lives in the Atacama Desert.', 25, 'USD', 2, 1),
 ('Mandarin Cactus', 'Pair of mandarin cacti. They together almost make an orange.', 20, 'USD', 2, 1),
 ('Pink Mr. Lincoln', 'Pink rose.', 5, 'USD', 1, 2),
 ('Colourful bunch Daisies', 'Spring flower.', 15, 'USD', 1, 2),
 ('Red Pat Austin', 'Red rose. Even Zorro would be jealous.', 10, 'USD', 1, 2),
 ('Pink Leucadendron', 'Purple?', 10, 'USD', 4, 2),
 ('Yellow Daffodil', 'Yellow flower!', 10, 'USD', 1, 2),
 ('White Lily', 'Snow White.', 5, 'USD', 4, 2),
 ('Pink Cinquefoil', 'Three head flower. If you ever try to cut one head off, it will grow back three instead! But only ' ||
  'after it cuts your hair.', 20, 'USD', 1, 2),
 ('Green Leaflet', 'The greenest of them all', 15, 'USD', 3, 3),
 ('Home Plant', 'This is the best choice if you, like most people forgets to water the plant time to time. It can live for a ' ||
  'month without watering!', 15, 'USD', 3, 3),
 ('Elephant Grass', 'Very nice for a bedroom.', 20, 'USD', 3, 3),
 ('Monstera', 'Leaves so big you could place in on a balcony and it casts shadows all over the place.', 35, 'USD', 3, 3),
 ('Piercy Growling', 'It may seem small now but will grow big and fierce.', 10, 'USD', 4, 3),
 ('ZZ Plant', 'A piece of forest for your home', 15, 'USD', 3, 3),
 ('Pothos', 'It spreads like gossip.', 25, 'USD', 4, 3),
 ('Lady Palm', 'Spectacular plant.', 45, 'USD', 3, 4),
 ('Parlor Palm', 'Best to place in spacious areas, it fills the room.', 40, 'USD', 3, 4),
 ('Florida Silver Palm', 'Palm tree from India.', 30, 'USD', 3, 4),
 ('Yucca', 'No big leaves but rather strong trunk.', 20, 'USD', 4, 4),
 ('Dracaenat', 'You may recognize it from cartoon network shows. It was often used as the hair of many iconic characters.',
 25, 'USD', 3, 4),
 ('Pygmy Date Palm', 'Maugli envies you if your choice fall upon thee.', 120, 'USD', 3, 4),
 ('Ficus', 'The crown of Caesar.', 35, 'USD', 3, 4);