import java.util.ArrayList;

public class Carte {

    private ArrayList<Boisson> boissons = new ArrayList<Boisson>();

    public Carte() {
        this.boissons = boissons;
    }

    public boolean ajouter( Boisson boisson ){

        for ( Boisson b : boissons ){

            if ( b.equals( boisson ) ) return false;

        }

        boissons.add( boisson );

        return true;

    }

    public boolean retirer( Boisson boisson ){

        if ( boissons.contains( boisson ) ){

            boissons.remove( boisson );

            return true;

        }

        return false;

    }

    public boolean contient( Boisson boisson ){

        return boissons.contains( boisson );

    }

    public int nombreDeBoissons(){

        return boissons.size();

    }

    @Override
    public String toString() {

        String string = "Carte :\n";

        for ( Boisson b : boissons ){

            string += b.toString() + "\n";

        }

        return string;

    }

}