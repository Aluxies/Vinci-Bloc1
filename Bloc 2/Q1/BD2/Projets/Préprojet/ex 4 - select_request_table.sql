-- 4. Ecrivez la requête SQL permettant d’obtenir le tableau
-- ci-dessus à partir de vos tables normalisées
-- (truc : triez par la date pour obtenir le même ordre).

SELECT p2.nom AS "NomSource", p2.prenom AS "PrenomSource", c2.num_compte AS "CompteSource",
       p1.nom AS "NomDestination", p1.prenom AS "PrenomDestination", c1.num_compte AS "CompteDestination",
       o.date_operation AS "DateOpération", o.montant AS "Montant"
FROM banque.operations o, banque.comptes c1, banque.comptes c2, banque.personnes p1, banque.personnes p2
WHERE o.destinataire = c1.num_compte
    AND o.source = c2.num_compte
    AND c2.personne = p2.id_personne
    AND c1.personne = p1.id_personne;