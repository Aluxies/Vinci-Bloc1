public class Pyramide {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print( "Entrez un nombre : " );
        int nombre = scanner.nextInt();

        int xCount = 1;
        int whiteCount = nombre;

        for ( int i=1; i<=nombre; i++ ){

            for ( int j=0; j<whiteCount; j++ ){

                System.out.print( " " );
            }

            for ( int k=0; k<xCount; k++ ){

                System.out.print( "X" );
            }

            for ( int l=0; l<whiteCount; l++ ){

                System.out.print( " " );

            };

            xCount += 2;
            whiteCount -= 1;

            System.out.println();

        };

    };

};