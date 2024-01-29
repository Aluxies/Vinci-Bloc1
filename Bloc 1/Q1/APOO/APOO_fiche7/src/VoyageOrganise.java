import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class VoyageOrganise extends Formule implements Iterable<Etape> {

    private double prix;
    private String nom;
    private ArrayList<Etape> listeEtapes;

    public VoyageOrganise(LocalDate dateDepart, int duree, double prix, String nom, ArrayList<Etape> listeEtapes) {
        super(dateDepart, duree);
        this.prix = prix;
        this.nom = nom;
        this.listeEtapes = listeEtapes;
    }

    @Override
    public Iterator<Etape> iterator() {
        return null;
    }

    public boolean ajouter(Etape etape ){

        if ( listeEtapes.contains( etape ) ) return false;

        listeEtapes.add( etape );

        return true;

    }

    public boolean supprimer( Etape etape ){

        if ( !listeEtapes.contains( etape ) ) return false;

        listeEtapes.remove( etape );

        return true;

    }

    public double getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public double calculerPrix(){

        return getPrix();
    }

    @Override
    public String toString() {
        return "VoyageOrganise{" +
                "prix=" + prix +
                ", nom='" + nom + '\'' +
                ", listeEtapes=" + listeEtapes +
                '}';
    }

}