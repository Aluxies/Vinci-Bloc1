package domaine;

import java.util.*;

import domaine.Plat.Type;

public class Livre {

    private HashMap<String, SortedSet<Plat>> plats;

    public Livre() {

        this.plats = new HashMap<String, SortedSet<Plat>>();

    }

    public boolean ajouterPlat ( Plat plat ) {

        util.Util.checkObject( plat );

        String typePlat = plat.getType().toString();

        if ( plats.get( typePlat ) == null ) {

            TreeSet<Plat> treeSet = new TreeSet<Plat>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {

                    int compareDifficulte = o1.getNiveauDeDifficulte().compareTo( o2.getNiveauDeDifficulte() );

                    if ( compareDifficulte == 0 ) {

                        return o1.getNom().compareTo( o2.getNom() );

                    }

                    return compareDifficulte;
                }
            });

            plats.put( typePlat, treeSet );

        }

        return plats.get( typePlat ).add( plat );

    }

    public boolean supprimerPlat ( Plat plat ) {

        util.Util.checkObject( plat );

        String typePlat = plat.getType().toString();

        return plats.get( typePlat ).remove( plat );

    }

    public SortedSet<Plat> getPlatsParType ( Plat.Type type ) {

        util.Util.checkObject( type );

        SortedSet<Plat> platsParType = plats.get( type.toString() );

        if ( platsParType == null ) return null;

        return platsParType;

    }

    public boolean contient ( Plat plat ) {

        util.Util.checkObject( plat );

        for ( Plat element : plats.get( plat.getType().toString() ) ) {

            if ( element.equals( plat ) ) return true;

        }

        return false;

    }

    public Set<Plat> tousLesPlats () {

        HashSet<Plat> tousLesPlats = new HashSet<Plat>();

        for ( SortedSet<Plat> treeSet : plats.values() ) {

            tousLesPlats.addAll( treeSet );

        }

        return tousLesPlats;

    }

    public String toString() {

        String string = "";

        for ( int i=0; i< Type.values().length; i++ ) {

            String typePlat = Type.values()[i].toString();

            string += typePlat + "\n=====\n";

            if ( plats.get( typePlat ) != null ) {

                for ( Plat plat : plats.get( typePlat ) ) {

                    string += plat.getNom() + "\n";

                }

            }

        }

        return string;

    }



}