public class Joueur {

    private String matricule;
    private String nom;
    private String prenom;

    private Date dateDeNaissance;

    private Club club;

    private int partiesJouees;
    private int pointsElo;

    public Joueur(String matricule, String nom, String prenom, Date dateDeNaissance, Club club) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.club = club;
        this.partiesJouees = 0;
        this.pointsElo = 0;
    }

    public String getMatricule() {
        return this.matricule;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Date getDateDeNaissance() {
        return this.dateDeNaissance;
    }

    public Club getClub() {
        return this.club;
    }

    public int getPartiesJouees() {
        return this.partiesJouees;
    }

    public int getPointsElo() {
        return this.pointsElo;
    }

    public void incrementerNombreParties(){

        this.partiesJouees ++;

    }

    public boolean setPointsElo(int pointsElo) {

        if ( this.partiesJouees < 10 ) return false;
        if ( pointsElo < 1150 ) return false;

        this.pointsElo = pointsElo;

        return true;

    }

    public boolean setClub(Club club) {

        if ( this.club == club ) return false;

        if ( this.club.getDirecteurDesTournois() == this ){

            this.club.deleteDirecteurDesTournois();

        }

        this.club = club;

        return true;

    }



    @Override
    public String toString() {
        return "\nMatricule : " + this.matricule +
                "\nNom : " + this.nom +
                "\nPrenom : " + this.prenom +
                "\nDateDeNaissance : " + this.dateDeNaissance +
                "\nClub : " + this.club.getNom() +
                "\nPartiesJouees : " + this.partiesJouees +
                "\nPointsElo : " + this.pointsElo;
    }
}
