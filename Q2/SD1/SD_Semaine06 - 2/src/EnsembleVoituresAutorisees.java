public class EnsembleVoituresAutorisees{

	// TODO
	// Utilisez un objet de la classe EnsembleTableHashing

	private EnsembleTableHashing<Voiture> listeVoituresAutorisees;
	private int taille;
	private final int CAPACITE_INIT = 500;

	// construit un ensemble vide mais pouvant contenir jusqu'a 500 voitures
	public EnsembleVoituresAutorisees(){

		listeVoituresAutorisees = new EnsembleTableHashing<Voiture>(CAPACITE_INIT);

	}


	/**
	 * ajoute la voiture dans l ensemble des voitures autorisees
	 * @param voiture la voiture autorisee
	 * @return true si la voiture etait pas encore presente, false sinon
	 */
	public boolean ajouterVoiture(Voiture voiture){

		return listeVoituresAutorisees.ajouter( voiture );

	}

	/**
	 * retire la voiture de l ensemble des voitures autorisees
	 * @param voiture la voiture non autorisee
	 * @return true si la voiture etait presente, false sinon
	 */
	public boolean retirerVoiture(Voiture voiture){

		return listeVoituresAutorisees.enlever( voiture );

	}

	
	/**
	 * verifie si la voiture est presente dans l ensemble des voitures autorisees
	 * @param voiture la voiture a verifier
	 * @return true si la voiture est presente, false sinon
	 */
	public boolean voitureAutorisee(Voiture voiture){

		return listeVoituresAutorisees.contient( voiture );

	}




}