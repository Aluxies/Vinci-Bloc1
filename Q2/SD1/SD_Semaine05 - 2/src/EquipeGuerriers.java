import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class EquipeGuerriers {

	private ArrayList<Guerrier> vecteurGuerriers;
	private LinkedList<Guerrier> listeGuerriersEnJeu;
	private int pointsDeVieDeDepart;
	
	
	
	// Attention : seul interet de ce constructeur : les tests!!!
	// A NE PAS MODIFIER
	public EquipeGuerriers(ArrayList<Guerrier> vecteurGuerriers,
			LinkedList<Guerrier> listeGuerriersEnJeu, int pointsDeVieDeDepart) {
		super();
		this.vecteurGuerriers = vecteurGuerriers;
		this.listeGuerriersEnJeu = listeGuerriersEnJeu;
		this.pointsDeVieDeDepart = pointsDeVieDeDepart;
	}

	/**
	 * construit l'equipe 
	 * @param nombreGuerriers nombre de guerriers en vie au debut du jeu
	 * @param pointsDeVieDeDepart nombre de points de vie attribue a chaque guerrier au debut du jeu
	 * @throws IllegalArgumentException il faut au moins 1 guerrier vivant pour combattre la creature du mal
	 */
	public EquipeGuerriers(int nombreGuerriers, int pointsDeVieDeDepart){

		if ( nombreGuerriers < 1 || pointsDeVieDeDepart < 1 ) throw new IllegalArgumentException();

		this.vecteurGuerriers = new ArrayList<Guerrier>();
		this.listeGuerriersEnJeu = new LinkedList<Guerrier>();

		for ( int i = 1; i <= nombreGuerriers; i++ ){

			Guerrier new_guerrier = new Guerrier( i, pointsDeVieDeDepart );

			vecteurGuerriers.add( new_guerrier );
			listeGuerriersEnJeu.add( new_guerrier );

		}

		this.pointsDeVieDeDepart = pointsDeVieDeDepart;
	
	} 

	/**
	 * renvoie le nombre de guerriers encore en vie
	 * @return le nombre de guerriers encore en vie
	 */
	public int nombreGuerriersEnVie(){

		return listeGuerriersEnJeu.size();
	
	}
	
	/**
	 * selectionne un guerrier, lui diminue son nombre de points de vie et le remet en jeu s'il n'est pas mort
	 * @param pointsDeViePerdus le nombre perdu par le guerrier au combat
	 * @return le guerrier qui a combattu
	 * @throws 'NoSuchElementException si tous les guerriers sont morts
	 * @throws IllegalArgumentException le nombre de points de vie perdus est un nombre <= 0
	 */
	public Guerrier jouer(int pointsDeViePerdus){

		if ( pointsDeViePerdus <= 0 ) throw new IllegalArgumentException();
		if ( nombreGuerriersEnVie() == 0 ) throw new NoSuchElementException();

		Guerrier guerrier = listeGuerriersEnJeu.removeFirst();

		int pointsDeVie = guerrier.getPointsDeVie() - pointsDeViePerdus;

		guerrier.setPointsDeVie( pointsDeVie );

		if ( pointsDeVie > 0 ) listeGuerriersEnJeu.add( guerrier );

		return guerrier;
		
	}
	
	/**
	 * renvoie le guerrier dont le numero est passe en parametre, meme s'il est mort
	 * @param numero le numero du guerrier demande
	 * @return le guerrier qui a comme numero le numero passe en parametre
	 * @throws IllegalArgumentException le numero n'a pas ete attribue
	 */
	public Guerrier getGuerrier(int numero){

		if ( numero <= 0 || numero > nombreGuerriersEnVie() ) throw new IllegalArgumentException();
		
		return vecteurGuerriers.get( numero - 1 );
		
	}
	
	/**
(	 * ajoute un nouveau guerrier dans l'equipe
	 * ce guerrier recoit un numero non attribue et le nombre de points de vie de depart
	 * ce guerrier va prendre place au combat en se placant apres le guerrier vivant qui porte le numero juste plus petit que lui
	 * Par exemple : g4 est mort et ordre des combats : g3 g1 g2 ', le nouveau guerrier va se placer entre g3 et g1)
	 * @return le numero du guerrier ajoute
	 */
	public int ajouterNouveauGuerrier(){

		int numero = vecteurGuerriers.size() + 1;

		Guerrier guerrier = new Guerrier(numero, pointsDeVieDeDepart);
		vecteurGuerriers.add(guerrier);

		int indice = 0;
		int numeroMax = 0;

		for (Guerrier guerrier1 : listeGuerriersEnJeu){

			if (guerrier1.getNumero() > numeroMax){

				indice++;
				numeroMax = guerrier1.getNumero();

			}

		}

		listeGuerriersEnJeu.add(indice, guerrier);

		return numero;
	
	}
	
	// Attention : seul interet de ce toString() : les tests!!!
	// A NE PAS MODIFIER
	public String toString(){
		return "vecteur :\n"+vecteurGuerriers.toString()+"\nliste :\n"+listeGuerriersEnJeu.toString();
	}
	
	
}
