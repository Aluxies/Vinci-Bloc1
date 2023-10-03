public class DessinCarresEmboites {

    public static void main(String[] args) {

        Tortue tortue = new Tortue();

        tortue.accelerer();

        int longueur = 100;

        for ( int i=0; i<5; i++ ){

            tortue.dessinerUnCarre( longueur );

            longueur -= 10;

        }

    }

}