public class TestPanier {

    public static void main(String[] args) {

        Panier panier = new Panier();

        CD cd = new CD( "CD-001", 10.99, "Nevermind", "Nirvana", 12 );

        DVD dvd = new DVD( "DVD-001", 6.99, "Star Wars VI", "Georges Lucas" );

        Livre livre1 = new Livre( "LIVRE-001",12.49, "Harry Potter 1", "J.K. Rowling", 331 );
        Livre livre2 = new Livre( "LIVRE-002", 19.99, "Hunger Games 1", "Suzanne Collins", 253 );

        System.out.println( "Panier initial :" );

        System.out.println( panier );

        System.out.println();

        System.out.println( "Panier test : ajout" );
        System.out.println();

        panier.ajouter( cd );
        panier.ajouter( dvd );
        panier.ajouter( livre1 );
        panier.ajouter( livre2 );

        System.out.println( panier );

        System.out.println();

        System.out.println( "Panier test : prix total " );
        System.out.println();

        System.out.println( "Prix total : " + panier.calculerPrixTotal() );

        System.out.println();

        System.out.println( "Panier test : suppression du cd" );
        System.out.println();

        panier.supprimer( cd );

        System.out.println();

        System.out.println( panier );

        System.out.println();

        System.out.println( "Panier test : recherche produit à partir de sa référence" );
        System.out.println();

        System.out.println( panier.trouverProduit( "DVD-001" ) );

    }
}
