import java.util.ArrayList;

public class EnsembleTrieImpl<E> implements EnsembleTrie<E> {

	private Noeud racine;
	private int taille;

	public EnsembleTrieImpl() {
		racine = null;
		taille = 0;
	}

	public boolean estVide() {
		return racine == null;
	}

	public int taille() {
		return taille;
	}

	public E min() {

		if ( estVide() ) return null;

		return min( racine );

	}

	private E min( Noeud noeud ) {

		if ( noeud.gauche == null ) return noeud.element;

		return min( noeud.gauche );

	}

	public boolean contient(E element) {
		
		return contient( racine, element );

	}

	private boolean contient( Noeud noeud, E element ){

		if ( noeud == null ) return false;
		if ( ( (Comparable<E>) element ).compareTo( noeud.element ) == 0 ) return true;
		else if ( ( (Comparable<E>) element ).compareTo( noeud.element ) < 0 ){

			return contient( noeud.gauche, element );

		} else return contient( noeud.droit, element );

	}

	public boolean ajouter(E element) {

		if ( racine == null ){
			racine = new Noeud( element );
			taille++;
			return true;
		}

		return ajouter( element, racine );
	}

	private boolean ajouter( E element, Noeud noeud ){

		if ( noeud == null ) return false;

		if ( ( (Comparable<E>) element ).compareTo( noeud.element ) == 0 ) return false;

		if ( ( (Comparable<E>) element ).compareTo( noeud.element ) < 0 ){

			if ( noeud.gauche == null ){

				noeud.gauche = new Noeud( element );
				taille++;
				return true;

			}
			else return ajouter( element, noeud.gauche );

		} else {

			if ( noeud.droit == null ){
				noeud.droit = new Noeud( element );
				taille++;
				return true;
			}
			else return ajouter( element, noeud.droit );

		}
	}

	public E predecesseur(E element) {

		ArrayList arrayList = new ArrayList();

		remplirListe( racine, arrayList );

		int index = arrayList.indexOf( element );

		if ( index <= 0 ) return null;

		return (E) arrayList.get( index -1 );

	}

	private void remplirListe(Noeud noeud,ArrayList<E> liste) {

		if ( noeud == null ) return;

		remplirListe( noeud.gauche, liste );
		liste.add( noeud.element );
		remplirListe( noeud.droit, liste );

	}

	// A NE PAS MODIFIER!!!
	// VA SERVIR POUR LES TESTS!!!
	public String toString() {
		return "[ " + toString(racine) + " ]";
	}

	private String toString(Noeud n) {
		if (n == null)
			return "";
		if (n.gauche == null && n.droit == null)
			return "" + n.element;
		if (n.gauche == null)
			return " [ ] " + n.element + " [ " + toString(n.droit) + " ] ";
		if (n.droit == null)
			return " [ " + toString(n.gauche) + " ] " + n.element + " [ ] ";
		return " [ " + toString(n.gauche) + " ] " + n.element + " [ " + toString(n.droit) + " ] ";
	}

	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS
	// permet de construire l'ensembleTrie de l'enonce

	public EnsembleTrieImpl(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
		Noeud nG = new Noeud(null, e2, new Noeud(e5));
		Noeud nG1 = new Noeud(new Noeud(e7), e4, new Noeud(e6));
		Noeud nD = new Noeud(nG1, e3, null);
		racine = new Noeud(nG, e1, nD);
		taille = 7;
	}


	public class Noeud {

		private E element;
		private Noeud gauche;
		private Noeud droit;

		private Noeud(E element) {
			this.element = element;
			this.gauche = null;
			this.droit = null;
		}

		private Noeud(Noeud gauche, E element, Noeud droit) {
			this.element = element;
			this.gauche = gauche;
			this.droit = droit;
		}
	}

}
