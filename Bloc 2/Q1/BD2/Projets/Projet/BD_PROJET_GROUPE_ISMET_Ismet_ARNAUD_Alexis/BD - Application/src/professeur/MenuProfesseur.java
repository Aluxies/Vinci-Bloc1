package professeur;

import utils.Application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuProfesseur {

    public static void renderMenu() throws InterruptedException, SQLException {

        Connection connection = SqlConnectionProfesseur.create();

        professeur.SqlQueriesProfesseur queriesProfesseur = new professeur.SqlQueriesProfesseur( connection );

        Scanner scannerNumero;

        System.out.println( "Bienvenue sur l'application professeur !\n" );

        int select;

        do {

            scannerNumero = new Scanner( System.in );

            TimeUnit.SECONDS.sleep( 3 );

            System.out.println( "Voici les différentes opérations disponibles :\n" );

            System.out.println(
                    "\tN°1 - Encoder un étudiant\n" +
                            "\tN°2 - Encoder une entreprise\n" +
                            "\tN°3 - Encoder un mot-clé à proposer pour un stage\n" +
                            "\tN°4 - Voir les offres de stage non validées\n" +
                            "\tN°5 - Valider une offre de stage \n" +
                            "\tN°6 - Voir les offres de stage validées\n" +
                            "\tN°7 - Voir les étudiants sans stage\n" +
                            "\tN°8 - Voir les offres de stage attribuées\n" +
                            "\tN°9 - Fermer l'application\n" );

            System.out.print( "Entrez le numéro de l'opération : " );
            select = scannerNumero.nextInt();

            switch ( select ) {
                case 1:
                    encoderEtudiant( queriesProfesseur );
                    break;
                case 2:
                    encoderEntreprise( queriesProfesseur );
                    break;
                case 3:
                    encoderMotClePropose( queriesProfesseur );
                    break;
                case 4:
                    voirOffresStagesNonValidees( queriesProfesseur );
                    break;
                case 5:
                    validerOffreStage( queriesProfesseur );
                    break;
                case 6:
                    voirOffresStagesValidees( queriesProfesseur );
                    break;
                case 7:
                    voirEtudiantsSansStage( queriesProfesseur );
                    break;
                case 8:
                    voirOffresStagesAttribuees( queriesProfesseur );
                    break;
                default:
                    System.out.println( "Fermeture de l'application..." );
                    TimeUnit.SECONDS.sleep( 1 );
                    connection.close();
                    System.out.println( "Connexion terminée" );
                    Application.choose();
                    break;
            }

        } while ( select >= 1 && select <= 8 );

    }

    public static void encoderEtudiant( SqlQueriesProfesseur queriesProfesseur ) {

        Scanner scannerParametres = new Scanner( System.in );

        System.out.println( "Encoder un étudiant :" );

        System.out.print( "Veuillez entrer le nom de l'étudiant : " );

        String nom = scannerParametres.nextLine();

        System.out.print( "Veuillez entrer le prénom de l'étudiant : " );

        String prenom = scannerParametres.nextLine();

        System.out.print( "Veuillez entrer l'email de l'étudiant : " );

        String email = scannerParametres.nextLine();

        System.out.print( "Veuillez entrer le mot de passe de l'étudiant : " );

        String motDePasse = scannerParametres.nextLine();

        String sel = utils.BCrypt.gensalt();
        String motDePasseAStocker =
                utils.BCrypt.hashpw(motDePasse, sel);

        System.out.print( "Veuillez entrer le semestre de l'étudiant : " );

        String semestre = scannerParametres.nextLine();

        queriesProfesseur.encoderEtudiant( nom, prenom, email, motDePasseAStocker, semestre );

    }

    public static void encoderEntreprise( SqlQueriesProfesseur queriesProfesseur ) {

        Scanner scannerParametres = new Scanner( System.in );

        System.out.println( "Encoder une entreprise :" );

        System.out.print( "Veuillez entrer l'identifiant de l'entreprise (3 CHAR.) : " );

        String identifiant = scannerParametres.nextLine();

        System.out.print( "Veuillez entrer le nom de l'entreprise : " );

        String nom = scannerParametres.nextLine();

        System.out.print( "Veuillez entrer l'adresse de l'entreprise : " );

        String adresse = scannerParametres.nextLine();

        System.out.print( "Veuillez entrer l'email de l'entreprise : " );

        String email = scannerParametres.nextLine();

        System.out.print( "Veuillez entrer le mot de passe de l'entreprise : " );

        String motDePasse = scannerParametres.nextLine();

        String sel = utils.BCrypt.gensalt();
        String motDePasseAStocker =
                utils.BCrypt.hashpw(motDePasse, sel);

        queriesProfesseur.encoderEntreprise( identifiant, nom, adresse, email, motDePasseAStocker );

    }

    public static void encoderMotClePropose( SqlQueriesProfesseur queriesProfesseur ) {

        Scanner scannerParametres = new Scanner( System.in );

        System.out.println( "Encoder un mot-clé :" );

        System.out.print( "Veuillez entrer le mot-clé à proposer : " );

        String motClePropose = scannerParametres.nextLine();

        queriesProfesseur.encoderMotClePropose( motClePropose );

    }

    public static void voirOffresStagesNonValidees( SqlQueriesProfesseur queriesProfesseur ) {

        queriesProfesseur.voirOffresStagesNonValidees();

    }

    public static void validerOffreStage( SqlQueriesProfesseur queriesProfesseur ) {

        Scanner scannerParametres = new Scanner( System.in );

        System.out.println( "Valider une offre de stage :" );

        System.out.print( "Veuillez entrer le code de l'offre : " );

        String codeOffre = scannerParametres.nextLine();

        queriesProfesseur.validerOffreStage( codeOffre );

    }

    public static void voirOffresStagesValidees( SqlQueriesProfesseur queriesProfesseur ) {

        queriesProfesseur.voirOffresStagesValidees();

    }

    public static void voirEtudiantsSansStage( SqlQueriesProfesseur queriesProfesseur ) {

        queriesProfesseur.voirEtudiantsSansStage();

    }

    public static void voirOffresStagesAttribuees( SqlQueriesProfesseur queriesProfesseur ) {

        queriesProfesseur.voirOffresStagesAttribuees();
    }

}
