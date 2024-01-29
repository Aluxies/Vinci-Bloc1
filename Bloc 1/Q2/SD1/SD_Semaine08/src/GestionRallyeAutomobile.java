import java.util.Scanner;

public class GestionRallyeAutomobile {

    private static Scanner scanner = new Scanner(System.in);
    private static RallyeAutomobile rallyeAutomobile;

    public static void main(String[] args) {

        System.out.println("**************************************");
        System.out.println("Programme Test pour la classe RallyeAutomobile");
        System.out.println("**************************************");

        System.out.print("Entrez le nombre de pilotes : ");

        int nombrePilotes = scanner.nextInt();

        String[] pilotes = new String[nombrePilotes];

        for (int i = 0; i < nombrePilotes; i++) {

            System.out.print("Entrez le nom du pilote " + (i+1) + " : " );

            pilotes[i] = scanner.next();

        }

        rallyeAutomobile = new RallyeAutomobile(pilotes);

        int choix;
        do {
            System.out.println("1 -> Afficher toute la course");
            System.out.println("2 -> Afficher le pilote en tête");
            System.out.println("3 -> Enregistrer un dépassement");
            System.out.println("4 -> Retirer un pilote de la course");
            System.out.println("5 -> Donner la position d'un pilote (encore dans la course)");
            System.out.println("6 -> Faire franchir la ligne d'arrivée au pilote de tête");
            System.out.println("7 -> Remettre un pilote dans la course (après un autre pilote)");
            System.out.println("8 -> Afficher les pilotes hors course");
            System.out.println("9 -> Afficher le classement");
            System.out.println();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    afficherCourse();
                    break;
                case 2:
                    afficherPiloteEnTete();
                    break;
                case 3:
                    depassement();
                    break;
                case 4:
                    disqualifierPilote();
                    break;
                case 5:
                    positionPilote();
                    break;
                case 6:
                    franchirLigneDArrivee();
                    break;
                case 7:
                    remettreEnCourse();
                    break;
                case 8:
                    afficherPilotesHorsCourse();
                    break;
                case 9:
                    afficherClassement();
                    break;
                default:
                    break;
            }
        } while (choix >= 1 && choix <= 9 );

    }

    private static void afficherCourse() {

        System.out.println( rallyeAutomobile.afficherCourse() );

    }

    private static void afficherPiloteEnTete() {

        System.out.println( rallyeAutomobile.donnerPiloteEnTete() );

    }

    private static void depassement() {

        System.out.print( "Entrez le pilote qui dépasse : " );
        String pilote = scanner.next();

        boolean resultat = rallyeAutomobile.effectuerDepassement( pilote );

        if ( resultat ) System.out.println( "Le pilote " + pilote + " a effectué un dépassement" );
        else System.out.println( "Le pilote " + pilote + " n'a pas pu effectuer de dépassement" );

    }

    private static void disqualifierPilote() {

        System.out.print( "Entrez le pilote à retirer : " );
        String pilote = scanner.next();

        boolean resultat = rallyeAutomobile.disqualifierPilote( pilote );

        if ( resultat ) System.out.println( "Le pilote " + pilote + " a été disqualifié" );
        else System.out.println( "Le pilote " + pilote + " n'a pas pu être disqualifié" );

    }

    private static void positionPilote() {

        System.out.print( "Entrez un pilote : " );
        String pilote = scanner.next();

        int positionPilote = rallyeAutomobile.donnerPositionPilote( pilote );

        if ( positionPilote == -1 ) System.out.println( "Le pilote " + pilote + " ne fait pas partie de la course" );
        else System.out.println( "Le pilote " + pilote + " est en " + positionPilote + "e position" );

    }

    private static void franchirLigneDArrivee(){

        boolean resultat = rallyeAutomobile.franchirLigneDArrivee();

        if ( resultat ) System.out.println( "Le pilote en tête a bien franchi la ligne d'arrivée");
        else System.out.println( "Il n'y a plus de pilotes dans la course" );

    }

    private static void remettreEnCourse(){

        System.out.print( "Entrez le pilote à remettre en course : " );

        String pilote = scanner.next();

        System.out.print( "Entrez le pilote après lequel vous remettez en course : " );

        String pilotePrecedant = scanner.next();

        boolean resultat = rallyeAutomobile.remettreEnCourse( pilote, pilotePrecedant );

        if ( resultat ) System.out.println( "Le pilote " + pilote + " a été remis en course après le pilote " + pilotePrecedant );
        else System.out.println( "Le pilote n'a pas pu être remis en course" );

    }

    private static void afficherPilotesHorsCourse(){

        System.out.println( rallyeAutomobile.afficherPilotesHorsCourse() );

    }

    private static void afficherClassement(){

        System.out.println( rallyeAutomobile.afficherClassement() );

    }

}