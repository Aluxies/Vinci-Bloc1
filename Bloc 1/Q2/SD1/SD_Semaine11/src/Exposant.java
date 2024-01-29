
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class Exposant {
    private String nom;
    private String email;

    private LinkedList<Emplacement> listeEmplacements;

    public Exposant(String nom, String email) {
        this.nom = nom;
        this.email = email;
        this.listeEmplacements = new LinkedList<Emplacement>();
    }

    public boolean ajouterEmplacement( Emplacement emplacement ){

        // Si l'emplacement est déjà réservé pour l'exposant, on renvoie false

        if ( listeEmplacements.contains( emplacement ) ) return false;

        // on ajoute l'emplacement à la liste d'emplacements de l'exposant

        listeEmplacements.add( emplacement );

        return true;

    }

    public boolean retirerEmplacement( Emplacement emplacement ){

        // Si l'emplacement n'est pas déjà réservé pour l'exposant, on renvoie false

        if ( !listeEmplacements.contains( emplacement ) ) return false;

        // on retire l'emplacement de la liste d'emplacements de l'exposant

        listeEmplacements.remove( emplacement );

        return true;

    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public Iterator<Emplacement> tousLesEmplacements(){

        return listeEmplacements.iterator();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exposant exposant = (Exposant) o;
        return nom.equals(exposant.nom) && email.equals(exposant.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, email);
    }


    @Override
    public String toString() {

        String string = "nom: " + nom + "; email: " + email + "; emplacements: ";

        for ( Emplacement emplacement : listeEmplacements ) {

            string += emplacement.getNumero() + " ";

        }

        return string;

    }
}

