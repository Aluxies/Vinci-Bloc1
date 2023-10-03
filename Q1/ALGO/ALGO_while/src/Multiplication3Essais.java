public class Multiplication3Essais {

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
	
		System.out.print("\nEntrainement aux multiplications. Voici une multiplication :");
		System.out.print("\nVous avez droit à 3 essais");

		int premierNombre = unEntierAuHasardEntre (0, 10);
		int secondNombre = unEntierAuHasardEntre (0, 10);

		int resultat = premierNombre * secondNombre;

		System.out.print("\n\nCalculez : " + premierNombre + " x " + secondNombre + " = ");

		int reponse = scanner.nextInt();

		int compteurEssais = 1;

		while ( compteurEssais < 3 && resultat != reponse ){

			System.out.print( "\nC'est faux !\nRecommencez : " );

			reponse = scanner.nextInt();

			compteurEssais ++;

		};

		if ( resultat == reponse ){

			System.out.print( "\nBravo ! Vous avez trouvé la réponse après " + compteurEssais + " essai(s) !" );

		} else if ( compteurEssais == 3 ){

			System.out.print( "\nVous avez effectué 3 essais sans succès !" );

		};

	};
	public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){

		double nombreReel;
		int resultat;

		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;

	};
	
};