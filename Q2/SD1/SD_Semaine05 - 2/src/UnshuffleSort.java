import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * Algorithme de tri : UnshuffleSort

 * Cet algorithme de tri necessite l?utilisation d?une liste de deques.
 * Cet algorithme de tri comporte deux etapes. La premiere consiste a repartir
 * les entiers a trier dans un nombre variable de deques. Lorsque tous
 * les entiers auront ete repartis, la deuxieme etape se chargera de remplir la
 * table a renvoyer.
 *
 * Les 2 etapes sont basees sur le principe suivant : La liste des deques devra
 * toujours etre triee en utilisant le premier entier de chaque deque comme clef de tri.
 * Les deques aussi sont tries.
 *
 *
 */
public class UnshuffleSort {

	private LinkedList<ArrayDeque<Integer>>  listeDeDeques;

	public UnshuffleSort() {
		this.listeDeDeques = new LinkedList<ArrayDeque<Integer>>();
	}

	/**
	 * tri de la table d'entiers re?ue en parametre
	 *
	 * @param tableATrier la table a trier
	 * @return table contenant les entiers tries
	 */
	public int[] trier(int[] tableATrier) {
		remplirDeques(tableATrier);
		return viderDeques(tableATrier.length);
	}

	/**
	 * 1ere etape du tri : repartition des entiers dans des deques
	 * @param tableATrier la table a trier
	 */
	private void remplirDeques(int[]tableATrier) {
		// Pour le debug:
		for (int i = 0; i < tableATrier.length; i++) {
			placerEntier(tableATrier[i]);
		}

		System.out.println("etape1");
		System.out.println(listeDeDeques);
	}

	public void placerEntier(int entier) {

		for (ArrayDeque<Integer> listeDeDeque : listeDeDeques) {
			if (listeDeDeque.isEmpty()) {
				listeDeDeque.add(entier);
				return;
			}
			if (listeDeDeque.getFirst() >= entier) {
				listeDeDeque.addFirst(entier);
				return;
			} else if (listeDeDeque.getLast() <= entier) {
				listeDeDeque.addLast(entier);
				return;
			}
		}
		listeDeDeques.addLast(new ArrayDeque<Integer>());
		listeDeDeques.getLast().add(entier);
		// Pour le debug:
		System.out.println(listeDeDeques);
	}


	/**
	 * 2eme etape du tri : on vide les deques
	 *
	 * @param taille nombre d'entiers de la table a trier
	 * @return table contenant les entiers tries
	 */
	private int[] viderDeques(int taille) {
		int[] table = new int[taille];
		int min = Integer.MAX_VALUE;
		int indexMin = 0;
		for (int i = 0; i < taille; i++) {
			if (listeDeDeques.isEmpty()) break;
			table[i] = listeDeDeques.getFirst().removeFirst();
			if (listeDeDeques.getFirst().isEmpty()) listeDeDeques.removeFirst();
			if (!listeDeDeques.isEmpty()) {
				for (int j = 0; j < listeDeDeques.size(); j++) {
					if (listeDeDeques.get(j).getFirst() < min) {
						min = listeDeDeques.get(j).getFirst();
						indexMin = j;
					}
				}
				listeDeDeques.addFirst(listeDeDeques.get(indexMin));
				listeDeDeques.remove(indexMin + 1);
				min = Integer.MAX_VALUE;
			}
		}
		// Pour le debug:
		System.out.println("etape2");

		return table;

	}

	private int supprimerPlusPetitEntier(){

		// supprime et renvoie l'entier supprime

		return 0;
	}

	private void reorganiserListe(){

		// Pour le debug:
		System.out.println(listeDeDeques);

	}

	private void deplacerPremierDeque() {

	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public String toString(){
		return listeDeDeques.toString();
	}

}