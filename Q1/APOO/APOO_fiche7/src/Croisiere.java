import java.time.LocalDate;
import java.util.ArrayList;

public class Croisiere extends VoyageOrganise {

    private String nomBateau;

    private int nombreCabines;

    private ArrayList<String> activites;

    public Croisiere(LocalDate dateDepart, int duree, double prix, String nom, ArrayList<Etape> listeEtapes, String nomBateau, int nombreCabines, ArrayList<String> activites) {
        super(dateDepart, duree, prix, nom, listeEtapes);
        this.nomBateau = nomBateau;
        this.nombreCabines = nombreCabines;
        this.activites = activites;
    }

    public String getNomBateau() {
        return nomBateau;
    }

    public int getNombreCabines() {
        return nombreCabines;
    }

    public boolean ajouter( String activite ){

        if ( activites.contains( activite ) ) return false;

        activites.add( activite );

        return true;

    }

    public boolean supprimer( String activite ){

        if ( !activites.contains( activite ) ) return false;

        activites.remove( activite );

        return true;

    }

    @Override
    public String toString() {
        return "Croisiere{" +
                "nomBateau='" + nomBateau + '\'' +
                ", nombreCabines=" + nombreCabines +
                ", activites=" + activites +
                '}';
    }

}