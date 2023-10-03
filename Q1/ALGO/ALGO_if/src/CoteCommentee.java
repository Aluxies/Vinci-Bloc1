public class CoteCommentee {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        System.out.print( "Entrez la cote de votre UE : " );
        double cote = scanner.nextDouble();

        if ( cote < 10 ){

            System.out.print( "L'étudiant n'a pas validé l'UE." );

        } else if ( cote < 14){

            System.out.print( "L'étudiant a validé l'UE." );

        } else if ( cote < 16){

            System.out.print( "L'étudiant a validé l'UE avec une belle cote." );

        } else System.out.print( "L'étudiant a validé l'UE avec une très belle cote." );

    }
}
