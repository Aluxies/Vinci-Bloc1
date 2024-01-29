import java.time.LocalDate;

public class FormuleSimple extends Formule {

    private Vol volAller;
    private Vol volRetour;

    public FormuleSimple(LocalDate dateDepart, int duree, Vol volAller, Vol volRetour) {
        super(dateDepart, duree);
        this.volAller = volAller;
        this.volRetour = volRetour;
    }

    public Vol getVolAller() {
        return volAller;
    }

    public Vol getVolRetour() {
        return volRetour;
    }

    public double calculerPrix(){

        return volAller.getPrix() + volRetour.getPrix();

    }

    @Override
    public String toString() {
        return "FormuleSimple{" +
                "volAller=" + volAller +
                ", volRetour=" + volRetour +
                '}';
    }
}
