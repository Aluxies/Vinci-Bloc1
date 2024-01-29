import java.util.Objects;

public class Cube implements Solide {

    private double cote;

    public Cube( double cote ){

        if ( cote <= 0 ) throw new IllegalArgumentException( "Le côté ne peut être 'nul ou négatif'" );

        this.cote = cote;

    }

    public double donnerVolume(){

        double volume = Math.pow( cote, 3 );

        return volume;
    }

    public double donnerSurface(){

        double surface = 6 * Math.pow( cote, 2 );

        return surface;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cube cube)) return false;
        return Double.compare(cube.cote, cote) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cote);
    }

    @Override
    public String toString() {
        return "Cube de cote " + cote;
    }
}
