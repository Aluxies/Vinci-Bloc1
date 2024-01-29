import java.util.ArrayList;
import java.util.Iterator;

public class Panier implements Iterable<Produit> {

    private ArrayList<Produit> liste;

    private final static int NOMBRE_MAX_PRODUITS = 10;

    public Panier() {

        this.liste = new ArrayList<Produit>();

    }

    public boolean ajouter( Produit produit ) throws PanierPleinException, IllegalArgumentException {

        if ( produit == null ) throw new IllegalArgumentException( "Produit ne peut être 'nul'" );
        if ( liste.size() >= NOMBRE_MAX_PRODUITS ) throw new PanierPleinException( "Le panier est plein !!!!" );

        if ( liste.contains( produit ) ) return false;

        liste.add( produit );

        return true;

    }

    public boolean supprimer( Produit produit ) throws IllegalArgumentException, ProduitNonPresentException {

        if ( produit == null ) throw new IllegalArgumentException( "Produit ne peut être 'nul' !" );

        if ( !liste.contains( produit ) ) throw new ProduitNonPresentException( "Le produit n'est pas présent dans la table" );

        liste.remove( produit );

        return true;

    }

    public int nombreProduits(){

        return liste.size();

    }

    public Produit trouverProduit( String reference ) throws ProduitNonPresentException {

        if ( reference == "" ) throw new IllegalArgumentException( "La référence ne peut être 'chaîne de caractères vide'" );

        if ( reference == null ) throw new IllegalArgumentException( "La référence ne peut être 'nulle' !" );

        for ( Produit produit : liste ){

            if ( produit.getReference() == reference ) return produit;

        }

        throw new ProduitNonPresentException( "Le produit n'est pas présent dans la table" );

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