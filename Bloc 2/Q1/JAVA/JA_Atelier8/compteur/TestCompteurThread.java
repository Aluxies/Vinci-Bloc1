package compteur;

public class TestCompteurThread {

	public static void main(String[] args) throws InterruptedException {
		CompteurThread[] compteurs = { new CompteurThread("Bolt", 10), new CompteurThread("Jakson", 10), new CompteurThread("Robert", 10), new CompteurThread("Stéphanie", 10) };

		for(int i = 0; i < compteurs.length; i++) {
			compteurs[i].start();
		}

		for(int i = 0; i < compteurs.length; i++) {
			compteurs[i].join();
		}

		System.out.println("Le(la) gagnant(e) est " + CompteurThread.getGagnant().getNom());
	}

}