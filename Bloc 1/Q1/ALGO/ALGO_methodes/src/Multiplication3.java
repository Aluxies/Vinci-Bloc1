public class Multiplication3 {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Tu vas faire 5 multiplications");
		System.out.println("Au depart la valeur minimale des nombres a multiplier est 1 et la valeur maximale est 10");
		System.out.println("Ces 2 bornes augmenteront de 10 a chaque multiplication reussie");
		System.out.println("Pour chaque multiplication, tu as droit a 1 essai");
		System.out.println("Tu recevras la reponse, si tu la rate");

		faireCinqMultiplication();

	}

	public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){
		double nombreReel;
		int resultat;
		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;
	}

	/**
	 * fait 5 multiplications
	 * borneMin = 1 : int
	 * borneMax = 10 : int
	 * Génère deux nombres entre borneMin et borneMax : int
	 * Augmente les bornes de 10 à chaque itération
	 * Si l'utilisateur a faux, la réponse est affichée
	 */
	public static void faireCinqMultiplication(){

		int bonneReponses = 0;
		int nombreMultiplications = 5;

		int borneMin = 1;
		int borneMax = 10;

		for ( int i=1; i<=nombreMultiplications; i++ ){

			System.out.println( "\nCalcul n°" + i + "\n");

			int nombre1 = unEntierAuHasardEntre( borneMin, borneMax );
			int nombre2 = unEntierAuHasardEntre( borneMin, borneMax );

			System.out.print( nombre1 + " x " + nombre2 + " = " );

			int reponse = nombre1 * nombre2;
			int x = scanner.nextInt();

			if ( x != reponse ){

				System.out.println( "Faux, la réponse est : " + reponse );

			} else {

				bonneReponses++;
				System.out.println( "Bravo !" );

			}

			borneMin += 10;
			borneMax += 10;

		}

		System.out.println( "\nVous avez réussi " + bonneReponses + " / "+ nombreMultiplications + " multiplications !" );

	}

}