public class Deliberation {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        int nombreEtudiants = 25;
        int nombreCotes = 10;

        System.out.println( "Il y a " + nombreEtudiants + " étudiants dans une classe." );
        System.out.println( "Vous allez devoir entrer " + nombreCotes + " cotes par étudiant.\n" );


        double moyenneClasse = boucleSurLesEtudiants( nombreEtudiants, nombreCotes );

        System.out.println( "La moyenne de la classe est de : " + moyenneClasse );

    }

    /**
     * boucle sur un certain nombre d'étudiants pour lire un certain nombre de cotes par étudiant
     * @param nombreEtudiants : nombre entier
     * @param nombreCotes : nombre entier
     * @return moyenne d'une classe : nombre entier
     */
    public static double boucleSurLesEtudiants( int nombreEtudiants, int nombreCotes ){

        double moyenneClasse = 0;

        for ( int i=1; i<=nombreEtudiants; i++ ){

            System.out.println( "\nEtudiant n°" + i + "\n" );

            moyenneClasse += boucleSurLesCotes( nombreCotes );

        }

        moyenneClasse /= nombreEtudiants;

        return moyenneClasse;

    }

    /**
     * Lit 10 cotes comprises entre 0 et 20
     * Ajoute chaque cote à la moyenne
     * Divise la moyenne par le nombre de cotes
     * @param nombreCotes : nombre entier
     * @return la moyenne de l'étudiant : nombre réel
     */
    public static double boucleSurLesCotes( int nombreCotes ){

        double moyenneEtudiant = 0;

        for ( int i=1; i<=nombreCotes; i++ ){

            System.out.println( "Cote n°" + i + "/" + nombreCotes );
            System.out.print( "Entrez une cote sur 20 : " );
            double cote = Utilitaires.lireReelComprisEntre( 0, 20 );

            moyenneEtudiant += cote;

        }

        moyenneEtudiant /= nombreCotes;

        System.out.println( "\nMoyenne de l'étudiant : " + moyenneEtudiant );

        return moyenneEtudiant;

    }

}
