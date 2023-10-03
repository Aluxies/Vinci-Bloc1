import java.util.Arrays;

public class TableauJeuDeLaVie {

	private boolean[][] table;
	private int longueur; 	// nb colonnes
	private int largeur; 	// nb lignes

	/**
	 * Cree un tableau sans aucune cellule vivante.
	 * @param longueur
	 * @param largeur
	 */
	public TableauJeuDeLaVie(int largeur, int longueur) {
		table = new boolean[largeur][longueur];
		this.largeur = largeur;
		this.longueur = longueur;
	}

	public void setXY(int x, int y, boolean enVie){
		table[x][y] = enVie;
	}

	public boolean getXY(int x, int y){
		return table[x][y];
	}

	/**
	 * Calcule le nombre de voisines vivantes de la cellule x, y
	 * @param x
	 * @return Le nombre de voisines vivantes
	 */
	public int nbVoisines(int x, int y){

		int largeur = table.length;
		int longueur = table[0].length;

		int nbVoisines = 0;

		for ( int i=x-1; i<=x+1; i++ ){

			if ( i < 0 || i > largeur-1 ) continue;

			for ( int j=y-1; j<=y+1; j++ ){

				if ( j < 0 || j > longueur-1 ) continue;

				if ( i == x && j == y ) continue;

				if ( table[i][j] == true ) nbVoisines++;

			}

		}

		return nbVoisines;

	}

	/**
	 * Calcule l'etat d'une cellule a la generation suivante.
	 * Les regles suivantes sont appliquees:
	 * - Une cellule morte possedant exactement trois voisines vivantes devient vivante (elle nait).
	 * - Une cellule vivante possedant deux ou trois voisines vivantes le reste, sinon elle meurt.
	 * @param x
	 * @param y
	 * @return true si la cellule en x, y sera en vie a la generation suivante
	 */
	private boolean enVieGenerationSuivante(int x, int y) {
		// TODO
		return false;
	}


	/**
	 * Calcule le tableau de jeu a la generation suivante
	 * en faisant vivre, mourir et naitre des cellules.
	 */
	public void generationSuivante(){
		// TODO
		// Il est indispensable de travailler avec une copie de la table
	}
	
}