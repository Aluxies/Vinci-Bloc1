public class DessinVentilateur {

    public static void main(String[] args) {

        Tortue tortue = new Tortue();
        tortue.accelerer();

        for ( int i=1; i<=3; i++ ){

            for ( int j=1; j<=4; j++ ){

                tortue.avancer( 50 );
                tortue.tournerADroite( 90 );

            };

            tortue.tournerADroite( 120 );

        };

    };

};