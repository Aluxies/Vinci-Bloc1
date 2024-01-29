class testPersonne {

    public static void main(String[] args) {

        Adresse adresse1 = new Adresse(
                "Wapper",
                "9-11",
                "2000",
                "Anvers"
        );

        Date naissance1 = new Date(
                28,
                6,
                1577
        );

        Personne personne1 = new Personne(
                "Rubens",
                "Pierre Paul",
                naissance1,
                adresse1
        );

        Adresse adresse2 = new Adresse(
                "Place de l'Alma",
                "3",
                "1200",
                "Woluwe-Saint-Lambert"
        );

        Date naissance2 = new Date(
                14,
                4,
                1452
        );

        Personne personne2 = new Personne(
                "De Vinci",
                "Léonard",
                naissance2,
                adresse2
        );

        Adresse adresse3 = new Adresse(
                "rue des Fossés Saint jacques",
                "20",
                "75005",
                "Paris"
        );

        Date naissance3 = new Date(
                3,
                4,
                1990
        );

        Personne personne3 = new Personne(
                "Samaras",
                "Ken",
                naissance3,
                adresse3
        );

        System.out.println(

                personne1.dateNaissanceComplete() + "\n" +
                personne1.adresseComplete() + "\n" +
                personne1.ageFinAnneeActuelle() + "\n" +
                personne1.ageFinAnnee( 2025 ) + "\n\n"

        );

        System.out.println(

                personne2.dateNaissanceComplete() + "\n" +
                personne2.adresseComplete() + "\n" +
                personne2.ageFinAnneeActuelle() + "\n" +
                personne2.ageFinAnnee( 2025 ) + "\n\n"

        );

        System.out.println(

                personne3.dateNaissanceComplete() + "\n" +
                personne3.adresseComplete() + "\n" +
                personne3.ageFinAnneeActuelle() + "\n" +
                personne3.ageFinAnnee( 2025 ) + "\n\n"

        );

    };

};