import java.time.LocalDate;

public class Utilitaire extends Vehicule {

    private double kmEntreCT, kmDernierCT;

    private final static int KM_ENTRE_CT_PAR_DEFAUT = 50000;

    public Utilitaire( String immatriculation, LocalDate miseEnCirculation, double kmEntreCT ){

        super( immatriculation, miseEnCirculation );

        this.kmEntreCT = kmEntreCT;

    }

    public Utilitaire( String immatriculation, LocalDate miseEnCirculation ){

        super( immatriculation, miseEnCirculation );

        this.kmEntreCT = KM_ENTRE_CT_PAR_DEFAUT;

    }

    public double getKmEntreCT() {
        return kmEntreCT;
    }

    public double getKmDernierCT() {
        return kmDernierCT;
    }

    public void setKmDernierCT( double kmDernierCT ){

        this.kmDernierCT = kmDernierCT;

    }

    public boolean verifierControleTechnique(){

        // Il faut faire un contrôle technique tous les 50.000 km
        // kmDernierCT : nombre de kilomètres parcourus jusqu'au dernierCT
        // kmEntreCT : nombre de kilomètres maximal entre deux CT

    // Si le nombre de kilomètres parcourus jus'au dernier CT est
    // plus petit que le nombre de kilomètres maximal entre deux CT
    // on retourne 'true'

        double kilometresParcourus = super.getKilometrage() - kmDernierCT;

        if ( kilometresParcourus <= kmEntreCT ){

            return true;

        }

        return false;

    }

    @Override
    public String toString() {

        return super.toString() +
                "\nType de véhicule : utilitaire" +
                "\nKilomètres entre CT : " + kmEntreCT +
                "\nKilomètres dernier CT : " + kmDernierCT;

    }

}