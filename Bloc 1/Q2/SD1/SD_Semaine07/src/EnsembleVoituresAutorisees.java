import java.util.HashMap;
import java.util.HashSet;


public class EnsembleVoituresAutorisees {

	private HashMap<String, Proprietaire> ensemble;
	

	/**
	 * construit un ensemble vide
	 */
	public EnsembleVoituresAutorisees(){

		ensemble = new HashMap<String, Proprietaire>();

	}

	/**
	 * ajoute une voiture a condition que celle-ci ne soit pas deja presente
	 * @param plaque la plaque de la voiture a ajouter
	 * @param proprietaire le proprietaire de la voiture a ajouter
	 * @return true si la voiture n'etait pas encore presente, false sinon
	 */
	public boolean ajouterVoiture(String plaque, Proprietaire proprietaire){

		if ( voitureAutorisee( plaque ) ) return false;

		ensemble.put( plaque, proprietaire );

		return true;
	
	}


	/**
	 * retire une voiture a condition que celle-ci soit presente
	 * @param plaque la plaque de la voiture a ajouter
	 * @return true si la voiture etait presente, false sinon
	 */
	public boolean retirerVoiture(String plaque){

		if ( !voitureAutorisee( plaque ) ) return false;

		ensemble.remove( plaque );

		return true;
		
	}


	
	/**
	 * verifie si la voiture est autorisee car presente dans l'ensemble
	 * @param plaque la plaque de la voiture a verifier
	 * @return true si la voiture est presente dans l'ensemble, false sinon
	 */
	public boolean voitureAutorisee(String plaque){

		return ensemble.containsKey( plaque );
		
	}
	
	/**
	 * renvoie le proprietaire de la voiture
	 * @param plaque la plaque de la voiture recherchee
	 * @return le proprietaire ou null si la plaque n'est pas dans l'ensemble
	 */
	public Proprietaire donnerProprietaire(String plaque){

		return ensemble.get( plaque );

	}
	
	public String toString(){

		String string = "";

		for ( String key : ensemble.keySet() ){

			string += "Key : " + key  + " | Value : " + donnerProprietaire( key ).getNom() + "\n";

		}

		return string;

	}
}
