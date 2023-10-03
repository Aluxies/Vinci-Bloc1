public class magIPL {

    public static void main(String[] args) {

        MainJoueur mainJoueur1 = new MainJoueur();

        Terrain terrain = new Terrain('b' );
        Creature creature = new Creature( 6, "Golem", 4, 6 );
        Sortilege sortilege = new Sortilege( 1, "Croissance Gigantesque", "Permet de grandir énormément" );

        System.out.println( "Là, j'ai en stock :" );

        mainJoueur1.piocher( terrain );
        mainJoueur1.piocher( creature );
        mainJoueur1.piocher( sortilege );

        System.out.println( mainJoueur1.fournirDetail() );

        System.out.println( "Carte jouée : " + mainJoueur1.jouer( 2 ).fournirDetail() );

        System.out.println( "Maintenant, il me reste : ");

        System.out.println( mainJoueur1.fournirDetail() );

        System.out.println();

        System.out.println( "Main n°2 :" );

        MainJoueur mainJoueur2 = new MainJoueur();

        System.out.println( "Tentative d'ajout d'une carte de l'autre main" );

        try {

            mainJoueur2.piocher( terrain );

        } catch( IllegalArgumentException e ){

            System.out.println( e.getMessage() );

        }

        System.out.println( "Tentative d'ajout de la carte sortilège : " );

        mainJoueur2.piocher( sortilege );

        System.out.println( "Main du 2e joueur : " );

        System.out.println( mainJoueur2.fournirDetail() );


    }

}