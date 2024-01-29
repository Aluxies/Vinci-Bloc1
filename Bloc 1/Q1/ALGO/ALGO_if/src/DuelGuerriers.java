public class DuelGuerriers {

	public static void main(String[] args) {

		Guerrier frappeFort = new Guerrier(
				"FrappeFort",
				unEntierAuHasardEntre(1, 6 ),
				unEntierAuHasardEntre( 0, 3 )
		);

		Guerrier cogneDur = new Guerrier(
				"cogneDur",
				unEntierAuHasardEntre(1, 6 ),
				unEntierAuHasardEntre( 0, 3 )
		);


		int pointDeVieFrappeFort = frappeFort.pointsDeVie;
		int bouclierFrappeFort = frappeFort.bouclier;

		int pointDeVieCogneDur = cogneDur.pointsDeVie;
		int bouclierCogneDur = cogneDur.bouclier;

		int degatsFrappeFort = unEntierAuHasardEntre( 1, 6 );

		System.out.println( "\nFrappeFort a " + pointDeVieFrappeFort + " points de vie et un bouclier de niveau " + bouclierFrappeFort);
		System.out.println( "\nCogneDur a " + pointDeVieCogneDur + " points de vie et un bouclier de niveau " + bouclierCogneDur );

		if ( bouclierCogneDur != 0 ){

			if ( degatsFrappeFort > bouclierCogneDur ){

				degatsFrappeFort -= bouclierCogneDur;
				pointDeVieCogneDur -= degatsFrappeFort;

				System.out.println( "\nLe bouclier de CogneDur a réduit les dégats de " + bouclierCogneDur + " points." );
				System.out.println( "\nFrappeFort inflige " + degatsFrappeFort + " points de dégat à CogneDur." );

			} else {

				System.out.println( "\nLe bouclier de CogneDur a absorbé tous les dégats." );

			}

		} else {

			pointDeVieCogneDur -= degatsFrappeFort;

			System.out.println( "\nFrappeFort inflige " + degatsFrappeFort + " points de dégat à CogneDur." );
			System.out.println( "\nCogneDur a " + pointDeVieCogneDur + " points de vie." );

		}

		if ( pointDeVieCogneDur > 0 ){

			int degatsCogneDur = unEntierAuHasardEntre( 1, 6 );

			if ( bouclierFrappeFort != 0 ){

				if ( degatsCogneDur > bouclierFrappeFort ){

					degatsCogneDur -= bouclierFrappeFort;
					pointDeVieFrappeFort -= degatsCogneDur;

					System.out.println( "\nLe bouclier de FrappeFort a réduit les dégats de " + bouclierFrappeFort + " points." );
					System.out.println( "\nCogneDur inflige " + degatsCogneDur + " points de dégat à FrappeFort." );

				} else {

					System.out.println( "\nLe bouclier de FrappeFort a absorbé tous les dégats." );

				}

			} else {

				pointDeVieFrappeFort -= degatsCogneDur;

				System.out.println( "\nCogneDur inflige " + degatsCogneDur + " points de dégat à FrappeFort." );

				System.out.println( "\nFrappeFort a " + pointDeVieFrappeFort + " points de vie." );

			}

			if ( pointDeVieFrappeFort > 0 ){

				System.out.println( "\nFrappeFort et CogneDur terminent ex-aequos." );

			} else System.out.println( "\nCogneDur est vainceur." );

		} else System.out.println( "\nFrappeFort est vainceur." );


	}
	
	public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){
		double nombreReel;
		int resultat;

		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;
	}

}