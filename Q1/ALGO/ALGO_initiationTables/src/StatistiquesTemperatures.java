import java.util.Arrays;

public class StatistiquesTemperatures {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);
	public static Temperatures temperatures;
	
	public static void main(String[] args) {
		
		chargerTemperatures();
		System.out.println(temperatures.toString());
		
		System.out.println("*****************************");
		System.out.println("Temperatures : statistiques :");
		System.out.println("*****************************");
		int choix;
		do{
			System.out.println();
			System.out.println("1 -> afficher toutes les temperatures");	
			System.out.println("2 -> calculer la moyenne");
			System.out.println("3 -> afficher la température minimale");
			System.out.println("4 -> afficher le nombre de jours de gel");
			System.out.println("5 -> afficher les jours de gel");
			System.out.println("6 -> afficher si toutes les temperatures sont positives");
			System.out.println("7 -> afficher si il y a au moins un jour de gel");
			System.out.println("8 -> afficher s'il y a au moins une température supérieure à une autre");
			System.out.println("9 -> afficher la température maximale");
			System.out.println("10 -> afficher les jours de température maximale");
			System.out.println("11 -> afficher les jours de température minimale");

			System.out.println();

			System.out.print("Entrez votre choix : ");

			choix = scanner.nextInt();

			switch(choix){	
			case 1: afficherTout();   
			break;
			case 2: afficherMoyenne();   
			break;
			case 3: afficherTemperatureMin();
			break;
			case 4: afficherNombresJoursDeGel();
			break;
			case 5: afficherJoursDeGel();
			break;
			case 6: afficherTemperaturesToutesPositives();
			break;
			case 7: afficherAuMoinsUnJourDeGel();
			break;
			case 8: afficherAuMoinsUneTemperatureSuperieureA();
			break;
			case 9: afficherTemperatureMax();
			break;
			case 10: afficherJoursMax();
			break;
			case 11: afficherJoursMin();
			break;
			}
		}while(choix >=1 && choix<=10);
	}
	
	private static void afficherTout() {
		System.out.println(temperatures.toString());
	}

	private static void afficherMoyenne() { System.out.println(temperatures.moyenne()); }

	private static void afficherTemperatureMin() { System.out.println(temperatures.temperatureMin()); }

	private static void afficherNombresJoursDeGel() { System.out.println(temperatures.nombreJoursDeGel()); }

	private static void afficherJoursDeGel(){ System.out.println( Arrays.toString( temperatures.joursDeGel())); }

	private static void afficherTemperaturesToutesPositives(){ System.out.println( temperatures.toutesPositives()); }

	private static void afficherAuMoinsUneTemperatureSuperieureA(){
		System.out.println( temperatures.contientAuMoinsUneTemperatureSuperieureA(5.0));
		System.out.println( temperatures.contientAuMoinsUneTemperatureSuperieureA(0.0));
	}
	private static void afficherAuMoinsUnJourDeGel(){ System.out.println( temperatures.contientAuMoinsUnJourDeGel() ); }

	private static void afficherTemperatureMax(){ System.out.println( temperatures.temperatureMax() ); }

	private static void afficherJoursMax(){ System.out.println( Arrays.toString( temperatures.joursMax() ) ); }

	private static void afficherJoursMin(){ System.out.println( Arrays.toString( temperatures.joursMin() )); }
	public static void chargerTemperatures(){
		double[]tableTemperatures = {-1,1,0,1,-1,-3,-3,-2,0,-1,0,1,1,3,5,4,2,0,1,1,5,3,2,0,-1,0,1,1,3,4,5};
		temperatures = new Temperatures("janvier",tableTemperatures);
	}
	
	public static void afficherTable(int[]table){
		System.out.println(Arrays.toString(table));
	}
	
}
