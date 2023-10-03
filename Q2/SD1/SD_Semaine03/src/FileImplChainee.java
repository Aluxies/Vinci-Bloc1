public class FileImplChainee<E> implements File<E>{

	private Noeud tete;
	private Noeud queue;
	private int taille;

	public FileImplChainee(){
		tete=null;
		queue=null;
		taille=0;
	}

	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	// tete 'a' 'b' 'c' queue : ['a','b','c']
	public FileImplChainee(Object[] table) {
		if(table == null)
			throw new IllegalArgumentException();
		this.taille = table.length;

		if(taille==0)
			return;
		queue = new Noeud((E)table[table.length-1],null);
		Noeud noeud = queue;
		for (int i = table.length-2; i>=0;i--) {
			noeud = new Noeud((E)table[i],noeud);
		}
		this.tete=noeud;
	}

	// A ne pas modifier ! Methode utilisee pour les tests
	public String toString(){
		String aRenvoyer="";
		Noeud baladeur=tete;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>taille){
				return "boucle infinie dans toString(), chainage a verifier";
			}
			aRenvoyer+=" "+baladeur.element;
			baladeur=baladeur.suivant;
		}
		return aRenvoyer;
	}


	public boolean estVide(){
		return taille==0;
	}


	public int taille(){
		return taille;
	}

	
	public void enfile(E element){

		if ( element == null ) throw new IllegalArgumentException();

		Noeud elt = new Noeud( element );

		// Cas où il n'y a pas de tête, l'élément ajouté devient la tête
		if ( tete == null ) {

			tete = elt;
			queue = elt;

		} else {

		// Cas où il y a une tete -> il y a une queue :

			// Element ajouté va après la queue
			queue.suivant = elt;
			// Element ajouté devient la queue
			queue = queue.suivant;

		};

		taille++;

	}

	
	public E defile() throws FileVideException{

		if ( estVide() ) throw new FileVideException();

		E teteSupprimee = tete.element;

		tete = tete.suivant;

		taille--;

		return teteSupprimee;
	}

	
	public E premier()throws FileVideException{

		if ( estVide() ) throw new FileVideException();

		return tete.element;

	}

	// classe interne
	private class Noeud{
		private E element;
		private Noeud suivant;

		private Noeud(E element, Noeud suivant){
			this.element = element;
			this.suivant = suivant;
		}
		
		private Noeud(E element){
			this.element = element;
			this.suivant = null;
		}

	}

} 



