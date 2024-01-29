public class RecitationTableMultiplication {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Choisir au hasard un entier entre 1 et 10

        int nombreTable = unEntierAuHasardEntre (0, 10);

        System.out.print( "\nVous allez devoir donner la table de multiplication de " + nombreTable );

        int compteur = 0;
        int reponse = 0 * nombreTable;

        do {

            // Présenter les multiplications à réaliser

            compteur++;

            System.out.print( "\n" + compteur + "x" + nombreTable + " = " );
            reponse = scanner.nextInt();

        } while ( reponse == ( compteur * nombreTable ) && compteur < 10 );


        if ( ( compteur * nombreTable ) == reponse && compteur == 10 ){

            System.out.print( "\nFélicitation !" );

        } else {

            // L'élève se trompe, le programme donne la bonne réponse
            System.out.print( "\nC'est faux !\nLa bonne réponse est " + ( compteur * nombreTable ) );

        };

    };

    public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){

        double nombreReel;
        int resultat;

        nombreReel = Math.random();
        resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
        return resultat;

    };

};