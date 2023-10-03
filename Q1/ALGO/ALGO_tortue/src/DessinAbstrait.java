public class DessinAbstrait {

    public static Tortue tortue = new Tortue();

    public static void main(String args[]) {

        tortue.accelerer();

        for ( int i = 0; i<5; i++ ){

            tortue.avancer( 50 );
            tortue.tournerAGauche( 30 );
            tortue.avancer( 50 );
            tortue.tournerADroite( 102 );

        };

    };

};
