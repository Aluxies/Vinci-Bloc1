public class Terrain extends Carte {

    final static private char[] COULEURS_EXISTANTES = { 'B', 'b', 'n', 'r', 'v' };
    final static private char COULEUR_PAR_DEFAUT = 'i';

    private char couleur;

    public Terrain( char couleur ){

        super( 0 );

        this.couleur = COULEUR_PAR_DEFAUT;

        for ( char c : COULEURS_EXISTANTES ){

            if ( c == couleur ){

                this.couleur = c;
                break;

            }

        }

    }

    public char getCouleur() {
        return couleur;
    }

    public String fournirDetail() {

        return "Terrain - " + super.fournirDetail() + ", couleur : " + couleur;

    }
}
