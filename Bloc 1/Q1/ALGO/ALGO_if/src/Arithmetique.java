public class Arithmetique {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        int choixOperation = unEntierAuHasardEntre(0, 3);

        String signeOperation = "signeOperation";

        int resultat = 0;

        int entier1 = 0;
        int entier2 = 0;

        if ( choixOperation == 0 ){

            entier1 = unEntierAuHasardEntre( 1, 100 );
            entier2 = unEntierAuHasardEntre( entier2, 100 );

            signeOperation = "-";
            resultat = entier1 - entier2;

        };

        if ( choixOperation == 1 ){

            entier1 = unEntierAuHasardEntre( 1, 99 );
            entier2 = unEntierAuHasardEntre( 1, entier1 );

            signeOperation = "+";
            resultat = entier1 + entier2;

        };

        if ( choixOperation == 2 ){

            entier1 = unEntierAuHasardEntre( 1, 100 );
            entier2 = unEntierAuHasardEntre( 1, entier1 );

            signeOperation = "/";
            resultat = entier1 / entier2;

        };

        if ( choixOperation == 3 ){

            entier1 = unEntierAuHasardEntre( 1, 10 );
            entier2 = unEntierAuHasardEntre( 1, 10 );

            signeOperation = "x";
            resultat = entier1 * entier2;

        };

        System.out.println( entier1 + " " + signeOperation + " " + entier2 );

        System.out.println( "Entrez le résultat de ce calcul : " );
        int reponse = scanner.nextInt();

        if ( reponse == resultat ) System.out.println( "Bravo !!" );
        else System.out.println( "Hé non, le resultat était : " + resultat);

    };

    public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){

        double nombreReel;
        int resultat;

        nombreReel = Math.random();
        resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
        return resultat;

    };

};
