import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListeSDImpl<E> implements ListeSD<E>,Iterable<E> {

	private Noeud tete, queue;
	private HashMap<E, Noeud> mapElementNoeud;

	public ListeSDImpl () {

		queue = new Noeud();
		tete = new Noeud();

		queue.suivant = null;
		queue.precedent = tete;

		tete.suivant = queue;
		tete.precedent = null;

		mapElementNoeud = new HashMap<>();

	}

	public int taille () {
		return mapElementNoeud.size();
	}

	public boolean estVide () {

		return taille() == 0;
	
	}

	public boolean contient (E element) {

		return mapElementNoeud.containsKey( element );

	}

	public E premier() {

		if ( estVide() ) return null;

		return tete.suivant.element;

	}

	public E dernier() {

		if ( estVide() ) return null;

		return queue.precedent.element;

	}

	public E donnerPrecedent (E element) {

		if ( !contient( element ) ) return null;

		Noeud noeud = mapElementNoeud.get( element );

		Noeud noeudPrecedent = noeud.precedent;

		return noeudPrecedent.element;

	}

	public E donnerSuivant (E element) {

		if ( !contient( element ) ) return null;

		Noeud noeud = mapElementNoeud.get( element );
		Noeud noeudSuivant = noeud.suivant;

		return noeudSuivant.element;

	}

	public boolean insererEnTete (E element){

		if ( contient( element ) ) return false;

		Noeud nouveauNoeud = new Noeud( element );
		Noeud noeudAvant = tete;
		Noeud noeudApres = tete.suivant;

		nouveauNoeud.precedent = noeudAvant;
		nouveauNoeud.suivant = noeudApres;

		noeudAvant.suivant = nouveauNoeud;
		noeudApres.precedent = nouveauNoeud;

		mapElementNoeud.put( element, nouveauNoeud );

		return true;

	}

	public boolean insererEnQueue (E element) {

		if ( contient( element ) ) return false;

		Noeud nouveauNoeud = new Noeud( element );
		Noeud noeudAvant = queue.precedent;
		Noeud noeudApres = queue;

		nouveauNoeud.precedent = noeudAvant;
		nouveauNoeud.suivant = noeudApres;

		noeudAvant.suivant = nouveauNoeud;
		noeudApres.precedent = nouveauNoeud;

		mapElementNoeud.put( element, nouveauNoeud );

		return true;

	}

	public boolean insererApres (E element, E elementAInserer) {

		if ( contient( elementAInserer ) ) return false;
		if ( !contient( element ) ) return false;

		Noeud noeud = mapElementNoeud.get( element );
		Noeud nouveauNoeud = new Noeud( elementAInserer );

		Noeud noeudAvant = noeud;
		Noeud noeudApres = noeud.suivant;

		nouveauNoeud.precedent = noeudAvant;
		nouveauNoeud.suivant = noeudApres;

		noeudAvant.suivant = nouveauNoeud;
		noeudApres.precedent = nouveauNoeud;

		mapElementNoeud.put( elementAInserer, nouveauNoeud );

		return true;

	}

	public boolean insererAvant (E element, E elementAInserer) {

		if ( contient( elementAInserer ) ) return false;
		if ( !contient( element ) ) return false;

		Noeud noeud = mapElementNoeud.get( element );
		Noeud nouveauNoeud = new Noeud( elementAInserer );

		Noeud noeudAvant = noeud.precedent;
		Noeud noeudApres = noeud;

		nouveauNoeud.precedent = noeudAvant;
		nouveauNoeud.suivant = noeudApres;

		noeudAvant.suivant = nouveauNoeud;
		noeudApres.precedent = nouveauNoeud;

		mapElementNoeud.put( elementAInserer, nouveauNoeud );

		return true;

	}


	public boolean supprimer (E element) {

		if ( !contient( element ) ) return false;

		Noeud noeud = mapElementNoeud.get( element );

		Noeud noeudAvant = noeud.precedent;
		Noeud noeudApres = noeud.suivant;

		noeudAvant.suivant = noeudApres;
		noeudApres.precedent = noeudAvant;

		mapElementNoeud.remove( element );

		return true;

	}

	
	public boolean permuter (E element1, E element2) {

		if ( !contient( element1 ) ) return false;
		if ( !contient( element2 ) ) return false;

		Noeud noeudElement1 = mapElementNoeud.get( element1 );
		Noeud noeudElement2 = mapElementNoeud.get( element2 );

		E e1 = noeudElement1.element;
		E e2 = noeudElement2.element;

		noeudElement1.element = e2;
		noeudElement2.element = e1;

		mapElementNoeud.replace( e2, noeudElement2, noeudElement1 );
		mapElementNoeud.replace( e1, noeudElement1, noeudElement2 );

		return true;

	}

	public Iterator<E> iterator() {

		return new IterateurImpl<>();
		// il faut renvoyer un objet de type Iterator :
		//return new IterateurImpl<E>();
		// completez la classe interne IterateurImpl !
	}

	public String toString () {
		String aRenvoyer = "";
		int num = 1;
		Noeud baladeur = tete.suivant;
		while (baladeur != queue) {
			aRenvoyer += num + " - " + baladeur.element + "\n";
			baladeur = baladeur.suivant;
			num++;
		}
		return aRenvoyer;   
	}



	// Classe interne Noeud
	private class Noeud{
		private E element;
		private Noeud suivant;
		private Noeud precedent;

		private Noeud() {
			this(null, null, null);
		}

		private Noeud(E element) {
			this(null, element, null);
		}

		private Noeud(Noeud precedent, E element, Noeud suivant) {
			this.element = element;
			this.suivant = suivant;
			this.precedent = precedent;
		}
	}

	

	// Classe interne IterateurImpl
	private class IterateurImpl<E> implements Iterator<E>{

		private Noeud noeudCourant;

		private IterateurImpl() {

			noeudCourant = tete.suivant;

		}

		public boolean hasNext() {

			return  noeudCourant != queue;

		}

		// renvoie l element qui se trouve dans le noeud courant
		// le noeud courant passe au noeud suivant
		public E next() {

			if ( !hasNext() ) throw new NoSuchElementException();

			E elementCourant = (E) noeudCourant.element;

			noeudCourant = noeudCourant.suivant;

			return elementCourant;

		}

		// A NE PAS COMPLETER : Les suppressions sont interdites
		public void remove() {
			throw new UnsupportedOperationException();			
		}
	}

	// pour les tests
	public ListeSDImpl(E[] tableACopier) {
		mapElementNoeud = new HashMap<E, Noeud>();
		tete = new Noeud();   // sentinelle de tete
		queue = new Noeud();  // sentinelle de queue
		Noeud prec = tete;
		for (int i = 0; i < tableACopier.length; i++) {
			Noeud nouveauNoeud = new Noeud(tableACopier[i]);
			mapElementNoeud.put(tableACopier[i], nouveauNoeud);
			nouveauNoeud.precedent = prec;
			prec.suivant = nouveauNoeud;
			prec = nouveauNoeud;
		}
		prec.suivant = queue;
		queue.precedent = prec;
	}

	// pour les tests
	public String teteQueue(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = tete.suivant;
			int cpt=0;
			while (baladeur != queue) {
				if(cpt==0)
					aRenvoyer += baladeur.element;
				else
					aRenvoyer += ","+baladeur.element;
				baladeur = baladeur.suivant;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

	// pour les tests
	public String queueTete(){
		try{
			String aRenvoyer = "(";
			Noeud baladeur = queue.precedent;
			int cpt=0;
			while (baladeur != tete) {
				if(cpt==0)
					aRenvoyer += baladeur.element;
				else
					aRenvoyer += ","+baladeur.element;
				baladeur = baladeur.precedent;
				cpt++;
				if(cpt==100){
					return "boucle infinie";
				}
			}
			return aRenvoyer+")";
		}catch (NullPointerException e){
			return "nullPointerException";
		}
	}

}
