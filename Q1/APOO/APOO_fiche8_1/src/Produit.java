import java.util.Objects;

public abstract class Produit {

    private String reference;

    private double prix;

    public Produit(String reference, double prix) {

        this.reference = reference;
        this.prix = prix;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit produit)) return false;
        return Objects.equals(reference, produit.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    public String getReference() {
        return reference;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {

        if ( prix <= 0 ) throw new IllegalArgumentException( "Le prix du produit ne peut être 'négatif ou nul'" );

        this.prix = prix;

    }

    @Override
    public String toString() {
        return "Produit :\n" +
                "\nReference : " + reference +
                "\nPrix : " + prix;
    }
}