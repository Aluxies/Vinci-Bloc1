
public class TestQuestionNPS {

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	/**
	 * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
	 * 
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
		
		System.out.println("*********************************");
		System.out.println("Tests pour la classe QuestionNPS");
		System.out.println("*********************************");
		do{
			System.out.println("Menu");
			System.out.println("****");
			System.out.println("1 -> contientReponses()");
			System.out.println("2 -> moyenne()");
			System.out.println("3 -> nombreDetracteurs()");
			System.out.println("4 -> scoreNPS()");
			System.out.println("5 -> tableFrequences()");
			System.out.println("6 -> mediane()");
			System.out.print("\nEntrez votre choix : ");

			choix=scanner.nextInt();

			switch(choix){
				case 1: testerContientReponses();
					break;
				case 2: testerMoyenne();
					break;
				case 3: testerNombreDetracteurs();
					break;
				case 4: testerScoreNPS();
					break;
				case 5: testerTableFrequences();
					break;
				case 6: testerMediane();
					break;
			}
		}while(choix >=1&&choix<=6);
		System.out.println("\nFin des tests");
	}

	private static void testerContientReponses() {
		System.out.println();
		System.out.println("contientReponses()");
		System.out.println("------------------");

		System.out.println("test 1 : table 0 1 2 3 4 5 6 7 8 9 10");
		try{
			int[] table1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
			assertEquals("test 1 ko ", true , QuestionNPS.contientReponses(table1));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 1 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 1 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 2 ok");

		System.out.println();
		System.out.println("test 2 : table 11 0 1 4 ");
		try{
			int[] table2 = {11, 0, 1, 4};
			assertEquals("test 2 ko ", false , QuestionNPS.contientReponses(table2));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 2 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 2 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 2 ok");

		System.out.println();
		System.out.println("test 3 : table 1 0 4 -1");
		try{
			int[] table3 = {1, 0, 4, -1};
			assertEquals("test 3 ko ", false , QuestionNPS.contientReponses(table3));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 3 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 3 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 3 ok");

		System.out.println();
		System.out.println("test 4 : table vide");
		try{
			int[] table4 = {};
			assertEquals("test 4 ko ", true , QuestionNPS.contientReponses(table4));
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 4 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 4 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 4 ok");

		System.out.println();
		System.out.println("tous les tests ont reussi!");
		System.out.println();

	}

	private static void testerMoyenne() {
		System.out.println();
		System.out.println("moyenne()");
		System.out.println("---------");

		System.out.println("test 1 : reponses 3 4 7 1");
		try{
			int[] table1 = {3,4,7,1};
			QuestionNPS q1 = new QuestionNPS("q",table1);
			assertEquals("test 1 ko ", 3.75 , q1.moyenne());
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 1 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 1 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 1 ok");


		System.out.println();
		System.out.println("tous les tests ont reussi!");
		System.out.println();
	}


	private static void testerNombreDetracteurs() {
		System.out.println();
		System.out.println("nombreDetracteurs()");
		System.out.println("-------------------");

		System.out.println("test 1 : reponses 3 4 7 1 9 10 8 6 0 9 (5 detracteurs 3 promoteurs 2 passifs)");
		try{
			int[] table1 = {3, 4, 7, 1, 9, 10, 8, 6, 0, 9};
			QuestionNPS q1 = new QuestionNPS("q",table1);
			assertEquals("test 1 ko ", 5 , q1.nombreDetracteurs());
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 1 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 1 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 1 ok");


		System.out.println();
		System.out.println("tous les tests ont reussi!");
		System.out.println();
	}


	private static void testerScoreNPS() {
		System.out.println();
		System.out.println("scoreNPS()");
		System.out.println("-----------");
		
		System.out.println("test 1 : reponses 3 4 7 1 9 10 8 6 0 9 (5 detracteurs 3 promoteurs 2 passifs)");
		try{
			int[] table1 = {3, 4, 7, 1, 9, 10, 8, 6, 0, 9};
			QuestionNPS q1 = new QuestionNPS("q",table1);
			assertEquals("test 1 ko ", -20 , (int) q1.scoreNPS());
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 1 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 1 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 1 ok");
		
		
		System.out.println();
		System.out.println("tous les tests ont reussi!");
		System.out.println();
	}

	
	private static void testerTableFrequences() {
		System.out.println();
		System.out.println();
		System.out.println("tableFrequences()");
		System.out.println("-----------------");
		
		System.out.println("test 1 : reponses 1 3 9 3 8");
		try{
			int[] table1 = {1,3,9,3,8};
			QuestionNPS q1 = new QuestionNPS("q1",table1);
			String q1Avant = q1.toString();
			int[] fr1 = q1.tableFrequences();
			if(fr1==null){
				System.out.println("test 1 ko la table renvoyee est null");
				System.exit(0);
			}
			String q1Apres = q1.toString();
			if(!q1Avant.equals(q1Apres)){
				System.out.println("test 1 ko la table des reponses a ete modifiee");
				System.out.println("l'algorithme doit s'appliquer sur une copie de cette table !");
				System.exit(0);

			}
			assertEquals("test 1 ko la dimension de la table renvoyee est ko (il y a 11 reponses possibles)",11,fr1.length);
			assertEquals("test 1 ko frequence 0 ko", 0, fr1[0]);
			assertEquals("test 1 ko frequence 1 ko", 1, fr1[1]);
			assertEquals("test 1 ko frequence 2 ko", 0, fr1[2]);
			assertEquals("test 1 ko frequence 3 ko", 2, fr1[3]);
			assertEquals("test 1 ko frequence 4 ko", 0, fr1[4]);
			assertEquals("test 1 ko frequence 5 ko", 0, fr1[5]);
			assertEquals("test 1 ko frequence 6 ko", 0, fr1[6]);
			assertEquals("test 1 ko frequence 7 ko", 0, fr1[7]);
			assertEquals("test 1 ko frequence 8 ko", 1, fr1[8]);
			assertEquals("test 1 ko frequence 9 ko", 1, fr1[9]);
			assertEquals("test 1 ko frequence 10 ko", 0, fr1[10]);
			
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 1 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 1 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 1 ok");
		System.out.println();
		
		
		System.out.println("test 2 : reponses 0 10 0 10 0 0");
		try{
			int[] table2 = {0,10,0,10,0,0};
			QuestionNPS q2 = new QuestionNPS("q",table2);
			int[] fr2 = q2.tableFrequences();
			if(fr2==null){
				System.out.println("test 2 ko la table renvoyee est null");
				System.exit(0);
			}
			assertEquals("test 2 ko la dimension de la table renvoyee est ko (il y a 11 reponses possibles)",11,fr2.length);
			assertEquals("test 2 ko frequence 0 ko", 4, fr2[0]);
			assertEquals("test 2 ko frequence 1 ko", 0, fr2[1]);
			assertEquals("test 2 ko frequence 2 ko", 0, fr2[2]);
			assertEquals("test 2 ko frequence 3 ko", 0, fr2[3]);
			assertEquals("test 2 ko frequence 4 ko", 0, fr2[4]);
			assertEquals("test 2 ko frequence 5 ko", 0, fr2[5]);
			assertEquals("test 2 ko frequence 6 ko", 0, fr2[6]);
			assertEquals("test 2 ko frequence 7 ko", 0, fr2[7]);
			assertEquals("test 2 ko frequence 8 ko", 0, fr2[8]);
			assertEquals("test 2 ko frequence 9 ko", 0, fr2[9]);
			assertEquals("test 2 ko frequence 10 ko", 2, fr2[10]);
			
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 2 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 2 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 2 ok");
		
		System.out.println();
		System.out.println("test 3 : aucune reponse");
		try{
			int[] table3 = {};
			QuestionNPS q1 = new QuestionNPS("q1",table3);
			int[] fr3 = q1.tableFrequences();
			if(fr3==null){
				System.out.println("test 3 ko la table renvoyee est null");
				System.exit(0);
			}
			assertEquals("test 3 ko la dimension de la table renvoyee est ko (il y a 11 reponses possibles)",11,fr3.length);
			assertEquals("test 3 ko frequence 0 ko", 0, fr3[0]);
			assertEquals("test 3 ko frequence 1 ko", 0, fr3[1]);
			assertEquals("test 3 ko frequence 2 ko", 0, fr3[2]);
			assertEquals("test 3 ko frequence 3 ko", 0, fr3[3]);
			assertEquals("test 3 ko frequence 4 ko", 0, fr3[4]);
			assertEquals("test 3 ko frequence 5 ko", 0, fr3[5]);
			assertEquals("test 3 ko frequence 6 ko", 0, fr3[6]);
			assertEquals("test 3 ko frequence 7 ko", 0, fr3[7]);
			assertEquals("test 3 ko frequence 8 ko", 0, fr3[8]);
			assertEquals("test 3 ko frequence 9 ko", 0, fr3[9]);
			assertEquals("test 3 ko frequence 10 ko", 0, fr3[10]);
			
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 3 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 3 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 3 ok");
		
		System.out.println();
		System.out.println("tous les tests ont reussi!");
		System.out.println();
		
	}

	private static void testerMediane() {
		System.out.println();
		System.out.println();
		System.out.println("mediane()");
		System.out.println("---------");
		
		System.out.println("test 1 : reponses 1 2 3 4 5");
		try{
			int[] table1 = {1,2,3,4,5};
			QuestionNPS q1 = new QuestionNPS("q1",table1);
			QuestionNPS q1Copie = new QuestionNPS("q1",table1);
			assertEquals("test 1 ko",3,q1.mediane());
			assertEquals("test 1 ko tableReponses modifiee", q1Copie.toString(), q1.toString());
	
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 1 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 1 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 1 ok");
		System.out.println();
		
		System.out.println("test 2 : reponses 2 4 5 1 3");
		try{
			int[] table2 = {2,4,5,1,3};
			QuestionNPS q2 = new QuestionNPS("q2",table2);
		
			assertEquals("test 2 ko",3,q2.mediane());

		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 2 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 2 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 2 ok");
		System.out.println();
		
		System.out.println("test 3 : reponses 2 8 9 1 9 7 4");
		try{
			int[] table3 = {2,8,9,1,9,7,4};
			QuestionNPS q3 = new QuestionNPS("q3",table3);
		
			assertEquals("test 3 ko",7,q3.mediane());

		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 3 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 3 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 3 ok");
		
		System.out.println();
		
		System.out.println("test 4 : reponses 9 ");
		try{
			int[] table4 = {9};
			QuestionNPS q4 = new QuestionNPS("q4",table4);
		
			assertEquals("test 4 ko",9,q4.mediane());

		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("test 4 ko, il y a eu sortie de table");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e){
			System.out.println("test 4 ko, il y a eu une exception inattendue");
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("test 4 ok");
		
		System.out.println();
		System.out.println("tous les tests ont reussi!");
		System.out.println();
	}

}
