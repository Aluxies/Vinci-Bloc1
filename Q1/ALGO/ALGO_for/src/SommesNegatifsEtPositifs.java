public class SommesNegatifsEtPositifs {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        System.out.println( "Vous allez devoir entrer 5 entiers négatifs ou positifs" );

        int sommePositive = 0;
        int sommeNegative = 0;

        for ( int i = 1; i<=5; i++ ){

            System.out.println( "Entrez l'entier " + i + " : " );
            int entier = scanner.nextInt();

            if ( entier > 0 ){

                sommePositive += entier;

            } else {

                sommeNegative += entier;

            };

        };

        System.out.println( "La somme des entiers positifs est égale à : " + sommePositive );
        System.out.println( "La somme des entiers négatifs est égale à : " + sommeNegative );

    };

};
