public class Carre {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print( "Entrez un nombre : " );
        int nombre = scanner.nextInt();

        for ( int i=1; i<=nombre; i++ ){

            for ( int j=1; j<=nombre; j++ ){

                System.out.print( "X" );

            };

            System.out.println();

        };

    };

};