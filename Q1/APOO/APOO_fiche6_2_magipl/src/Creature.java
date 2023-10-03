public class Creature extends Carte {

    private String nom;

    private int nombrePointsDegats;
    private int nombrePointsVie;

    private static final int DEGATS_PAR_DEFAUT = 0;
    private static final int VIE_PAR_DEFAUT = 0;

    public Creature( int cout, String nom, int nombrePointsDegats, int nombrePointsVie ){

        super( cout );

        this.nom = nom;

        if ( nombrePointsVie <= 0 ) throw new IllegalArgumentException( "Le nombre de points de dégât ne peut être 'nul ou négatif'" );

        this.nombrePointsDegats = nombrePointsDegats;

        if ( nombrePointsVie <= 0 ) throw new IllegalArgumentException( "Le nombre de points de vie ne peut être 'nul ou négatif'" );

        this.nombrePointsVie = nombrePointsVie;

    }

    public Creature( int cout, String nom ){

        this( cout, nom, DEGATS_PAR_DEFAUT, VIE_PAR_DEFAUT );

    }

    public String getNom() {
        return nom;
    }

    public int getNombrePointsDegats() {
        return nombrePointsDegats;
    }

    public int getNombrePointsVie() {
        return nombrePointsVie;
    }

    public String fournirDetail() {

        return "Créature - " + super.fournirDetail() + ", nom : " + nom + " (" + nombrePointsDegats + "/" + nombrePointsVie + ")";

    }
}
