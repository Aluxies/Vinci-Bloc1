package entreprise;
import utils.Application;
import utils.BCrypt;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuEntreprise {
    public static int idEntreprise;

    public static void renderMenu() throws InterruptedException, SQLException {

        Connection connection = SqlConnectionEntreprise.create();

        Scanner scannerNumero = new Scanner(System.in);

        SqlQueriesEntreprise queriesEntreprise = new SqlQueriesEntreprise(connection);

        System.out.println("Bienvenue sur l'application entreprise !\n");

        boolean loginSuccessful = false;

        System.out.println("Connectez-vous");

        do {

            System.out.println("Entrez votre identifiant: ");
            String identifiant = scannerNumero.nextLine();

            System.out.println("Entrez votre mot de passe: ");
            String mdp = scannerNumero.nextLine();

            try {
                PreparedStatement ps = connection.prepareStatement("SELECT identifiant,mdp,nom,id_entreprise FROM projet.entreprises WHERE identifiant = ? ");
                ps.setString(1, identifiant);
                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        if (BCrypt.checkpw(mdp, rs.getString(2))) {

                            loginSuccessful = true;

                            idEntreprise = rs.getInt(4);

                            System.out.println("Bienvenue " + rs.getString(3));

                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("erreur");
                e.printStackTrace();
            }

            if (!loginSuccessful){
                System.out.println("Identifiant ou mot de passe incorrect.\n");
            }

        } while (!loginSuccessful);

        int select;

        do {

            TimeUnit.SECONDS.sleep(3);

            System.out.println("Voici les différentes opérations disponibles :\n");

            System.out.println(
                    "\tN°1 - Encoder une offre de stage\n" +
                            "\tN°2 - Voir les mots clés disponibles pour décrire une offre de stage\n" +
                            "\tN°3 - Ajouter un mot clé a une offre de stage\n" +
                            "\tN°4 - Voir les offres de stage\n" +
                            "\tN°5 - Voir les candidatures pour une offre de stage\n" +
                            "\tN°6 - Selectionner un étudiant pour une offre de stage\n" +
                            "\tN°7 - Annuler une offre de stage\n" +
                            "\tN°8 - Fermer l'application\n");

            System.out.print("Entrez le numéro de l'opération : ");
            select = scannerNumero.nextInt();

            switch (select) {
                case 1:
                    encoderOffreStage(queriesEntreprise);
                    break;
                case 2:
                    voirMotsClesDisponibles(queriesEntreprise);
                    break;
                case 3:
                    ajoutermotcleaoffrestage(queriesEntreprise);
                    break;
                case 4:
                    voirSesOffresStage(queriesEntreprise);
                    break;
                case 5:
                    voirCandidaturesPourOffreStage(queriesEntreprise);
                    break;
                case 6:
                    selectionnerEtudiantPourStage(queriesEntreprise);
                    break;
                case 7:
                    annulerOffreStage(queriesEntreprise);
                    break;
                default:
                    System.out.println("Fermeture de l'application...");
                    TimeUnit.SECONDS.sleep(1);
                    connection.close();
                    System.out.println("Connexion terminée");
                    Application.choose();
                    break;
            }

        } while (select >= 1 && select <= 7);

    }

    /* 1. Encoder Offre de stage */
    public static void encoderOffreStage(SqlQueriesEntreprise queriesEntreprise) throws SQLException {

        Scanner scannerParametres = new Scanner(System.in);

        System.out.println("Encoder une Offre de Stage");

        System.out.println("Veuillez entrer la description du stage: ");

        String description = scannerParametres.nextLine();

        System.out.println("Veuillez entrer le semestre du stage: ");

        String semestre = scannerParametres.nextLine();

        queriesEntreprise.encoderOffreStage(idEntreprise,description,semestre);
    }

    /* 2. Voir les mots clés disponibles */
    public static void voirMotsClesDisponibles(SqlQueriesEntreprise queriesEntreprise){

        queriesEntreprise.voirMotsClesDisponibles();

    }

    /* 3. Ajouter un mot clé a l'offre de stage */
    public static void ajoutermotcleaoffrestage(SqlQueriesEntreprise queriesEntreprise) throws SQLException{

        Scanner scannerParametres = new Scanner(System.in);

        System.out.println("Ajoutez une mot clé a l'offre de stage");

        System.out.println("Entrez le mot clé pour le stage");
        String motCle = scannerParametres.nextLine();

        System.out.println("Entrez le code de l'offre de stage");
        String codeOffre = scannerParametres.nextLine();

        queriesEntreprise.ajoutermotcleaoffrestage(idEntreprise,motCle,codeOffre);


    }

    public static void voirSesOffresStage(SqlQueriesEntreprise queriesEntreprise) throws SQLException {

        queriesEntreprise.voirSesOffresStage(idEntreprise);

    }

    /* 5. Voir les candidatures pour offre de stage */
    public static void voirCandidaturesPourOffreStage(SqlQueriesEntreprise queriesEntreprise) throws SQLException {
        Scanner scannerParametres = new Scanner(System.in);

        System.out.println("Entrez le code pour lequel vous souhaitez voir les candidatures: ");
        String codeOffre = scannerParametres.nextLine();

        queriesEntreprise.voirCandidaturesPourOffreStage(idEntreprise, codeOffre);
    }

    /* 6. Selectionner un étudiant pour un stage */
    public static void selectionnerEtudiantPourStage(SqlQueriesEntreprise queriesEntreprise) throws SQLException {

        Scanner scannerParametres = new Scanner(System.in);

        System.out.println("Entrez le code de l'offre pour lequel vous voulez selectionner un étudiant");
        String codeStage = scannerParametres.nextLine();

        System.out.println("Entrez l'email de l'etudiant ");
        String emailEtudiant = scannerParametres.nextLine();

        queriesEntreprise.selectionnerEtudiantPourStage(codeStage,emailEtudiant,idEntreprise);
    }

    /* 7. Annuler une offre de stage en donnant son code. */

    public static void annulerOffreStage(SqlQueriesEntreprise queriesEntreprise) throws SQLException{

        Scanner scannerParametres = new Scanner(System.in);

        System.out.println("Entrez le code de l'offre pour lequel vous voulez annuler");
        String codeStage = scannerParametres.nextLine();

        queriesEntreprise.annulerOffreStage(codeStage,idEntreprise);
    }


}
