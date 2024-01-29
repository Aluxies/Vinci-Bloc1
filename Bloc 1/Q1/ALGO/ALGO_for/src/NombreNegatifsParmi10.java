public class NombreNegatifsParmi10 {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        int compteurNombreNegatifs = 0;

        System.out.println( "Vous allez devoir entrer 10 entiers positifs ou négatifs !\n\n" );

        for ( int i = 1; i<=10; i++ ){

            System.out.println( "Entier " + i + " :" );
            int entier = scanner.nextInt();

            if ( entier < 0 ) compteurNombreNegatifs ++;

        };

        System.out.println( "Il y a " + compteurNombreNegatifs + " nombre(s) négatif(s)" );
    };

};
