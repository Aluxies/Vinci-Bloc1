public class CoteCommenteeV2 {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main (String[] args) {

        System.out.print("Veuillez entrer la cote : ");
        double cote = scanner.nextDouble();

        String commentaire = donnerCommentaire( cote );

        System.out.println( commentaire );

    }

    public static String donnerCommentaire( double cote ){

        if (cote < 10) return "L'etudiant n'a pas valide l'UE.";
        else {

            if (cote < 14) return "L'etudiant a valide l'UE.";

            else {

                if (cote < 16) return "L'etudiant a valide l'UE avec une belle cote.";
                else return "L'etudiant a valide l'UE avec une tres belle cote.";

            }

        }

    }

}