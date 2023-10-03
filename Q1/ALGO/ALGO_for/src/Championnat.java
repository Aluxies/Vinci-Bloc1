public class Championnat {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        int nombreCotes = 8;

        System.out.println( "Vous allez devoir entrer " + nombreCotes + " entiers\n");

        double coteMax = 0;
        double coteMin = 10;

        double somme = 0;
        double moyenne = 0;

        for ( int i = 1; i <= nombreCotes ; i++ ) {

            System.out.print( "Entrez la cote n°" + i + " (sur 10) : " );
            double cote = scanner.nextDouble();

            somme += cote;

            if ( cote < coteMin ){

                coteMin = cote;

            };

            if ( cote > coteMax ){

                coteMax = cote;

            };

        };

        somme -= ( coteMax + coteMin );
        moyenne = somme / ( nombreCotes - 2 );

        System.out.println( "\nVous devez avoir 8/10 pour pouvoir réussir le championnat\n" );

        String mot = "";

        if ( moyenne < 8 ){

            mot = "échoué";

        } else mot = "gagné";

        System.out.println( "Vous avez eu la note de " + moyenne + "/10" );
        System.out.println( "Vous avez " + mot + " au championnat !" );

    };

};
