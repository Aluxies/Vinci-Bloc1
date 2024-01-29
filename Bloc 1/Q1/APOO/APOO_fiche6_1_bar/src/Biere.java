public class Biere extends BoissonAlcoolisee {

    private boolean pression;

    public Biere(String nom, int contenance, double prix, double degreAlcool, boolean pression) {
        super(nom, contenance, prix, degreAlcool);
        this.pression = pression;
    }

    public boolean isPression() {
        return pression;
    }

    @Override
    public String toString() {

        if ( isPression() ) return "Bière pression : " + super.toString();
        else return "Bière en bouteille : " + super.toString();

    }

}