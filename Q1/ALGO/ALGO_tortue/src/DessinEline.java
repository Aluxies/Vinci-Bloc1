public class DessinEline {

    public static Tortue tortue = new Tortue();

    public static void main(String args[]) {

        tortue.accelerer();

        tortue.definirCouleur( "NOIR" );
        tortue.tournerADroite( 180 );
        tortue.avancer( 250 );

        // E

            tortue.definirCouleur( "ROUGE" );
            tortue.avancer( 50 );

            for ( int j=0; j<2; j++){

                int pixels1 = 25;

                if ( j == 1 ) pixels1 = 50;

                tortue.tournerADroite( 90 );
                tortue.avancer( 50 );
                tortue.tournerADroite( 90 );
                tortue.avancer( pixels1 );
                tortue.tournerADroite( 180 );
                tortue.avancer( pixels1 );

            };

            tortue.tournerAGauche( 90 );
            tortue.avancer( 100 );
            tortue.tournerAGauche( 90 );
            tortue.avancer( 50 );

        
        tortue.definirCouleur( "NOIR" );
        tortue.avancer( 50 );


        // L

            tortue.definirCouleur( "ROUGE" );

            for ( int j=0; j<2; j++ ){

                int pixels2 = 100;

                if ( j == 1 ){

                    pixels2 = 50;

                    tortue.tournerAGauche( 180 );
                    tortue.avancer( 100 );

                };

                tortue.tournerAGauche( 90 );
                tortue.avancer( pixels2 );

            };

        tortue.definirCouleur( "NOIR" );
        tortue.avancer( 50 );

        // I

            tortue.definirCouleur( "ROUGE" );
            tortue.tournerAGauche( 90 );
            tortue.avancer( 75 );
            tortue.definirCouleur( "NOIR" );
            tortue.avancer( 15 );
            tortue.tournerAGauche( 90 );
            tortue.definirCouleur( "ROUGE" );

            for ( int k=0; k<360; k++){

                tortue.avancer( 0.1 );
                tortue.tournerADroite( 1 );

            };

            tortue.definirCouleur( "NOIR" );
            tortue.tournerAGauche( 90 );
            tortue.avancer( 15 );
            tortue.definirCouleur( "ROUGE" );
            tortue.avancer( 75 );


        tortue.definirCouleur( "NOIR" );
        tortue.tournerAGauche( 90 );
        tortue.avancer( 50 );
        tortue.tournerAGauche( 90 );

        // N


            tortue.definirCouleur( "ROUGE" );
            tortue.avancer( 100 );
            tortue.tournerADroite( 150 );
            tortue.avancer( 117.5 );
            tortue.tournerAGauche( 150 );
            tortue.avancer( 100 );
            tortue.tournerADroite( 180 );
            tortue.avancer( 100 );
            tortue.tournerAGauche( 90 );


        tortue.definirCouleur( "NOIR" );
        tortue.avancer( 100 );
        tortue.tournerAGauche( 180 );

            // E

            tortue.definirCouleur( "ROUGE" );
            tortue.avancer( 50 );

            for ( int j=0; j<2; j++){

                int pixels1 = 25;

                if ( j == 1 ) pixels1 = 50;

                tortue.tournerADroite( 90 );
                tortue.avancer( 50 );
                tortue.tournerADroite( 90 );
                tortue.avancer( pixels1 );
                tortue.tournerADroite( 180 );
                tortue.avancer( pixels1 );

            };

        tortue.tournerAGauche( 90 );
        tortue.avancer( 100 );
        tortue.tournerAGauche( 90 );
        tortue.avancer( 50 );
        tortue.definirCouleur( "NOIR" );
        tortue.avancer( 75 );

            // <3

            tortue.definirCouleur( "ROUGE" );
            tortue.tournerAGauche( 120 );
            tortue.avancer( 90 );
            tortue.tournerADroite( 30 );

            for ( int x=0; x<180; x++){

                tortue.avancer( 0.4 );
                tortue.tournerADroite( 1 );

            };

            tortue.tournerADroite( 180 );

            for ( int x=0; x<180; x++){

                tortue.avancer( 0.4 );
                tortue.tournerADroite( 1 );

            };

            tortue.tournerADroite( 30 );
            tortue.avancer( 90 );

    };

};
