public class CalculMoyenne {

    public static void main(String[] args) {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        char reponse = 'Y';

        int coteMax = 10;
        int somme = 0;
        int compteurDeCotes = 0;

        do {

            System.out.print( "\nEntrez une cote sur " + coteMax + " : ");

            int cote = scanner.nextInt();

            somme += cote;
            compteurDeCotes ++;

            System.out.print( "\nY a-t-il une autre cote ( [Y, y, O, o] pour continuer ) ? ");

            reponse = scanner.next().charAt( 0 );

        } while ( reponse == 'Y' || reponse == 'y' || reponse == 'O' || reponse == 'o' );

        int moyenne = somme / compteurDeCotes;

        System.out.print( "\nVotre moyenne est de " + moyenne + " / " + coteMax );

    };

};