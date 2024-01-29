import java.time.LocalDateTime;

public class Vol {

    private double prix;

    private LocalDateTime dateHeureVol;

    private String aeroportDepart;
    private String aeroportArrivee;

    public Vol(double prix, LocalDateTime dateHeureVol, String aeroportDepart, String aeroportArrivee) {
        this.prix = prix;
        this.dateHeureVol = dateHeureVol;
        this.aeroportDepart = aeroportDepart;
        this.aeroportArrivee = aeroportArrivee;
    }

    public double getPrix() {
        return prix;
    }

    public LocalDateTime getDateHeureVol() {
        return dateHeureVol;
    }

    public String getAeroportDepart() {
        return aeroportDepart;
    }

    public String getAeroportArrivee() {
        return aeroportArrivee;
    }

    @Override
    public String toString() {
        return "Vol{" +
                "prix=" + prix +
                ", dateHeureVol=" + dateHeureVol +
                ", aeroportDepart='" + aeroportDepart + '\'' +
                ", aeroportArrivee='" + aeroportArrivee + '\'' +
                '}';
    }
}
