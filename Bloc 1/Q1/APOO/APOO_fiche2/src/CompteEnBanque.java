public class CompteEnBanque {

    private Personne titulaire;
    private String numeroDeCompte;
    private Date dateDeValidite, dateOuverture;
    private double solde;

    public CompteEnBanque( Personne titulaire, String numeroDeCompte, Date dateDeValidite, Date dateOuverture, double solde ){

        this.titulaire = titulaire;
        this.numeroDeCompte = numeroDeCompte;
        this.dateDeValidite = dateDeValidite;
        this.dateOuverture = dateOuverture;
        this.solde = solde;

    };

    public Personne getTitulaire(){

        return this.titulaire;

    };

    public String getNumero(){

        return this.numeroDeCompte;

    };

    public Date getDateDeValidite(){

        return this.dateDeValidite;

    };

    public Date getDateOuverture(){

        return this.dateOuverture;

    };

    public double getSolde(){

        return this.solde;

    };

    public String toString(){

        return "\n\nTitulaire : " + this.titulaire.getPrenom() + " " + this.titulaire.getNom() + "\n"
                + "Numéro de compte : " + this.numeroDeCompte + "\n"
                + "Date d'ouverture : " + this.dateOuverture.getJour() + " / " + this.dateOuverture.getMois() + " / " + this.dateOuverture.getAnnee() + "\n"
                + "Solde : " + this.solde;

    };

    public Boolean modifierDateDeValidite( Date dateDeValidite ){

        if ( this.dateDeValidite == dateDeValidite ) return false;

        this.dateDeValidite = dateDeValidite;

        return true;

    };

    public Boolean retrait( double montant ){

        if ( montant > this.solde ) return false;

        this.solde -= montant;

        return true;

    };

    public Boolean deposer( double montant ){

        if ( montant <= 0 ) return false;

        solde += montant;

        return true;

    };

    public Boolean virement( double montant, CompteEnBanque destinataire ){

        if ( montant > this.solde ) return false;

        if ( this == destinataire ) return false;

        this.solde -= montant;

        destinataire.deposer( montant );

        return true;

    };

};