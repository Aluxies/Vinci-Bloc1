import java.util.NoSuchElementException;

import java.util.ArrayList;
public class ListeCaracteres {

	private NoeudCaractere tete;
	// N'ajoutez pas d'autres attributs
	
	public ListeCaracteres() {
		this.tete=null;
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public ListeCaracteres(char[] tableCaracteres) {
		if(tableCaracteres==null)
			throw new IllegalArgumentException();
		for (int i = tableCaracteres.length-1; i>=0; i--) {
			this.tete=new NoeudCaractere(tableCaracteres[i],tete);
		}	
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS!!!
	public String toString(){
		String aRenvoyer = "";
		NoeudCaractere baladeur = tete;
		while(baladeur != null) {
			aRenvoyer += " " + baladeur.caractere;
			baladeur = baladeur.suivant;
		}
		return aRenvoyer;
	}
	
	// A NE PAS MODIFIER --> POUR LES TESTS !!!
	public void remplacerToutParX(){
		NoeudCaractere baladeur = tete;
		while(baladeur != null) {
			baladeur.caractere = 'x';
			baladeur = baladeur.suivant;
		}
	}
	
	/**
	 * verifie la presence du caractere passe en parametre dans la liste
	 * @param caractereRecherche
	 * @return true si le caractere est present dans la liste, false sinon
	 */
	public boolean contient(char caractereRecherche){

		NoeudCaractere baladeur = tete;

		while ( baladeur != null ){

			if ( baladeur.caractere == caractereRecherche ) return true;

			baladeur = baladeur.suivant;

		}

		return false;

	}

	
	/**
	 * calcule le nombre de fois qu'apparait le caractere passe en parametre dans la liste
	 * @param caractereRecherche
	 * @return le nombre d'occurrences du caractere
	 */
	public int nombreOccurrences(char caractereRecherche){

		NoeudCaractere baladeur = tete;

		int nombreOccurences = 0;

		if ( !contient( caractereRecherche ) ) return nombreOccurences;

		while ( baladeur != null ){

			if ( baladeur.caractere == caractereRecherche ) nombreOccurences++;

			baladeur = baladeur.suivant;

		}
		
		return nombreOccurences;
		
	}

	
	/**
	 * remplace la 1ere occurrences du caractere a remplacer par un nouveau caractere
	 * @param caractereARemplacer le caractere a remplacer
	 * @param nouveauCaractere le nouveau caractere
	 */
	public void remplacer(char caractereARemplacer, char nouveauCaractere){
		
		NoeudCaractere baladeur = tete;

		while ( baladeur != null ){

			if ( baladeur.caractere == caractereARemplacer ){

				baladeur.caractere = nouveauCaractere;
				break;

			}

			baladeur = baladeur.suivant;

		}

	}

	
	/**
	 * remplace toutes les occurrences du caractere a remplacer par un nouveau caractere
	 * @param caractereARemplacer le caractere a remplacer
	 * @param nouveauCaractere le nouveau caractere
	 */
	public void remplacerTout(char caractereARemplacer, char nouveauCaractere){

		NoeudCaractere baladeur = tete;

		while ( baladeur != null ){

			if ( baladeur.caractere == caractereARemplacer ){

				baladeur.caractere = nouveauCaractere;

			}

			baladeur = baladeur.suivant;

		}

	}
	
	
	/**
	 * recherche le plus grand caractere de la liste ('a'<'b'< ...)
	 * @return le plus grand caractere 
	 * @throws NoSuchElementException si la liste est vide
	 */
	public char max() throws NoSuchElementException{

		if ( tete == null ) throw new NoSuchElementException();

		NoeudCaractere baladeur = tete;
		char max = tete.caractere;

		while ( baladeur != null ){

			if ( baladeur.caractere > max ) max = baladeur.caractere;

			baladeur = baladeur.suivant;

		}

		return max;

	}
	
	
	
	
	/**
	 * cree une arrayList contenant les caracteres de la liste 
	 * L'ordre doit etre respecte (une liste est une structure lineaire)
	 * @return l'arrayList cree
	 */
	public ArrayList<Character> enArrayList(){

		ArrayList<Character> listeCaracteres = new ArrayList<Character>();

		NoeudCaractere baladeur = tete;

		while ( baladeur != null ){

			listeCaracteres.add( baladeur.caractere );

			baladeur = baladeur.suivant;

		}

		return listeCaracteres;
		
	}

	/**
	 * verifie si les 2 listes contiennent les memes caracteres et ceci dans le meme ordre
	 * Une liste est une structure LINEAIRE!
	 * @param l la liste a comparer a la liste courante
	 * @return true si les 2 listes sont les memes, false sinon
	 */
	public boolean estEgalA(ListeCaracteres l){

		NoeudCaractere baladeur1 = tete;
		NoeudCaractere baladeur2 = l.tete;

		while ( baladeur1 != null && baladeur2 != null ){

			if ( baladeur1.caractere != baladeur2.caractere ) return false;

			baladeur1 = baladeur1.suivant;
			baladeur2 = baladeur2.suivant;

		}

		// Si un des deux baladeurs est null, mais pas l'autre
		// Alors on n'a pas les mêmes listes, car on n'a pas
		// la même longueur de liste

		if ( baladeur1 == null && baladeur2 != null || baladeur1 != null && baladeur2 == null ) return false;

		return true;

	}
	
	/**
	 * cree une liste qui est une copie de la liste courante (meme ordre)
	 * @return une copie de la liste courante
	 */
	public ListeCaracteres clone(){
		
		return null;
		// TODO		
		// DEFI!	
		
	}

	

	/**
	 * supprime une fois le caractere passe en parametre
	 * si le caractere se trouve plusieurs fois, c est sa premiere occurrence qui sera supprimee
	 * @param caractereASupprimer
	 * @return true si le caractere etait bien present dans la liste, false sinon
	 */
	public boolean supprimerPremiereOccurrence(char caractereASupprimer){
		
		if(tete==null)
			return false;
		
		if(tete.caractere==caractereASupprimer){
			this.tete = this.tete.suivant;
			return true;
		}
		
		NoeudCaractere baladeur = this.tete;
		while(baladeur.suivant != null) {
			if(baladeur.suivant.caractere == caractereASupprimer){
				baladeur.suivant = baladeur.suivant.suivant;
				return true;
			}
			baladeur = baladeur.suivant;
		}
		
		return false;	
	}

	
	
	/**
	 * supprime le caractere autant de fois qu'il est present dans la liste
	 * @param caractereASupprimer
	 * @return le nombre de suppressions effectuees
	 */
	public int supprimerToutesLesOccurrences(char caractereASupprimer){
		
		return 0;	
		// TODO
		// DEFI!
		// Lisez attentivement la solution iterative de supprimerPremiereOccurrence
	
	}
	

	

	private class NoeudCaractere{
		private char caractere;
		private NoeudCaractere suivant;

		public NoeudCaractere(char caractere, NoeudCaractere suivant){
			this.caractere = caractere;
			this.suivant = suivant;
		}

	}
}
