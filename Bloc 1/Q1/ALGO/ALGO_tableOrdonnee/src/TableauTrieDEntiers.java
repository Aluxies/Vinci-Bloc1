import java.util.Arrays;

public class TableauTrieDEntiers{

	private int [] tableDEntiers;  // table d'entiers triee par ordre croissant
	private int nombreDEntiers;    // nombre d'entiers dans tableDEntiers
	private static final int TAILLE = 10;

	public TableauTrieDEntiers(){
		this.tableDEntiers = new int[TAILLE];
		this.nombreDEntiers = 0;
	}


	public int getNombreDEntiers(){
		return this.nombreDEntiers;
	}


	/** 
	 * methode qui ajoute un entier si la table n'est pas encore pleine.
	 * La table doit rester triee!
	 * @param entier l'entier a ajouter
	 * @return boolean signale si l'entier a pu etre ajoute
	 */

	public boolean ajouterUnEntier(int entier){

		if ( nombreDEntiers == tableDEntiers.length ) return false;

		int indiceEntier = 0;

		for ( int i = 0; i < nombreDEntiers; i++ ){

			if ( entier > tableDEntiers[i] ) indiceEntier++;
			else break;

		}

		for ( int i = nombreDEntiers-1; i >= indiceEntier; i-- ){

			tableDEntiers[i+1] = tableDEntiers[i];

		}

		tableDEntiers[indiceEntier] = entier;

		nombreDEntiers++;

		return true;

	}

	
	/**
	 * methode qui recherche l'indice correspondant a la premiere occurrence de l'entier passe en parametre
	 * @param entier l'entier recherche
	 * @return indice correspondant a l'entier, -1 s'il n'est pas dans la table
	 */
	private int trouverIndiceLineaire(int entier){
		
		//recherche sequentielle cout O(N)

		for (int i = 0; i < this.nombreDEntiers; i++) {
			if (this.tableDEntiers[i]==entier)
				return i;
			if (this.tableDEntiers[i]>entier)
				return -1;
		}
		return -1;
		
	}

	
	/** 
	 * methode qui verifie si la table contient l'entier passe en parametre
	 * @param entier l'entier recherche
	 * @return boolean true si l'entier est present dans la table, false sinon
	 */
	public boolean contient(int entier){

		return trouverIndiceLineaire( entier ) != -1;

	}  

	
	/** 
	 * methode qui supprime une occurrence de l'entier passe en parametre.
	 * La table doit rester triee!
	 * @param entier l'entier a supprimer
	 * @return boolean signale si l'entier a pu etre supprime
	 */
	public boolean supprimerUneOccurrence(int entier){

		if ( !contient( entier ) ) return false;

		int indiceEntier = trouverIndiceLineaire( entier );

		for ( int i = indiceEntier; i < nombreDEntiers-1; i++ ){

			tableDEntiers[i] = tableDEntiers[i+1];

		}

		nombreDEntiers--;

		return true;

	} 

	
	/** 
	 * methode qui supprime toutes les occurrences d'un entier
	 * @param entier l'entier a supprimer
	 * @return int le nombre de suppressions effectuees
	 */
	public int supprimerToutesLesOccurrences(int entier){

		int nombreSuppressions = 0;

		int indiceEntier = trouverIndiceLineaire( entier );

		if ( !contient( entier ) ) return nombreSuppressions;

		for ( int i = indiceEntier; i < nombreDEntiers; i++ ){

			if ( tableDEntiers[i] == entier ){

				for ( int j = indiceEntier; j < nombreDEntiers-1; j++ ){

					tableDEntiers[j] = tableDEntiers[j+1];

				}

				nombreDEntiers--;
				nombreSuppressions++;
				i--;

			}

		}

		return nombreSuppressions;

	}

	
	/** 
	 * methode qui verifie si la table contient des ex-aequos
	 * @return boolean true si la table contient des ex-aequos, false sinon
	 */
	public boolean contientExAequo(){

		for ( int i = 0; i < nombreDEntiers; i++ ){

			int nombreExAequos = 0;

			int entier = tableDEntiers[i];

			for ( int j = 0; j < nombreDEntiers; j++ ){

				if ( tableDEntiers[j] == entier ) nombreExAequos++;

			}

			if ( nombreExAequos > 1 ) return true;

		}

		return false;

	}

	
	/** 
	 * methode qui supprime tous les ex-aequos
	 * @return int le nombre de suppressions effectuees
	 */
	public int supprimerTousLesExAequos(){

		int nombreSuppressions = 0;

		for ( int i = 0; i < nombreDEntiers-1; i++ ){

			int entierCompare = tableDEntiers[i];

			int nombreExAequos = 0;

			for ( int j = i+1; j < nombreDEntiers; j++ ){

				if ( tableDEntiers[j] == entierCompare ) nombreExAequos++;

				if ( nombreExAequos > 0 ){

					tableDEntiers[j] = tableDEntiers[j+1];

					nombreSuppressions++;

					nombreDEntiers--;

				}

				System.out.println( Arrays.toString( tableDEntiers ) );

			}
		}

		return nombreSuppressions;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableauTrieDEntiers t = (TableauTrieDEntiers)obj;
		if (t.nombreDEntiers!=this.nombreDEntiers)
			return false;
		for (int i = 0; i < nombreDEntiers;i++)
			if (this.tableDEntiers[i]!=t.tableDEntiers[i])
				return false;
		return true;
	}


	public String toString(){
		if(nombreDEntiers==0)
			return "[]";
		String aRenvoyer = "["+tableDEntiers[0];
		for (int i = 1; i < nombreDEntiers; i++)
			aRenvoyer = aRenvoyer + ", " + this.tableDEntiers[i];
		return aRenvoyer+"]";
	}

	/**
	 * constructeur par recopie
	 * ce constructeur leve une exception si la table passee en parametre contient plus de 10 entiers
	 * utile pour les tests et/ou si elle n'est pas triee
	 * @param tableARecopier une table triee d'au plus TAILLE entiers triee par ordre croissant
	 */

	public TableauTrieDEntiers(int[] tableARecopier){
		if (tableARecopier.length>TAILLE)
			throw new IllegalArgumentException();
		this.nombreDEntiers = tableARecopier.length;
		tableDEntiers= new int[TAILLE];
		if(tableARecopier.length!=0)
			tableDEntiers[0] = tableARecopier[0];
		for (int i = 1; i<nombreDEntiers; i++){
			if(tableARecopier[i]<tableARecopier[i-1]){
				throw new IllegalArgumentException();
			}
			tableDEntiers[i] = tableARecopier[i];
		}
	}


}