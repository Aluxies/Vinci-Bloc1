public class PyramideV2 {

    public static void main(String[] args) {

        int compteurChiffres = 1;
        int compteurEspaces = 3;

        for ( int i=1; i<=4; i++ ){

            for ( int j=1; j<=compteurEspaces; j++ ){

                System.out.print( ' ' );

            }

            int compteur = 0;

            for ( int k=1; k<=compteurChiffres; k++ ){

                if ( k <= ( compteurChiffres + 1 ) / 2 ) compteur ++;
                else compteur --;

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