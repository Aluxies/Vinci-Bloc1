
public class Statistiques {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {

		System.out.print( "Entrez le nombre de cotes : " );
		int nombreCotes = scanner.nextInt();

		double coteMax = 0;
		double coteMin = 20;
		double coteMoyenne = 0;
		double somme = 0;

		for ( int i = 1; i <= nombreCotes ; i++ ) {

			System.out.print( "Entrez la cote n°" + i + " (/ 20) : " );
			double cote = scanner.nextDouble();

			somme += cote;

			if ( cote > coteMax ){

				coteMax = cote;

			} else if ( cote < coteMin && cote < coteMax ){

				coteMin = cote;

			};

		};

		coteMoyenne = somme / nombreCotes;

		System.out.println( "\nLa cote la plus elevee est "+ coteMax );
		System.out.println( "La cote la plus basse est "+ coteMin );
		System.out.println( "La cote moyenne est de " + coteMoyenne );

	};

};
