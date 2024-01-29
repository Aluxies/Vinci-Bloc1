public class DessinBonhommeDeNeige {

    public static Tortue tortue = new Tortue();

    public static void main(String[] args) {

        tortue.accelerer();

        // Petit cercle en haut

        for ( int i=0; i<360; i++ ){

            tortue.avancer( 0.5 );
            tortue.tournerAGauche( 1 );

        };

        // Grand cercle en dessous

        for ( int j=0; j<360; j++ ){

            tortue.avancer( 1 );
            tortue.tournerADroite( 1 );

        };

        for ( int k=0; k<180; k++ ){

            tortue.avancer( 1 );
            tortue.tournerADroite( 1 );

        };

        // Déplacer jusqu'au sapin

        tortue.avancer( 10 );
        tortue.definirCouleur( "NOIR" );
        tortue.avancer( 230 );

        // SAPIN

        tortue.definirCouleur( "BLANC" );
        tortue.avancer( 40);
        tortue.tournerADroite( 90 );
        tortue.avancer( 60 );

        tortue.definirCouleur( "VERT" );

        for ( int l=0; l<3; l++){

            int pixels1 = 100;
            int pixels2 = 100;
            int angle = 90;

            if ( l == 1 ){

                pixels1 = 60;
                pixels2 = 100;
                angle = 135;

            } else if ( l == 2 ){

                pixels1 = 40;
                pixels2 = 100;
                angle = 135;

            }

            tortue.tournerAGauche( angle );
            tortue.avancer( pixels1 );
            tortue.tournerADroite( 135 );
            tortue.avancer( pixels2 );

        };


        tortue.tournerADroite( 90 );

        for ( int m=0; m<3; m++){

            int pixels1 = 40;
            int pixels2 = 100;
            int angle = 135;

            if ( m == 1 ){

                pixels1 = 60;
                pixels2 = 100;
                angle = 135;

            } else if ( m == 2 ){

                pixels1 = 85;
                pixels2 = 100;
                angle = 90;

            }

            tortue.avancer( pixels2 );
            tortue.tournerADroite( 135 );
            tortue.avancer( pixels1 );
            tortue.tournerAGauche( angle );

        };

        tortue.definirCouleur( "BLANC" );
        tortue.avancer( 60 );

        // Déplacement

        tortue.tournerAGauche( 90 );
        tortue.definirCouleur( "NOIR" );
        tortue.avancer( 100 );

        tortue.tournerADroite( 90 );
        tortue.avancer( 50);
        tortue.tournerAGauche( 90 );
        tortue.avancer( 200 );
        tortue.tournerAGauche( 90 );
        tortue.avancer( 50 );
        tortue.tournerADroite( 90 );
        tortue.avancer( 100 );
        tortue.tournerAGauche( 90 );
        tortue.avancer( 150);
        tortue.tournerADroite( 90);

        // Maison

        tortue.definirCouleur( "BLANC" );

        for ( int n=0; n<3; n++ ){

            tortue.avancer( 150 );
            tortue.tournerAGauche( 120 );

        };

        for ( int o=0; o<4; o++ ){

            tortue.avancer( 150 );
            tortue.tournerADroite( 90 );

        };

        // Deplacement

        tortue.tournerADroite( 90 );
        tortue.avancer( 150 );
        tortue.tournerAGauche( 90 );
        tortue.avancer( 60 );
        tortue.tournerAGauche( 90 );

        // Porte

        for ( int p=0; p<4; p++ ){

            int pixels = 75;

            if ( p == 1 || p == 3 ){

                pixels = 30;

            } else pixels = 75;

            tortue.avancer( pixels );
            tortue.tournerADroite( 90 );


        }






    }
}
