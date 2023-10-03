public class DessinPriseElectrique {

    public static Tortue tortue = new Tortue();

    public static void main(String args[]) {

        tortue.accelerer();
        tortue.definirCouleur( "BLEU" );

        for (int i=0; i<360; i++){

            tortue.avancer( 1 );
            tortue.tournerADroite( 1 );

        };

        tortue.definirCouleur( "NOIR" );
        tortue.tournerADroite(90 );
        tortue.avancer( 50);
        tortue.tournerADroite( 90 );
        tortue.avancer( 15 );
        tortue.definirCouleur( "BLEU" );

        for ( int j=0 ; j<360 ; j++ ){

            tortue.avancer( 0.1 );
            tortue.tournerAGauche( 1);

        };

        tortue.definirCouleur( "NOIR" );
        tortue.tournerADroite( 180 );
        tortue.avancer( 30 );
        tortue.definirCouleur( "BLEU" );

        for ( int k=0 ; k<360 ; k++ ){

            tortue.avancer( 0.1 );
            tortue.tournerADroite( 1 );
        }

    };

};
