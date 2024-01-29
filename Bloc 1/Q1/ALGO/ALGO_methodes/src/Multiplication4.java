public class Multiplication4 {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("Tu vas faire des multiplications de nombres compris entre 0 et 10");
		System.out.println("Tu peux choisir le nombre d'essais que tu auras pour chaque multiplication");
		System.out.println("Ce nombre sera le meme pour toutes les multiplications");
		System.out.println("Tu recevras la reponse, si tu ne l'as toujours pas trouvee au terme du nombre d'essais accorde");
		System.out.println("Apres chaque multiplication, le programme te demandera si tu en veux une autre");
	
		faireDesMultiplications();
	}


	public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){
		double nombreReel;
		int resultat;
		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;
	}

	/**
	 * fait x multiplications
	 * Génère deux nombres compris entre 0 et 10 : int
	 * Permet à l'utilisateur de choisir le nombre d'essais accordés pour tous les multiplications : int
	 * Au bout de x essais, si la réponse n'est toujours pas trouvée, la réponse est affichée
	 * Demande après chaque multiplication si l'utilisateur veut une autre multiplication
	 */
	public static void faireDesMultiplications(){

		int bonneReponses = 0;
		int nombreMultiplications = 0;

		System.out.print( "\nChoisisez le nombre d'essais par multiplication que vous souhaitez vous accorder : ");

		int nombreEssaisMax = scanner.nextInt();

		char continuer = 'o';

		while ( continuer == 'o' ){

			nombreMultiplications ++;

			System.out.println( "\nCalcul n°" + nombreMultiplications + "\n");

			int nombre1 = unEntierAuHasardEntre( 0, 10 );
			int nombre2 = unEntierAuHasardEntre( 0, 10 );

			System.out.print( nombre1 + " x " + nombre2 + " = " );

			int reponse = nombre1 * nombre2;
			int x = scanner.nextInt();

			int nombreEssais = 1;

			while ( x != reponse && nombreEssais < nombreEssaisMax ){

				nombreEssais ++;

				System.out.println( "Faux, recommencez : ");
				System.out.print( nombre1 + " x " + nombre2 + " = " );

				x = scanner.nextInt();

			}

			if ( x != reponse ){

				System.out.println( "Faux, la réponse est : " + reponse );

			} else {

				bonneReponses ++;
				System.out.println( "Bravo !" );

			}

			System.out.print( "Souhaitez vous continuer à faire des calculs ? (o/n) : ");

			continuer = scanner.next().charAt( 0 );

		}

		System.out.println( "\nVous avez réussi " + bonneReponses + " / "+ nombreMultiplications + " multiplications !" );

	}

}