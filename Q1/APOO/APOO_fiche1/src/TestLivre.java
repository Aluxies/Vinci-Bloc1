public class TestLivre {

    public static void main(String args[]) {

        Livre livre1 = new Livre(
                "978-2-87963-727-3",
                "Harry Potter à l'école des sorciers",
                "Rowling",
                "J.K.",
                8.95,
                2017,
                320
        );
        Livre livre2 = new Livre(
                "978-2-87963-727-3",
                "Le Cahier Transmath 6e",
                "Malaval",
                "Joël",
                29.89,
                2016,
                111
        );


        System.out.println(

                "Livre 1 :\n\n- Titre : " +
                livre1.titre +
                "\n- Année d'édition : " +
                livre1.anneeEdition +
                "\n- Prénom / Nom auteur : " +
                livre1.prenomAuteur +
                " / " +
                livre1.nomAuteur

        );

        System.out.println(

                "\nLivre 2 :\n\n- ISBN : " +
                 livre2.isbn +
                 "\n- Titre : " +
                 livre2.titre +
                 "\n- Prénom / Nom auteur : " +
                 livre2.prenomAuteur +
                 " / " +
                 livre2.nomAuteur +
                 "\n- Prix conseillé : " +
                 livre2.prixConseille +
                 "\n- Année d'édition : " +
                 livre2.anneeEdition +
                 "\n- Nombre de pages : " +
                 livre2.nombreDePages

        );

    };

}
