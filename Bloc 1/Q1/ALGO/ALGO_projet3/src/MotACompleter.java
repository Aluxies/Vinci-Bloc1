
public class MotACompleter {
	
	private char[] motATrouver;   // le mot a trouver
	private char[] motACompleter; // le mot a completer

	/**
	 * au depart le mot a completer contient uniquement des '_'
	 * @param motATrouver
	 */
	public MotACompleter(String motATrouver) {		
		this.motATrouver= motATrouver.toUpperCase().toCharArray();
		this.motACompleter = new char[motATrouver.length()];
		for (int i = 0; i < motACompleter.length; i++) {
			motACompleter[i] = '_';
		}
	}

	public String motATrouver() {
		return String.copyValueOf(motATrouver);
	}

	public String motACompleter() {
		return String.copyValueOf(motACompleter);
	}

	/**
	 * renvoie une chaine de caracteres avec le mot a completer en mettant un espace entre chaque lettre
	 * @return le mot a completer pour affichage
	 */
	public String toString(){
		if(motACompleter.length==0)
			return "";
		String aAfficher = ""+motACompleter[0];
		for (int i = 1; i < motACompleter.length; i++) {
			aAfficher += " "+ motACompleter[i];
		}
		return aAfficher;
	}
	
	/**
	 * verifie si le mot a completer ne contient plus de _
	 * @return true si le mot est complet, false sinon
	 */
	public boolean estComplet() {

		for ( int i=0; i<motACompleter.length; i++ ){

			if ( motACompleter[i] == '_' ) return false;

		}

		return true;
	}

	/**
	 * verifie si le mot a trouver contient la lettre passee en parametre
	 * @param lettre la lettre recherchee
	 * @return true si la lettre a ete trouvee, false sinon 
	 */
	public boolean contientLettre(char lettre){
		lettre = Character.toUpperCase(lettre);

		for ( int i=0; i<motATrouver.length; i++ ){

			if ( motATrouver[i] == lettre ) return true;

		}

		return false;
	}
	
	/**
	 * ajoute (ou ecrase) la lettre dans le mot a completer autant de fois qu'il se trouve
	 * dans le mot a trouver en respectant leur position
	 * ex : mot a trouver COLIBRI - mot a completer  C_L_BR_ - lettre I
	 *      mot a completer -> C_LIBRI
	 * @param lettre la lettre a ajouter
	 */
	public void ajouterLettre(char lettre){
		lettre = Character.toUpperCase(lettre);

		for ( int i=0; i<motACompleter.length; i++ ){

			if ( motATrouver[i] == lettre ){

				motACompleter[i] = lettre;

			}

		}

	}

	/**
	 * ajoute la premiere lettre et la derniere lettre du mot a trouver au mot a completer autant de fois que necessaire
	 * ex : mot a trouver COLIBRI - mot a completer  __L_BR_ --> C_LIBRI
	 */
	public void ajouterPremiereEtDerniereLettres(){

		int premierIndice = 0;
		int dernierIndice = motATrouver.length - 1;

		ajouterLettre( motATrouver[premierIndice] );
		ajouterLettre( motATrouver[dernierIndice] );

		//TODO
		// suggestion : utiliser la methode ajouterLettre()
	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public MotACompleter(String motATrouver,String motACompleter) {
		this.motATrouver= motATrouver.toUpperCase().toCharArray();
		this.motACompleter = motACompleter.toUpperCase().toCharArray();
	}

}
