import java.util.Objects;

public class Cylindre implements Solide {

    private double rayon, hauteur;

    public Cylindre( double rayon, double hauteur ){

        if ( rayon <= 0 ) throw new IllegalArgumentException( "Le rayon ne peut être 'nul ou négatif'" );
        if ( hauteur <= 0 ) throw new IllegalArgumentException( "La hauteur ne peut être 'nulle ou négative'" );

        this.rayon = rayon;
        this.hauteur = hauteur;

    }

    public double donnerVolume(){

        double volume = hauteur * Math.PI * Math.pow( rayon, 2 );

        return volume;
    }

    public double donnerSurface(){

        double surface = 2 * Math.PI * Math.pow( rayon, 2 ) + 2 * Math.PI * rayon * hauteur;

        return surface;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cylindre cylindre)) return false;
        return Double.compare(cylindre.rayon, rayon) == 0 && Double.compare(cylindre.hauteur, hauteur) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rayon, hauteur);
    }

    @Override
    public String toString() {
        return "Cylindre de rayon " + rayon + " et de hauteur " + hauteur;
    }

}