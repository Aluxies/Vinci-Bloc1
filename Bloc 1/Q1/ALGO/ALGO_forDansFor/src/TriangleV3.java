public class TriangleV3 {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print( "Entrez un nombre : " );
        int nombre = scanner.nextInt();

        int xCount = nombre;
        int whiteCount = 0;

        for ( int i=1; i<=nombre; i++ ){

            for ( int k=0; k<whiteCount; k++){

                System.out.print( " " );

            };

            for ( int j=0; j<xCount; j++ ){

                System.out.print( "X" );

            };

            xCount --;
            whiteCount ++;
            System.out.println();

        };

    };

};