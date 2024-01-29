public class Sortilege extends Carte {

    private String nom;
    private String explication;

    public Sortilege( int cout, String nom, String explication ) {

        super( cout );

        this.nom = nom;
        this.explication = explication;

    }

    public String getNom() {
        return nom;
    }

    public String getExplication() {
        return explication;
    }

    public String fournirDetail() {

        return "Sortilège - " + super.fournirDetail() + ", nom : " + nom;

    }
}
