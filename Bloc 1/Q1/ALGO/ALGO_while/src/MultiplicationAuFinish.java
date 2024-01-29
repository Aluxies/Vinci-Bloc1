public class MultiplicationAuFinish {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("\nEntrainement aux multiplications. Voici une multiplication :");
		System.out.print("\nTant que vous n'avez pas trouvé la bonne réponse, tu dois la recommencer! ");

		int premierNombre = unEntierAuHasardEntre(0, 10);
		int secondNombre = unEntierAuHasardEntre(0, 10);

		int resultat = premierNombre * secondNombre;

		System.out.print("\nCalculez : " + premierNombre + " x " + secondNombre + " = ");

		int reponse = scanner.nextInt();
		int compteurEssais = 1;

		while ( resultat != reponse ){

			System.out.print( "\nC'est faux ! Recommencez : " );

			reponse = scanner.nextInt();

			compteurEssais ++;

		};

		if ( resultat == reponse ) System.out.print( "\nBravo ! Vous avez trouvé la réponse après " + compteurEssais + " essai(s) !" );

	};
	public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){
		double nombreReel;
		int resultat;

		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;

	};

};