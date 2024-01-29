public class TestEtudiantCotesTriees{

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String [] args){
		int choix;
		System.out.println("******************************************************");
		System.out.println("Programme Test pour la classe Etudiant avec table triee");
		System.out.println("*******************************************************");
		do{
			System.out.println();
			System.out.println("1 -> Tester la methode moyenne");
			System.out.println("2 -> Tester la methode nombreEchecs");
			System.out.println("3 -> Tester la methode min");
			System.out.println("4 -> Tester la methode max");
			System.out.println();
			System.out.print("Entrez votre choix : ");
			choix=scanner.nextInt();
			switch(choix){
				case 1: verifierMoyenne();
					break;
				case 2: verifierNombreEchecs();
					break;
				case 3: verifierMin();
					break;
				case 4: verifierMax();
					break;
			}
		}while(choix>=1 && choix<=4);
		System.out.println("Merci pour votre visite.");
	}


	private static void verifierMoyenne(){
		System.out.println();
		System.out.println("test 1 : cotes : 10 12 14 16");
		try {
			double[] tableTestee = {10, 12, 14, 16};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1, tableTestee);
			double moyenne = etudiant.moyenne();
			if (moyenne == 13)
				System.out.println("le test 1 a reussi");
			else {
				System.out.println("le test 1 a echoue, moyenne attendue : 13, moyenne recue : " + moyenne);
				System.exit(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 1 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		System.out.println("test 2 : cotes : 9 10 11 12 13 14");
		try {
			double[] tableTestee = {9,10,11,12,13,14};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1, tableTestee);
			double moyenne = etudiant.moyenne();
			if (moyenne == 11.5)
				System.out.println("le test 2 a reussi");
			else {
				System.out.println("le test 2 a echoue, moyenne attendue : 11,5, moyenne recue : " + moyenne);
				System.exit(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 2 a echoue : exception inattendue!!!");
			System.exit(0);
		}
		System.out.println();
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}

	private static void verifierNombreEchecs(){
		System.out.println();
		try{
			System.out.println("test 1 : cotes : 1 6 14 16");
			double[] tableTestee = {1,6,14,16};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1,tableTestee);
			double nombreEchecs = etudiant.nombreEchecs();
			if(nombreEchecs != 2){
				System.out.println("Attention! Votre methode a trouve un nombre d'echecs : "+ nombreEchecs);
				System.exit(0);
			}
			System.out.println("le test 1 a reussi");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 1 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		try{
			System.out.println("test 2 : cotes : 1 2 5 6");
			double[] tableTestee2 = {1,2,5,6};
			EtudiantCotesTriees etudiant2 = new EtudiantCotesTriees(1,tableTestee2);
			int nombreEchecs = etudiant2.nombreEchecs();
			if(nombreEchecs != 4){
				System.out.println("Attention! Votre methode a trouve un nombre d'echecs : "+ nombreEchecs);
				System.exit(0);
			}
			System.out.println("le test 2 a reussi");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 2 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		try{
			System.out.println("test 3 : cotes : 17 18 18 19");
			double[] tableTestee3 = {17,18,18,19};
			EtudiantCotesTriees etudiant3 = new EtudiantCotesTriees(1,tableTestee3);
			int nombreEchecs = etudiant3.nombreEchecs();
			if(nombreEchecs != 0){
				System.out.println("Attention! Votre methode a trouve un nombre d'echecs : "+ nombreEchecs);
				System.exit(0);
			}
			System.out.println("le test 3 a reussi");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 3 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		try{
			System.out.println("test 4 : 1 cote : 10");
			double[] tableTestee4 = {10};
			EtudiantCotesTriees etudiant4 = new EtudiantCotesTriees(1,tableTestee4);
			int nombreEchecs = etudiant4.nombreEchecs();
			if(nombreEchecs != 0){
				System.out.println("Attention! Votre methode a trouve un nombre d'echecs : "+ nombreEchecs);
				System.exit(0);
			}
			System.out.println("le test 4 a reussi");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test 4 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		try{
			System.out.println("test 5 : 1 cote : 0");
			double[] tableTestee4 = {0};
			EtudiantCotesTriees etudiant4 = new EtudiantCotesTriees(1,tableTestee4);
			int nombreEchecs = etudiant4.nombreEchecs();
			if(nombreEchecs != 1){
				System.out.println("Attention! Votre methode a trouve un nombre d'echecs : "+ nombreEchecs);
				System.exit(0);
			}
			System.out.println("le test 5 a reussi");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test 5 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}


	private static void verifierMin(){
		System.out.println();
		try {
			System.out.println("test 1 : cotes : 2 6 14 16");
			double[] tableTestee = {2, 6, 14, 16};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1, tableTestee);
			double min = etudiant.min();
			if (min == 2)
				System.out.println("le test 1 a reussi");
			else {
				System.out.println("Attention! Votre methode a trouve la cote min " + min);
				System.exit(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 1 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		try {
			System.out.println("test 2 : cotes : 0 12 14 16 17");
			double[] tableTestee = {0, 12, 14, 16, 17};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1, tableTestee);
			double min = etudiant.min();
			if (min == 0)
				System.out.println("le test 2 a reussi");
			else {
				System.out.println("Attention! Le test 2 a echoue");
				System.out.println("Votre methode a trouve la cote min " + min);
				System.exit(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test 2 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		try {
			System.out.println("test 3 : cotes : 20 20 20 20 20");
			double[] tableTestee = {20,20,20,20,20};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1, tableTestee);
			double min = etudiant.min();
			if (min == 20)
				System.out.println("le test 3 a reussi");
			else {
				System.out.println("Attention! Le test 3 a echoue");
				System.out.println("Votre methode a trouve la cote min " + min);
				System.exit(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test 3 a echoue : exception inattendue!!!");
			System.exit(0);
		}
		System.out.println();
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}

	private static void verifierMax(){
		System.out.println();
		try {
			System.out.println("test 1 : cotes : 2 6 14 16");
			double[] tableTestee = {2, 6, 14, 16};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1, tableTestee);
			double max = etudiant.max();
			if (max == 16)
				System.out.println("le test 1 a reussi");
			else {
				System.out.println("Attention! Votre methode a trouve la cote min " + max);
				System.out.println(" pour l'etudiant : " + etudiant.toString());
				System.exit(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("test 1 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		try {
			System.out.println("test 2 : cotes : 0 12 14 16 20");
			double[] tableTestee = {0, 12, 14, 16, 20};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1, tableTestee);
			double max = etudiant.max();
			if (max == 20)
				System.out.println("le test 2 a reussi");
			else {
				System.out.println("Attention! Le test 2 a echoue");
				System.out.println("Votre methode a trouve la cote min " + max);
				System.exit(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test 2 a echoue : exception inattendue!!!");
			System.exit(0);
		}

		System.out.println();
		try {
			System.out.println("test 3 : cotes : 0 0 0");
			double[] tableTestee = {0,0,0};
			EtudiantCotesTriees etudiant = new EtudiantCotesTriees(1, tableTestee);
			double max = etudiant.min();
			if (max == 0)
				System.out.println("le test 3 a reussi");
			else {
				System.out.println("Attention! Le test 3 a echoue");
				System.out.println("Votre methode a trouve la cote min " + max);
				System.exit(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("le test 3 a echoue : exception inattendue!!!");
			System.exit(0);
		}
		System.out.println();
		System.out.println("Tous les tests ont reussi!");
		System.out.println();
	}

}
