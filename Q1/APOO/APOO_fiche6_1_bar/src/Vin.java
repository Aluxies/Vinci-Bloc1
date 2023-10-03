import java.util.Arrays;

public class Vin extends BoissonAlcoolisee {

    final private String[] COULEURS_EXISTANTES = { "rouge", "blanc", "rosé" };
    private String cepage;
    private String couleur;
    private String regionOrigine;
    private String paysOrigine;

    public Vin(String nom, int contenance, double prix, double degreAlcool, String cepage, String couleur, String regionOrigine, String paysOrigine) {

        super(nom, contenance, prix, degreAlcool);

        if ( cepage == null ) throw new IllegalArgumentException( "Le cépage ne peut être 'null'" );
        if ( cepage.equals( "" ) ) throw new IllegalArgumentException( "Le cépage ne peut être 'une chaîne de caractères vide'" );

        if ( regionOrigine == null ) throw new IllegalArgumentException( "La région d'origine ne peut être 'null'" );
        if ( regionOrigine.equals( "" ) ) throw new IllegalArgumentException( "La région d'origine ne peut être 'une chaîne de caractères vide'" );

        if ( paysOrigine == null ) throw new IllegalArgumentException( "Le pays d'origine ne peut être 'null'" );
        if ( paysOrigine.equals( "" ) ) throw new IllegalArgumentException( "Le pays d'origine ne peut être 'une chaîne de caractères vide'" );


        this.cepage = cepage;

        for ( String c : COULEURS_EXISTANTES ){

            if ( c.equals( couleur ) ){

                this.couleur = c;
                break;

            }

        }

        if ( this.couleur == null ){

            String couleurs = Arrays.toString(COULEURS_EXISTANTES);

            throw new IllegalArgumentException( "La couleur doit être choisie parmi les valeurs suivantes : " +
                    couleurs.substring( 1, couleurs.length()-1 ));

        }

        this.regionOrigine = regionOrigine;
        this.paysOrigine = paysOrigine;
    }

    public String getCepage() {
        return cepage;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getRegionOrigine() {
        return regionOrigine;
    }

    public String getPaysOrigine() {
        return paysOrigine;
    }

    @Override
    public String toString() {
        return "Vin " + couleur + " : " + super.toString() + "\nCepage : " + cepage + "\nOrigine : " + regionOrigine + " - " + paysOrigine;
    }

}