public class DessinVentilateur {

    public static void main(String[] args) {

        Tortue tortue = new Tortue();

        tortue.accelerer();

        for ( int i=0; i<3; i++ ){

            tortue.dessinerUnCarre( 100 );
            tortue.tournerAGauche( 120 );
        }

    }

}