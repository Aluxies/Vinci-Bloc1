package professeur;

import java.sql.*;

public class SqlQueriesProfesseur {

    private PreparedStatement encoderEtudiant;
    private PreparedStatement encoderEntreprise;
    private PreparedStatement encoderMotClePropose;
    private PreparedStatement voirOffresStagesNonValidees;
    private PreparedStatement validerOffreStage;
    private PreparedStatement voirOffresStagesValidees;
    private PreparedStatement voirEtudiantsSansStage;
    private PreparedStatement voirOffresStagesAttribuees;

    public SqlQueriesProfesseur( Connection connection ) {

        try {

            this.encoderEtudiant = connection.prepareStatement("SELECT * FROM projet.encoderEtudiant( ?, ?, ?, ?, ? )");

            this.encoderEntreprise = connection.prepareStatement("SELECT * FROM projet.encoderEntreprise( ?, ?, ?, ?, ? )");

            this.encoderMotClePropose = connection.prepareStatement("SELECT * FROM projet.encoderMotClePermis( ? )");

            this.voirOffresStagesNonValidees = connection.prepareStatement("SELECT code_offre, semestre_offre, description_offre, nom_entreprise FROM projet.voirOffresStage vos WHERE vos.etat_offre != 'validée'");

            this.validerOffreStage = connection.prepareStatement("SELECT * FROM projet.validerOffre( ? )");

            this.voirOffresStagesValidees = connection.prepareStatement("SELECT code_offre, semestre_offre, description_offre, nom_entreprise FROM projet.voirOffresStage vos WHERE vos.etat_offre = 'validée'");

            this.voirEtudiantsSansStage = connection.prepareStatement("SELECT nom, prenom, email, semestre, nbr_candidatures_en_attente FROM projet.voirEtudiantsSansStage");

            this.voirOffresStagesAttribuees = connection.prepareStatement("SELECT code_offre, nom_entreprise, et.nom AS \"nom_etudiant\", et.prenom AS \"prenom_etudiant\" FROM projet.voirOffresStage, projet.etudiants et WHERE etat_offre = 'attribuée' AND etudiant_offre = et.id_etudiant");

        } catch ( SQLException e ) {

            System.out.println( "Une erreur est survenue : " );

            e.printStackTrace();

        }

    }

    public void encoderEtudiant( String nom, String prenom, String email, String motDePasse, String semestre ) {

        try {

            encoderEtudiant.setString( 1, nom );

            encoderEtudiant.setString( 2, prenom );

            encoderEtudiant.setString( 3, email );

            encoderEtudiant.setString( 4, motDePasse );

            encoderEtudiant.setString( 5, semestre );

            encoderEtudiant.execute();

            System.out.println( "L'étudiant " + nom + " " + prenom + " a bien été enregistré." );

        } catch ( SQLException e ) {

            System.out.println( "Erreur survenue lors de l'encodage de l'étudiant.");

            e.printStackTrace();

        }

    }

    public void encoderEntreprise( String identifiant, String nom, String adresse, String email, String motDePasse ) {

        try {

            encoderEntreprise.setString( 1, identifiant );

            encoderEntreprise.setString( 2, nom );

            encoderEntreprise.setString( 3, adresse );

            encoderEntreprise.setString( 4, email );

            encoderEntreprise.setString( 5, motDePasse );

            encoderEntreprise.execute();

            System.out.println( "L'entreprise " + nom + " a bien été enregistrée." );

        } catch ( SQLException e ) {

            System.out.println( "Erreur survenue lors de l'encodage de l'entreprise.");

            e.printStackTrace();

        }

    }

    public void encoderMotClePropose( String motClePropose ) {

        try {

            encoderMotClePropose.setString( 1, motClePropose );

            encoderMotClePropose.execute();

            System.out.println( "Le mot-clé " + motClePropose + " a bien été enregistré." );

        } catch ( SQLException e ) {

            System.out.println( "Erreur survenue lors de l'encodage du mot-clé.");

            e.printStackTrace();

        }

    }

    public void voirOffresStagesNonValidees() {

        System.out.println( "Voici les offres de stage non validées :\n\n" );

        try ( ResultSet rs = voirOffresStagesNonValidees.executeQuery() ) {

            ResultSetMetaData metaData = rs.getMetaData();

            String colonneCode = metaData.getColumnName( 1 );
            String colonneSemestre = metaData.getColumnName( 2 );
            String colonneDescription = metaData.getColumnName( 3 );
            String colonneNom = metaData.getColumnName( 4 );

            System.out.format( "%10s %15s %25s %15s\n", colonneCode, colonneSemestre, colonneDescription, colonneNom );

            while( rs.next() ) {

                String codeOffre = rs.getString( 1 );
                String semestreOffre = rs.getString( 2 );
                String descriptionOffre = rs.getString( 3 );
                String nomEntreprise = rs.getString( 4 );

                System.out.format( "%10s %15s %25s %15s\n", codeOffre, semestreOffre, descriptionOffre, nomEntreprise );

            }

        } catch (SQLException e) {

            System.out.println( "Erreur survenue lors du visionnage des offres de stage non validées.");

            e.printStackTrace();

        }

    }

    public void validerOffreStage( String code ) {

        try {

            validerOffreStage.setString( 1, code );

            validerOffreStage.execute();

            System.out.println( "L'offre stage avec le code " + code + " a bien été validée." );

        } catch ( SQLException e ) {

            System.out.println( "Erreur survenue lors de la validation de l'offre de stage.");

            e.printStackTrace();

        }

    }

    public void voirOffresStagesValidees() {

        System.out.println( "Voici les offres de stage validées :\n\n" );

        try ( ResultSet rs = voirOffresStagesValidees.executeQuery() ) {

            ResultSetMetaData metaData = rs.getMetaData();

            String colonneCode = metaData.getColumnName( 1 );
            String colonneSemestre = metaData.getColumnName( 2 );
            String colonneDescription = metaData.getColumnName( 3 );
            String colonneNom = metaData.getColumnName( 4 );

            System.out.format( "%10s %15s %25s %15s\n", colonneCode, colonneSemestre, colonneDescription, colonneNom );

            while( rs.next() ) {

                String codeOffre = rs.getString( 1 );
                String semestreOffre = rs.getString( 2 );
                String descriptionOffre = rs.getString( 3 );
                String nomEntreprise = rs.getString( 4 );

                System.out.format( "%10s %15s %25s %15s\n", codeOffre, semestreOffre, descriptionOffre, nomEntreprise );

            }

        } catch (SQLException e) {

            System.out.println( "Erreur survenue lors du visionnage des offres de stage validées.");

            e.printStackTrace();

        }

    }

    public void voirEtudiantsSansStage() {

        System.out.println( "Voici les étudiants sans stage :\n\n" );

        try ( ResultSet rs = voirEtudiantsSansStage.executeQuery() ) {

            ResultSetMetaData metaData = rs.getMetaData();

            String colonneNom = metaData.getColumnName( 1 );
            String colonnePrenom = metaData.getColumnName( 2 );
            String colonneEmail = metaData.getColumnName( 3 );
            String colonneNbrCandidaturesEnAttente = metaData.getColumnName( 4 );

            System.out.format( "%15s %15s %40s %5s\n", colonneNom, colonnePrenom, colonneEmail, colonneNbrCandidaturesEnAttente );

            while( rs.next() ) {

                String nomEtudiant = rs.getString( 1 );
                String prenomEtudiant = rs.getString( 2 );
                String emailEtudiant = rs.getString( 3 );
                String nbrCandidaturesEnAttenteEtudiant = rs.getString( 4 );

                System.out.format( "%15s %15s %40s %5s\n", nomEtudiant, prenomEtudiant, emailEtudiant, nbrCandidaturesEnAttenteEtudiant );

            }

        } catch (SQLException e) {

            System.out.println( "Erreur survenue lors du visionnage des étudiants sans stage.");

            e.printStackTrace();

        }

    }

    public void voirOffresStagesAttribuees() {

        System.out.println( "Voici les offres de stage attribuées :\n\n" );

        try ( ResultSet rs = voirOffresStagesAttribuees.executeQuery() ) {

            ResultSetMetaData metaData = rs.getMetaData();

            String colonneCode = metaData.getColumnName( 1 );
            String colonneNomEntreprise = metaData.getColumnName( 2 );
            String colonneNomEtudiant = metaData.getColumnName( 3 );
            String colonnePrenomEtudiant = metaData.getColumnName( 4 );

            System.out.format( "%10s %15s %15s %15s\n", colonneCode, colonneNomEntreprise, colonneNomEtudiant, colonnePrenomEtudiant );

            while( rs.next() ) {

                String codeOffre = rs.getString( 1 );
                String nomEntreprise = rs.getString( 2 );
                String nomEtudiant = rs.getString( 3 );
                String prenomEtudiant = rs.getString( 4 );

                System.out.format( "%10s %15s %15s %15s\n", codeOffre, nomEntreprise, nomEtudiant, prenomEtudiant );

            }

        } catch (SQLException e) {

            System.out.println( "Erreur survenue lors du visionnage des offres de stage attribuées.");

            e.printStackTrace();

        }

    }

    // validerOffreStage

}