public class RechercheMax3V2 {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Entrez l'entier 1 : ");

        int entier1 = scanner.nextInt();

        System.out.println("Entrez l'entier 2 : ");

        int entier2 = scanner.nextInt();

        System.out.println("Entrez l'entier 3 : ");

        int entier3 = scanner.nextInt();


        int plusGrandEntier = max3( entier1, entier2, entier3 );

        System.out.println( "Le plus grand entier est " + plusGrandEntier );

    }

    public static int max3( int entier1, int entier2, int entier3 ){

        if ( entier1 > entier2 && entier1 > entier3 ){

            return entier1;

        } else {

            if ( entier2 > entier3 ){

                return entier2;

            } else return entier3;

        }

    }

}