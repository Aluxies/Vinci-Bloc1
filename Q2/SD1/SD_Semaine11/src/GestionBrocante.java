import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class GestionBrocante {

	private static MonScanner scanner = new MonScanner("lancement.txt");
	private static Brocante brocante;

	public static void main(String[] args) {

		System.out.println("**********************");
		System.out.println("gestion d'une brocante");
		System.out.println("**********************");
		System.out.println();

		// configuration de la brocante
		System.out.println("Configuration de la brocante");
		System.out.println("----------------------------");
		System.out.print("Entrez le nombre d'emplacements : ");
		int nombreEmplacements = scanner.nextInt();
		char[] tableTypeEmplacements = new char[nombreEmplacements];
		System.out.println();
		for (int i = 0; i < tableTypeEmplacements.length; i++) {
			System.out.print("Entrez le type de l'emplacement n°" + (i + 1) + " : ");
			char typeEmplacement = scanner.next().charAt(0);
			scanner.nextLine();
			tableTypeEmplacements[i] = typeEmplacement;
		}
		System.out.println();
		System.out.print("Entrez le nombre de riverains : ");
		int nombreRiverains = scanner.nextInt();
		scanner.nextLine();
		String[] tableRiverains = new String[nombreRiverains];
		for (int i = 0; i < tableRiverains.length; i++) {
			System.out.print("Entrez le nom du riverain " + (i + 1) + " : ");
			String nomRiv  = scanner.nextLine();
			tableRiverains[i] = nomRiv;
		}
		brocante = new Brocante(tableTypeEmplacements, tableRiverains);
		System.out.println();

		// Phase 1
		System.out.println("Phase 1");
		System.out.println("-------");
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> consulter un exposant via son nom");
			System.out.println("4 -> lister tous les exposants");
			System.out.println("5 -> désister un emplacement");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					reserverPhase1();
					break;
				case 2:
					afficherTout();
					break;
				case 3:
					consulterExposant();
					break;
				case 4:
					afficherTousLesExposants();
					break;
				case 5:
					desister();
					break;
			}

		} while (choix >= 1 && choix <= 5);

		brocante.changerPhase();
		System.out.println();
		System.out.println();

		// Phase 2
		System.out.println("Phase 2");
		System.out.println("-------");
		choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> consulter un exposant via son nom");
			System.out.println("4 -> lister tous les exposants");
			System.out.println("5 -> désister un emplacement");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					reserverPhase2();
					break;
				case 2:
					afficherTout();
					break;
				case 3:
					consulterExposant();
					break;
				case 4:
					afficherTousLesExposants();
					break;
				case 5:
					desister();
					break;
			}

		} while (choix >= 1 && choix <= 5);

		System.out.println("Fin de la brocante!");
	}

	private static void reserverPhase1() {

		if ( !brocante.emplacementLibre() ){

			System.out.println( "Il n'y a plus aucun emplacement libre." );
			return;

		}

		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		if ( !brocante.estUnRiverain( nom ) ){

			System.out.println( "Le nom " + nom + "  n'existe pas." );
			return;

		}

		if ( brocante.nombreEmplacementsRiverain( nom ) == 3 ){

			System.out.println( nom + " a déjà 3 emplacements qui lui sont réservés." );
			return;

		}

		Exposant exposant = brocante.getExposant( nom );

		if ( exposant == null ){

			System.out.print("Entrez l'email : ");
			String email = scanner.nextLine();

			exposant = new Exposant( nom, email );

		}

		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		scanner.nextLine();

		if ( !brocante.estLibre( numero ) ){

			System.out.println( "L'emplacement n°" + numero + " est déjà occupé." );
			return;

		}

		boolean resultat = brocante.reserver( exposant, numero );

		if ( resultat ){

			System.out.println( "L'emplacement n°" + numero + " a bien été réservé pour l'exposant " + exposant.getNom() + " !" );

		} else System.out.println( "L'emplacement n°" + numero + " n'a pas pu être réservé." );

	}

	private static void reserverPhase2() {

		if ( !brocante.emplacementLibre() ){

			System.out.println( "Il n'y a plus aucun emplacement libre." );
			return;

		}

		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		Exposant exposant = brocante.getExposant( nom );

		if ( exposant == null ){

			System.out.print("Entrez l'email : ");
			String email = scanner.nextLine();

			exposant = new Exposant( nom, email );

		}

		System.out.print( "Entrez le type d'emplacement : " );
		char type = scanner.next().charAt(0);
		scanner.nextLine();

		if ( brocante.typeEmplacementVide( type ) ){

			System.out.println( "Il n'y a plus d'emplacements libres du type " + type + "." );
			return;

		}

		if ( !brocante.emplacementLibre( type ) ){

			System.out.println( "Le type " + type + " n'existe pas." );
			return;

		}

		int numeroEmplacement = brocante.attribuerAutomatiquementEmplacement( type, exposant );

		if ( numeroEmplacement == -1 ){

			System.out.println( "Il n'y a plus d'emplacement de ce type disponible" );

		} else {

			System.out.println( "L'emplacement n°" + numeroEmplacement + " a été attribué à " + exposant.getNom() );

		}

	}

	private static void desister(){

		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		Exposant exposant = brocante.getExposant( nom );

		if ( exposant == null ){

			System.out.println( "Cet exposant n'existe pas." );

		}

		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		scanner.nextLine();

		boolean resultat = brocante.desister( exposant, numero );

		if ( resultat ){

			System.out.println( "L'emplacement n°" + numero + " a bien été retiré de la liste des emplacements de " + exposant.getNom() );

		} else {

			System.out.println( "L'emplacement n°" + numero + " n'a pas pu être retiré." );

		}

	}

	private static void consulterExposant() {

		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		Exposant exposant = brocante.getExposant( nom );

		if ( exposant == null ){

			System.out.println( "Le nom " + nom + " ne correspond à aucun exposant." );

		} else {

			System.out.println( "Voici les données concernant l'exposant : " + exposant );

		}

	}

	private static void afficherTousLesExposants(){

		System.out.println( brocante.donnerMapExposants() );

	}

	private static void afficherTout() {
		System.out.println("Emplacements :") ;
		System.out.println("--------------") ;
		System.out.println() ;
		System.out.println(brocante) ;
		//System.out.println() ;
	}
}
