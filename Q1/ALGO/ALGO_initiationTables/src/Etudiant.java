
public class Etudiant{

	private double[] tableCotes;
	private int matricule;
	
	
	
	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS
	/**
	 * constructeur par recopie
	 * @param matricule
	 * @param tableARecopier
	 * @throws IllegalArgumentException si la table est null ou si la table ne contient aucune cote ou si le contenu ne correspond pas a des cotes
	 */
	public Etudiant(int matricule, double[] tableARecopier){	
		if( tableARecopier==null)
			throw new IllegalArgumentException("table null");
		if(tableARecopier.length == 0)
			throw new IllegalArgumentException("aucune cote");
		this.matricule = matricule;
		this.tableCotes = new double[tableARecopier.length];
		for(int i = 0; i < tableCotes.length; i++) {
			if(tableARecopier[i]<0||tableARecopier[i]>20)
				throw new IllegalArgumentException("cote non comprise entre 0 et 20");
			tableCotes[i] = tableARecopier[i];
		}			
	}

	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS
	public String toString(){
		String aRenvoyer = "\nMatricule : "+matricule+"\nCotes : ";
		for(int i = 0; i < tableCotes.length; i++) 
			aRenvoyer = aRenvoyer + "  " + tableCotes[i];
		return aRenvoyer;
	}

	
	/**
	 * 						
	 * @param coteMin
	 * @param coteMax
	 * @return
	 * @throws IllegalArgumentException 
	 */
	public int nombreDeCotesEntre(int coteMin, int coteMax){
		if(coteMin<0||coteMin>20)
			throw new IllegalArgumentException("coteMin non comprise entre 0 et 20");
		if(coteMax<0||coteMax>20)
			throw new IllegalArgumentException("coteMax non comprise entre 0 et 20");
		if(coteMin > coteMax)
			throw new IllegalArgumentException("coteMin > coteMax");
		int compteur = 0;
		for (int i = 0; i < tableCotes.length; i++) {
			if(tableCotes[i]>=coteMin && tableCotes[i]<=coteMax)
				compteur++;
		}
		return compteur;
	}
	
	
	/**
	 * compte le nombre d'occurrence de la cote passee en parametre
	 * @param coteRecherchee
	 * @return le nombre de cotes egales a la cote passee en parametre
	 * @throws IllegalArgumentException si la cote recherchee n'est pas comprise entre 0 et 20 
	 */
	public int nombreCotesEgalesA(double coteRecherchee){
		if(coteRecherchee<0||coteRecherchee>20)
			throw new IllegalArgumentException("cote non comprise entre 0 et 20");
		int compteur = 0;
		for (int i = 0; i < tableCotes.length; i++) {
			if(tableCotes[i]==coteRecherchee)
				compteur++;
		}
		return compteur;
	}
	
	/**
	 * calcule la moyenne des cotes
	 * @return la moyenne
	 */
	public double moyenne(){

		double somme = 0;

		for (int i = 0; i < tableCotes.length; i++) {

			somme += tableCotes[i];

		}

		double moyenne = somme / tableCotes.length;

		return moyenne;
	}
	

	/**
	 * calcule le nombre de cotes < 10
	 * @return le nombre de cotes < 10
	 */
	public int nombreEchecs(){

		int compteurNombreEchecs = 0;

		double coteMax = 20.0;

		for (int i = 0; i < tableCotes.length; i++) {

			if ( tableCotes[i] < coteMax / 2 ) compteurNombreEchecs++;

		}

		return compteurNombreEchecs;
	}


	/**
	 * recherche la plus petite cote
	 * @return la plus petite cote
	 */
	public double min(){

		double coteMin = 20;

		for (int i = 0; i < tableCotes.length; i++) {

			if ( tableCotes[i] < coteMin ) coteMin = tableCotes[i];

		}

		return coteMin;
	}


	/**
	 * recherche la plus grande cote
	 * @return la plus grande cote
	 */
	public double max(){

		double coteMax = 0;

		for (int i = 0; i < tableCotes.length; i++) {

			if ( tableCotes[i] > coteMax ) coteMax = tableCotes[i];

		}

		return coteMax;
	}

	public int nombreUEValidees(){

		int nombreUEValidees = 0;

		for (int i = 0; i < tableCotes.length; i++) {

			if ( tableCotes[i] >= 10) nombreUEValidees++;

		}

		return nombreUEValidees;

	}
}