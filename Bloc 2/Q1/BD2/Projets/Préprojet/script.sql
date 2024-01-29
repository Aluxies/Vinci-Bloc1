DROP SCHEMA IF EXISTS banque CASCADE;
CREATE SCHEMA banque;

-- Create the "Personnes" table
CREATE TABLE banque.personnes (
    id_personne SERIAL PRIMARY KEY,
    nom VARCHAR(40) NOT NULL,
    prenom VARCHAR(40) NOT NULL,
    balance_utilisateur INT DEFAULT 0
);

-- Create the "Comptes" table with a foreign key reference to the "Personnes" table
CREATE TABLE banque.comptes (
    num_compte CHAR(10) PRIMARY KEY,
    personne INT REFERENCES banque.personnes(id_personne) NOT NULL,
    balance_total INT DEFAULT 0
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

CREATE FUNCTION banque.insertOperations(
    nomSource VARCHAR(40),
    prenomSource VARCHAR(40),
    compteSource CHAR(10),
    nomDestination VARCHAR(40),
    prenomDestination VARCHAR(40),
    compteDestination CHAR(10),
    dateOperation date,
    montantOperation INTEGER
) RETURNS integer AS $$
DECLARE
    source RECORD;
    destination RECORD;
    compteSource RECORD;
    compteDestination RECORD;
    idOperation INTEGER;
BEGIN
    SELECT p.id_personne
    FROM banque.personnes p
    WHERE p.prenom = prenomSource
        AND p.nom = nomSource
    INTO source;

    IF ( source IS NULL ) THEN RETURN 0; END IF;

    SELECT p.id_personne
    FROM banque.personnes p
    WHERE p.prenom = prenomDestination
        AND p.nom = nomDestination
    INTO destination;

    IF ( destination IS NULL ) THEN RETURN 0; END IF;

    SELECT c.num_compte
    FROM banque.comptes c
    WHERE c.personne = source.id_personne
    INTO compteSource;

    IF ( compteSource IS NULL ) THEN RETURN 0; END IF;

    SELECT c.num_compte
    FROM banque.comptes c
    WHERE c.personne = destination.id_personne
    INTO compteDestination;

    IF ( compteDestination IS NULL ) THEN RETURN 0; END IF;

    INSERT INTO banque.operations(date_operation, montant, source, destinataire)
    VALUES (dateOperation, montantOperation, compteSource.num_compte, compteDestination.num_compte)
    RETURNING id_operation INTO idOperation;

    RETURN idOperation;
END
$$ LANGUAGE plpgsql;

SELECT *
FROM banque.insertOperations( 'Damas', 'Christophe', '1234-56789', 'Cambron', 'Isabelle', '5632-12564', DATE( '26/10/2023' ), 250 );

CREATE FUNCTION banque.afficherEvolutionCompte(
    compteBancaire CHAR(10)
)RETURNS SETOF RECORD AS $$
DECLARE
    record RECORD;
    somme INTEGER := 0;
    sortie RECORD;
BEGIN
    FOR record IN SELECT *
    FROM banque.operations op
    WHERE op.source = compteBancaire OR op.destinataire = compteBancaire
    LOOP
        IF record.destinataire = compteBancaire THEN
            somme := somme + record.montant;
        ELSE
            somme := somme - record.montant;
        END IF;
        SELECT record.date_operation, record.source,record.destinataire,somme INTO sortie;
        RETURN NEXT sortie;
    end loop;
    RETURN;
end
$$ LANGUAGE plpgsql;

CREATE FUNCTION banque.mettreAJourCompteApresInsertOperation()
RETURNS TRIGGER AS $$
DECLARE
BEGIN
    UPDATE banque.comptes
    SET balance_total = balance_total + NEW.montant
    WHERE num_compte = NEW.destinataire;

    UPDATE banque.comptes
    SET balance_total = balance_total - NEW.montant
    WHERE num_compte = NEW.source;

    RETURN NEW;
end
$$ LANGUAGE plpgsql;

CREATE TRIGGER triggerOperationsSurComptes
    AFTER INSERT ON banque.operations
    FOR EACH ROW EXECUTE PROCEDURE banque.mettreAJourCompteApresInsertOperation();

SELECT *
FROM banque.afficherEvolutionCompte( '1234-56789') t (date_operation date, source CHAR(10), destinataire CHAR(10), somme INTEGER);

CREATE FUNCTION banque.mettreAJourBalancePersonne()
RETURNS TRIGGER AS $$
DECLARE
BEGIN
    UPDATE banque.personnes
    SET balance_utilisateur = balance_utilisateur - OLD.balance_total + NEW.balance_total
    WHERE NEW.personne = personnes.id_personne;

    RETURN NEW;
end
$$ LANGUAGE plpgsql;

CREATE TRIGGER triggerComptesPersonnes
    AFTER UPDATE ON banque.comptes
    FOR EACH ROW EXECUTE PROCEDURE banque.mettreAJourBalancePersonne();

CREATE FUNCTION banque.modifierMontantOperation(
    dateOperationChoisie date, newMontant INT, sourceChoisi CHAR(10), destinataireChoisi CHAR(10)
)
RETURNS void AS $$
DECLARE
    operation int;
BEGIN

    SELECT COUNT(*)
    FROM banque.operations op
    WHERE op.date_operation = dateOperationChoisie
        AND op.destinataire = destinataireChoisi
        AND op.source = sourceChoisi
    INTO operation;

    IF operation <> 1 THEN RAISE data_exception; END IF;

    UPDATE banque.operations
    SET montant = newMontant
    WHERE date_operation = dateOperationChoisie
        AND destinataire = destinataireChoisi
        AND source = sourceChoisi;

end
$$ LANGUAGE plpgsql;

CREATE FUNCTION banque.supprimerOperation(
    dateOperationChoisie date, montantChoisi INT, sourceChoisi CHAR(10), destinataireChoisi CHAR(10)
)
RETURNS void AS $$
DECLARE
BEGIN

    DELETE FROM banque.operations op
    WHERE op.date_operation = dateOperationChoisie
        AND op.montant = montantChoisi
        AND op.source = sourceChoisi
        AND op.destinataire = destinataireChoisi;

end
$$ LANGUAGE plpgsql;

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
       ( DATE( '9/12/2006' ), 80, '7896-23565', '9876-87654' ),
       ( DATE( '9/12/2006' ), 80, '7896-23565', '9876-87654' );

SELECT *
FROM banque.modifierMontantOperation( DATE( '4/12/2006' ), 130, '7896-23565', '9876-87654' );

SELECT *
FROM banque.supprimerOperation( DATE( '9/12/2006' ), 80, '7896-23565', '9876-87654' );
