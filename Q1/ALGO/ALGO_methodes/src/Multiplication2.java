public class Multiplication2 {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Tu vas faire des multiplications.");
		System.out.println("La valeur minimale des 2 nombres a multiplier est 0.");
		System.out.println("Tu peux choisir la valeur maximale de ces 2 nombres.");
		System.out.println("Cette valeur sera la meme pour tous les exercices");
		System.out.println("Pour chaque multiplication, tant que tu n'auras pas donne la bonne reponse, tu devras la recommencer");
		System.out.println("Le programme termine lorsque tu auras reussi 3 multiplications du premier coup");
	
		faireDeuxMultiplication();

	}

	public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){
		double nombreReel;
		int resultat;
		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;
	}

	/**
	 * fait 3 multiplications
	 * Génère deux nombres entre 0 et 10 : int
	 * Multiplie ces nombres pour calculer le résultat
	 * Compare ce résultat avec la réponse de l'utilisateur
	 * Si l'utilisateur a faux, il recommence jusqu'à ce qu'il ait juste
	 * Si l'utilisateur a réussi, on passe à la multiplication suivante
	 * Au bout de 3 multiplications réussies, le programme s'arrête
	 */
	public static void faireDeuxMultiplication(){

		int nombreEssais = 0;
		int nombreMultiplications = 2;

		for ( int i=1; i<=nombreMultiplications; i++ ){

			System.out.println( "\nCalcul n°" + i + "\n");

			int nombre1 = unEntierAuHasardEntre( 0, 10 );
			int nombre2 = unEntierAuHasardEntre( 0, 10 );

			System.out.print( nombre1 + " x " + nombre2 + " = " );

			int reponse = nombre1 * nombre2;
			int x = scanner.nextInt();

			while ( x != reponse ){

				System.out.println( "Faux, Recommencez" );
				System.out.print( nombre1 + " x " + nombre2 + " = " );

				x = scanner.nextInt();
				nombreEssais ++;

			}

			nombreEssais ++;
			System.out.println( "Bravo !" );

		}

		System.out.println( "Vous avez réussi les " + nombreMultiplications + " multiplications en " + nombreEssais + " essais !" );

	}

}
