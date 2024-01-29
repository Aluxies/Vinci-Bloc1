public class ResultatsSurCent10Participants {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println( "Vous allez devoir entrer le résultat (sur 100) de 10 participants\n" );

        Participant premier = new Participant( 0, 0 );
        Participant deuxieme = new Participant( 0, 0 );

        for ( int i = 1; i <= 10; i++ ){

            System.out.println( "Entrez le résultat du participant numéro " + i + " : " );

            int resultat = scanner.nextInt();

            if ( resultat > premier.getResultat() ){

                deuxieme.setNumero( premier.getNumero() );
                deuxieme.setResultat( premier.getResultat() );

                premier.setNumero( i );
                premier.setResultat( resultat );

            } else if ( resultat > deuxieme.getResultat() ){

                deuxieme.setNumero( i );
                deuxieme.setResultat( resultat );

            };

        };

        System.out.println( "Le participant numéro " + premier.getNumero() + " est vainqueur avec un résultat de " + premier.getResultat() + "% !" );

        if ( premier.getResultat() == deuxieme.getResultat() ) System.out.println( "Présence d'ex-aequo entre le numéro " + premier.getNumero() + " et le numéro " + deuxieme.getNumero() );

    };

};