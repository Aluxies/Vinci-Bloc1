import java.util.Arrays;

public class TestParticipants {

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * 
	 * @param messageErreur message a afficher en cas de probleme
	 * @param attendu la valeur qu'on s'attendait a recevoir
	 * @param recu la valeur qu'on a recu en realite
	 */
	private static void assertEquals(String messageErreur, Object attendu, Object recu) {
		if (attendu == null) {
			if (recu != null) {
				System.out.println();
				System.out.println(messageErreur);
				System.out.println("    --> attendu : " + attendu);
				System.out.println("    --> recu : " + recu);
				System.exit(0);
			}
		} else {
			if (attendu instanceof Character && recu instanceof String) {
				attendu = "" + attendu;
			}
			if (attendu instanceof String && recu instanceof Character) {
				recu = "" + recu;
			}
			if (!attendu.equals(recu)) {
				System.out.println();
				System.out.println(messageErreur);
				System.out.println("    --> attendu : " + attendu);
				System.out.println("    --> recu : " + recu);
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {

		int choix;
		System.out.println("************************************************");
		System.out.println("Programme Test pour la classe Participants (V2):");
		System.out.println("************************************************");
		System.out.println();
		do {
			System.out.println("1 -> Tester la methode 'donnerIndex(String participant)'");
			System.out.println("2 -> Tester la methode 'contientParticipant(String participant)'");
			System.out.println("3 -> Tester la methode 'ajouterParticipant(int index, String participant)'");
			System.out.println("4 -> Tester la methode 'ajouterParticipant(String participant)'");
			System.out.println("5 -> Tester la methode 'supprimerParticipant(int index)'");
			System.out.println("6 -> Tester la methode 'supprimerParticipant(String participant)'");
			System.out.print("\nEntrez votre choix : ");
			choix = scanner.nextInt();
			switch (choix) {
				case 1:
					testerDonnerIndex();
					break;
				case 2:
					testerContient();
					break;
				case 3:
					testerAjouterViaIndex();
					break;
				case 4:
					testerAjouterViaNom();
					break;
				case 5 :
					testerSupprimerViaIndex();
					break;
				case 6:
					testerSupprimerViaNom();
					break;
			}
		} while (choix >= 1 && choix <= 6);
		System.out.println("Merci pour votre visite.");
	}

	private static void testerDonnerIndex() {

		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : donner index d'un participant qui existe dans une table pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pU","pV","pM","pW","pL"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "pU","pV","pM","pW","pL"};
			String participant = "pW";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", 3, p.donnerIndex(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits a ete modifie)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table modifie)", Arrays.toString(tSol), p.toString());
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 2;
		System.out.println("test " + numeroTest + " : donner index d'un participant qui existe dans une table non pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA","pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pB";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", 1, p.donnerIndex(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : donner index d'un participant qui n'existe dans une table non pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA", "pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pX";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", -1, p.donnerIndex(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : donner index d'un participant qui n'existe dans une table pleine ");
		try {
			int nombreMaxParticipants = 3;
			String[] t = { "pA","pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pX";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", -1, p.donnerIndex(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 5;
		System.out.println("test " + numeroTest + " : donner index d'un participant qui est le premier dans une table non pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA","pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pA";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", 0, p.donnerIndex(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 6;
		System.out.println("test " + numeroTest + " : donner index d'un participant qui est le dernier dans une table non pleine ");
		try {
			int nombreMaxParticipants = 7;
			String[] t = { "pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pE";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", 4, p.donnerIndex(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 7;
		System.out.println("test " + numeroTest + " : donner index d'un participant qui est le premier dans une table pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA", "pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pA";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", 0, p.donnerIndex(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 8;
		System.out.println("test " + numeroTest + " : donner index d'un participant qui est le dernier dans une table pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA", "pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pE";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", 4, p.donnerIndex(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 8;
		System.out.println("test " + numeroTest + " : donner index d'un participant dans une table vide");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pX";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (index renvoye)", -1, p.donnerIndex(participant));
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


	private static void testerContient() {

		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : contient un participant qui existe dans une table pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pU","pV","pM","pW","pL"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = { "pU","pV","pM","pW","pL"};
			String participant = "pW";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.contientParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits a ete modifie)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table modifie)", Arrays.toString(tSol), p.toString());
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
			String[] t = { "pA","pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pB";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.contientParticipant(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : contient un participant qui n'existe dans une table non pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA", "pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pX";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", false, p.contientParticipant(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : contient un participant qui n'existe dans une table pleine ");
		try {
			int nombreMaxParticipants = 3;
			String[] t = { "pA","pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pX";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", false, p.contientParticipant(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 5;
		System.out.println("test " + numeroTest + " : contient un participant qui est le premier dans une table non pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA","pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pA";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.contientParticipant(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 6;
		System.out.println("test " + numeroTest + " : contient un participant qui est le dernier dans une table non pleine ");
		try {
			int nombreMaxParticipants = 7;
			String[] t = { "pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pE";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.contientParticipant(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 7;
		System.out.println("test " + numeroTest + " : contient un participant qui est le premier dans une table pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pA";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.contientParticipant(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 8;
		System.out.println("test " + numeroTest + " : contient un participant qui est le dernier dans une table pleine ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = { "pA", "pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pE";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.contientParticipant(participant));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 9;
		System.out.println("test " + numeroTest + " : contient un participant dans une table vide ");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String participant = "pX";
			System.out.println("le participant a verifier : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", false, p.contientParticipant(participant));
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


	private static void testerAjouterViaIndex() {

		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (3 places libres)");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pX","pC","pD","pE"};
			String participant = "pX";
			int index = 2;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 2;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (3 places libres)");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pX","pA","pB","pC","pD","pE"};
			String participant = "pX";
			int index = 0;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (3 places libres)");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pX","pE"};
			String participant = "pX";
			int index = 4;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (1 place libre)");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pX","pB","pC","pD"};
			String participant = "pX";
			int index = 1;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 5;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (1 place libre)");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pX","pA","pB","pC","pD"};
			String participant = "pX";
			int index = 0;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 6;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (1 place libre)");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pX"};
			String participant = "pX";
			int index = 4;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 7;
		System.out.println("test " + numeroTest + " : ajout dans une table vide");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pX"};
			String participant = "pX";
			int index = 0;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 8;
		System.out.println("test " + numeroTest + " : ajout dans une table pleine");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pE"};
			String participant = "pX";
			int index = 1;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 9;
		System.out.println("test " + numeroTest + " : ajout dans une table qui contient deja le participant");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			String participant = "pC";
			int index = 1;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 10;
		System.out.println("test " + numeroTest + " : ajout dans une table qui contient deja le participant");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			String participant = "pD";
			int index = 1;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterParticipant(index,participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 11;
		System.out.println("test " + numeroTest + " : index invalide : pas de trou dans la table!");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			String participant = "pX";
			int index = 5;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			p.ajouterParticipant(index,participant);
			System.out.println("le test "+numeroTest+ " a echoue");
			System.out.println("Il fallait lancer une IllegalArgumentException");
			System.exit(0);
		}catch (IllegalArgumentException e) {

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 12;
		System.out.println("test " + numeroTest + " : index invalide : pas d'index -1 !");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			String participant = "pX";
			int index = -1;
			System.out.println("le participant a ajouter : " +participant);
			System.out.println("a l'index : "+index);
			p.ajouterParticipant(index,participant);
			System.out.println("le test "+numeroTest+ " a echoue");
			System.out.println("Il fallait lancer une IllegalArgumentException");
			System.exit(0);
		}catch (IllegalArgumentException e) {

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		System.out.println("Tous les tests ont reussi !");
		System.out.println();
	}

	private static void testerAjouterViaNom() {
		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (3 places libres)");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pE","pX"};
			String participant = "pX";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 2;
		System.out.println("test " + numeroTest + " : ajout dans une table non pleine (1 place libre)");
		try {
			int nombreMaxParticipants = 6;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pE","pX"};
			String participant = "pX";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : ajout dans une table vide");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pX"};
			String participant = "pX";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", true, p.ajouterParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : ajout dans une table pleine");
		try {
			int nombreMaxParticipants = 5;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pE"};
			String participant = "pX";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 5;
		System.out.println("test " + numeroTest + " : ajout dans une table qui contient deja le participant");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pE"};
			String participant = "pD";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();
		numeroTest = 6;
		System.out.println("test " + numeroTest + " : ajout dans une table qui contient deja le participant");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pE"};
			String participant = "pA";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 7;
		System.out.println("test " + numeroTest + " : ajout dans une table qui contient deja le participant");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD","pE"};
			String participant = "pE";
			System.out.println("le participant a ajouter : " +participant);
			assertEquals("le test "+numeroTest+ " a echoue (boolean renvoye)", false, p.ajouterParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}


	private static void testerSupprimerViaIndex() {

		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pD","pE"};
			String participant = "pC";
			int index = 2;
			System.out.println("suppression participant a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (participant renvoye)", participant, p.supprimerParticipant(index));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
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
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			String participant = "pE";
			int index = 4;
			System.out.println("suppression participant a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (participant renvoye)", participant, p.supprimerParticipant(index));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pB","pC","pD","pE"};
			String participant = "pA";
			int index = 0;
			System.out.println("suppression participant a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (participant renvoye)", participant, p.supprimerParticipant(index));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : suppression dans une table pleine");
		try {
			int nombreMaxParticipants = 4;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pC","pD"};
			String participant = "pB";
			int index = 1;
			System.out.println("suppression participant a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (participant renvoye)", participant, p.supprimerParticipant(index));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : suppression dans une table pleine");
		try {
			int nombreMaxParticipants = 3;
			String[] t = {"pA","pB","pC"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB"};
			String participant = "pC";
			int index = 2;
			System.out.println("suppression participant a l'index : "+index);
			assertEquals("le test "+numeroTest+ " a echoue (participant renvoye)", participant, p.supprimerParticipant(index));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 6;
		System.out.println("test " + numeroTest + " : index invalide : pas d'index -1 !");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			int index = -1;
			System.out.println("suppression participant a l'index : "+index);
			p.supprimerParticipant(index);
			System.out.println("le test "+numeroTest+ " a echoue");
			System.out.println("Il fallait lancer une IllegalArgumentException");
			System.exit(0);
		}catch (IllegalArgumentException e) {

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 7;
		System.out.println("test " + numeroTest + " : index invalide : pas de participant a cet index !");
		try {
			int nombreMaxParticipants = 4;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			int index = 4;
			System.out.println("suppression participant a l'index : "+index);
			p.supprimerParticipant(index);
			System.out.println("le test "+numeroTest+ " a echoue");
			System.out.println("Il fallait lancer une IllegalArgumentException");
			System.exit(0);
		}catch (IllegalArgumentException e) {

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 8;
		System.out.println("test " + numeroTest + " : index invalide : pas de participant a cet index !");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			int index = 4;
			System.out.println("suppression participant a l'index : "+index);
			p.supprimerParticipant(index);
			System.out.println("le test "+numeroTest+ " a echoue");
			System.out.println("Il fallait lancer une IllegalArgumentException");
			System.exit(0);
		}catch (IllegalArgumentException e) {

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








	private static void testerSupprimerViaNom() {

		System.out.println();
		int numeroTest = 1;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pD","pE"};
			String participant = "pC";
			System.out.println("suppression participant  : "+participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.supprimerParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
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
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			String participant = "pE";
			System.out.println("suppression participant  : "+participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.supprimerParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 3;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine");
		try {
			int nombreMaxParticipants = 8;
			String[] t = {"pA","pB","pC","pD","pE"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pB","pC","pD","pE"};
			String participant = "pA";
			System.out.println("suppression participant  : "+participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.supprimerParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 4;
		System.out.println("test " + numeroTest + " : suppression dans une table pleine");
		try {
			int nombreMaxParticipants = 4;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC"};
			String participant = "pD";
			System.out.println("suppression participant  : "+participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.supprimerParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 5;
		System.out.println("test " + numeroTest + " : suppression dans une table pleine");
		try {
			int nombreMaxParticipants = 4;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pB","pC","pD"};
			String participant = "pA";
			System.out.println("suppression participant  : "+participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", true, p.supprimerParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 6;
		System.out.println("test " + numeroTest + " : suppression dans une table pleine qui ne contient pas le participant");
		try {
			int nombreMaxParticipants = 4;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			String participant = "pX";
			System.out.println("suppression participant  : "+participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", false, p.supprimerParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 7;
		System.out.println("test " + numeroTest + " : suppression dans une table non pleine qui ne contient pas le participant");
		try {
			int nombreMaxParticipants = 7;
			String[] t = {"pA","pB","pC","pD"};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {"pA","pB","pC","pD"};
			String participant = "pX";
			System.out.println("suppression participant  : "+participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", false, p.supprimerParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();

		numeroTest = 8;
		System.out.println("test " + numeroTest + " : suppression dans une table vide");
		try {
			int nombreMaxParticipants = 4;
			String[] t = {};
			System.out.println("le nombre max de participants : "+nombreMaxParticipants);
			Participants p = new Participants(nombreMaxParticipants, t);
			System.out.println("les participants deja inscrits : "+ Arrays.toString(t));
			String[] tSol = {};
			String participant = "pX";
			System.out.println("suppression participant  : "+participant);
			assertEquals("le test "+numeroTest+ " a echoue (booleen renvoye)", false, p.supprimerParticipant(participant));
			assertEquals("le test "+numeroTest+ " a echoue (nombre inscrits)", tSol.length, p.getNombreInscrits());
			assertEquals("le test "+numeroTest+ " a echoue (contenu table)", Arrays.toString(tSol), p.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test "+numeroTest+ " a echoue : exception inattendue");
			System.exit(0);
		}
		System.out.println("le test " + numeroTest + " a reussi");
		System.out.println();










		// test 1 : suppression dans une table vide
		try {
			Participants tP = new Participants(4);
			Participants tPSol = new Participants(4);
			assertEquals("test 1 a echoue (booleen renvoye)", false, tP.supprimerParticipant("p1"));
			assertEquals("test 1 a echoue (nombre inscrits)", 0, tP.getNombreInscrits());
			assertEquals("test 1 a echoue (contenu table)", tPSol.toString(), tP.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 1 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		// test 2 : suppression dans une table non vide et non pleine qui ne
		// contient pas le participant
		try {
			String[] t = { "p1", "p2", "p3" };
			Participants tP = new Participants(4, t);
			String[] tSol = { "p1", "p2", "p3" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 2 a echoue (booleen renoye)", false, tP.supprimerParticipant("p4"));
			assertEquals("test 2 a echoue (nombre inscrits)", 3, tP.getNombreInscrits());
			assertEquals("test 2 a echoue (contenu table)", tPSol.toString(), tP.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 2 a echoue : exception inattendue");
			System.exit(0);
		}

		// test 3 : suppression dans une table non vide et non pleine qui
		// contient le participant
		try {
			String[] t = { "p1", "p2", "p3", "p4", "p5" };
			Participants tP = new Participants(6, t);
			String[] tSol = { "p1", "p3", "p4", "p5" };
			Participants tPSol = new Participants(6, tSol);
			assertEquals("test 3 a echoue (booleen renvoye)", true, tP.supprimerParticipant("p2"));
			assertEquals("test 3 a echoue (nombre inscrits)", 4, tP.getNombreInscrits());
			assertEquals("test 3 a echoue (contenu table)", tPSol.toString(), tP.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 3 a echoue : exception inattendue");
			System.exit(0);
		}

		// test 4 : suppression dans une table pleine qui ne contient pas le
		// participant
		try {
			String[] t = { "p1", "p2", "p3", "p4" };
			Participants tP = new Participants(4, t);
			String[] tSol = { "p1", "p2", "p3", "p4" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 4 a echoue (booleen renvoye)", false, tP.supprimerParticipant("p5"));
			assertEquals("test 4 a echoue (nombre inscrits)", 4, tP.getNombreInscrits());
			assertEquals("test 4 a echoue (contenu table)", tPSol.toString(), tP.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 4 a echoue : exception inattendue");
			System.exit(0);
		}

		// test 5 : suppression dans une table pleine qui contient le
		// participant
		try {
			String[] t = { "p1", "p2", "p3", "p4", "p5" };
			Participants tP = new Participants(5, t);
			String[] tSol = { "p1", "p3", "p4","p5" };
			Participants tPSol = new Participants(5, tSol);
			assertEquals("test 5 a echoue (booleen renvoye)", true, tP.supprimerParticipant("p2"));
			assertEquals("test 5 a echoue (nombre inscrits)", 4, tP.getNombreInscrits());
			assertEquals("test 5 a echoue", tPSol.toString(), tP.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 5 a echoue : exception inattendue");
			System.exit(0);
		}

		// test 6 : suppression dans une table pleine qui contient le
		// participant en premier lieu
		try {
			String[] t = { "p1", "p2", "p3", "p4","p5" };
			Participants tP = new Participants(5, t);
			String[] tSol = { "p2", "p3", "p4","p5" };
			Participants tPSol = new Participants(5, tSol);
			assertEquals("test 6 a echoue", true, tP.supprimerParticipant("p1"));
			assertEquals("test 6 a echoue (nombre inscrits)", 4, tP.getNombreInscrits());
			assertEquals("test 6 a echoue", tPSol.toString(), tP.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 6 a echoue : exception inattendue");
			System.exit(0);
		}

		// test 7 : suppression dans une table pleine qui contient le
		// participant en dernier lieu
		try {
			String[] t = { "p1", "p2", "p3", "p4" };
			Participants tP = new Participants(4, t);
			String[] tSol = { "p1", "p2", "p3" };
			Participants tPSol = new Participants(4, tSol);
			assertEquals("test 7 a echoue", true, tP.supprimerParticipant("p4"));
			assertEquals("test 7 a echoue (nombre inscrits)", 3, tP.getNombreInscrits());
			assertEquals("test 7 a echoue", tPSol.toString(), tP.toString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 7 a echoue : exception inattendue");
			System.exit(0);
		}

		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}

}
