import java.time.LocalDate;

public class Voiture extends Vehicule {

    private final static int ANNEE_AVANT_CT = 4;
    private final static int ANNEE_ENTRE_CT = 1;

    public Voiture( String immatriculation, LocalDate miseEnCirculation ){

        super( immatriculation, miseEnCirculation );

    }

    public boolean verifierControleTechnique(){

        LocalDate dernierCT = super.getDernierCT();
        LocalDate dateActuelle = LocalDate.now();

    // Vérifier le cas où le premier contrôle technique n'a pas été fait à temps : ANNEE_AVANT_CT

        LocalDate miseEnCirculationPlus4 = getMiseEnCirculation().plusYears( ANNEE_AVANT_CT );

        if ( dateActuelle.isBefore( miseEnCirculationPlus4 ) ){

            return true;

        }

    // Vérifier le cas où le dernier contrôle technique n'a pas été fait à temps : ANNE_ENTRE_CT

        LocalDate miseEnCirculationPlus1 = dernierCT.plusYears( ANNEE_ENTRE_CT );

        if ( dateActuelle.isBefore( miseEnCirculationPlus1 ) ){

            return true;

        }

        return false;

    }

    @Override
    public String toString() {

        return super.toString() +
                "\nType de véhicule : voiture";

    }

}