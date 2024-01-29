import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ServeurImpressions {

	// table de 10 files d'attente d'impression
	private ArrayDeque<DemandeImpression>[] tableFilesDAttente;
	
	/**
	 * construit une table avec 10 files d'attente d'impression
	 */
	public ServeurImpressions() {

		int nombreFiles = 10;

		tableFilesDAttente = new ArrayDeque[nombreFiles];

		for (int i = 0; i < nombreFiles; i++) {

			tableFilesDAttente[i] = new ArrayDeque<DemandeImpression>();

		};

	}
	
	/**
	 * verifie si toutes les files d'attente sont vides
	 * @return true si toutes les files sont vides, false sinon
	 */
	public boolean serveurVide(){

		for (ArrayDeque<DemandeImpression> file : tableFilesDAttente ) {

			if ( !file.isEmpty() ) return false;

		};

		System.out.println( Arrays.toString( tableFilesDAttente ) );

		return true;

	};
	
	/**
	 * ajoute la demande d'impression en fin de la file correspondante a sa priorite
	 * @param demande la demande a ajouter
	 * @throws IllegalArgumentException si la demande est null
	 */
	public void ajouter(DemandeImpression demande){

		if ( demande == null ) throw new IllegalArgumentException();

		tableFilesDAttente[demande.getPriorite()].add( demande );

	}
	
	/**
	 * retire la demande d'impression en tete de file de priorite la plus haute qui est non vide
	 * @return la demande d'impression qui a ete retiree
	 * @throws NoSuchElementException si aucune demande d impression dans le serveur
	 */
	public DemandeImpression retirer(){

		if ( serveurVide() ) throw new NoSuchElementException();

		for (int i = 9; i >= 0; i--) {

			if ( !tableFilesDAttente[i].isEmpty() ) return tableFilesDAttente[i].removeFirst();

		};

		return null;

	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	@Override
	public String toString() {
		return Arrays.toString(tableFilesDAttente) ;
	}

}


