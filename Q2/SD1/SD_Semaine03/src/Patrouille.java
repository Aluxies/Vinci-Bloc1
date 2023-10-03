
public class Patrouille {

	private String nomPatrouille;
	private Noeud tete; 
	private int nombreScouts; // le CP est compris dans ce nombre

	/**
	 * construit une patrouille avec, comme CP, le scout passe en parametre
	 * @param nomPatrouille le nom de la patrouille
	 * @param CP le scout qui est le CP
	 * @throws IllegalArgumentException en cas de parametres null ou vide
	 */
	public Patrouille(String nomPatrouille, String CP){
		if(nomPatrouille == null || nomPatrouille.length()==0)
			throw new IllegalArgumentException();
		if(CP == null || CP.length()==0)
			throw new IllegalArgumentException();
	
		this.nomPatrouille = nomPatrouille;
		this.tete = new Noeud( CP );
		this.nombreScouts++;

	}
	
	public int getNombreScouts(){
		return nombreScouts;
	}

	/**
	 * ajoute le scout apres son CP	
	 * (les homonymes sont acceptes)
	 * @param scout le scout a ajouter
	 * @throws IllegalArgumentException en cas de parametre null ou vide
	 */

	public void ajouterScout(String scout) {
		// On vérifie si le paramètre scout est null ou vide
		if (scout == null || scout.length() == 0) {
			// Si c'est le cas, on lève une exception IllegalArgumentException
			throw new IllegalArgumentException();
		}

		/*	Généré par ChatGPT

		// On crée un nouveau Noeud contenant le nom du scout à ajouter
		Noeud nouveauNoeud = new Noeud( scout );

		// On ajoute le nouveau Noeud au début de la pile, en faisant pointer son attribut "suivant" vers l'ancienne tête de la pile
		nouveauNoeud.suivant = tete;
		tete = nouveauNoeud;

		// On incrémente le nombre de scouts dans la pile
		nombreScouts++;

		// Si le nombre de scouts dans la pile est supérieur à 1, on échange les positions du nouveau Noeud et de l'ancienne tête de la pile
		if (nombreScouts > 1) {
			Noeud temp = nouveauNoeud.suivant;
			nouveauNoeud.suivant = temp.suivant;
			temp.suivant = nouveauNoeud;
			tete = temp;
		}
		*/

		// Généré par moi-même

		// On crée un nouveau Noeud avec le nom du scout en paramètre et le pointeur suivant sur le Noeud qui était en tête de pile
		Noeud nouveauNoeud = new Noeud(scout, tete.suivant);

		// On met à jour le pointeur suivant du Noeud qui est en tête de pile pour qu'il pointe sur le nouveau Noeud créé
		tete.suivant = nouveauNoeud;

		// On incrémente le nombre de scouts dans la pile
		nombreScouts++;

	}
	

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public String  toString(){
		String aRenvoyer="["+tete.scout;
		Noeud baladeur = tete.suivant;
		int cpt = 0;
		while(baladeur!=null) {
			cpt++;
			if(cpt>nombreScouts){
				return "boucle infinie dans toString(), chainage a verifier";
			}
			aRenvoyer=aRenvoyer+ ", "+baladeur.scout;
			baladeur=baladeur.suivant;
		}
		return aRenvoyer+"]";

	}

	// Classe interne Noeud
	private class Noeud{
		
		private String scout;
		private Noeud suivant;
		
		public Noeud(String scout) {
			this.scout = scout;
		}

		public Noeud(String scout, Noeud suivant) {
			super();
			this.scout = scout;
			this.suivant = suivant;
		}		
	}
}
