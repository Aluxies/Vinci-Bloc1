package etudiant;

import utils.Application;
import utils.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuEtudiant {
    public static int idEtudiant;
    public static String semestre;

    public static void renderMenu() throws SQLException, InterruptedException {

        Connection connection = SqlConnectionEtudiant.create();

        Scanner scannerNumero = new Scanner(System.in);

        SqlQueriesEtudiant queriesEtudiant = new SqlQueriesEtudiant(connection);

        System.out.println("Bienvenue sur l'application étudiant! \n");

        boolean loginSuccessful = false;

        do {

            System.out.println("Connectez-vous");

            System.out.println("Entrez votre email: ");
            String email = scannerNumero.nextLine();
            System.out.println("Entrez votre mot de passe: ");
            String mdp = scannerNumero.nextLine();

            try {
                PreparedStatement ps= connection.prepareStatement("SELECT email,mdp,id_etudiant,prenom,semestre FROM projet.etudiants WHERE email = ? ");
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        if (BCrypt.checkpw(mdp, rs.getString(2))) {

                            loginSuccessful = true;



                            idEtudiant = rs.getInt(3);

                            semestre = rs.getString(5);

                            System.out.println("Bienvenue " + rs.getString(4));


                        }
                    }
                }
            } catch (SQLException e){
                System.out.println("erreur");
                e.printStackTrace();
            }

            if (!loginSuccessful){
                System.out.println("Email ou mot de passe incorrect.\n");
            }

        }while (!loginSuccessful);


        int select;

        do {

            TimeUnit.SECONDS.sleep( 3 );

            System.out.println();

            System.out.println("Voici les différentes opérations disponibles :\n");

            System.out.println(
                    "\tN°1 - Voir toutes les offres de stage dans l’état « validée »\n" +
                            "\tN°2 - Recherche d’une offre de stage par mot clé.\n" +
                            "\tN°3 - Poser sa candidature.\n" +
                            "\tN°4 - Voir les offres de stage pour lesquels vous avez posé votre candidature.\n" +
                            "\tN°5 - Annuler une candidature\n" +
                            "\tN°6 - Fermer l'application\n");

            System.out.print("Entrez le numéro de l'opération : ");
            select = scannerNumero.nextInt();

            switch (select) {
                case 1:
                    voirOffresStagesValideesParSemestre(queriesEtudiant);
                    break;
                case 2:
                    rechercherOffreStageParMC(queriesEtudiant);
                    break;
                case 3:
                    poserCandidature(queriesEtudiant);
                    break;
                case 4:
                    voirOffresStageEtudiantAvecCandidature(queriesEtudiant);
                    break;
                case 5:
                    annulerCandidature(queriesEtudiant);
                    break;
                default:
                    System.out.println( "Fermeture de l'application..." );
                    TimeUnit.SECONDS.sleep( 1 );
                    connection.close();
                    System.out.println( "Connexion terminée" );
                    Application.choose();
                    break;
            }

        } while (select >= 1 && select <= 5);
    }

    public static void voirOffresStagesValideesParSemestre(SqlQueriesEtudiant queriesEtudiant) throws SQLException {

        queriesEtudiant.voirOffresStagesValideesParSemestre(semestre);

    }

    public static void rechercherOffreStageParMC(SqlQueriesEtudiant queriesEtudiant) throws SQLException {

        Scanner scannerParametres = new Scanner(System.in);

        System.out.println("Entrez la mot clé");
        String motCle = scannerParametres.nextLine();

        queriesEtudiant.rechercherOffreStageParMC(motCle);

    }

    public static void poserCandidature(SqlQueriesEtudiant queriesEtudiant) throws SQLException {

        Scanner scannerParametres = new Scanner(System.in);

        System.out.println("Entrez le code de l'offre: ");
        String code = scannerParametres.nextLine();

        System.out.println("Entrez votre motivation: ");
        String motivation = scannerParametres.nextLine();

        queriesEtudiant.poserCandidature(code,motivation,idEtudiant);

    }

    public static void voirOffresStageEtudiantAvecCandidature(SqlQueriesEtudiant queriesEtudiant) throws SQLException {

        queriesEtudiant.voirOffresStageEtudiantAvecCandidature(idEtudiant);

    }

    public static void annulerCandidature(SqlQueriesEtudiant queriesEtudiant){

        Scanner scannerParametres = new Scanner(System.in);

        System.out.println("Entrez le code de l'offre pour lequel vous voulez annuler votre candidature: ");
        String code = scannerParametres.nextLine();

        queriesEtudiant.annulerCandidature(code,idEtudiant);


    }
}
