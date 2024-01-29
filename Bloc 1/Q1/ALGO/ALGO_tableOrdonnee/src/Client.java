
public class Client {
	
	private String nom;
	private String prenom;
	private double[] tableMontants;			// table triee selon l'ordre decroissant des montants 
	private static final int TAILLE = 10;	// le nombre de montants retenus
//	private int nombreMontants = 0;  --> pour ex supplementaire
	
	/**
	 * construit un client avec ses 10 meilleurs montants = 0
	 * @param nom nom du client
	 * @param prenom prenom
	 */
	public Client (String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.tableMontants = new double[TAILLE];   // par defaut contient des 0!
	
		//this.nombreMontants = 0;   --> pour ex supplementaire
	}
	
	/**
	 * ajoute le montant si celui-ci fait partie des 10 meilleurs montants
	 * le tri de la table doit etre respecte
	 * @param montant le montant a eventuellement ajouter
	 * @return true si le montant a ete ajoute, false sinon
	 */
	public boolean ajouter (double montant){

		int indiceMontant = 0;

		for ( int i=0; i < tableMontants.length-1; i++ ){

			if ( montant <= tableMontants[i] ){

				indiceMontant = i;

				System.out.println( indiceMontant );

			}

			if ( tableMontants[i] != 0 && tableMontants[i] >= montant ){

				indiceMontant++;

			}

		}

		for ( int i=tableMontants.length-2; i >= indiceMontant; i-- ){

			tableMontants[i+1] = tableMontants[i];

		}

		tableMontants[indiceMontant] = montant;

		// TODO
		return true;
	}

	
	@Override
	public String toString() {
		String aRenvoyer = "" + nom + " " + prenom + "  montants : ";
		for (int i = 0; i < TAILLE; i++) {
			aRenvoyer += " "+tableMontants[i];
		}
		return aRenvoyer;
		
//      --> pour ex supplementaire		
//		String aRenvoyer = "" + nom + " " + prenom + "  montants : ";
//		for (int i = 0; i < nombreMontants; i++) {
//			aRenvoyer += " "+tableMontants[i];
//		}
//		return aRenvoyer;
	}
	
	
	
	
	
	
	
}
