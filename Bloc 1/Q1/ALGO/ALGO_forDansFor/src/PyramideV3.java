public class PyramideV3 {

    public static void main(String[] args) {

        int compteurChiffres = 1;
        int compteurEspaces = 3;

        for ( int i=1; i<=4; i++ ){

            for ( int j=1; j<=compteurEspaces; j++ ){

                System.out.print( ' ' );

            }

            int compteur = 6 + (i - 1);

            for ( int k=1; k<=compteurChiffres; k++ ){

                if ( k <= ( compteurChiffres + 1 ) / 2 ) {

                    compteur ++;
                    if ( compteur == 10 ) compteur = 0;

                } else {

                    compteur --;
                    if ( compteur == -1 ) compteur = 9;
                }

                System.out.print( compteur );

            }

            for ( int j=1; j<=compteurEspaces; j++ ){

                System.out.print( ' ' );

            }

            compteurChiffres += 2;
            compteurEspaces--;
            System.out.println();

        }

    }

}