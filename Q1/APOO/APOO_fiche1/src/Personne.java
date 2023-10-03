class Personne {

    private String nom, prenom;

    private Date dateDeNaissance;

    private Adresse domicile;

    Personne( String nom, String prenom, Date dateDeNaissance, Adresse domicile){

        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.domicile = domicile;

    };

    public String ageFinAnneeActuelle(){

        int ageActuel = 2022 - this.dateDeNaissance.getAnnee();

        return prenom + " / " + nom + " aura " + ageActuel + " ans le 31 / 12 / 2022";

    };

    public String ageFinAnnee( int annee ){

        int ageActuel = annee - this.dateDeNaissance.getAnnee();

        return this.prenom +
                " / " +
                this.nom +
                " aura " +
                ageActuel +
                " ans le 31 / 12 / " +
                annee;

    };

    public String adresseComplete(){

        return this.prenom +
                " / " +
                this.nom +
                " habite à " +
                this.domicile.getCodePostal() +
                " " +
                this.domicile.getVille() +
                ", " +
                this.domicile.getRue() +
                " " +
                this.domicile.getNumero();

    };

    public String dateNaissanceComplete(){

        return this.prenom +
                " / " +
                this.nom +
                " est né le " +
                this.dateDeNaissance.getJour() +
                " / " +
                this.dateDeNaissance.getMois() +
                " / " +
                this.dateDeNaissance.getAnnee();

    };

};
