public class TestPanier {

    public static void main(String[] args) {

        // 1) Créer un panier

        Panier panier = new Panier();

        System.out.println( "1) " + panier );
        System.out.println();

        // 2) Créer un livre de référence "123"

        Livre livre1 = new Livre( "123", 12.99, "Harry Potter", "J.K. Rowling", 559 );

        System.out.println( "2) Livre1 : " + livre1 );
        System.out.println();

        try {

            // 3) Supprimer livre1 du panier

            panier.supprimer( livre1 );

        } catch ( ProduitNonPresentException e ){

            System.out.println( "3) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            System.out.println( "3) " + e.getMessage() );

        } finally {

            System.out.println();

        }


        try {

            // 4) Supprimer "null" du panier

            panier.supprimer( null );

        } catch ( ProduitNonPresentException e ){

            System.out.println( "4) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            System.out.println( "4) " + e.getMessage() );

        } finally {

            System.out.println();

        }


        try {

            // 5) Chercher le produit de référence "123" dans le panier

            panier.trouverProduit( "123" );

        } catch ( ProduitNonPresentException e ){

            System.out.println( "5) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            System.out.println( "5) " + e.getMessage() );

        } finally {

            System.out.println();

        }


        try {

            // 6) Chercher le produit de référence null

            panier.trouverProduit( null );

        } catch ( ProduitNonPresentException e ){

            System.out.println( "6) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            System.out.println( "6) " + e.getMessage() );

        } finally {

            System.out.println();

        }


        try {

            // 7) Ajouter "null" au panier

            panier.ajouter( null );

        } catch ( PanierPleinException e ){

            System.out.println( "7) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            System.out.println( "7) " + e.getMessage() );

        } finally {

            System.out.println();

        }


        boolean error1 = false;

        try {

            // 8) Ajouter livre1 au panier

            panier.ajouter( livre1 );

        } catch ( PanierPleinException e ){

            error1 = true;
            System.out.println( "8) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            error1 = true;
            System.out.println( "8) " + e.getMessage() );

        } finally {

            if ( !error1 ){

                System.out.println( "8) Ajout de livre1 réussi" );

            }

            System.out.println();

        }


        try {

            // 9) Chercher un produit de référence "1234" dans le panier

            panier.trouverProduit( "1234" );

        } catch ( ProduitNonPresentException e ){

            System.out.println( "9) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            System.out.println( "9) " + e.getMessage() );

        } finally {

            System.out.println();

        }


        boolean error2 = false;

        try {

            // 10) Chercher un produit de référence "1234" dans le panier

            panier.trouverProduit( "123" );

        } catch ( ProduitNonPresentException e ){

            error2 = true;
            System.out.println( "10) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            error2 = true;
            System.out.println( "10) " + e.getMessage() );

        } finally {

            if ( !error2 ){

                System.out.println( "10) Recherche du produit réussie" );

            }

            System.out.println();

        }


        try {

            // 11) Chercher un produit de référence "" dans le panier

            panier.trouverProduit( "" );

        } catch ( ProduitNonPresentException e ){

            error2 = true;
            System.out.println( "11) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            error2 = true;
            System.out.println( "11) " + e.getMessage() );

        } finally {

            if ( !error2 ){

                System.out.println( "11) Recherche du produit réussie" );

            }

            System.out.println();

        }


        // 12) Créer un cd de référence "123"

        CD cd1 = new CD( "cd1", 25.99, "Bella", "Maître Gims", 5 );

        System.out.println( "12) Cd1 : " + cd1 );
        System.out.println();

        try {

            // 13) Supprimer cd1 du panier

            panier.supprimer( cd1 );

        } catch ( ProduitNonPresentException e ){

            System.out.println( "13) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            System.out.println( "13) " + e.getMessage() );

        } finally {

            System.out.println();

        }


        boolean error3 = false;

        try {

            // 14) Ajouter cd1 au panier

            panier.ajouter( cd1 );

        } catch ( PanierPleinException e ){

            error3 = true;
            System.out.println( "14) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            error3 = true;
            System.out.println( "14) " + e.getMessage() );

        } finally {

            if ( !error3 ){

                System.out.println( "14) Ajout de cd1 réussi" );

            }

            System.out.println();

        }

        // 15) Créer un livre de référence "123"

        Livre livre2 = new Livre( "123", 34.24, "Hunger Games", "???",245 );

        System.out.println( "15) Livre 2 : " + livre2 );
        System.out.println();


        boolean error4 = false;

        try {

            // 16) Supprimer livre2 du panier

            panier.supprimer( livre2 );

        } catch ( ProduitNonPresentException e ){

            error4 = true;
            System.out.println( "16) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            error4 = true;
            System.out.println( "16) " + e.getMessage() );

        } finally {

            if ( !error4 ){

                System.out.println( "16) Suppression de livre2 réussie" );

            }

            System.out.println();

        }


        // 17) Afficher le panier

        System.out.println( "17) " + panier );

        // 18) Faire une boucle qui ajoute 9 DVD au panier

        for ( int i = 0; i < 9; i++ ){

            boolean error5 = false;

            try {

                panier.ajouter( new CD( "CD"+i, 24.66, "L'odeur de l'essence", "Orelsan", 5 ) );

            } catch ( PanierPleinException e ){

                error5 = true;
                System.out.println( "17) " + e.getMessage() );

            } catch ( IllegalArgumentException e ){

                error5 = true;
                System.out.println( "17) " + e.getMessage() );

            } finally {

                if ( !error5 ){

                    System.out.println( "17) Ajout de cd" + i + " réussi" );

                }

                System.out.println();

            }

        }

        // 19) Ajouter livre1 au panier

        try {

            panier.ajouter( livre1 );

        } catch ( PanierPleinException e ){

            System.out.println( "19) " + e.getMessage() );

        } catch ( IllegalArgumentException e ){

            System.out.println( "19) " + e.getMessage() );

        } finally {

            System.out.println();

        }

        // 20) Afficher le panier

        System.out.println( "20) " + panier );


    }

}