public class Club {

    private String matricule;
    private String nom;
    private Adresse adresse;

    private Joueur directeurDesTournois;

    public Club(String matricule, String nom, Adresse adresse) {
        this.matricule = matricule;
        this.nom = nom;
        this.adresse = adresse;
        this.directeurDesTournois = null;
    }

    public String getMatricule() {
        return this.matricule;
    }

    public String getNom() {
        return this.nom;
    }

    public Adresse getAdresse() {
        return this.adresse;
    }

    public Joueur getDirecteurDesTournois() {
        return this.directeurDesTournois;
    }

    public boolean setDirecteurDesTournois(Joueur directeurDesTournois) {

        if ( directeurDesTournois != null ) return false;

        else {

            if ( directeurDesTournois.getClub().getMatricule() != this.matricule ) return false;

            this.directeurDesTournois = directeurDesTournois;
            return true;

        }

    }

    public boolean deleteDirecteurDesTournois() {

        if ( this.directeurDesTournois == null ) return false;

        else {

            this.directeurDesTournois = null;
            return true;

        }

    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {

        String string = "\nMatricule : " + this.matricule +
                "\nNom : " + this.nom +
                "\nAdresse : " + this.adresse;

        if ( this.directeurDesTournois != null ) {

            string += "\nDirecteur des tournois :\n" +
                    "\nPrénom : " + this.directeurDesTournois.getPrenom() +
                    "\nNom : " + this.directeurDesTournois.getNom();

        }

        return string;

    }
}