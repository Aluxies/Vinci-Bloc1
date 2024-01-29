class Personne {

    private String nom, prenom;

    private Date dateDeNaissance;

    private Adresse domicile;

    public void demenager( String rue, String numero, String codePostal, String ville ){

        this.domicile = new Adresse( rue, numero, codePostal, ville );

    };

    public Personne( String newNom, String newPrenom, Date newDateDeNaissance, Adresse newDomicile){

        this.nom = newNom;
        this.prenom = newPrenom;
        this.dateDeNaissance = newDateDeNaissance;
        this.domicile = newDomicile;

    };

    public String getPrenom(){

        return this.prenom;

    };

    public String getNom(){

        return this.nom;

    };

    public String calculerAge(){

        int ageActuel = 2022 - this.dateDeNaissance.getAnnee();

        return this.prenom + " / " + this.nom + " aura " + ageActuel + " ans le 31 / 12 / 2022";

    };

    public String calculerAgeEn( int annee ){

        int ageActuel = annee - this.dateDeNaissance.getAnnee();

        return this.prenom +
                " / " +
                this.nom +
                " aura " +
                ageActuel +
                " ans le 31 / 12 / " +
                annee;

    };

    public String toString(){

        return this.prenom + " " + this.nom + "\n" +
                "Né(e) le : " + this.dateDeNaissance.getJour() +
                " / " + this.dateDeNaissance.getMois() +
                " / " + this.dateDeNaissance.getAnnee() + "\n" +
                "Habite à " + this.domicile.getRue() +
                " numéro " + this.domicile.getNumero() +
                ", " + this.domicile.getCodePostal() +
                " " + this.domicile.getVille() + "\n";

    };

    public String getDateDeNaissance(){

        return this.dateDeNaissance.getJour() + "/" +
                this.dateDeNaissance.getMois() + "/" +
                this.dateDeNaissance.getAnnee();

    };

    public String getDomicile(){

        return this.domicile.getRue() + " numéro " +
                this.domicile.getNumero() + ", " +
                this.domicile.getCodePostal() + " " +
                this.domicile.getVille();

    };

};
