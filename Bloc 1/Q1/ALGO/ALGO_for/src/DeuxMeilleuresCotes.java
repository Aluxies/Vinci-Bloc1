public class DeuxMeilleuresCotes {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println( "Vous allez devoir entrer 5 cotes (sur 10)\n\n" );

        int coteMax1 = 0;
        int coteMax2 = 0;

        for ( int i = 1; i <= 5; i ++ ){

            System.out.println( "Cote numéro " + i + " (sur 10) : " );
            int cote = scanner.nextInt();

            if ( cote > coteMax1 ){

                coteMax2 = coteMax1;
                coteMax1 = cote;

            } else if ( cote > coteMax2 ){

                coteMax2 = cote;

            };

        };


        System.out.println( "La première cote maximale est : " + coteMax1 );
        System.out.println( "La deuxième cote maximale est : " + coteMax2 );

    };

};