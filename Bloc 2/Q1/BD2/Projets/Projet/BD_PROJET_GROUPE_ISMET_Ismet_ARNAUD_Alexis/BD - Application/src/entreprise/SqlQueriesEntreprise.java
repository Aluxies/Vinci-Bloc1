package entreprise;

import java.sql.*;

public class SqlQueriesEntreprise {

    private PreparedStatement encoderOffreStage;
    private PreparedStatement voirMotsClesDisponibles;
    private PreparedStatement ajoutermotcleaoffrestage;
    private PreparedStatement voirSesOffresStage;
    private PreparedStatement voirCandidaturesPourOffreStage;
    private PreparedStatement selectionnerEtudiantPourStage;
    private PreparedStatement annulerOffreStage;

    public SqlQueriesEntreprise(Connection connection){

        try{

            this.encoderOffreStage = connection.prepareCall("SELECT * FROM projet.encoderOffreStage(?,?,?)");

            this.voirMotsClesDisponibles = connection.prepareStatement("SELECT id_mot_cle_propose, mot_cle FROM projet.voirMotsClesDisponibles");

            this.voirCandidaturesPourOffreStage = connection.prepareStatement("SELECT etat_candidature, nom_etudiant, prenom_etudiant, email_etudiant, motivation_candidature FROM projet.voirCandidaturesOffreStage WHERE id_entreprise = ? AND code_offre = ?");

            this.ajoutermotcleaoffrestage = connection.prepareCall("SELECT * FROM projet.ajoutermotcleaoffrestage(?,?,?)");

            this.voirSesOffresStage = connection.prepareCall("SELECT code, semestre, description, nom FROM projet.voirSesOffresStage(?) offresStageValidees (code VARCHAR(20), semestre CHAR(2), description VARCHAR(150), nom VARCHAR(40) )");

            this.selectionnerEtudiantPourStage = connection.prepareCall("SELECT * FROM projet.selectionnerEtudiantPourStage(?,?,?)");

            this.annulerOffreStage = connection.prepareCall("SELECT * FROM projet.annulerOffreStage(?,?)");

        }catch ( SQLException e ) {

            System.out.println( "Erreur lors de l'initialisation des attributs." );

            e.printStackTrace();

            System.exit(1);

        }
    }


        /* 1. Encoder une offre de stage */
    public void encoderOffreStage(int idEntreprise, String description, String semestre){

        try{
            encoderOffreStage.setInt(1,idEntreprise);

            encoderOffreStage.setString(2,description);

            encoderOffreStage.setString(3,semestre);

            encoderOffreStage.execute();

            System.out.println( "L'offre de stage " + description + " a bien été enregistré." );

        }catch (SQLException e){
            System.out.println( "Erreur survenue lors de l'encodage de l'offre de stage.");
            e.printStackTrace();
        }
    }

        /* 2. Voir les mots clés disponibles */
    public void voirMotsClesDisponibles(){

        System.out.println( "Voici les mots-clés disponibles :\n" );

        try(ResultSet rs = voirMotsClesDisponibles.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();

            String colonneIdMotCle = metaData.getColumnName(1);
            String colonneMotCle = metaData.getColumnName(2);

            System.out.format( "%10s %15s\n", colonneIdMotCle, colonneMotCle );

            while( rs.next() ) {

                String iDMotCle = rs.getString( 1 );
                String motCle = rs.getString( 2 );

                System.out.format( "%10s %20s\n", iDMotCle, motCle );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        /* 3. Ajouter un mot clé a l'offre de stage */
    public void ajoutermotcleaoffrestage(int idEntreprise, String motCle, String codeOffre) throws SQLException{

        try{
            ajoutermotcleaoffrestage.setInt(1,idEntreprise);

            ajoutermotcleaoffrestage.setString(2,motCle);

            ajoutermotcleaoffrestage.setString(3,codeOffre);

            ajoutermotcleaoffrestage.execute();

            System.out.println( "La mot clé " + motCle + " a bien été ajouté pour l'offre de stage " + codeOffre );

        }catch (SQLException e){
            System.out.println( "Erreur survenue lors de l'ajout du mot clé.");
            e.printStackTrace();
        }

    }

        /* 4. Voir ses offres de stage */
    public void voirSesOffresStage(int idEntreprise) throws SQLException {

        System.out.println("Voici vos offres de stages\n");

        voirSesOffresStage.setInt(1,idEntreprise);

        try(ResultSet rs = voirSesOffresStage.executeQuery()){

            ResultSetMetaData metaData = rs.getMetaData();

            String colonnecodeOffre = metaData.getColumnName(1);
            String colonnesemestreStage = metaData.getColumnName(2);
            String colonnedescriptionStage = metaData.getColumnName(3);
            String colonnenomEtudiant = metaData.getColumnName(4);

            System.out.format("%10s %15s %15s %10s\n", colonnecodeOffre, colonnesemestreStage, colonnedescriptionStage, colonnenomEtudiant);

            while (rs.next()) {

                String codeOffre = rs.getString(1);
                String semestreStage = rs.getString(2);
                String descriptionStage = rs.getString(3);
                String nomEtudiant = rs.getString(4);

                System.out.format("%10s %10s %15s %20s\n", codeOffre, semestreStage, descriptionStage, nomEtudiant);
            }

            System.out.println();

        }catch (SQLException e) {
            System.out.println("Erreur survenue lors du visionnage des offres de stage.");
            e.printStackTrace();
        }

    }


        /* 5. Voir les candidatures pour offre de stage */
    public void voirCandidaturesPourOffreStage(int idEntreprise, String codeOffre) throws SQLException {

        System.out.println("Voici les candidatures pour l'offre :" + codeOffre + "\n");

        voirCandidaturesPourOffreStage.setInt(1, idEntreprise);
        voirCandidaturesPourOffreStage.setString(2, codeOffre);

        try (ResultSet rs = voirCandidaturesPourOffreStage.executeQuery()) {

            boolean hasCandidatures = false;

            while (rs.next()) {
                if (!hasCandidatures) {
                    // Afficher l'en-tête uniquement si des candidatures sont présentes
                    ResultSetMetaData metaData = rs.getMetaData();

                    /* int nbColonnes = metaData.getColumnCount();
                    for (int i = 1; i <= nbColonnes ; i++) {

                        System.out.println( metaData.getColumnName( i ) );

                    }*/

                    String colonneEtatCandidature = metaData.getColumnName(1);
                    String colonneNomEtudiant = metaData.getColumnName(2);
                    String colonnePrenomEtudiant = metaData.getColumnName(3);
                    String colonneEmailEtudiant = metaData.getColumnName(4);
                    String colonneMotivationCandidature = metaData.getColumnName(5);

                    System.out.format("%15s %15s %15s %20s %30s\n", colonneEtatCandidature, colonneNomEtudiant, colonnePrenomEtudiant, colonneEmailEtudiant, colonneMotivationCandidature);

                    hasCandidatures = true;
                }

                String etatCandidature = rs.getString(1);
                String nomEtudiant = rs.getString(2);
                String prenomEtudiant = rs.getString(3);
                String emailEtudiant = rs.getString(4);
                String motivationCandidature = rs.getString(5);

                System.out.format("%10s %15s %15s %30s %20s\n", etatCandidature, nomEtudiant, prenomEtudiant, emailEtudiant, motivationCandidature);
            }

            if (!hasCandidatures) {
                System.out.println("Il n'y a pas de candidatures pour cette offre ou vous n'avez pas d'offre ayant ce code.");
            }

            System.out.println();
        } catch (SQLException e) {
            System.out.println("Erreur survenue lors du visionnage des candidatures pour offre de stage.");
            e.printStackTrace();
        }
    }

        /* 6. Selectionner un étudiant pour une offre de stage */
    public void selectionnerEtudiantPourStage(String code, String email, int idEntreprise) throws SQLException{

        try {
            selectionnerEtudiantPourStage.setString(1,code);

            selectionnerEtudiantPourStage.setString(2,email);

            selectionnerEtudiantPourStage.setInt(3,idEntreprise);

            selectionnerEtudiantPourStage.execute();

            System.out.println("L'etudiant " + email + "a été selectionné pour l'offre de stage " + code);

        }catch (SQLException e){
            System.out.println( "Erreur survenue lors de la selection de l'etudiant pour l'offre de stage.");
            e.printStackTrace();
        }

    }

        /* 7. Annuler une offre de stage */
    public void annulerOffreStage(String code, int idEntreprise) throws SQLException{

        try{

            annulerOffreStage.setString(1,code);
            annulerOffreStage.setInt(2,idEntreprise);

            annulerOffreStage.execute();

            System.out.println("L'offre de stage " + code + " a été annulée");

        }catch (SQLException e){
            System.out.println( "Erreur survenue lors de l'annulation de l'offre de stage.");
            e.printStackTrace();
        }

    }
}
