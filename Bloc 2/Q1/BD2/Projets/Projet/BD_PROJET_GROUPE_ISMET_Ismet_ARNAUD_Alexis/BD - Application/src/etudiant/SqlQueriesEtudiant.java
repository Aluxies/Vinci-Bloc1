package etudiant;

import java.sql.*;

public class SqlQueriesEtudiant {

    private PreparedStatement voirOffresStagesValideesParSemestre;

    private PreparedStatement rechercherOffreStageParMC;

    private PreparedStatement poserCandidature;

    private PreparedStatement voirOffresStageEtudiantAvecCandidature;

    private PreparedStatement annulerCandidature;

    public SqlQueriesEtudiant(Connection connection) throws SQLException{

        try{

            this.voirOffresStagesValideesParSemestre = connection.prepareStatement("SELECT * FROM projet.voirOffresStagesValideesParSemestre WHERE semestre = ?");

            this.rechercherOffreStageParMC = connection.prepareStatement("SELECT ros.id_offre_stage, ros.code, ros.nom, ros.adresse, ros.description, ros.mots_cles_stage\n" +
                    "FROM projet.rechercherOffreStageParMC ros, projet.mots_cles_offres mco, projet.mots_cles_proposes mcp\n" +
                    "WHERE ros.id_offre_stage = mco.offre_stage\n" +
                    "    AND mco.mot_cle_propose = mcp.id_mot_cle_propose\n" +
                    "    AND mcp.mot_cle = ?");

            this.poserCandidature = connection.prepareStatement("SELECT * FROM projet.poserCandidature(?,?,?)");

            this.voirOffresStageEtudiantAvecCandidature = connection.prepareStatement("SELECT * FROM projet.voirOffresStageEtudiantAvecCandidature WHERE id_etudiant = ?");

            this.annulerCandidature = connection.prepareStatement("SELECT * FROM projet.annulerCandidature(?,?)");

        }catch ( SQLException e ) {

            System.out.println( "Erreur lors de l'initialisation des attributs." );

            e.printStackTrace();

            System.exit(1);

        }
    }

        /* 1. Voir offres stages */

    public void voirOffresStagesValideesParSemestre(String semestre) throws SQLException {

        System.out.println("Voici les offres de stages validées");

        voirOffresStagesValideesParSemestre.setString(1,semestre);

        try(ResultSet rs = voirOffresStagesValideesParSemestre.executeQuery()){

            ResultSetMetaData metaData = rs.getMetaData();

            String colonneCode = metaData.getColumnName(1);
            String colonneNom = metaData.getColumnName(2);
            String colonneAdresse = metaData.getColumnName(3);
            String colonneDescription = metaData.getColumnName(4);
            String colonneMotsCles = metaData.getColumnName(5);

            System.out.format("%10s %15s %15s %25s %25s\n", colonneCode,colonneNom,colonneAdresse,colonneDescription,colonneMotsCles);

            while (rs.next()){

                String code = rs.getString(1);
                String nom = rs.getString(2);
                String adresse = rs.getString(3);
                String description = rs.getString(4);
                String motsCles = rs.getString(5);

                System.out.format("%10s %15s %25s %15s %25s\n", code,nom,adresse,description,motsCles);

            }

        } catch (SQLException e) {
            System.out.println("Erreur survenue lors du visionnage des offres de stage.");
            e.printStackTrace();
        }



    }


    public void rechercherOffreStageParMC(String motCle) throws SQLException {

        System.out.println("Voici les offres de stages validées");

        rechercherOffreStageParMC.setString(1,motCle);

        try(ResultSet rs = rechercherOffreStageParMC.executeQuery()){

            ResultSetMetaData metaData = rs.getMetaData();

            String colonneIDOs = metaData.getColumnName(1);
            String colonneCode = metaData.getColumnName(2);
            String colonneNom = metaData.getColumnName(3);
            String colonneAdresse = metaData.getColumnName(4);
            String colonneDescription = metaData.getColumnName(5);
            String colonneMotsCles = metaData.getColumnName(6);

            System.out.format("%10s %15s %15s %20s %20s %20s\n",colonneIDOs, colonneCode,colonneNom,colonneAdresse,colonneDescription,colonneMotsCles);

            while (rs.next()){

                String idOs = rs.getString(1);
                String code = rs.getString(2);
                String nom = rs.getString(3);
                String adresse = rs.getString(4);
                String description = rs.getString(5);
                String motsCles = rs.getString(6);

                System.out.format("%10s %20s %15s %25s %15s %20s\n",idOs, code,nom,adresse,description,motsCles);

            }

        } catch (SQLException e) {
            System.out.println("Erreur survenue lors de la recherche des offres de stage.");
            e.printStackTrace();
        }

    }

    public void poserCandidature(String code, String motivation, int idEtudiant) throws SQLException {

        try {
            poserCandidature.setString(1,code);
            poserCandidature.setString(2,motivation);
            poserCandidature.setInt(3,idEtudiant);

            poserCandidature.execute();

            System.out.println("La candidature pour l'offre " + code + " a été posée");
        }catch (SQLException e){
            System.out.println( "Erreur survenue lors de l'encodage de la candidature.");
            e.printStackTrace();
        }
    }

    public void voirOffresStageEtudiantAvecCandidature(int idEtudiant) throws SQLException {

        System.out.println("Voici les offres de stages pour lequels vous avez posé votre candidature");

        voirOffresStageEtudiantAvecCandidature.setInt(1,idEtudiant);

        try(ResultSet rs = voirOffresStageEtudiantAvecCandidature.executeQuery()){

            ResultSetMetaData metaData = rs.getMetaData();

            String columOffreStage = metaData.getColumnName(1);
            String columNom = metaData.getColumnName(2);
            String columEtat= metaData.getColumnName(3);

            System.out.format( "%10s %10s %10s\n", columOffreStage, columNom,columEtat);

            while(rs.next()){

                String offreStage = rs.getString(1);
                String nom = rs.getString(2);
                String etat = rs.getString(3);

                System.out.format( "%5s %15s %15s\n", offreStage, nom,etat);


            }
        }catch (SQLException e) {

            System.out.println( "Erreur survenue lors du visionnage des offres de stage pour lequels vous avez posé votre candidature.");

            e.printStackTrace();

        }
    }

    public void annulerCandidature(String code, int idEtudiant) {

        try {
            annulerCandidature.setString(1,code);
            annulerCandidature.setInt(2,idEtudiant);

            annulerCandidature.execute();

            System.out.println("La candidature a été annulée");

        }catch (SQLException e){

            System.out.println( "Erreur survenue lors de l'annulation de la candidature.");
            e.printStackTrace();

        }
    }
}
