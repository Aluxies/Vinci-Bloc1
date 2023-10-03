public class SommeCinqEntiers {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        int somme = 0;

        System.out.println( "Vous allez devoir entrer 5 entiers positifs ou négatifs !\n\n" );

        for ( int i = 1; i<=5; i++ ){

            System.out.println( "Entier " + i + " :" );
            int entier = scanner.nextInt();

            somme += entier;

        };

        System.out.println( "La somme des 5 entiers est égale à " + somme );
    }
}
