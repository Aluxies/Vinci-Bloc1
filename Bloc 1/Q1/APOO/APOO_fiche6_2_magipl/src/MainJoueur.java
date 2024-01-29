import java.util.ArrayList;

public class MainJoueur {

    private static ArrayList<Carte> cartesEnJeu = new ArrayList<Carte>();

    private ArrayList<Carte> cartes = new ArrayList<Carte>();

    public MainJoueur(){

        this.cartes = cartes;

    }

    public void piocher( Carte carte ){

        if ( cartes.size() == 10 ) throw new TropDeCartesException( "Vous avez le nombre maximal de cartes (10 cartes)" );
        if ( cartesEnJeu.contains( carte )) throw new IllegalArgumentException( "La carte est déjà présente dans une main !" );

        cartesEnJeu.add( carte );

        cartes.add( carte );

    }

    public Carte jouer( int numeroCarte ){

        int numeroMaximal = cartes.size();

        if ( numeroCarte < 0 || numeroCarte > numeroMaximal ) throw new IllegalArgumentException( "Le numéro de la carte ne peut être 'inférieur à 0 ou supérieur à " + numeroMaximal );

        Carte carteSelectionnee = cartes.get( numeroCarte );

        cartes.remove( carteSelectionnee );
        cartesEnJeu.remove( carteSelectionnee );

        return carteSelectionnee;

    }

    public String fournirDetail(){

        String string = "";

        for ( Carte c : cartes ){

            string += c.fournirDetail() + "\n";

        }

        return string;

    }

}