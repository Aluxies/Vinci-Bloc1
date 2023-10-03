public class DessinJeuxOlympiques {

    public static void main(String[] args) {

        Tortue tortue = new Tortue();
        tortue.accelerer();

        for ( int i=1; i<=3; i++ ){

            for ( int j=1; j<=360; j++ ){

                tortue.avancer( 1 );
                tortue.tournerAGauche( 1 );

            }

            tortue.definirCouleur( "NOIR" );
            tortue.avancer( 125 );
            tortue.definirCouleur( "BLANC" );

        }

        tortue.definirCouleur( "NOIR" );
        tortue.tournerADroite( 180 );
        tortue.avancer( 187.5 );
        tortue.tournerADroite( 90 );
        tortue.avancer( 40 );
        tortue.tournerAGauche( 90 );
        tortue.definirCouleur( "BLANC" );

        for ( int i=1; i<=2; i++ ){

            for ( int j=1; j<=360; j++ ){

                tortue.tournerAGauche( 1 );
                tortue.avancer( 1 );

            }

            tortue.definirCouleur( "NOIR" );
            tortue.avancer( 125 );
            tortue.definirCouleur( "BLANC" );

        }

    }

}