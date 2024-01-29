DROP SCHEMA IF EXISTS projet CASCADE;
CREATE SCHEMA projet;

CREATE TABLE projet.entreprises (
id_entreprise SERIAL PRIMARY KEY,
identifiant CHAR(3) NOT NULL,
nom VARCHAR(40) NOT NULL,
adresse VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL UNIQUE,
mdp VARCHAR(60) NOT NULL,
CONSTRAINT identifiant CHECK ( identifiant ~ '[A-Z]{3}' )
);

CREATE TABLE projet.etudiants (
 id_etudiant SERIAL PRIMARY KEY,
 nom VARCHAR(40) NOT NULL,
 prenom VARCHAR(40) NOT NULL,
 email VARCHAR(60) NOT NULL UNIQUE,
 mdp VARCHAR(60) NOT NULL,
 semestre CHAR(2) NOT NULL,
 nbr_candidatures_en_attente INTEGER DEFAULT 0 NOT NULL ,
 CONSTRAINT semestre CHECK (semestre IN ('Q1','Q2')),
 CONSTRAINT email CHECK ( email ~ '[A-z]+\.[A-z]+@(student\.vinci\.be|vinci\.be)' )
);

CREATE TABLE projet.offres_stages (
 id_offre_stage SERIAL PRIMARY KEY,
 etat VARCHAR(20) NOT NULL DEFAULT 'non validée',
 code VARCHAR(20) NOT NULL,
 semestre CHAR(2) NOT NULL,
 description VARCHAR(150) NOT NULL,
 etudiant INT  REFERENCES projet.etudiants(id_etudiant) NULL,
 entreprise INT REFERENCES projet.entreprises(id_entreprise) NOT NULL,
 CONSTRAINT etat CHECK (etat IN ('non validée', 'validée', 'attribuée', 'annulée')),
 CONSTRAINT semestre CHECK (semestre IN ('Q1','Q2'))
);

CREATE TABLE projet.candidatures (
 id_candidature SERIAL PRIMARY KEY,
 etat VARCHAR(20) NOT NULL DEFAULT 'en attente',
 CONSTRAINT etat CHECK (etat IN ('en attente', 'acceptée', 'refusée', 'annulée')),
 motivation VARCHAR (200) NOT NULL,
 etudiant INT REFERENCES projet.etudiants(id_etudiant) NOT NULL,
 offre_stage INT REFERENCES projet.offres_stages(id_offre_stage) NOT NULL
);

CREATE TABLE projet.mots_cles_proposes(
 id_mot_cle_propose SERIAL PRIMARY KEY,
 mot_cle VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE projet.mots_cles_offres (
 mot_cle_propose INT NOT NUlL,
 offre_stage INT NOT NULL,
 PRIMARY KEY (mot_cle_propose, offre_stage),
 FOREIGN KEY (mot_cle_propose) REFERENCES projet.mots_cles_proposes(id_mot_cle_propose),
 FOREIGN KEY (offre_stage) REFERENCES projet.offres_stages(id_offre_stage)
);

-- Application professeur

-- 1. Encoder un étudiant

CREATE FUNCTION
projet.encoderEtudiant( nomEntré VARCHAR(40), prenomEntré VARCHAR(40), emailEntré VARCHAR(60), mdpEntré VARCHAR(60), semestreEntré CHAR(2) ) RETURNS integer AS $$
DECLARE
  idEtudiant INTEGER;
BEGIN
  INSERT INTO projet.etudiants (nom, prenom, email, mdp, semestre) VALUES
  (nomEntré, prenomEntré, emailEntré, mdpEntré, semestreEntré) RETURNING id_etudiant INTO idEtudiant;

  RETURN idEtudiant;
END;
$$ LANGUAGE
plpgsql;

-- 2. Encoder une entreprise

CREATE FUNCTION
projet.encoderEntreprise( identifiantEntre CHAR(3), nomEntre VARCHAR(40), adresseEntre VARCHAR(50), emailEntre VARCHAR(50), mdpEntre VARCHAR(60)) RETURNS integer AS $$
DECLARE
 idEntreprise INTEGER;
BEGIN
INSERT INTO projet.entreprises (identifiant, nom, adresse, email, mdp) VALUES
(identifiantEntre, nomEntre, adresseEntre, emailEntre, mdpEntre) RETURNING id_entreprise INTO idEntreprise;

RETURN idEntreprise;
END;
$$ LANGUAGE
plpgsql;

-- 3. Encore un mot clé

CREATE OR REPLACE FUNCTION
projet.verifierMotClePermis() RETURNS TRIGGER AS $$
DECLARE
 nbMotsCles INTEGER;
BEGIN
  SELECT count( mcp.id_mot_cle_propose )
  FROM projet.mots_cles_proposes mcp
  WHERE lower(mcp.mot_cle) = lower(NEW.mot_cle) INTO nbMotsCles;

  IF ( nbMotsCles > 1 ) THEN
      RAISE 'mot_cle_deja_existant';
  END IF;

  RETURN NEW;
END
$$ LANGUAGE
plpgsql;

CREATE FUNCTION
projet.encoderMotClePermis(motCleEntre VARCHAR(40)) RETURNS integer AS $$
DECLARE
 idMotCle INTEGER;
BEGIN
INSERT INTO projet.mots_cles_proposes (mot_cle) VALUES
(motCleEntre) RETURNING id_mot_cle_propose INTO idMotCle;

RETURN idMotCle;
END
$$ LANGUAGE
plpgsql;

CREATE TRIGGER trigger_verifier_mot_cle_permis BEFORE INSERT ON projet.mots_cles_proposes
  FOR EACH ROW EXECUTE PROCEDURE projet.verifierMotClePermis();

-- 4. Voir les offres de stage dans l'état "non validée"

-- Exemples pour la table offres_de_stag

CREATE VIEW projet.voirOffresStage AS
  SELECT os.id_offre_stage AS "id_offre",
         os.etat AS "etat_offre",
         os.code AS "code_offre",
         os.semestre AS "semestre_offre",
         os.description AS "description_offre",
         en.nom AS "nom_entreprise",
         en.id_entreprise AS "id_entreprise",
         os.etudiant AS "etudiant_offre"
  FROM projet.offres_stages os, projet.entreprises en
  WHERE os.entreprise = en.id_entreprise;

/* A METTRE SUR JAVA */

-- SELECT * FROM projet.voirOffresStage vo
--    WHERE etat_offre = 'non validée';

-- 5. Valider une offre de stage en donnant son code. –

CREATE OR REPLACE FUNCTION projet.verifierOffreStageExiste( codeOffre VARCHAR(20) ) RETURNS RECORD AS $$
DECLARE
   offreStage RECORD;
BEGIN
   SELECT *
   FROM projet.offres_stages os
   WHERE os.code = codeOffre INTO offreStage;

   IF ( offreStage IS NULL ) THEN
       RAISE 'Vous n''avez pas d''offre ayant ce code”.';
   end if;

   RETURN offreStage;
END
$$ LANGUAGE
plpgsql;

CREATE FUNCTION projet.validerOffre(codeOffre VARCHAR(20)) RETURNS VARCHAR AS $$
 DECLARE
     offreStageRecord RECORD;
 BEGIN

    offreStageRecord := projet.verifierOffreStageExiste(codeOffre);

    UPDATE projet.offres_stages
    SET etat = 'validée'
    WHERE code = codeOffre;

    RETURN codeOffre;
 END
$$ LANGUAGE plpgsql;

CREATE FUNCTION projet.verifierEtatOffre() RETURNS TRIGGER AS $$
   DECLARE
   BEGIN
      IF (OLD.etat != 'non validée' AND NEW.etat = 'validée') THEN
      RAISE EXCEPTION 'L ancienne etat n est pas a l etat non validée';
      END IF;

       RETURN NEW;

   END
$$ LANGUAGE plpgsql;

CREATE TRIGGER validerOffreTrigger BEFORE UPDATE ON projet.offres_stages FOR EACH ROW
EXECUTE PROCEDURE projet.verifierEtatOffre();

/* A METTRE SUR JAVA */
-- 6. Voir les offres de stage dans l’état "validée" --

-- SELECT * FROM projet.voirOffresStage
--    WHERE etat_offre = 'validée';

-- 7. Voir les étudiants qui n’ont pas de stage --

CREATE VIEW projet.voirEtudiantsSansStage AS
  SELECT DISTINCT e.nom,e.prenom,e.email,e.semestre,e.nbr_candidatures_en_attente, e.id_etudiant
  FROM projet.etudiants e, projet.candidatures ca
  WHERE e.id_etudiant = ca.etudiant
      AND e.id_etudiant NOT IN (
      SELECT DISTINCT e2.id_etudiant
      FROM projet.etudiants e2, projet.candidatures ca2
      WHERE e2.id_etudiant = ca2.etudiant
        AND ca2.etat = 'acceptée'
      );

/* A METTRE SUR JAVA */

-- SELECT * FROM projet.voirEtudiants
--    WHERE etat != 'accepté';

/* A METTRE EN JAVA */

-- 8. Voir les offres de stage dans l’état « attribuée ». --

-- SELECT * FROM projet.voirOffresStage
--    WHERE etat_offre = 'attribuée';

-- Lorsqu'il y a une nouvelle candidature
-- on incrémente le nbr_candidatures_en_attente

CREATE FUNCTION projet.detecterNouvelleCandidature() RETURNS TRIGGER AS $$
  DECLARE
  BEGIN

      IF ( NEW.etat = 'en attente' ) THEN

          UPDATE projet.etudiants et
          SET nbr_candidatures_en_attente=nbr_candidatures_en_attente+1
          WHERE et.id_etudiant = NEW.etudiant;

      end if;

      RETURN NEW;
  END
$$ LANGUAGE plpgsql;

CREATE TRIGGER detecterNouvelleCandidatureTrigger AFTER INSERT ON projet.candidatures FOR EACH ROW
EXECUTE PROCEDURE projet.detecterNouvelleCandidature();

-- Lorqu'une candidature n'est plus en attente
-- on diminue l'attribut nbr_candidatures_en_attente d'un étudiant

CREATE FUNCTION projet.detecterMiseAJourCandidature() RETURNS TRIGGER AS $$
  DECLARE
  BEGIN

      IF ( OLD.etat = 'en attente' AND NEW.etat <> 'en attente' ) THEN

          UPDATE projet.etudiants et
          SET nbr_candidatures_en_attente = nbr_candidatures_en_attente-1
          WHERE et.id_etudiant = NEW.etudiant;

      end if;

      RETURN NEW;
  END
$$ LANGUAGE plpgsql;

CREATE TRIGGER detecterMiseAJourCandidatureTrigger AFTER UPDATE ON projet.candidatures FOR EACH ROW
EXECUTE PROCEDURE projet.detecterMiseAJourCandidature();

-- Application Entreprise  --

-- 1. Encoder une offre de stage. --

-- Création de la fonction
CREATE OR REPLACE FUNCTION projet.verifierOffreStageAttribuee()
RETURNS TRIGGER AS $$
DECLARE
   nb_offre_stage INTEGER;
BEGIN
   -- Vérifier si l'entreprise a déjà une offre de stage attribuée ce semestre

   SELECT count(vosa.id_offre) FROM projet.voirOffresStage vosa
       WHERE vosa.etat_offre = 'attribuée'
           AND NEW.semestre = vosa.semestre_offre
           AND NEW.entreprise = vosa.id_entreprise INTO nb_offre_stage;

   IF (nb_offre_stage > 0) THEN
       -- L'entreprise a déjà une offre de stage attribuée ce semestre, annuler l'insertion
       RAISE EXCEPTION 'L''entreprise a déjà une offre de stage attribuée ce semestre.';
   END IF;

   -- Si aucune violation de contrainte n'a été détectée, continuer avec l'insertion
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION projet.encoderOffreStage(entrepriseEntre INTEGER,descriptionEntre VARCHAR(150), semestreEntre CHAR(2)) RETURNS INTEGER AS $$
    DECLARE
        id_offre_de_stage INTEGER;
        nv_code VARCHAR;
        nb_offres_entreprise INTEGER;
    BEGIN

        SELECT COALESCE(count(os.id_offre_stage), 0 )
        FROM projet.offres_stages os
        WHERE entrepriseEntre = os.entreprise INTO nb_offres_entreprise;

        SELECT CAST( CONCAT(en.identifiant,(nb_offres_entreprise+1)) as VARCHAR(20) )
        FROM projet.entreprises en
        WHERE en.id_entreprise = entrepriseEntre INTO nv_code;

        INSERT INTO projet.offres_stages(code, semestre, description, entreprise)
        VALUES (nv_code,semestreEntre,descriptionEntre,entrepriseEntre) RETURNING id_offre_stage INTO id_offre_de_stage;

        RETURN id_offre_de_stage;
    end;
$$ LANGUAGE plpgsql;

-- Création du trigger

CREATE TRIGGER after_insert_offre_stage
BEFORE INSERT ON projet.offres_stages
FOR EACH ROW
EXECUTE PROCEDURE projet.verifierOffreStageAttribuee();

-- 2. Voir les mots-clés disponibles pour décrire une offre de stage --
CREATE VIEW projet.voirMotsClesDisponibles AS
   SELECT mcp.id_mot_cle_propose, mcp.mot_cle
   FROM projet.mots_cles_proposes mcp;

-- Verifications for 3 and 5

CREATE OR REPLACE FUNCTION
projet.verifierMotCleDejaChoisi( idOffreStage INTEGER, motCleId INTEGER ) RETURNS VOID AS $$
DECLARE
   motCle RECORD;
BEGIN
   SELECT *
   FROM projet.mots_cles_offres mco
   WHERE mco.offre_stage = idOffreStage
       AND mco.mot_cle_propose = motCleId INTO motCle;


   IF ( motCle IS NOT NULL ) THEN
       RAISE 'Mot clé déjà présent pour cette offre de stage';
   end if;
END
$$ LANGUAGE
plpgsql;

CREATE OR REPLACE FUNCTION
projet.verifierMotCleExiste( motCleChoisi VARCHAR(40) ) RETURNS INT AS $$
DECLARE
   motCleId INT;
BEGIN
   SELECT mcp.id_mot_cle_propose
   FROM projet.mots_cles_proposes mcp
   WHERE lower(mcp.mot_cle) = lower(motCleChoisi) INTO motCleId;

   IF ( motCleId IS NULL ) THEN
       RAISE 'Mot clé inexistant';
   end if;

   RETURN motCleId;
END
$$ LANGUAGE
plpgsql;

CREATE OR REPLACE FUNCTION
projet.verifierNbMotsClesOffre( idOffreStage INT ) RETURNS VOID AS $$
DECLARE
   nbMotsClesOffre INT;
BEGIN
   SELECT COUNT( mco.mot_cle_propose )
   FROM projet.mots_cles_offres mco
   WHERE mco.offre_stage = idOffreStage INTO nbMotsClesOffre;

   IF ( nbMotsClesOffre = 3 ) THEN
       RAISE 'Offre de stage possède déjà 3 mots clés (max)';
   end if;

   RETURN;
END
$$ LANGUAGE
plpgsql;

CREATE OR REPLACE FUNCTION projet.verifierEtatOffreStage( codeOffreStage VARCHAR(20) ) RETURNS VOID AS $$
DECLARE
   offreStage RECORD;
BEGIN
   SELECT *
   FROM projet.offres_stages os
   WHERE os.code = codeOffreStage INTO offreStage;

   IF ( offreStage.etat = 'attribuée' OR offreStage.etat = 'annulée' ) THEN
       RAISE 'offre stage à l etat attribuée ou annulée';
   END IF;

END
$$ LANGUAGE
plpgsql;

CREATE OR REPLACE FUNCTION
projet.verifierOffreEntreprise( codeOffreStage VARCHAR(20), idEntreprise INTEGER ) RETURNS RECORD AS $$
DECLARE
   offreStage RECORD;
BEGIN

   SELECT *
   FROM projet.offres_stages os
   WHERE os.entreprise = idEntreprise
       AND os.code = codeOffreStage INTO offreStage;

   IF ( offreStage IS NULL ) THEN
       RAISE 'Il n''y a pas de candidatures pour cette offre ou vous n''avez pas d''offre ayant ce code';
   end if;

   RETURN offreStage;

END
$$ LANGUAGE
plpgsql;

-- 3

CREATE OR REPLACE FUNCTION
projet.ajouterMotCleAOffreStage( idEntreprise INTEGER, motCle VARCHAR(40), codeOffreStage VARCHAR(20) ) RETURNS VOID AS $$
DECLARE
   motCleId INTEGER;
   offreStage RECORD;
BEGIN

   SELECT *
   FROM projet.verifierOffreEntreprise(codeOffreStage, idEntreprise)
       ofSt (id_offre_stage INT, etat VARCHAR(20), code VARCHAR(20),
           semestre CHAR(2), description VARCHAR(150), etudiant INT, entreprise INT)
   INTO offreStage;

   -- On vérifie que l'offre de stage
   -- n'est pas attribuée ou annulée

   PERFORM *
   FROM projet.verifierEtatOffreStage( offreStage.code );

   -- On vérifie que l'offre de stage
   -- n'a pas déjà 3 mots clés

   PERFORM *
   FROM projet.verifierNbMotsClesOffre( offreStage.id_offre_stage );

   -- On vérifie que le mot clé existe dans
   -- ceux proposés par le professeur

   SELECT *
   FROM projet.verifierMotCleExiste( motCle )
   INTO motCleId;

   -- On vérifie si le mot clé est déjà
   -- présent pour l'offre de stage

   PERFORM *
   FROM projet.verifierMotCleDejaChoisi( offreStage.id_offre_stage, motCleId );

   INSERT INTO projet.mots_cles_offres (mot_cle_propose, offre_stage)
   VALUES (motCleId, offreStage.id_offre_stage);
   RETURN;

END
$$ LANGUAGE
plpgsql;

-- 4. Voir ses offres de stages --
    -- voir si il faut pas modifier pour ne pas pouvoir modifier le nom pour une offre qui est au stade validée
CREATE FUNCTION projet.voirSesOffresStage(entrepriseEntree INTEGER) RETURNS SETOF RECORD AS $$
    DECLARE
        record RECORD;
        sortie RECORD;
    BEGIN
        FOR record in SELECT * FROM projet.offres_stages os
                  LEFT JOIN projet.etudiants et ON os.etudiant = et.id_etudiant
            WHERE os.entreprise = entrepriseEntree
        LOOP

            IF record.etat != 'attribuée' THEN
            record.nom := 'pas attribuée';
            END IF;

            SELECT record.code, record.semestre, record.description, record.nom INTO sortie;
            RETURN NEXT sortie;
        end loop;

        RETURN;

    END;
$$ LANGUAGE plpgsql;

-- 5

CREATE OR REPLACE VIEW projet.voirCandidaturesOffreStage AS
    SELECT ca.etat AS etat_candidature,
           et.nom AS nom_etudiant,
           et.prenom AS prenom_etudiant,
           et.email AS email_etudiant,
           ca.motivation AS motivation_candidature,
           os.entreprise AS id_entreprise,
           os.code AS code_offre
    FROM projet.candidatures ca, projet.offres_stages os, projet.etudiants et
    WHERE ca.offre_stage = os.id_offre_stage
        AND ca.etudiant = et.id_etudiant;

-- 6. Sélectionner un étudiant pour une de ses offres de stage. --

CREATE FUNCTION projet.verifierOffreStageEntreprise() RETURNS TRIGGER AS $$
    BEGIN
        IF(NEW.entreprise IS NULL) THEN
            RAISE 'L offre de stage n’est pas une offre de l’entreprise';
        end if;

        IF (OLD.etat != 'validée' AND NEW.etat = 'attribuée') THEN
            RAISE 'L offre n est pas dans l état « validée »';
        end if;

        RETURN NEW;

    end;
$$ LANGUAGE plpgsql;

CREATE FUNCTION projet.verifierCandidatures() RETURNS TRIGGER AS $$
    BEGIN
        IF (OLD.etat != 'en attente' AND NEW.etat = 'acceptée') THEN
            RAISE 'L offre n est pas dans l état « validée »';
        end if;

        IF (OLD.etat != 'en attente' AND NEW.etat = 'annulée') THEN
            RAISE 'l offre ne peut pas etre annulée';
        end if;
        RETURN NEW;
    end;
$$ LANGUAGE plpgsql;

CREATE FUNCTION projet.selectionnerEtudiantPourStage(codeOffreEntre VARCHAR(40), emailEtudiantEntree VARCHAR(60),idEntreprise INTEGER) RETURNS VARCHAR AS $$
    DECLARE
        offreStage RECORD;
        idEtudiant INTEGER;
        candidatureEtudiant RECORD;
    BEGIN

    -- On recupere l'id de l'offre de stage pour lequel on veut attribuer un etudiant;

        SELECT *
        FROM projet.offres_stages os
        WHERE os.code = codeOffreEntre INTO offreStage;

        IF (offreStage.etat = 'attribuée') THEN
            RAISE 'offreStage déja attribuée.';
        end if;

        IF ( offreStage.etat != 'validée' ) THEN
            RAISE 'offreStage non validée.';
        end if;

        IF (offreStage.entreprise != idEntreprise) THEN
            RAISE 'l offre n appartien pas a l entreprise';
        end if;

    -- On recupere l'id de l'etudiant auquel on va attribuer l'offre de stage;
        SELECT et.id_etudiant
        FROM projet.etudiants et
        WHERE email = emailEtudiantEntree INTO idEtudiant;

        IF ( idEtudiant IS NULL ) THEN
            RAISE 'etudiant inexistant';
        end if;

        SELECT *
        FROM projet.candidatures ca
        WHERE ca.etudiant = idEtudiant
            AND ca.offre_stage = offreStage.id_offre_stage INTO candidatureEtudiant;

        IF ( candidatureEtudiant IS NULL ) THEN
            RAISE 'Il n existe aucune candidature de cet étudiant pour cette offre de stage.';
        end if;

        IF ( candidatureEtudiant.etat = 'annulée' ) THEN
            RAISE 'La candidature de cet étudiant est annulée';
        end if;

    -- L’état de l’offre passera à « attribuée ».
        UPDATE projet.offres_stages
        SET etat = 'attribuée', etudiant = idEtudiant
        WHERE id_offre_stage = offreStage.id_offre_stage;

    -- La candidature de l’étudiant passera à l’état « acceptée ».
        UPDATE projet.candidatures
        SET etat = 'acceptée'
        WHERE etudiant = idEtudiant
          AND offre_stage = offreStage.id_offre_stage
          AND etat = 'en attente';

    -- Les autres candidatures en attente de cet étudiant passeront à l’état « annulée ».
        UPDATE projet.candidatures
        SET etat = 'annulée'
        WHERE etudiant = idEtudiant
          AND offre_stage != offreStage.id_offre_stage
          AND etat = 'en attente';

    -- Les autres candidatures en attente d’étudiants pour cette offre passeront à « refusée ».
        UPDATE projet.candidatures
        SET etat = 'refusée'
        WHERE etudiant != idEtudiant
          AND offre_stage = offreStage.id_offre_stage
          AND etat = 'en attente';

    -- Annuler les autres offres de l'entreprise pour le même semestre

        UPDATE projet.offres_stages
        SET etat = 'annulée'
        WHERE entreprise = (
                SELECT entreprise
                FROM projet.offres_stages os2
                WHERE os2.id_offre_stage = offreStage.id_offre_stage
            )
            AND semestre = offreStage.semestre
            AND id_offre_stage != offreStage.id_offre_stage
            AND etat != 'annulée';

    RETURN 'Etudiant attribué pour le stage';
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER selectionnerEtudiantTrigger BEFORE UPDATE ON projet.offres_stages FOR EACH ROW
EXECUTE PROCEDURE projet.verifierOffreStageEntreprise();

CREATE TRIGGER selectionnerEtudiantTriggerStage BEFORE UPDATE ON projet.candidatures FOR EACH ROW
EXECUTE PROCEDURE projet.verifierCandidatures();

-- 7 Annuler une offre de stage en donnant son code --

CREATE FUNCTION projet.triggerAnnulerOffre() RETURNS TRIGGER AS $$
   DECLARE
   BEGIN
      IF (OLD.etat = 'annulée' AND NEW.etat = 'annulée') THEN
      RAISE EXCEPTION 'L offre a déja été annulée';
      END IF;

       RETURN NEW;

   END
$$ LANGUAGE plpgsql;

CREATE FUNCTION projet.annulerOffreStage(codeEntree VARCHAR(10), entrepriseEntree Integer) RETURNS VARCHAR AS $$
    DECLARE
        idOffreStage INTEGER;
        idCandidature INTEGER;
        idEntreprise INTEGER;
    BEGIN
        -- On recupere l'offre de stage
        SELECT os.id_offre_stage, os.entreprise
        FROM projet.offres_stages os, projet.entreprises en
        WHERE os.entreprise = en.id_entreprise
            AND os.etat != 'attribuée'
            AND os.etat != 'annulée'
            AND os.code = codeEntree INTO idOffreStage,idEntreprise;

        IF idOffreStage IS NULL
            THEN RAISE 'Aucune offre de stage trouvée pour annuler.';
        END IF;

        IF idEntreprise != entrepriseEntree
            THEN RAISE 'L offre de stage n appartien pas a l entreprise';
        END IF;

        -- On recupere l'id de la candidature
        SELECT id_candidature
        FROM projet.candidatures ca
        WHERE ca.offre_stage = idOffreStage
        AND etat = 'en attente' INTO idCandidature;

        UPDATE projet.offres_stages
        SET etat = 'annulée'
        WHERE id_offre_stage = idOffreStage;

        UPDATE projet.candidatures
        SET etat = 'refusée'
        WHERE id_candidature = idCandidature;

        RETURN 'l offre a été annulée';
    end;
$$ LANGUAGE plpgsql;

CREATE TRIGGER annulerOffreTrigger BEFORE UPDATE ON projet.offres_stages FOR EACH ROW
EXECUTE PROCEDURE projet.triggerAnnulerOffre();

-- Application étudiant --

-- 1. Voir toutes les offres de stage dans l’état « validée » --

CREATE VIEW projet.voirOffresStagesValideesParSemestre
AS SELECT os.code, en.nom, en.adresse, os.description, COALESCE( STRING_AGG(mcp.mot_cle, ', '), 'pas de mots-clés' ) AS mots_cles, os.semestre
FROM projet.offres_stages os
LEFT OUTER JOIN projet.mots_cles_offres mco
ON os.id_offre_stage = mco.offre_stage
LEFT OUTER JOIN projet.mots_cles_proposes mcp
ON mco.mot_cle_propose = mcp.id_mot_cle_propose, projet.entreprises en
WHERE os.etat = 'validée'
    AND os.entreprise = en.id_entreprise
GROUP BY os.code, en.nom, en.adresse, os.description, os.semestre;

-- 2. Recherche d’une offre de stage par mot clé. --

CREATE VIEW projet.rechercherOffreStageParMC AS
    SELECT DISTINCT os.id_offre_stage, os.code, en.nom, en.adresse, os.description, STRING_AGG(DISTINCT mcp.mot_cle, ', ') AS mots_cles_stage, os.semestre
    FROM projet.offres_stages os, projet.entreprises en, projet.mots_cles_offres mco, projet.mots_cles_proposes mcp
    WHERE en.id_entreprise = os.entreprise
        AND os.id_offre_stage = mco.offre_stage
        AND mco.mot_cle_propose = mcp.id_mot_cle_propose
        AND os.etat = 'validée'
    GROUP BY os.id_offre_stage, os.code, en.nom, en.adresse, os.description;

-- 3. Poser sa candidature.

CREATE OR REPLACE FUNCTION projet.poserCandidatureTrigger() RETURNS TRIGGER AS $$
    DECLARE
        semestreEtudiant CHAR(2);
        semestreOffre CHAR(2);
    BEGIN

        -- On récupere le semestre de l'etudiant
        SELECT et.semestre
        FROM projet.etudiants et
        WHERE et.id_etudiant=NEW.etudiant
        INTO semestreEtudiant;

        -- On récupere le semestre de l'offre
        SELECT os.semestre
        FROM projet.offres_stages os
        WHERE os.id_offre_stage = NEW.offre_stage
        INTO semestreOffre;

        IF EXISTS (SELECT 1
                    FROM projet.candidatures ca
                    WHERE ca.etudiant = NEW.etudiant
                      AND ca.etat = 'acceptée')
            THEN RAISE EXCEPTION 'Vous avez déjà une candidature acceptée.';
        END IF;

        IF EXISTS (SELECT 1
                    FROM projet.candidatures ca
                    WHERE ca.etudiant = NEW.etudiant AND offre_stage = NEW.offre_stage)
            THEN RAISE EXCEPTION 'Vous avez déjà posé votre candidature pour cette offre.';
    END IF;

        IF NOT EXISTS (SELECT 1
                        FROM projet.offres_stages os
                        WHERE os.id_offre_stage = NEW.offre_stage
                            AND os.etat='validée')
            THEN RAISE 'Cet offre de stage n a pas été validée';
    END IF;

        IF (semestreEtudiant!=semestreOffre)
            THEN RAISE 'Les semestres ne correspondent pas';
    END IF;

    RETURN NEW;

    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION projet.poserCandidature(codeOffreEntree VARCHAR(20),motivationEntree VARCHAR(200),etudiantEntree INTEGER) RETURNS VOID AS $$
DECLARE
    offreStage RECORD;
BEGIN

    SELECT *
    FROM projet.offres_stages os
    WHERE os.code = codeOffreEntree INTO offreStage;

    -- Insérer la candidature
    INSERT INTO projet.candidatures(motivation, etudiant, offre_stage)
    VALUES (motivationEntree, etudiantEntree, offreStage.id_offre_stage);
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER poserCandidatureTrigger
BEFORE INSERT ON projet.candidatures
FOR EACH ROW
EXECUTE PROCEDURE projet.poserCandidatureTrigger();




-- 4. Voir les offres de stage pour lesquels l’étudiant a posé sa candidature. --
    CREATE VIEW projet.voirOffresStageEtudiantAvecCandidature AS
        SELECT os.code, en.nom, ca.etat, id_etudiant
        FROM projet.candidatures ca, projet.offres_stages os, projet.entreprises en, projet.etudiants et
        WHERE ca.offre_stage = os.id_offre_stage
            AND os.entreprise = en.id_entreprise
            AND ca.etudiant = et.id_etudiant;

SELECT code, nom, etat FROM projet.voirOffresStageEtudiantAvecCandidature WHERE id_etudiant = 4;

-- 5. Annuler une candidature en précisant le code de l’offre de stage. --

CREATE FUNCTION projet.annulerCandidature(codeEntree VARCHAR(20), etudiantEntree INTEGER) RETURNS VARCHAR AS $$
    DECLARE
        idCandidature INTEGER;
    BEGIN
        SELECT id_candidature
        FROM projet.candidatures ca,
             projet.offres_stages os
        WHERE ca.offre_stage = os.id_offre_stage
            AND os.code = codeEntree
            AND ca.etudiant = etudiantEntree INTO idCandidature;

        UPDATE projet.candidatures
        SET etat = 'annulée'
        WHERE id_candidature = idCandidature;

        RETURN 'La candidature a été annulé';

    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER annulerCandidatureTrigger BEFORE UPDATE ON projet.candidatures FOR EACH ROW
EXECUTE PROCEDURE projet.verifierCandidatures();


-- AVANT LA DEMO

-- MDP ETUDIANT Jean De : MDP1

SELECT * FROM projet.encoderEtudiant( 'De', 'Jean', 'j.d@student.vinci.be', '$2a$10$uYkYOl5jSFs/zE1LCpQT0OjxFmmmzHCEVZlhTZv9KKYNQ8ThkXJE.', 'Q2' );

-- MDP ETUDIANT Marc Du : MDP2

SELECT * FROM projet.encoderEtudiant( 'Du', 'Marc', 'm.d@student.vinci.be', '$2a$10$DsA/bhweNk8tqn5sALwxjushZ1P4m9m/IO01ntEbcSpT1ehZU5n66', 'Q1' );

SELECT * FROM projet.encoderMotClePermis('Java');
SELECT * FROM projet.encoderMotClePermis('Web');
SELECT * FROM projet.encoderMotClePermis('Python');

-- MDP ENTREPRISE VINCI : vinci

SELECT * FROM projet.encoderEntreprise( 'VIN' , 'VINCI', 'Place de l''Alma 10', 'vinci@vinci.be', '$2a$10$q6Tb9Zl.cyLRhNHp/l09yO3Ntr7T5I5UlFj5YYJaXtQ3reg/9icrS' );

SELECT * FROM projet.encoderOffreStage( 1, 'stage SAP', 'Q2' );
SELECT * FROM projet.encoderOffreStage( 1, 'stage BI', 'Q2' );
SELECT * FROM projet.encoderOffreStage( 1, 'stage Unity', 'Q2' );
SELECT * FROM projet.encoderOffreStage( 1, 'stage IA', 'Q2' );
SELECT * FROM projet.encoderOffreStage( 1, 'stage mobile', 'Q1' );

SELECT * FROM projet.validerOffre( 'VIN1' );
SELECT * FROM projet.validerOffre( 'VIN4' );
SELECT * FROM projet.validerOffre( 'VIN5' );

SELECT * FROM projet.ajouterMotCleAOffreStage( 1, 'Java', 'VIN3' );
SELECT * FROM projet.ajouterMotCleAOffreStage( 1, 'Java', 'VIN5' );

SELECT * FROM projet.poserCandidature( 'VIN4', 'Motivation 1', 1 );
SELECT * FROM projet.poserCandidature( 'VIN5', 'Motivation 2', 2 );

-- MDP ENTREPRISE ULB : ulb

SELECT * FROM projet.encoderEntreprise( 'ULB' , 'ULB', 'Boulevard de l ulb', 'ulb@ulb.be', '$2a$10$hXN3vvrhyU/1nhaUCcNOdulUh4goF8IJtxYpA94Wo6GBDhRhSGel.' );

SELECT * FROM projet.encoderOffreStage( 2, 'stage javascript', 'Q2' );

SELECT * FROM projet.validerOffre( 'ULB1' );


-- ismet a l'application professeur
-- car il est propriétaire de la base de données

grant connect on database dbismetismet TO alexisarnaud;
grant usage on schema projet to alexisarnaud;

-- alexis a l'application entreprise

grant select on projet.entreprises, projet.candidatures, projet.offres_stages,
    projet.mots_cles_proposes,projet.mots_cles_offres,
    projet.etudiants,
    projet.voirMotsClesDisponibles,
    projet.voirCandidaturesOffreStage,
    projet.voiroffresstage TO alexisarnaud;
grant INSERT on projet.offres_stages,projet.mots_cles_offres TO alexisarnaud;
grant UPDATE on projet.offres_stages,projet.candidatures TO alexisarnaud;
GRANT USAGE, SELECT ON SEQUENCE projet.offres_stages_id_offre_stage_seq TO alexisarnaud;

-- alexis a l'application étudiant

grant select on projet.offres_stages,projet.entreprises,
    projet.etudiants,projet.mots_cles_offres,
    projet.mots_cles_proposes, projet.candidatures,
    projet.voirOffresStagesValideesParSemestre,
    projet.voirOffresStageEtudiantAvecCandidature,
    projet.rechercherOffreStageParMC to alexisarnaud;
grant insert on projet.candidatures to alexisarnaud;
grant usage, select on sequence projet.candidatures_id_candidature_seq to alexisarnaud;
grant update on projet.candidatures, projet.offres_stages, projet.etudiants to alexisarnaud;
