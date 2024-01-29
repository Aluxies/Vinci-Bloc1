
public class JeuDuPendu {

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Entrez le mot a trouver : ");
		String motATrouver = scanner.nextLine();

		System.out.print("Entrez votre indice : ");
		String motIndice = scanner.nextLine();

		FenetrePendu fenetrePendu = new FenetrePendu();

		MotACompleter mot = new MotACompleter(motATrouver);

		LettresLues lettresLues = new LettresLues();

		mot.ajouterPremiereEtDerniereLettres();

		fenetrePendu.afficherMot(mot.toString());

		int nombreDeMorceaux = 0;
		int nombreDeTours = 0;

		do {

			char lettre = Character.toUpperCase(fenetrePendu.lireLettre());

			if ( !lettresLues.lettreDejaLue( lettre ) ){

				nombreDeTours++;

				if ( nombreDeTours == 3 ){

					fenetrePendu.afficherInformation( motIndice );

				}

				if ( mot.contientLettre( lettre ) ){

					mot.ajouterLettre( lettre );
					fenetrePendu.afficherMot( mot.toString() );

				} else {

					nombreDeMorceaux++;

					fenetrePendu.afficherPendu( nombreDeMorceaux );

				}

			}

			lettresLues.ajouterLettreLue( lettre );

			fenetrePendu.afficherLettresLues( lettresLues.toString() );

		} while( !mot.estComplet() && nombreDeMorceaux != 6 );

		if ( nombreDeMorceaux == 6 ){

			fenetrePendu.afficherInformation( "Pendu !" );

		} else {

			fenetrePendu.afficherInformation( "Bravo !" );

		}

	}
}
