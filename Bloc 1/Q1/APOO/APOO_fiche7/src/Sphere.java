import java.util.Objects;

public class Sphere implements Solide {

    private double rayon;

    public Sphere( double rayon ){

        if ( rayon <= 0 ) throw new IllegalArgumentException( "Le rayon ne peut être 'nul ou négatif'" );

        this.rayon = rayon;

    }

    public double donnerVolume(){

        double volume = ( 4 * Math.PI * Math.pow( rayon, 3 ) ) / 3;

        return volume;

    }

    public double donnerSurface(){

        double surface = ( 4 * Math.PI * Math.pow( rayon, 2 ) );

        return surface;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sphere sphere)) return false;
        return Double.compare(sphere.rayon, rayon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rayon);
    }

    @Override
    public String toString() {
        return "Sphere de rayon " + rayon;
    }
}
