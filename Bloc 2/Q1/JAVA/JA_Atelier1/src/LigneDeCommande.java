public class LigneDeCommande {

    private Pizza pizza;
    private int quantite;
    private double prixUnitaire;

    public LigneDeCommande(Pizza pizza, int quantite) {

        if ( pizza == null ) throw new IllegalArgumentException( "L'objet 'pizza' ne peut être 'null'." );
        if ( quantite <= 0 ) throw new IllegalArgumentException( "La quantite ne peut être plus petite ou égale à 0." );

        this.pizza = pizza;
        this.quantite = quantite;
        this.prixUnitaire = pizza.calculerPrix();
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setQuantite(int quantite) {

        if ( quantite <= 0 ) throw new IllegalArgumentException( "La quantite ne peut être plus petite ou égale à 0." );

        this.quantite = quantite;

    }
    public double calculerPrixTotal(){
        return quantite * prixUnitaire;
    }

    public String toString() {
        return  quantite + " " + pizza.getTitre() + "  à " + prixUnitaire ;
    }
}
