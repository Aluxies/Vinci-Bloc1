public class Guerrier {
	
	private String nom;
	private int pointsDeVie;
	private int niveauBouclier;
	private Boolean etatBouclier = true;
	
	public Guerrier( String newNom, int newPointsDeVie, int newNiveauBouclier ) {

		nom = newNom;
		pointsDeVie = newPointsDeVie;
		niveauBouclier = newNiveauBouclier;

		if ( newNiveauBouclier == 0 ) etatBouclier = false;

	};

	public String getNom() {

		return nom;

	};
	
	public int getPointsDeVie() {

		return pointsDeVie;

	};

	public int getNiveauBouclier(){

		return niveauBouclier;

	};

	public Boolean getEtatBouclier(){

		return etatBouclier;

	};

	public int absorberDegats( int degats ){

		etatBouclier = false;

		if ( degats >= niveauBouclier ) return niveauBouclier;
		else return degats;

	};

	public int eviterDegats(){

		etatBouclier = false;

		return 0;

	};

	public void retierPointsDeVie( int degats ){

		int difference = pointsDeVie - degats;

		if ( difference >= 0 ) pointsDeVie -= degats;
		else pointsDeVie = 0;

	};

	public Boolean estVivant(){

		if ( pointsDeVie > 0 ) return true;
		else return false;

	};

	public String toString() {

		return nom + " a " + pointsDeVie + " points de vie";

	};

};