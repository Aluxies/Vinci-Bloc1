public class SommeCentEntiers {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        int somme = 0;

        for ( int i = 0; i<100; i++ ){

            System.out.println( "Entrez l'entier " + i );
            int entier = scanner.nextInt();

            somme += entier;

        };

        System.out.println( "La somme de ces 5 entiers est " + somme );
    }

}
