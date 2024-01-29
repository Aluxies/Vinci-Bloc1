public class Tournoi {

    private String nom;

    private Date dateDeDebut;
    private Date dateDeFin;

    private Club clubOrganisateur;

    public Tournoi(String nom, Date dateDeDebut, Date dateDeFin, Club clubOrganisateur) {
        this.nom = nom;
        this.dateDeDebut = dateDeDebut;
        this.dateDeFin = dateDeFin;
        this.clubOrganisateur = clubOrganisateur;
    }

    public Tournoi(String nom, Date dateDeDebut, Date dateDeFin) {
        this( nom, dateDeDebut, dateDeFin, null );
    }

    public String getNom() {
        return this.nom;
    }

    public Date getDateDeDebut() {
        return this.dateDeDebut;
    }

    public Date getDateDeFin() {
        return this.dateDeFin;
    }

    public Club getClubOrganisateur(){ return this.clubOrganisateur; }

    @Override
    public String toString() {

        String string = "\nNom : " + this.nom +
                "\nDateDeDebut : " + this.dateDeDebut +
                "\nDateDeFin : " + this.dateDeFin;

        if ( this.clubOrganisateur != null ){

            string +=  "\nClubOrganisateur : " +
                    this.clubOrganisateur.getNom();
        }

        return string;

    }
}
