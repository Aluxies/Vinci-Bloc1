import java.util.Arrays;
import java.util.NoSuchElementException;

public class QuestionNPS {
	
	private String question;
	private int[] tableReponses;
	
	// A NE PAS CHANGER
	// VA SERVIR POUR LES TESTS
	public QuestionNPS(String question, int[] tableARecopier){
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		//if(!contientReponses(tableARecopier))
		//	throw new IllegalArgumentException();
		this.question = question;
		this.tableReponses = new int[tableARecopier.length];
		for (int i = 0; i < tableARecopier.length; i++) {
			tableReponses[i]=tableARecopier[i];
		}
	}

	/**
	 * verifie si les entiers sont bien tous compris entre 0 et 10
	 * les reponses autorisees seront toujours comprises entre 0 et 10
	 * @param table la table a verifier
	 * @return true si tous les entiers sont des reponses, false sinon
	 * @throws IllegalArgumentException si la table est null
	 */
	public static boolean contientReponses(int[] table) {

		if ( table == null ) throw new IllegalArgumentException();

		for ( int i=0; i < table.length; i++ ){

			if ( table[i] > 10 || table[i] < 0 ) return false;

		}

		return true;

	}

	/**
	 * calcule la moyenne des reponses
	 * @return la moyenne
	 * @throws NoSuchElementException s'il n'y a pas de reponse
	 */
	public double moyenne(){

		if ( tableReponses.length == 0 ) throw new NoSuchElementException();

		double somme = 0;

		for ( int i=0; i < tableReponses.length; i++ ){

			somme += tableReponses[i];

		}

		double moyenne = somme / tableReponses.length;

		return moyenne;
	}

	/**
	 * renvoie le nombre de detracteurs
	 * un detracteur a donne une reponse <= 6
	 * @return le nombre de detracteurs
	 */
	public int nombreDetracteurs(){

		int nombreDetracteurs = 0;

		for (int i = 0; i < tableReponses.length; i++) {

			if ( tableReponses[i] <= 6 ) nombreDetracteurs++;

		}

		return nombreDetracteurs;

	}

	public int nombrePromoteurs(){

		int nombrePromoteurs = 0;

		for (int i = 0; i < tableReponses.length; i++) {

			if ( tableReponses[i] >= 9 ) nombrePromoteurs++;

		}

		return nombrePromoteurs;

	}

	/**
	 * calcule le score NPS 
	 * @return le score NPS
	 * @throws NoSuchElementException s'il n'y a pas de reponse
	 */
	public double scoreNPS(){

		if ( tableReponses.length == 0 ) throw new NoSuchElementException();

		double pourcentagePromoteurs = ( 100.0 * nombrePromoteurs() ) / tableReponses.length;
		double pourcentageDetracteurs = ( 100.0 * nombreDetracteurs() ) / tableReponses.length;

		double scoreNPS = pourcentagePromoteurs - pourcentageDetracteurs;

		return scoreNPS;

	}

	
	/**
	 * construit une table de frequences a partir des reponses reprises dans tableReponses
	 * @return la table de frequences
	 */
	public int[] tableFrequences(){

		int[] tableFrequences = new int[11];

		for (int i = 0; i < tableReponses.length; i++) {

			for ( int j=0; j <= 11; j++ ){

				if ( tableReponses[i] == j ) tableFrequences[j]++;

			}

		}

	    return tableFrequences;
	}


	/**
	 * recherche la mediane des reponses
	 * @return la mediane
	 * @throws NoSuchElementException s'il n'y a pas de reponse
	 */
	public int mediane(){

		if ( tableReponses.length == 0 ) throw new NoSuchElementException();

		int nombreReponses = tableReponses.length;

		int[] tableCopiee = new int[nombreReponses];

		for (int i = 0; i < nombreReponses; i++) {

			tableCopiee[i] = tableReponses[i];

		}

		int nombreEtapes = ( nombreReponses / 2 ) + 1;

		int mediane = 0;

		for (int i = 0; i < nombreEtapes; i++) {

			int min = 10;
			int indiceMin = 0;

			for ( int j=0; j < nombreReponses; j++ ){

				if ( tableCopiee[j] < min ){

					indiceMin = j;
					min = tableCopiee[indiceMin];

				}

			}

			mediane = tableCopiee[indiceMin];
			tableCopiee[indiceMin] = 11;

		}
		
		return mediane;
	}


	// A NE PAS CHANGER
	// VA SERVIR POUR LES TESTS
	public String toString(){
		String aRenvoyer = ""+question+" ";
		for (int i = 0; i < tableReponses.length; i++) {
			aRenvoyer += tableReponses[i] + " ";
		}
		return aRenvoyer;
	}

}
