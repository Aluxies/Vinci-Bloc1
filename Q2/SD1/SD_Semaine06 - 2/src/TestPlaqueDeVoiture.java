public class TestPlaqueDeVoiture {

	public final static int NBRE_LISTES = 2000;

	public static void main (String args[]) {

		int[] tablePlaques = new int[NBRE_LISTES];

		for (char i = 'A'; i <= 'Z'; i++){

			for (char j = 'A'; j <= 'Z'; j++){

				for (char k = 'A'; k <= 'Z'; k++){

					for (char l = '0'; l <= '9'; l++) {

						for (char m = '0'; m <= '9'; m++) {

							for (char n = '0'; n <= '9'; n++) {

								String plaque = "1"+i+j+k+l+m+n;

								Voiture voiture = new Voiture( plaque, "" );

								tablePlaques[Math.abs(voiture.hashCode() % NBRE_LISTES )]++;

							}

						}

					}

				}

			}

		}

		for (int i = 0; i < NBRE_LISTES; i++) {

			System.out.println( tablePlaques[i] );

		}

		// Attention, la methode hashCode() renvoie un entier
		// Cet entier pourrait etre negatif --> Math.abs()
		// Cet entier doit correspondre a une liste --> %NBRE_LISTES
	}
}