import java.time.LocalDate;

public abstract class Vehicule {

    private String immatriculation;

    private LocalDate miseEnCirculation, dernierCT;

    private double kilometrage;

    public Vehicule( String immatriculation, LocalDate miseEnCirculation ){

        this.immatriculation = immatriculation;
        this.miseEnCirculation = miseEnCirculation;
        this.dernierCT = miseEnCirculation;

    }

    public String getImmatriculation(){

        return immatriculation;

    }

    public LocalDate getMiseEnCirculation(){

        return miseEnCirculation;

    }

    public LocalDate getDernierCT(){

        return dernierCT;

    }

    public double getKilometrage(){

        return kilometrage;

    }

    public void setDernierCT( LocalDate dernierCT ){

        this.dernierCT = dernierCT;

    }

    public void setKilometrage( double kilometrage ){

        this.kilometrage = kilometrage;

    }

    public abstract boolean verifierControleTechnique();

    @Override
    public String toString() {
        return "Véhicule :\n" +
                "\nImmatriculation :  " + immatriculation +
                "\nKilométrage : " + kilometrage +
                "\nMise en circulation : " + miseEnCirculation +
                "\nDernier contrôle technique : " + dernierCT;
    }
}