public class TestEchecs {

    public static void main(String[] args) {

        // Test Club

        System.out.println( "Classe Club :");
        System.out.println( "-----------------------------------------------");
        System.out.println();

        Adresse adresse1 = new Adresse( "Paris", "10000", "Champs Elysées", "29" );
        Adresse adresse2 = new Adresse( "New York", "50000", "Time Square", "392" );

        Club club1 = new Club("1-1234-5678", "CHECKERS", adresse1 );
        Club club2 = new Club("2-9876-5432", "LEGENDS", adresse2 );

        System.out.println( "Club n°1 :");
        System.out.println();

        System.out.println( club1 );

        System.out.println();
        System.out.println( "Club n°2 :");

        System.out.println( club2 );

        System.out.println();

        // Test Joueur

        System.out.println( "Classe Joueur :");
        System.out.println( "-----------------------------------------------");
        System.out.println();

        Date naissance1 = new Date( 10, 6, 1979 );
        Date naissance2 = new Date( 26, 1, 1985 );

        Joueur joueur1 = new Joueur("1234-5678", "Matthieu", "Philippe", naissance1, club1 );
        Joueur joueur2 = new Joueur("9876-5432", "Garfield", "Andrew", naissance2, club2 );

        System.out.println( "Joueur n°1 :");

        System.out.println( joueur1 );
        System.out.println();

        System.out.println( "Joueur n°2 :");

        System.out.println( joueur2 );

        System.out.println();

        // Test Tournoi

        System.out.println( "Classe Tournoi :");
        System.out.println( "-----------------------------------------------");
        System.out.println();

        Date dateDebutTournoi = new Date( 21, 2, 2007 );
        Date dateFinTournoi = new Date( 21, 3, 2007 );

        System.out.println( "Tournoi : ");
        Tournoi tournoi = new Tournoi("Grand Tournoi International Des Echecs", dateDebutTournoi, dateFinTournoi );

        System.out.println( tournoi );

        System.out.println();


        // Test Partie

        System.out.println( "Classe Partie :");
        System.out.println( "-----------------------------------------------");
        System.out.println();

        Date dateDebutPartie = new Date( 28, 2, 2007 );

        Partie partie = new Partie( dateDebutPartie, joueur1, joueur2, tournoi );

        System.out.println( partie );

        // JoueurNoir gagne

        System.out.println();

        partie.terminerPartie( 40 );
        System.out.println( partie );

        System.out.println();

        System.out.println( "Joueur n°1 :");

        System.out.println( joueur1 );
        System.out.println();

        System.out.println( "Joueur n°2 :");

        System.out.println( joueur2 );

        System.out.println();

    }

}