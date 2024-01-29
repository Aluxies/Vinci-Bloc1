-- TABLES CREATION
CREATE TABLE users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    password VARCHAR(60) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT false,
    email VARCHAR(50) NOT NULL unique
);

CREATE TABLE decorations(
   decoration_id INTEGER PRIMARY KEY AUTOINCREMENT,
   name VARCHAR(50) NOT NULL ,
   picture_path VARCHAR(50) NOT NULL,
   price DOUBLE PRECISION NOT NULL,
   date_added DATETIME NOT NULL DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')),
   description TEXT NOT NULL
);

CREATE TABLE fabrics(
    fabric_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(20) NOT NULL,
    description TEXT NOT NULL,
    price_per_square_meter DOUBLE PRECISION NOT NULL,
    color VARCHAR(20) NOT NULL
);

CREATE TABLE favorites(
    decoration_id INTEGER REFERENCES decorations(decoration_id) NOT NULL,
    user_id INTEGER REFERENCES users(user_id) NOT NULL,
    PRIMARY KEY(user_id, decoration_id)
);

CREATE TABLE curtains_orders(
    order_id INTEGER PRIMARY KEY AUTOINCREMENT,
    status CHAR(4) NOT NULL CHECK (status IN ('open', 'paid', 'sent')) DEFAULT 'open',
    user_id INTEGER NOT NULL REFERENCES users(user_id),
    order_date TIMESTAMP NOT NULL DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')),
    curtain_height INTEGER NOT NULL,
    curtain_width INTEGER NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    fabric_id INTEGER NOT NULL references fabrics(fabric_id)
);

CREATE TABLE tickets(
    ticket_id INTEGER PRIMARY KEY AUTOINCREMENT ,
    topic VARCHAR(50) NOT NULL,
    picture VARCHAR(50),
    user_id INTEGER REFERENCES users(user_id) NOT NULL,
    opening_date TIMESTAMP NOT NULL DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')),
    is_open BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE messages(
    message_id INTEGER PRIMARY KEY AUTOINCREMENT ,
    ticket_id INTEGER REFERENCES tickets(ticket_id),
    message TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')),
    user_id INTEGER NOT NULL REFERENCES users(user_id)
);



-- INSERTIONS

--users
INSERT INTO users (firstname, lastname, password, is_admin, email)
VALUES ('Olivier', 'Choquet', '$2b$10$IhFBuTnN4VzFYWdctUP4yOYaRIR9A//u0NbSyQ5yYv2YDV4ENMFwC', 0, 'o.ch@gmail.com');

INSERT INTO users (firstname, lastname, password, is_admin, email)
VALUES ('Gregory', 'Seront', '$2b$10$IhFBuTnN4VzFYWdctUP4yOYaRIR9A//u0NbSyQ5yYv2YDV4ENMFwC', 0, 'os.se@hotmail.com');

INSERT INTO users (firstname, lastname, password, is_admin, email)
VALUES ('Sébastien', 'Strebelle', '$2b$10$IhFBuTnN4VzFYWdctUP4yOYaRIR9A//u0NbSyQ5yYv2YDV4ENMFwC', 1, 's.st@vinci.be');

--fabrics
INSERT INTO fabrics(name,color,description,price_per_square_meter)
VALUES ('sunny', 'beige', 'Tissu isolant thermique 100% polyester', 33.9);

INSERT INTO fabrics(name,color,description,price_per_square_meter)
VALUES ('dino', 'bleu', 'Tissu motif dinosaures pour chambre d''enfants', 27.8);

INSERT INTO fabrics(name,color,description,price_per_square_meter)
VALUES ('floral', 'gris', 'Tissu motif floral 100% coton', 32);

INSERT INTO fabrics(name,color,description,price_per_square_meter)
VALUES ('occultant', 'anthracite', 'Tissu en velours parfait pour occulter sans le doubler', 39.9);

--curtains_orders
INSERT INTO curtains_orders(user_id, fabric_id,curtain_height, curtain_width, price, order_date, status)
VALUES (1,4,150,160,95.76,'2023-01-16 10:42:00', 'sent');

INSERT INTO curtains_orders(user_id, fabric_id,curtain_height, curtain_width, price, order_date, status)
VALUES (2,2,140,120,54.2,'2023-01-25 18:14:23', 'open');

INSERT INTO curtains_orders(user_id, fabric_id,curtain_height, curtain_width, price, order_date, status)
VALUES (1,1,230,140,109.16,'2023-02-21 13:40:00', 'paid');

INSERT INTO curtains_orders(user_id, fabric_id,curtain_height, curtain_width, price, order_date, status)
VALUES (1,1,230,180,140.35,'2023-02-21 13:45:15', 'paid');

INSERT INTO curtains_orders(user_id, fabric_id,curtain_height, curtain_width, price, order_date, status)
VALUES (1,3,140,120,45.56,'2023-03-12 17:03:47', 'open');


--decorations
INSERT INTO decorations(name, picture_path, price, date_added, description)
VALUES ('Coussin bleu', '/decorations/decorations_pictures/2023-1-6-10-46-22-cousssin_bleu.jpg', 12.99, '2023-01-06 10:46:22', 'Coussin uni bleu - housse 100% coton');

INSERT INTO decorations(name, picture_path, price, date_added, description)
VALUES ('Cadre mural', '/decorations/decorations_pictures/2023-1-6-10-48-33-cadre_mural.jpeg', 31.5, '2023-01-06 10:48:33', 'Cadre mural pour photo ou poster');

INSERT INTO decorations(name, picture_path, price, date_added, description)
VALUES ('Cadre photo', '/decorations/decorations_pictures/2023-1-6-11-12-6-cadre_photo.jpeg', 7.99, '2023-01-6 11:12:06', 'Cadre photo à poser contour noir');

INSERT INTO decorations(name, picture_path, price, date_added, description)
VALUES ('Cache-pot', '/decorations/decorations_pictures/2023-1-30-10-25-23-cache_pot.jpeg', 24.99, '2023-01-30 10:25:23', 'Cache pot en pierre naturel.');

--favorites_decorations
INSERT INTO favorites(decoration_id, user_id)
VALUES (3,1);

INSERT INTO favorites(decoration_id, user_id)
VALUES (4,2);

INSERT INTO favorites(decoration_id, user_id)
VALUES (4,1);

INSERT INTO favorites(decoration_id, user_id)
VALUES (1,1);

--tickets
INSERT INTO tickets(topic, opening_date, is_open, user_id)
VALUES ('Frais de livraison', '2023-01-09 09:14:12', false, 1);

INSERT INTO tickets(topic, opening_date, is_open, user_id)
VALUES ('Précision cadre', '2023-01-10 13:25:32', false, 1);

INSERT INTO tickets(topic, opening_date, is_open, user_id)
VALUES ('Commande non reçue', '2023-02-25 13:14:02', true, 1);

INSERT INTO tickets(topic, opening_date, is_open, user_id)
VALUES ('Livraison en France?', '2023-02-27 13:14:02', true, 2);

INSERT INTO tickets(topic, picture, opening_date, is_open, user_id)
VALUES ('Rideau tâché','/tickets/tickets_picture/2023-2-28-15-52-23-rideau.jpg', '2023-02-28 12:52:23', true, 1);


--tickets_messages
INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (1,1,'À combien s''élèvent les frais de livraison pour les rideaux?', '2023-01-09 09:14:12');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (1,3,'Pouvez-vous préciser le pays pour lequel vous voulez connaître les frais de livraison ?', '2023-01-09 09:16:32');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (2,1,'Pouvez-vous donner les dimensions du cadre mural?', '2023-01-10 13:25:32');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (2,3,'40cm x 60cm', '2023-01-10 16:18:22');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (1,1,'En Belgique', '2023-01-12 18:14:00');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (1,3,'La livraison est gratuite pour la belgique', '2023-01-13 08:31:21');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (3,1,'Je n''ai toujours pas reçu le rideau commandé le 16/01/2023', '2023-02-25 13:14:02');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (2,3,'Le rideau a été envoyé il y a deux jours. La livraison peut prendre jusqu''à une semaine', '2023-02-25 14:14:02');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (4,2,'Livrez-vous les rideaux en France?', '2023-01-10 16:18:22');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (5,1,'J''ai bien reçu le rideau commandé mais celui-ci avait une tâche en haut à gauche. Je voudrais être remboursé', '2023-02-28 12:52:23');

INSERT INTO messages(ticket_id, user_id, message, creation_date)
VALUES (5,3,'Vous devez d''abord nous renvoyer le rideau. Je vous envoie une étiquette à coller sur le colis pour ne pasavoir à l''affranchir', '2023-02-28 13:12:27');