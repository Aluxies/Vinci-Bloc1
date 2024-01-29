import java.util.Arrays;

public class NoirJauneRouge {
	
	private Ecolier[] tableTriee;   // table triee d'abord les noirs puis les jaunes puis les rouges
										// Pas de doublons
	private int nombreNoirs;
	private int nombreJaunes;
	private int nombreRouges;
	private static final int NOMBRE_MAX_ECOLIERS = 10;
	
	
	public NoirJauneRouge(){
		this.tableTriee = new Ecolier[NOMBRE_MAX_ECOLIERS];
		this.nombreNoirs = 0;
		this.nombreJaunes = 0;
		this.nombreRouges = 0;
	}
	


	

	/**
	 * determine le nombre d ecoliers contenu dans la table
	 * @return le nombre d ecoliers
	 */
	public int nombreEcoliers(){
		return nombreNoirs + nombreJaunes + nombreRouges;
	}
	

	private int trouverIndiceEcolier(String nom){

		// methode private
		// recherche a quel indice de la table se trouve l'ecolier dont le nom est passe en parametre
		// si le nom n'est pas dans la table, la methode renvoie -1

		for (int i = 0; i < nombreEcoliers(); i++) {
			if(tableTriee[i].getNom().equals(nom))
				return i;
		}
		return -1;
		
	}

	/**
	 * ajoute l ecolier, s'il n'y a pas d ecolier avec ce nom et si le nombre max d ecoliers n'est pas atteint
	 * @param nom
	 * @return true si l'ajout a pu etre fait, false sinon
	 */
	public boolean ajouter(String nom, char couleur){
		
		if(nom == null||nom.equals(""))
			throw new IllegalArgumentException();
		
		if(couleur!='n' && couleur!='j' && couleur!='r')
			throw new IllegalArgumentException();

		int nombreEcoliers = nombreEcoliers();

		if ( trouverIndiceEcolier( nom ) != -1 ) return false;

		if ( NOMBRE_MAX_ECOLIERS <= nombreEcoliers ) return false;

		Ecolier ecolier = new Ecolier( nom, couleur );

		// Valeur du premier rouge

		Ecolier premierRouge = tableTriee[nombreNoirs+nombreJaunes];

		// Valeur du premier jaune

		Ecolier premierJaune = tableTriee[nombreNoirs];

		if ( couleur == 'n' ){

			// On met le premier rouge dans la case vide après le dernier rouge
			// La case du premier rouge devient vide

			tableTriee[nombreEcoliers] = premierRouge;

			// On met le premier jaune dans la case vide après le dernier jaune
			// La case du premier jaune devient vide

			tableTriee[nombreNoirs+nombreJaunes] = premierJaune;

			// On met le nouvel écolier dans la case vide après le dernier noir

			tableTriee[nombreNoirs] = ecolier;

			// On augmente le nombre de noirs vu que l'on a ajouté un écolier avec la couleur noire

			nombreNoirs++;

			return true;

		}

		if ( couleur == 'j' ){

			// On met le premier rouge dans la case vide après le dernier rouge
			// La case du premier rouge devient vide

			tableTriee[nombreEcoliers] = premierRouge;

			// On met le nouvel écolier dans la case vide après le dernier jaune

			tableTriee[nombreNoirs+nombreJaunes] = ecolier;

			// On augmente le nombre de jaunes vu que l'on a ajouté un écolier avec la couleur jaune

			nombreJaunes++;

			return true;

		}

		// On met le nouvel écolier dans la case vide après le dernier rouge

		tableTriee[nombreEcoliers] = ecolier;

		// On augmente le nombre de rouge vu que l'on a ajouté un écolier avec la couleur rouge

		nombreRouges++;

		return true;

	}
	
	
	/**
	 * supprime l ecolier dont le nom est passe en parametre
	 * @param nom le nom de l ecolier a supprimer
	 * @return true si l ecolier a ete supprime, false sinon
	 */
	public boolean supprimer(String nom){
		
		if(nom == null||nom.equals(""))
			throw new IllegalArgumentException();

		if ( trouverIndiceEcolier( nom ) == -1 ) return false;

		int indiceEcolier = trouverIndiceEcolier( nom );

		Ecolier ecolier = tableTriee[indiceEcolier];

		Ecolier dernierRouge = tableTriee[nombreEcoliers()-1];

		Ecolier dernierJaune = tableTriee[nombreNoirs+nombreJaunes-1];

		Ecolier dernierNoir = tableTriee[nombreNoirs-1];

		System.out.println( Arrays.toString( tableTriee ) );
		System.out.println( ecolier );

		if ( ecolier.getCouleur() == 'n' ){

			tableTriee[indiceEcolier] = dernierNoir;

			tableTriee[nombreNoirs-1] = dernierJaune;

			tableTriee[nombreNoirs+nombreJaunes-1] = dernierRouge;

			nombreNoirs--;

			return true;

		}

		if ( ecolier.getCouleur() == 'j' ){

			tableTriee[indiceEcolier] = dernierJaune;

			tableTriee[nombreNoirs+nombreJaunes-1] = dernierRouge;

			nombreJaunes--;

			return true;

		}

		tableTriee[indiceEcolier] = dernierRouge;

		nombreRouges--;

		return true;

		// Pensez a utiliser la methode trouverIndiceEcolier() donnee ci-dessus
		
		// CONTRAINTE :
		// Utilisez l'algorithme de suppression IMPOSE. 
		// Celui-ci est explique dans le document DrapeauBelge
		

	
	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public NoirJauneRouge(Ecolier[] tableARecopier, int nombreNoirs, int nombreJaunes, int nombreRouges) {
		this.tableTriee = new Ecolier[NOMBRE_MAX_ECOLIERS];
		for (int i = 0; i < tableARecopier.length; i++) {
			tableTriee[i]=tableARecopier[i];
		}
		this.nombreNoirs = nombreNoirs;
		this.nombreJaunes = nombreJaunes;
		this.nombreRouges = nombreRouges;
	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public String toString() {
		String aRenvoyer="";
		for (int i = 0; i < nombreEcoliers(); i++) {
			aRenvoyer += "\n" + tableTriee[i];
		}
		return aRenvoyer;
	}

	public int getNombreNoirs() {
		return nombreNoirs;
	}

	public int getNombreJaunes() {
		return nombreJaunes;
	}

	public int getNombreRouges() {
		return nombreRouges;
	}

}
