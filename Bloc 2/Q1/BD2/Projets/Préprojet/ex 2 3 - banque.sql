DROP SCHEMA IF EXISTS banque CASCADE;
CREATE SCHEMA banque;

-- Create the "Personnes" table
CREATE TABLE banque.personnes (
    id_personne SERIAL PRIMARY KEY,
    nom VARCHAR(40) NOT NULL,
    prenom VARCHAR(40) NOT NULL
);

-- Create the "Comptes" table with a foreign key reference to the "Personnes" table
CREATE TABLE banque.comptes (
    num_compte CHAR(10) PRIMARY KEY,
    personne INT REFERENCES banque.personnes(id_personne) NOT NULL,
    -- Add a CHECK constraint to ensure the format is four digits, a hyphen, and five digits
    CONSTRAINT check_format CHECK (num_compte SIMILAR TO '[0-9]{4}-[0-9]{5}')
);

-- Create the "Operations" table with foreign key references to the "Comptes" table
CREATE TABLE banque.operations (
    id_operation SERIAL PRIMARY KEY,
    date_operation DATE NOT NULL,
    montant INT NOT NULL,
    source CHAR(10) REFERENCES banque.comptes(num_compte) NOT NULL,
    destinataire CHAR(10) REFERENCES banque.comptes(num_compte) NOT NULL,

    CONSTRAINT check_source_different_from_destinataire CHECK (source <> destinataire)
);

INSERT INTO banque.personnes (nom, prenom)
VALUES ( 'Damas', 'Christophe' ),
       ( 'Cambron', 'Isabelle' ),
       ( 'Ferneeuw', 'Stéphanie' );

INSERT INTO banque.comptes (num_compte, personne)
VALUES ( '1234-56789', 1 ),
       ( '9876-87654', 1 ),
       ( '5632-12564', 2 ),
       ( '1236-02364', 2 ),
       ( '7896-23565', 3 );

INSERT INTO banque.operations (date_operation, montant, source, destinataire)
VALUES ( DATE( '1/12/2006' ), 100, '1234-56789', '5632-12564' ),
       ( DATE( '2/12/2006' ), 120, '5632-12564', '1236-02364' ),
       ( DATE( '3/12/2006' ), 80, '9876-87654', '7896-23565' ),
       ( DATE( '4/12/2006' ), 80, '7896-23565', '9876-87654' ),
       ( DATE( '5/12/2006' ), 150, '1236-02364', '7896-23565' ),
       ( DATE( '6/12/2006' ), 120, '5632-12564', '1236-02364' ),
       ( DATE( '7/12/2006' ), 100, '1234-56789', '5632-12564' ),
       ( DATE( '8/12/2006' ), 80, '9876-87654', '7896-23565' ),
       ( DATE( '9/12/2006' ), 80, '7896-23565', '9876-87654' );