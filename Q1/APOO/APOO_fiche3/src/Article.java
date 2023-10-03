public class Article {

    private String reference, nom, description;

    private double prixHorsTVA, tauxDeTVA;

    public Article( String reference, String nom, String description, double prixHorsTVA, double tauxDeTVA ){

        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prixHorsTVA = prixHorsTVA;
        this.tauxDeTVA = 1 + tauxDeTVA / 100;

    }

    public Article( String reference, String nom, String description, double prixHorsTVA ){

        this( reference, nom, description, prixHorsTVA, 21 );

    }

    public String getReference(){

        return this.reference;

    }

    public String getNom(){

        return this.nom;

    }

    public String getDescription(){

        return this.description;

    }

    public double getPrixHorsTVA(){

        return this.prixHorsTVA;

    }

    public double getTauxDeTVA(){

        return this.tauxDeTVA;

    }

    public void setDescription( String description ){

        this.description = description;

    }

    public void setPrixHorsTVA( double prixHorsTVA ){

        this.prixHorsTVA = prixHorsTVA;

    }

    public void setTauxDeTVA( double tauxDeTVA ){

        this.tauxDeTVA = 1 + tauxDeTVA / 100;

    }

    public String toString(){

        return this.nom +
                "\nRéférence : " + this.reference;


    }

    public double calculerPrixTVAComprise(){

        return this.prixHorsTVA * this.tauxDeTVA;

    }

    public double calculerPrixTVAComprise( double reduction ){

        reduction = 1 - reduction / 100;

        return ( this.prixHorsTVA * this.tauxDeTVA ) * reduction;

    }

}