public class NombreMystere {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        int nombreMystere = unEntierAuHasardEntre( 1, 100 );
        int reponse = -1;

        int compteurEssais = 0;

        do {

            compteurEssais ++;

            System.out.print( "\nEssayez de deviner le nombre mystère ( 1 -> 100 ) : " );

            reponse = scanner.nextInt();

            if ( reponse < nombreMystere ){

                System.out.print( "\nLe nombre mystère est plus grand que " + reponse );

            } else if ( reponse > nombreMystere ){

                System.out.print( "\nLe nombre mystère est plus petit que " + reponse );

            };

        } while ( reponse != nombreMystere );


        if ( reponse == nombreMystere ){

            System.out.print( "\nBravo, vous avez trouvé le nombre mystère après "+ compteurEssais + " essai(s) !\n\nLe nombre mystère est bien " + nombreMystere + " !" );

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