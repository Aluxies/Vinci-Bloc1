public class calendrierMoisDonne {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        int longueurMois = 2;

        do {

            System.out.print( "\n\nEntrez la longueur du mois (28, 29, 30, 31 jours) : " );

            longueurMois = scanner.nextInt();

        } while ( longueurMois < 28 || longueurMois > 31 );

        int premierJourMois = 0;

        do {

            System.out.print( "\n\nEntrez le premier jour du mois (lundi : 1, mardi : 2,..) : " );

            premierJourMois = scanner.nextInt();

        } while ( premierJourMois < 1 || premierJourMois > 7 );

        String string = "\tLu \tMa \tMe \tJe \tVe \tSa \tDi\n";

        int jourSemaine = premierJourMois;

        for ( int j=1; j<=premierJourMois-1; j++ ){

            string += "\t ";

        }

        for ( int k=1; k<=longueurMois; k++ ){

            string += "\t" + k + " ";

            jourSemaine ++;

            if ( jourSemaine == 8 ){

                string += "\n";
                jourSemaine = 1;

            }

        }

        System.out.println( string );

    }

}