import java.util.Arrays;

public class Ens1 extends EnsembleAbstrait {

	private boolean[] tabB; // e appartient à l'ensemble courant ssi tabE[e.val()] est à true.
	private int cardinal;

	public Ens1() {

		// MAX = 50
		// On veut l'ensemble = [ 0, 1, 2, ... , 48, 49, 50 ] -> indices
		// MAIS
		// Si on fait new boolean[MAX]
		// On a l'ensemble = [ 0, 1, 2, ... , 48, 49 ] -> indices
		// DONC
		// Si on fait new boolean[MAX+1]
		// On aura donc l'ensemble = [ 0, 1, 2, ... , 48, 49, 50 ] -> indices

		int longueur = MAX+1;

		tabB = new boolean[longueur];

	}

	/*public Ens1( EnsembleInterface ensembleInterface ){

		if ( ensembleInterface == null ) throw new IllegalArgumentException();

		int longueur = MAX+1;

		tabB = new boolean[longueur];

	}*/

	public Ens1( Elt elt ){

		if ( elt == null ) throw new IllegalArgumentException();

		int longueur = MAX+1;

		tabB = new boolean[longueur];

		tabB[elt.val()] = true;

		cardinal++;

	}
	
	public boolean estVide() {

		if ( cardinal == 0 ) return true;

		return false;

	}
	
	public Elt unElement() {

		if ( estVide() ) throw new MathException( "La suite est vide !" );

		// On commence à l'indice 1 car il n'y a jamais d'élément à l'indice 0
		// L'univers va de 1 à MAX

		for (int i = 1; i < MAX+1; i++) {

			if ( tabB[i] == true ) return new Elt( i );

		}

		return null;

	}

	public boolean contient(Elt e) {

		if ( e == null ) throw new IllegalArgumentException();

		return tabB[ e.val() ];

	}

	public void ajouter(Elt e) {

		if ( e == null ) throw new IllegalArgumentException();

		// On n'ajoute un élément que quand il n'y a pas d'élément à l'indice "e.val()"

		if ( tabB[e.val()] == false ){

			tabB[e.val()] = true;
			cardinal++;

		}
		
	}

	public void enlever(Elt e) {

		if ( e == null ) throw new IllegalArgumentException();

		// On n'ajoute un élément que quand il n'y a pas d'élément à l'indice "e.val()"

		if ( tabB[e.val()] == true ){

			tabB[e.val()] = false;
			cardinal--;

		}
		
	}

	public int cardinal() {

		return cardinal;

	}

	public void complementer() {

		for (int i = 1; i < MAX+1; i++) {

			// On inverse toutes les valeurs du tableau
			// false devient true
			// true devient false

			tabB[i] = !tabB[i];

		}

		cardinal = MAX-cardinal;
		
	}

	public String toString() {

		String string = "{";

		if ( cardinal == 0 ) return "{}";

		for (int i = 1; i < MAX+1; i++) {

			if ( i == MAX ){

				string += i;
				break;

			}

			if ( tabB[i] ){

				string += i + ",";

			}

		}

		return string + "}";
	}
	
}
