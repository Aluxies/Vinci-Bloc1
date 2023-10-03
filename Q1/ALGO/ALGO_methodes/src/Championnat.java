
/**
 * Championnat.java
 * @author 
 * 
 */
public class Championnat {

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	
	public static void main(String[] args) {

		System.out.println( "Pour chaque concurrant, vous allez devoir entrer 8 cotes comprises entre 0 et 10.");
		System.out.println( "La moyenne se calcule après avoir soustrait la cote minimale et la cote maximale de la sommme." );
		System.out.println( "Un concurrant échoue si sa moyenne est inférieure à 8, sinon il réussit.\n" );

		char lettre = 'p';
		int nombreFinalistes = 0;


		do {

			double coteConcurent = resultatUnConcurrent();

			String motReussiteOuEchec;

			if ( coteConcurent >= 8 ){

				motReussiteOuEchec = "réussi";
				nombreFinalistes++;

			} else motReussiteOuEchec = "échoué";

			System.out.println( "La moyenne du concurrent est : " + coteConcurent );
			System.out.println( "Le concurrent a " + motReussiteOuEchec + " au championnat ! " );

			System.out.println( "Il y a " + nombreFinalistes + " finaliste(s)");

			System.out.println( "Encore un concurrent (o/n) ? " );

			lettre = Utilitaires.lireOouN();

		} while ( lettre != 'n' );


			
	
	}

	/**
	 * lit les cotes, calcule et renvoie le resultat d'un concurrent.
	 * Chaque concurrent est cote pour la presentation de son exercice, sur 10 points, par 8 membres du jury.
     * On elimine de ces cotes la plus haute et la plus basse. Le resultat d un concurrent est egal a�la moyenne des 6 cotes restantes.
	 * @return le resultat du concurrent
	*/
	public static double resultatUnConcurrent() {

		int nombreCotes = 8;

		int coteMin = 10;
		int coteMax = 0;
		int cote;
		double coteMoyenne = 0;

		for ( int i = 1; i <= nombreCotes; i++ ){

			System.out.print( "Cote n°" + i + "/8 ( de 0 à 10 ) : " );

			cote = Utilitaires.lireEntierComprisEntre( 0, 10 );

			if ( cote > coteMax ){

				coteMax = cote;

			} else if ( cote < coteMin ){

				coteMin = cote;

			}

			coteMoyenne += cote;

		}

		coteMoyenne -= coteMax;
		coteMoyenne -= coteMin;

		coteMoyenne /= ( nombreCotes - 2 );

		return coteMoyenne;
	}

}
