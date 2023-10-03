public class DessinEtoile {

    public static Tortue tortue = new Tortue();

    public static void main(String args[]) {

        tortue.accelerer();
        tortue.definirCouleur( "BLEU" );

        for ( int i = 0 ; i<3 ; i++ ){

            tortue.avancer( 100 );
            tortue.tournerADroite( 120 );

        };

        tortue.avancer( 33 );
        tortue.tournerAGauche(60 );
        tortue.avancer( 33 );

        for ( int j = 0 ; j<3 ; j++ ){

            tortue.tournerADroite( 120 );
            tortue.avancer( 100 );

        };

    };

};
