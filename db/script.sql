CREATE DATABASE cardstore;
GRANT ALL PRIVILEGES ON games.* TO jose;
USE cardstore;

-- Tabla de usuarios (players)
CREATE TABLE IF NOT EXISTS players (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    role VARCHAR(20) DEFAULT 'user',
    competitive BOOLEAN DEFAULT FALSE,
    last_login DATETIME
);

-- Tabla de cartas (cards)
CREATE TABLE IF NOT EXISTS cards (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    release_date DATE,              -- Usa DATE si solo quieres la fecha
    attack VARCHAR(10),
    defense VARCHAR(10),
    is_foil BOOLEAN DEFAULT FALSE,
    price FLOAT,
    image VARCHAR(100)
);

-- Tabla de mazos preconstruidos (starter_decks)
CREATE TABLE IF NOT EXISTS starter_decks (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    release_date DATE NOT NULL,
    discontinued BOOLEAN DEFAULT FALSE,
    price FLOAT,
    image VARCHAR(100)
);

-- Tabla de expansiones
CREATE TABLE IF NOT EXISTS expansions (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    release_date DATE NOT NULL,
    discontinued BOOLEAN DEFAULT FALSE,
    price FLOAT,
    image VARCHAR(100)
);

INSERT INTO players (username, password, email, role, last_login) VALUES
('admin', SHA1('admin'), 'admin@cardstore.com', 'admin', NOW()),
('alice', SHA1('alice123'), 'alice@gmail.com', 'user', NOW()),
('bob', SHA1('bob123'), 'bob@yahoo.com', 'user', NOW()),
('carlos', SHA1('carlospw'), 'carlos@correo.com', 'user', NULL),
('daniel', SHA1('danipass'), 'daniel@hotmail.com', 'user', NULL),
('eva', SHA1('evapass'), 'eva@gmail.com', 'user', NOW()),
('fran', SHA1('franpw'), 'fran@protonmail.com', 'user', NOW()),
('grace', SHA1('gracepw'), 'grace@outlook.com', 'user', NULL),
('hector', SHA1('hectorpw'), 'hector@mail.com', 'user', NOW()),
('ines', SHA1('inespw'), 'ines@correo.com', 'user', NULL);

INSERT INTO cards (name, release_date, attack, defense, is_foil, price, image) VALUES
('Blue-Eyes White Dragon', '2002-03-01', '3000', '2500', TRUE, 100.50, 'blueeyes.jpg'),
('Dark Magician', '2002-03-01', '2500', '2100', FALSE, 70.99, 'darkmagician.jpg'),
('Red-Eyes Black Dragon', '2002-06-01', '2400', '2000', FALSE, 50.00, 'redeyes.jpg'),
('Summoned Skull', '2003-05-05', '2500', '1200', TRUE, 30.00, 'skull.jpg'),
('Kuriboh', '2003-05-05', '300', '200', FALSE, 5.00, 'kuriboh.jpg'),
('Celtic Guardian', '2004-01-10', '1400', '1200', FALSE, 12.50, 'celtic.jpg'),
('Exodia the Forbidden One', '2002-10-15', '1000', '1000', TRUE, 300.00, 'exodia.jpg'),
('Obelisk the Tormentor', '2005-11-30', '4000', '4000', TRUE, 999.99, 'obelisk.jpg');

INSERT INTO starter_decks (name, release_date, discontinued, price, image) VALUES
('Starter Deck: Yugi', '2002-03-01', FALSE, 19.99, 'yugi_deck.jpg'),
('Starter Deck: Kaiba', '2002-03-01', FALSE, 19.99, 'kaiba_deck.jpg'),
('Starter Deck: Joey', '2002-06-01', TRUE, 15.99, 'joey_deck.jpg'),
('Starter Deck: Pegasus', '2002-06-01', TRUE, 15.99, 'pegasus_deck.jpg'),
('Starter Deck: Mai', '2003-01-01', FALSE, 17.99, 'mai_deck.jpg'),
('Starter Deck: Rex', '2003-03-10', TRUE, 13.99, 'rex_deck.jpg'),
('Starter Deck: Weevil', '2003-03-10', TRUE, 13.99, 'weevil_deck.jpg'),
('Starter Deck: Bakura', '2004-07-15', FALSE, 18.99, 'bakura_deck.jpg'),
('Starter Deck: Marik', '2004-07-15', TRUE, 16.99, 'marik_deck.jpg'),
('Starter Deck: Espa Roba', '2005-02-28', FALSE, 19.99, 'espa_deck.jpg');

INSERT INTO expansions (name, release_date, discontinued, price, image) VALUES
('Legend of Blue Eyes White Dragon', '2002-03-01', TRUE, 120.00, 'lbe.jpg'),
('Metal Raiders', '2002-05-01', TRUE, 110.00, 'mr.jpg'),
('Spell Ruler', '2002-09-01', TRUE, 100.00, 'sr.jpg'),
('Pharaoh''s Servant', '2002-10-01', TRUE, 105.00, 'ps.jpg'),
('Labyrinth of Nightmare', '2003-03-01', TRUE, 95.00, 'ln.jpg'),
('Legacy of Darkness', '2003-05-01', FALSE, 90.00, 'lod.jpg'),
('Pharaonic Guardian', '2003-07-01', FALSE, 85.00, 'pg.jpg'),
('Magician''s Force', '2003-10-01', FALSE, 80.00, 'mf.jpg'),
('Dark Crisis', '2004-01-01', FALSE, 75.00, 'dc.jpg'),
('Invasion of Chaos', '2004-03-01', FALSE, 70.00, 'ioc.jpg');
