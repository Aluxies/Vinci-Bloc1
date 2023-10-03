public class DessinEtoileContours {

    public static Tortue tortue = new Tortue();

    public static void main(String args[]) {

        tortue.accelerer();

        for ( int i = 0; i<6; i++ ){

            tortue.tournerADroite(120 );
            tortue.avancer(50 );
            tortue.tournerAGauche( 60 );
            tortue.avancer( 50 );


        };

    };

};