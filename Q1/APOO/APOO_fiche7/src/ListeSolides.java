import java.util.ArrayList;
import java.util.Iterator;

public class ListeSolides implements Iterable<Solide>{

    private ArrayList<Solide> listeSolides;

    public ListeSolides(){

        this.listeSolides = new ArrayList<Solide>();

    }

    public boolean estVide(){

        int size = listeSolides.size();

        if ( size == 0 ) return true;
        else return false;

    }

    public int nombreDeSolides(){

        int size = listeSolides.size();

        return size;

    }

    public Iterator<Solide> iterator(){

        return listeSolides.iterator();

    }

    public boolean ajouter( Solide solide ){

        if ( listeSolides.contains( solide ) ) return false;

        listeSolides.add( solide );

        return true;

    }

    public boolean supprimer( Solide solide ){

        if ( !listeSolides.contains( solide ) ) return false;

        listeSolides.remove( solide );

        return true;

    }

    public boolean contient( Solide solide ){

        return listeSolides.contains( solide );

    }

    public ArrayList<Solide> trouverPlusGrand(){

        ArrayList<Solide> plusGrandVolumes = new ArrayList<Solide>();

        double volumeMax = listeSolides.get( 0 ).donnerVolume();

        for ( Solide solide : listeSolides ){

            if ( solide.donnerVolume() > volumeMax ){

                volumeMax = solide.donnerVolume(); // VolumeMax est actualisé

                plusGrandVolumes.clear(); // On vide l'ArrayList

                plusGrandVolumes.add( solide ); // On ajoute le solide correspondant au VolumeMax

            } else if (solide.donnerVolume() == volumeMax) {

                plusGrandVolumes.add(solide);

            }

        }

        return plusGrandVolumes;

    }

    @Override
    public String toString() {

        String string = "Liste des solides :\n";

        for ( Solide solide : listeSolides ){

            string += solide.toString() + "\n";

        }

        return string;

    }
}