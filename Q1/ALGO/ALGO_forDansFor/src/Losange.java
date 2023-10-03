public class Losange {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print( "Entrez un nombre : " );
        int nombre = scanner.nextInt();

        int xCount = nombre;
        int whiteCount = 0;

        for ( int i=1; i<=nombre*2; i++ ){

            for ( int j=0; j<xCount; j++ ){

                System.out.print( "X" );

            }

            for ( int k=0; k<whiteCount; k++ ){

                System.out.print( " " );

            }

            for ( int l=0; l<xCount; l++ ){

                System.out.print( "X" );

            }

            if ( i <= nombre - 1 ){

                xCount -= 1;
                whiteCount += 2;

            } else if ( i >= nombre + 1 ){

                xCount += 1;
                whiteCount -= 2;

            }

            System.out.println();

        }

    }

}