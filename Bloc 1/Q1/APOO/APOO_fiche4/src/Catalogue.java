import java.util.ArrayList;

public class Catalogue {

    private ArrayList<Livre> livres;

    public Catalogue(){

        this.livres = new ArrayList<Livre>();

    }

    public boolean contient( Livre livre ){

        return this.livres.contains( livre );

    }

    public boolean ajouter( Livre livre ){

        if ( !this.contient( livre )){

            this.livres.add( livre );

            return true;

        }

        return false;

    }

    public boolean retirer( Livre livre ){

        if ( this.contient( livre ) ){

            this.livres.remove( livre );

            return true;

        }

        return false;

    }

    public int nombreDeLivres(){

        return this.livres.size();

    }

    public boolean estVide(){

        if ( nombreDeLivres() == 0 ) return true;

        return false;

    }

    public Livre chercherLivre( String isbn ){

        for ( Livre livre : livres ){

            if ( livre.getIsbn() == isbn ) return livre;

        }

        return null;
    }

    @Override
    public String toString() {

        String string = "Liste des livres :\n";

        for ( Livre livre : livres ) {

            string += livre + "\n";

        }

        return string;
    }
}