public class NombreMystereInverse {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        int essaiProgramme = unEntierAuHasardEntre( 0, 100 );

        int valeurMinimale = 0;
        int valeurMaximale = 100;

        int compteurEssais = 0;

        char signe = 'a';

        Boolean continuerEssais = true;

        do {

            compteurEssais++;

            System.out.print( "\nTentative numéro " + compteurEssais + "\n\nJ'ai choisi le nombre : " + essaiProgramme + "\n\nVotre nombre est...\n\n- Plus grand ( + )\n- Plus petit ( - )\n- Égal ( = )\n\nVotre indice : " );

            signe = scanner.next().charAt( 0 );

            if ( signe == '+' || signe == '-' ){

                if ( valeurMaximale == valeurMinimale ){

                    System.out.print( "\nDésolé, mais je pense que vous m'induisez en erreur.\nJ'abandonne. ");

                    continuerEssais = false;

                } else {

                    if ( signe == '+' ){

                        valeurMinimale = essaiProgramme + 1;

                        System.out.print( "\nLe nombre que je dois trouver est plus grand que " + essaiProgramme );
                        essaiProgramme = unEntierAuHasardEntre( valeurMinimale , valeurMaximale );

                    };

                    if ( signe == '-' ){

                        valeurMaximale = essaiProgramme - 1;

                        System.out.print( "\nLe nombre que je dois trouver est plus petit que " + essaiProgramme );
                        essaiProgramme = unEntierAuHasardEntre( valeurMinimale , valeurMaximale );

                    };

                };

            };

        } while ( signe != '=' && continuerEssais );

        if ( signe == '=' ){

            System.out.print( "\n\nJ'ai réussi à trouver le nombre " + essaiProgramme + " au bout de " + compteurEssais + " essais !" );

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