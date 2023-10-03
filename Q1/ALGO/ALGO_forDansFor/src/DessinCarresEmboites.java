public class DessinCarresEmboites {

    public static void main(String[] args) {

        Tortue tortue = new Tortue();

        int longueur = 90;

        for ( int i=1; i<=5; i++ ){

            for ( int j=1; j<=4; j++ ){

                tortue.avancer( longueur );
                tortue.tournerADroite( 90 );

            };

            longueur -= 10;

        };

    };

};