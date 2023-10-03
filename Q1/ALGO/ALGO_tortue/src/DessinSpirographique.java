public class DessinSpirographique {

    public static Tortue tortue = new Tortue();

    public static void main(String args[]) {

        tortue.accelerer();
        tortue.definirCouleur( "ROUGE" );

        for ( int i=0; i<36; i++ ){

            if ( i == 9 ) tortue.definirCouleur( "BLEU" );
            if ( i == 18 ) tortue.definirCouleur( "JAUNE" );
            if ( i == 27 ) tortue.definirCouleur( "VERT" );

            for ( int j=0; j<360; j++ ){

                tortue.avancer( 1 );
                tortue.tournerADroite( 1 );

            };

            tortue.tournerADroite( 10 );

        };

    };

};
