import java.util.Arrays;

public class TestParticipants {

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a recu en realite
	 */
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu==null) {
			if (recu!=null) {
				System.out.println(messageErreur+". Attendu="+attendu+" recu="+recu);
				System.exit(0);
			}
		} else if (!attendu.equals(recu)) {
			System.out.println(messageErreur+". Attendu="+attendu+" recu="+recu);
			System.exit(0);
		}
	}

	public static void main(String[] args) {

		int choix;
		System.out.println("********************************************");
		System.out.println("Programme Test pour la classe Participants :");
		System.out.println("********************************************");
		System.out.println();
		do {
			System.out.println("1 -> Tester la methode 'contientParticipant()'");
			System.out.println("2 -> Tester la methode 'ajouterUnParticipant()'");
			System.out.println("3 -> Tester la methode 'supprimerUnParticipant()'");
			System.out.print("\nEntrez votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {
				case 1:
					contient();
					break;
				case 2:
					ajouter();
					break;
				case 3:
					supprimer();
					break;
			}
		} while (choix >= 1 && choix <= 3);
		System.out.println("Merci pour votre visite.");
	}

	private static void contient() {
		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : contient un participant qui existe dans une table non pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1", "p2","p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3"};
			String participant = "p2";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.contientParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 2;
		System.out.println("test " + numeroTest + " : contient un participant qui existe dans une table non pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1", "p2","p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3"};
			String participant = "p1";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.contientParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : contient un participant qui existe dans une table non pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1", "p2","p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3"};
			String participant = "p3";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.contientParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : contient un participant qui existe dans une table pleine ");
		try {
			int nombreMaxParticipants = 4;
			String[] t = { "p1", "p2","p3","p4"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3","p4"};
			String participant = "p4";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.contientParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 5;
		System.out.println("test " + numeroTest + " : contient un participant qui n'existe pas dans une table non pleine ");
		try {
			int nombreMaxParticipants = 7;
			String[] t = { "p1", "p2","p3","p4"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3","p4"};
			String participant = "p9";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.contientParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 6;
		System.out.println("test " + numeroTest + " : contient un participant qui n'existe pas dans une table pleine ");
		try {
			int nombreMaxParticipants = 4;
			String[] t = { "p1", "p2","p3","p4"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3","p4"};
			String participant = "p9";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.contientParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();


		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}

	private static void ajouter() {

		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (3 places libres)");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1", "p2" };
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3"};
			String participant = "p3";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();


		numeroTest = 2;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (1 place libre)");
		try {
			int nombreMaxParticipants = 3;
			String[] t = { "p1", "p2"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3"};
			String participant = "p3";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : ajout dans une table pleine (ajout impossible)");
		try {
			int nombreMaxParticipants = 3;
			String[] t = { "p1", "p2", "p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3"};
			String participant = "p4";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();


		numeroTest = 4;
		System.out.println("test " + numeroTest + " : ajout d'un participant deja inscrit (ajout impossible)");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1", "p2", "p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1", "p2", "p3"};
			String participant = "p2";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 5;
		System.out.println("test " + numeroTest + " : ajout dans une table vide (4 places libres)");
		try {
			int nombreMaxParticipants = 4;
			String[] t = {};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1"};
			String participant = "p1";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}

	private static void supprimer() {

		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1","p2","p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1","p3"};
			String participant = "p2";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 2;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine");
		try {
			int nombreMaxParticipants = 8;
			String[] t = { "p1","p2","p3","p4","p5","p6"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1","p6","p3","p4","p5"};
			String participant = "p2";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine");
		try {
			int nombreMaxParticipants = 4;
			String[] t = { "p1","p2","p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1","p2"};
			String participant = "p3";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine");
		try {
			int nombreMaxParticipants = 7;
			String[] t = { "p1","p2","p3","p4","p5"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p5","p2","p3","p4"};
			String participant = "p1";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 5;
		System.out.println("test " + numeroTest + " : suppression dans une table pleine");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1","p2","p3","p4","p5"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1","p5","p3","p4"};
			String participant = "p2";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 6;
		System.out.println("test " + numeroTest + " : suppression dans une table pleine");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1","p2","p3","p4","p5"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1","p2","p3","p4"};
			String participant = "p5";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 7;
		System.out.println("test " + numeroTest + " : suppression dans une table qui ne contient pas le participant");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "p1","p2","p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1","p2","p3"};
			String participant = "p9";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 8;
		System.out.println("test " + numeroTest + " : suppression dans une table qui ne contient pas le participant");
		try {
			int nombreMaxParticipants = 3;
			String[] t = { "p1","p2","p3"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "p1","p2","p3"};
			String participant = "p9";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 9;
		System.out.println("test " + numeroTest + " : suppression dans une table vide");
		try {
			int nombreMaxParticipants = 4;
			String[] t = {};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {};
			String participant = "p9";
			System.out.println("le participant a supprimer : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.supprimerUnParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : sortie de table");
			System.exit(0);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		/*
		// test 1 : suppression dans une table vide
		try {
			Participants tP = new Participants(4);
			Participants tPSol = new Participants(4);
			assertEquals("test 1 a echoue (booleen renvoye)", false, tP.supprimerUnParticipant("p1"));
			assertEquals("test 1 a echoue (contenu table)", tPSol.toString(), tP.toString());

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("test 1 a echoue : NullPointerException!!!");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("test 1 a echoue : ArrayIndexOutOfBoundsException!!!");
			System.exit(0);

		}

		// test 2 : suppression dans une table non vide et non pleine qui ne
		// contient pas le participant
		try {
			String[] t = { "p1", "p2", "p3" };
			Participants tP = new Participants(4, t);
			String[] tSol = { "p1", "p2", "p3" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 2 a echoue (booleen renvoye)", false, tP.supprimerUnParticipant("p4"));
			assertEquals("test 2 a echoue (nombre inscrits)", 3, tP.getNombreInscrits());
			assertEquals("test 2 a echoue (contenu table)", tPSol.toString(), tP.toString());

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("test 2 a echoue : NullPointerException!!!");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("test 2 a echoue : ArrayIndexOutOfBoundsException!!!");
			System.exit(0);
		}

		// test 3 : suppression dans une table non vide et non pleine qui
		// contient le participant
		try {
			String[] t = { "p1", "p2", "p3" };
			Participants tP = new Participants(4, t);
			String[] tSol = { "p1", "p3" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 3 a echoue (booleen renvoye)", true, tP.supprimerUnParticipant("p2"));
			assertEquals("test 3 a echoue (nombre inscrits)", 2, tP.getNombreInscrits());
			assertEquals("test 3 a echoue (contenu table)", tPSol.toString(), tP.toString());

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("test 3 a echoue : NullPointerException!!!");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("test 3 a echoue : ArrayIndexOutOfBoundsException!!!");
			System.exit(0);
		}

		// test 4 : suppression dans une table pleine qui ne contient pas le
		// participant
		try {
			String[] t = { "p1", "p2", "p3", "p4" };
			Participants tP = new Participants(4, t);
			String[] tSol = { "p1", "p2", "p3", "p4" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 4 a echoue (booleen renvoye)", false, tP.supprimerUnParticipant("p5"));
			assertEquals("test 4 a echoue (contenu table)", tPSol.toString(), tP.toString());

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("test 4 a echoue : NullPointerException!!!");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("test 4 a echoue : ArrayIndexOutOfBoundsException!!!");
			System.exit(0);
		}

		// test 5 : suppression dans une table pleine qui contient le
		// participant
		try {
			String[] t = { "p1", "p2", "p3", "p4" };
			Participants tP = new Participants(4, t);
			String[] tSol1 = { "p1", "p3", "p4" };
			Participants tPSol1 = new Participants(4, tSol1);
			String[] tSol = { "p1", "p4", "p3" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 5 a echoue (booleen renvoye)", true, tP.supprimerUnParticipant("p2"));
			if (tP.toString().equals(tPSol1.toString())) {
				System.out.println("ATTENTION! Le test 5 a reussi, mais votre methode n'est pas performante!!!");
				System.out.println("Avez-vous suivi les conseils de la fiche?");
				System.out.println("Vous devez revoir votre methode!");
				System.exit(0);
			}
			assertEquals("test 5 a echoue", tPSol.toString(), tP.toString());

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("test 5 a echoue : NullPointerException!!!");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("test 5 a echoue : ArrayIndexOutOfBoundsException!!!");
			System.exit(0);
		}

		// test 6 : suppression dans une table pleine qui contient le
		// participant en premier lieu
		try {
			String[] t = { "p1", "p2", "p3", "p4" };
			Participants tP = new Participants(4, t);
			String[] tSol = { "p4", "p2", "p3" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 6 a echoue", true, tP.supprimerUnParticipant("p1"));
			assertEquals("test 6 a echoue", tPSol.toString(), tP.toString());

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("test 6 a echoue : NullPointerException!!!");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("test 6 a echoue : ArrayIndexOutOfBoundsException!!!");
			System.exit(0);
		}

		// test 7 : suppression dans une table pleine qui contient le
		// participant en dernier lieu
		try {
			String[] t = { "p1", "p2", "p3", "p4" };
			Participants tP = new Participants(4, t);
			String[] tSol = { "p1", "p2", "p3" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 7 a echoue", true, tP.supprimerUnParticipant("p4"));
			assertEquals("test 7 a echoue", tPSol.toString(), tP.toString());

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("test 7 a echoue : NullPointerException!!!");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("test 7 a echoue : ArrayIndexOutOfBoundsException!!!");
			System.exit(0);
		}
*/
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}
}
