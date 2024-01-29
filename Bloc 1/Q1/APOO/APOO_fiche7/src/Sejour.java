import java.time.LocalDate;

public class Sejour extends FormuleSimple {

    private double prixNuitee;

    private String nom;

    private int nombreEtoiles;

    public Sejour(LocalDate dateDepart, int duree, Vol volAller, Vol volRetour, double prixNuitee, String nom, int nombreEtoiles) {
        super(dateDepart, duree, volAller, volRetour);
        this.prixNuitee = prixNuitee;
        this.nom = nom;
        this.nombreEtoiles = nombreEtoiles;
    }

    public double getPrixNuitee() {
        return prixNuitee;
    }

    public String getNom() {
        return nom;
    }

    public int getNombreEtoiles() {
        return nombreEtoiles;
    }

    public double calculerPrix(){

       return prixNuitee * getDuree() + super.calculerPrix();

    }

    @Override
    public String toString() {
        return "Sejour{" +
                "prixNuitee=" + prixNuitee +
                ", nom='" + nom + '\'' +
                ", nombreEtoiles=" + nombreEtoiles +
                '}';
    }
}
