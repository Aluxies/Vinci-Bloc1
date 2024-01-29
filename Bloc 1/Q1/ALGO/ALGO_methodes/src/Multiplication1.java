public class Multiplication1{

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Tu vas faire 5 multiplications");
		System.out.println("La valeur minimale des nombres a multiplier est 0, la valeur maximale est 10");
		System.out.println("Pour chaque multiplication, tu as droit a 1 essai");
		System.out.println("Tu recevras la reponse, si tu la rate");
		System.out.println("A la fin, le programme affichera le nombre de bonnes reponses.");



		System.out.println( "Vous avez eu " + faireUneMultiplication() + " bonne(s) réponses !" );

	
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
	 * Génère deux nombres entre 0 et 10 : int
	 * Multiplie ces nombres pour calculer le résultat
	 * Compare une fois ( un essai ) ce résultat avec la réponse de l'utilisateur
	 * Si raté, affiche la bonne réponse
	 * @return nombre de bonnes réponses : int
	 */
	public static int faireUneMultiplication(){

		int nombreBonnesReponses = 0;
		int nombreMultiplications = 5;

		int nombre1 = unEntierAuHasardEntre( 0, 10 );
		int nombre2 = unEntierAuHasardEntre( 0, 10 );

		for ( int i=1; i<=nombreMultiplications; i++ ) {

			System.out.println( "\nCalcul n°" + i + "\n");
			System.out.print( nombre1 + " x " + nombre2 + " = " );

			int reponse = nombre1 * nombre2;
			int x = scanner.nextInt();

			if ( reponse != x ){

				System.out.println( "Faux ! La réponse est : " + reponse );

			} else {

				System.out.println( "Bravo !" );
				nombreBonnesReponses ++;

			}

		}

		return nombreBonnesReponses;

	}

}