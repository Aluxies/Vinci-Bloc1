public class TriangleV2 {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print( "Entrez un nombre : " );
        int nombre = scanner.nextInt();

        int xCount = nombre;

        for ( int i=1; i<=nombre; i++ ){

            for ( int j=0; j<xCount; j++ ){

                System.out.print( "X" );

            };

            xCount --;
            System.out.println();

        };

    };

};