
public class ArbreDEntiersPlus extends ArbreDEntiers {

	public ArbreDEntiersPlus () {
		super();
	}

	public ArbreDEntiersPlus (ArbreDEntiersPlus sousArbreGauche, int entier, ArbreDEntiersPlus sousArbreDroit) {
		super(sousArbreGauche, entier, sousArbreDroit);
	}

	public ArbreDEntiersPlus (int entier) {
		super(new ArbreDEntiersPlus(),entier,new ArbreDEntiersPlus());
	}

	public int hauteur () {

		return hauteur( racine );

	}

	private int hauteur( NoeudEntier noeud ){

		// On traite le cas où l'arbre est vide
		// et donc la racine est à null

		if ( noeud == null ) return -1;

		int hauteurG = hauteur( noeud.gauche );
		int hauteurD = hauteur( noeud.droit );

		System.out.println( hauteurG );
		System.out.println( hauteurD );

		if ( hauteurG < hauteurD ) return 1 + hauteurD;
		else return 1 + hauteurG;

	}

	public boolean estCompletementRempli () {
		//TODO
		// Ex supplementaire
		//La definition (non recursive!) de cette methode est donnee dans l'enonce
		return false;
	}

	public boolean estComplet () {
		//TODO
		// Ex supplementaire
		//La definition recursive! de cette methode est donnee dans l'enonce
		return false;
	}

}
