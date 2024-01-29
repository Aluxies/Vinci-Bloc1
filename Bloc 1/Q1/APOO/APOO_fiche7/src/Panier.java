import java.util.ArrayList;
import java.util.Iterator;

public class Panier implements Iterable<Produit> {

    private ArrayList<Produit> liste;

    public Panier() {

        this.liste = new ArrayList<Produit>();

    }

    public boolean ajouter( Produit produit ){

        if ( liste.contains( produit ) ) return false;

        liste.add( produit );

        return true;

    }

    public boolean supprimer( Produit produit ){

        if ( !liste.contains( produit ) ) return false;

        liste.remove( produit );

        return true;

    }

    public int nombreProduits(){

        return liste.size();

    }

    public Produit trouverProduit( String reference ){

        for ( Produit produit : liste ){

            if ( produit.getReference() == reference ) return produit;

        }

        return null;

    }

    public double calculerPrixTotal(){

        double prixTotal = 0;

        if ( liste.size() == 0 ) return prixTotal;

        for ( Produit produit : liste ){

            prixTotal += produit.getPrix();

        }

        return prixTotal;

    }

    @Override
    public Iterator<Produit> iterator() {
        return liste.iterator();
    }

    @Override
    public String toString() {

        String string = "Panier :\n\n";

        if ( liste.size() == 0 ) string += "Vide";

        for ( Produit produit : liste ){

            string += produit.toString() + "\n";

        }

        return string;

    }
}