public class DessinLunettes {

    public static void main(String[] args) {

        Tortue tortue = new Tortue();

        tortue.accelerer();

        tortue.dessinerUnCarre( 100 );

        tortue.tournerAGauche( 180 );
        tortue.avancer( 50 );
        tortue.tournerAGauche( 90 );

        tortue.dessinerUnCarre( 100 );

    }

}