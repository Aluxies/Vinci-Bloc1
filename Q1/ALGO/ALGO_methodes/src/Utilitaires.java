public class Utilitaires {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    /**
     * genere un entier compris entre les 2 valeurs passees en parametre
     * @param valeurMinimale la valeur minimale de l'entier genere
     * @param valeurMaximale la valeur maximale de l'entier genere
     * @return l'entier genere
     */
    public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){
        return (int) (Math.random() * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
    }

    /**
     * lit un nombre reel positif ou nul
     * @return un nombre reel positif ou nul
     */
    public static double lireReelPositif(){
        double reel = scanner.nextDouble();
        while(reel < 0){
            System.out.println("Attention le reel doit etre positif");
            System.out.print("Recommencez : ");
            reel = scanner.nextDouble();
        }
        return reel;
    }

    /**
     * lit un nombre reel strictement positif
     * @return un nombre reel strictement positif
     */
    public static double lireReelStrictementPositif(){
        double reel = scanner.nextDouble();
        while(reel <= 0){
            System.out.println("Attention le reel doit etre strictement positif");
            System.out.print("Recommencez : ");
            reel = scanner.nextDouble();
        }
        return reel;
    }

    /**
     * lit un entier positif ou nul
     * @return un entier positif ou nul
     */
    public static int lireEntierPositif(){
        int entier = scanner.nextInt();
        while(entier < 0){
            System.out.println("Attention l'entier doit etre positif");
            System.out.print("Recommencez : ");
            entier = scanner.nextInt();
        }
        return entier;
    }

    /**
     * lit un entier strictement positif
     * @return un entier strictement positif
     */
    public static int lireEntierStrictementPositif(){
        int entier = scanner.nextInt();
        while(entier <= 0){
            System.out.println("Attention l'entier doit etre strictement positif");
            System.out.print("Recommencez : ");
            entier = scanner.nextInt();
        }
        return entier;
    }

    /**
     * lit un nombre reel compris entre les 2 valeurs passees en parametre
     * @param min le plus petit reel accepte
     * @param max le plus grand reel accepte
     * @return un nombre reel compris entre 2 bornes
     */
    public static double lireReelComprisEntre( double min, double max ){

        double reel = scanner.nextDouble();

        while ( reel < min || reel > max ){

            System.out.println( "Attention, le réel doit être compris entre " + min + " et " + max );
            System.out.print( "Recommencez : ");

            reel = scanner.nextDouble();

        }

       return reel;

    }

    /**
     * lit un nombre entier compris entre les 2 valeurs passees en parametre
     * @param min le plus petit entier accepte
     * @param max le plus grand entier accepte
     * @return un nombre entier compris entre 2 bornes
     */
    public static int lireEntierComprisEntre( int min, int max ){

        int entier = scanner.nextInt();

        while ( entier < min || entier > max ){

            System.out.println( "Attention, l'entier doit être compris entre " + min + " et " + max );
            System.out.println( "Recommencez : " );

            entier = scanner.nextInt();

        }

        return entier;

    }


    /**
     * lit le caractere o ou n
     * @return le caractere o un n
     */
    public static char lireOouN(){

        char lettre = scanner.next().charAt( 0 );

        while ( lettre != 'n' && lettre != 'o'){

            System.out.println( "Attention, la réponse doit être 'o' ou 'n' !" );
            System.out.print( "Recommencez : " );

            lettre = scanner.next().charAt( 0 );

        }

        return lettre;

    }

    /**
     * lit un entier non nul
     * @return entier non nul
     */
    public static int lireEntierNonNul(){

        int entier = scanner.nextInt();

        while ( entier == 0 ){

            System.out.println( "Attention, l'entier ne peut être égal à 0 !" );
            System.out.print( "Recommencez : " );

            entier = scanner.nextInt();

        }

        return entier;

    }

    public static char lireCharParmis( String charateresParmis){

        char caractere = '´';

        boolean caractereTrouve = false;

        do {

            caractere = scanner.next().charAt(0);

            caractereTrouve = charateresParmis.contains( caractere );

        } while ( !caractereTrouve );

        return caractere;

    }

}