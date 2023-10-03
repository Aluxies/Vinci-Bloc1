public class DessinLunettes {

    public static Tortue tortue = new Tortue();

    public static void main(String args[]) {

        tortue.definirCouleur( "BLEU" );

        for ( int i = 0 ; i<4 ; i++ ){

            tortue.avancer( 100 );
            tortue.tournerADroite( 90 );

        };

        tortue.tournerADroite( 180 );
        tortue.avancer( 50 );
        tortue.tournerAGauche( 90 );

        for ( int i = 0 ; i<4 ; i++ ){

            tortue.avancer( 100 );
            tortue.tournerADroite( 90 );

        };

    };

};
