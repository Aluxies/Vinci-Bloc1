import java.util.Objects;

public class Ingredient {

    private String nom;
    private double prix;

    public Ingredient ( String nom, double prix ) {

        if ( nom == null ) throw new IllegalArgumentException( "L'objet 'nom' ne peut être 'null'." );
        if ( nom.equals( " " ) ) throw new IllegalArgumentException( "L'objet 'nom' ne peut être égal à 'blanc'." );

        if ( prix <= 0 ) throw new IllegalArgumentException( "Le prix ne peut être plus petit ou égal à 0." );

        this.nom = nom;
        this.prix = prix;

    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {

        if ( prix <= 0 ) throw new IllegalArgumentException( "Le prix ne peut être plus petit ou égal à 0." );

        this.prix = prix;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}