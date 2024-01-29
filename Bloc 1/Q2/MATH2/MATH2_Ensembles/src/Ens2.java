import java.util.Arrays;

public class Ens2 extends EnsembleAbstrait {

	private Elt[] elements; // contient les elements de l'ensemble. Il ne peut pas y avoir de doublon.
	private int cardinal;

	public Ens2() {

		// Tableau d'éléments

		elements = new Elt[MAX];
		
	}

	/*public Ens2( EnsembleInterface ensembleInterface ){

		elements = new Elt[MAX];

	}*/

	public Ens2( Elt elt ){

		ajouter( elt );

	}

	public boolean estVide() {

		if ( cardinal == 0 ) return true;

		return false;
	}
	
	public Elt unElement() {

		if ( estVide() ) throw new MathException( "L'ensemble est vide !" );

		// Lorsque le cardinal est plus grand que 0, à l'indice 0 il y a toujours un élément

		Elt element = elements[0];

		System.out.println( Arrays.toString( elements ) );

		return element;

	}

	public boolean contient(Elt e) {

		if ( e == null ) throw new IllegalArgumentException();

		if ( estVide() ) return false;

		for (int i = 0; i < cardinal; i++) {

			if ( elements[i].equals( e ) ) return true;

		}

		return false;

	}

	public void ajouter(Elt e) {

		if ( e == null ) throw new IllegalArgumentException();

		if ( !contient( e ) ){

			elements[cardinal] = e;

			cardinal++;

		}
		
	}

	public void enlever(Elt e) {

		if ( e == null ) throw new IllegalArgumentException();

		// Pour supprimer il faut vérifier si il est présent, le retirer et ensuite tout décaler

		for (int i = 0; i < cardinal; i++) {

			if ( elements[i].equals( e ) ){

				elements[i] = elements[cardinal-1];
				cardinal--;
				break;

			}

		}
		
	}

	public int cardinal() {

		return cardinal;
		
	}

	public void complementer() {

		Ens2 tab = new Ens2();

		for (int i = 0; i < MAX; i++) {

			tab.ajouter( new Elt( i+1 ) );

		}

		for ( int i = 0; i < cardinal; i++ ){

			tab.enlever( elements[i] );

		}

		elements = tab.elements;
		cardinal = tab.cardinal;

	}

	public String toString() {

		String string = "{";

		for (int i = 0; i < cardinal; i++) {

			string += elements[i];

			if ( i != cardinal ) string += ",";

		}

		string += "}";

		return string;

	}

}
