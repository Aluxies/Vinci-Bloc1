/** Classe Relation héritant de RelationDeBase
	 Fournit des outils de manipulation des relations entre sous-ensembles de l'Univers
 */

import java.util.*;

public class Relation extends RelationDeBase {

	/** Valeur numérique de MAXELT */
	private static final int MAX = Elt.MAXELT.val();

	/** Construit la Relation vide sur l'ensemble vide */
	public Relation() {
		super();
	}

	/** Construit la Relation vide de d vers a */
	public Relation(EnsembleAbstrait d, EnsembleAbstrait a) {
		super(d, a);
	}

	/** Clone */
	public Relation clone() {

		return (Relation) super.clone();
	}
	
	//Ex1
	//renvoie le domaine de la relation courante
	public EnsembleAbstrait domaine() {

		Ensemble domaine = new Ensemble();

		for ( Couple couple : this ) {

			domaine.ajouter( couple.getX() );

		}

		return domaine;

	}
	
	//renvoie l'image de la relation courante
	public EnsembleAbstrait image() {

		Ensemble image = new Ensemble();

		for ( Couple couple : this ) {

			image.ajouter( couple.getY() );

		}

		return image;

	}
	
	// EX 2
	// renvoie la complémentaire de la relation courante
	public Relation complementaire() {

		Relation complementaire = new Relation( depart(), arrivee() );

		for ( Elt x : depart() ){

			for ( Elt y : arrivee() ){

				if ( !contient( x, y ) ){

					complementaire.ajouter( x, y );

				}

			}

		}

		return complementaire;

	}

	// renvoie la réciproque de la relation courante
	public Relation reciproque() {

		Relation reciproque = new Relation( arrivee(), depart() );

		for ( Couple couple : this ) {

			reciproque.ajouter( couple.getY(), couple.getX() );

		}

		return reciproque;

	}

	// si possible, remplace la relation courante par son union avec r
	//sinon, lance une IllegalArgumentException
	public void ajouter(RelationInterface r) {

		if ( r == null || !r.depart().equals( depart() ) || !r.arrivee().equals( arrivee() ) ) throw new IllegalArgumentException();

		for ( Couple couple  : r ) {

			if ( !contient( couple ) ) ajouter( couple );

		}

	}

	// si possible, remplace this par sa différence avec r
	//sinon, lance une IllegalArgumentException
	public void enlever(RelationInterface r) {

		if ( r == null || !r.depart().equals( depart() ) || !r.arrivee().equals( arrivee() ) ) throw new IllegalArgumentException();

		if ( r == this ){

			Relation relation = this.clone();

			for ( Couple couple : relation ) {

				if ( contient( couple ) ) enlever( couple );

			}

		} else {

			for ( Couple couple : r ) {

				if ( contient( couple ) ) enlever( couple );

			}

		}



	}

	// si possible, remplace this par son intersection avec r
	//sinon, lance une IllegalArgumentException
	public void intersecter(RelationInterface r) {

		if ( r == null || !r.depart().equals( depart() ) || !r.arrivee().equals( arrivee() ) ) throw new IllegalArgumentException();

		for ( Elt x : depart() ) {

			for ( Elt y : arrivee() ) {

				Couple couple = new Couple( x, y );

				/*
					Si la relation courante contient le couple,
					mais que la 2e relation ne contient pas le couple,
					on retire le couple pour former l'intersection,
					afin de n'avoir que des couples communs aux deux relations
				 */

				if ( contient( couple ) && !r.contient( couple ) ){

					enlever( couple );

				}
			}

		}

	}

	// si possible, renvoie la composée : this après r
	//sinon, lance une IllegalArgumentException
	public Relation apres(RelationInterface r) {

		// Il faut regarder si l'ensemble de l'arrivee de la relation de départ
		// est égal à l'ensemble de départ de la relation d'arrivée

		if ( r == null || !r.arrivee().equals( this.depart() ) ) throw new IllegalArgumentException();

		Relation composee = new Relation( r.depart(), this.arrivee() );

		// this o r
		// r est la relation de départ
		// this est la relation d'arrivée

		for ( Couple couple1 : r ) {

			for ( Couple couple2 : this ) {

				/*
				 	On regarde si le y de la relation r
				 	a la même valeur que le x de la relation this,
				 	dans le cas où c'est vrai, on ajoute le couple avec
				 	le x de la relation r et le y de la relation this
				 */

				if ( couple1.getY().val() == couple2.getX().val() ){

					composee.ajouter( couple1.getX(), couple2.getY() );

				}

			}

		}

		return composee;

	}


	
	/*Les exercices 4 et 5 ne concernent que les relations sur un ensemble.
	 * Les méthodes demandées génèreront donc une MathException lorsque l'ensemble de départ
	 * ne coïncide pas avec l'ensemble d'arrivée.
	 */
	
	/* Ex 4 */
		
	// Clôture la Relation courante pour la réflexivité
	public void cloReflex() {

		if ( !depart().equals( arrivee() ) ) throw new MathException();

		for ( Elt x : depart() ) {

			Couple couple = new Couple( x, x );

			if ( !contient( couple ) ) ajouter( couple );

		}

	}

	// Clôture la Relation courante pour la symétrie
	public void cloSym() {

		if ( !depart().equals( arrivee() ) ) throw new MathException();

		for ( Elt x : depart() ) {

			for ( Elt y : arrivee() ) {

				if ( x.val() != y.val() ){

					Couple couple1 = new Couple( x, y );
					Couple couple2 = new Couple( y, x );

					// S'il y a une flèche dans le sens de x à y,
					// on ajoute celle dans l'autre sens

					if ( contient( couple1 ) ){

						ajouter( couple2 );

					}

					// S'il y a une flèche dans le sens de y à x,
					// on ajoute celle dans l'autre sens

					if ( contient( couple2 ) ){

						ajouter( couple1 );

					}

				}

			}

		}

	}

	// Clôture la Relation courante pour la transitivité (Warshall)
	public void cloTrans() {

		if ( !depart().equals( arrivee() ) ) throw new MathException();

		for ( Elt j : arrivee() ){

			for ( Elt i : depart() ){

				// S'il y a une flèche qui va de i vers j

				if ( contient( i, j ) ){

					for ( Elt k : arrivee() ){

						// ET s'il y a une flèche qui va de j vers k

						if ( contient( j, k ) ){

							// ALORS il doit y avoir une flèche de i vers k ( clôture transitive )

							ajouter( i, k );

						}

					}

				}

			}

		}

	}
	
	
	//Ex 5
	/*Les questions qui suivent ne concernent que les relations sur un ensemble.
	 * Les méthodes demandées génèreront donc une MathException lorsque l'ensemble de départ
	 * ne coïncide pas avec l'ensemble d'arrivée.
	 */
	// renvoie true ssi this est réflexive
	public boolean reflexive(){

		if ( !depart().equals( arrivee() ) ) throw new MathException();

		for ( Elt x : depart() ){

			if ( !contient( x, x ) ) return false;

		}

		return true;

	}

	// renvoie true ssi this est antiréflexive
	public boolean antireflexive(){

		if ( !depart().equals( arrivee() ) ) throw new MathException();

		for ( Elt x : depart() ){

			if ( contient( x, x ) ) return false;

		}

		return true;

	}

	// renvoie true ssi this est symétrique
	public boolean symetrique(){

		if ( !depart().equals( arrivee() ) ) throw new MathException();

		if ( cardinal() == 0 ) return true;

		for ( Elt x : depart() ){

			for ( Elt y : arrivee() ){

				if ( contient( x, y ) && !contient( y, x ) || !contient( x, y ) || contient( y, x ) ){

					return false;

				}

			}

		}

		return true;

	}

	// renvoie true ssi this est antisymétrique
	public boolean antisymetrique(){

		if ( !depart().equals( arrivee() ) ) throw new MathException();

		if ( cardinal() == 0 ) return true;

		for ( Couple couple : this ) {

			// Si la relation contient un couple qui va dans l'autre sens
			// et que les deux éléments du couples sont différents
			// alors la relation est antisymétrique

			if ( contient( couple.getY(), couple.getX() ) && !couple.getY().equals( couple.getX() ) ) return false;

		}

		return true;

	}

	// renvoie true ssi  this est transitive
	public boolean transitive(){

		if ( !depart().equals( arrivee() ) ) throw new MathException();

		for ( Couple couple1 : this ){

			for ( Couple couple2 : this ){

				if ( couple1.getY().equals( couple2.getX() ) ){

					if ( !contient( couple1.getX(), couple2.getY() ) ) return false;

				}

			}

		}

		return true;

	}
	
	// Ex 6
	//Construit une copie de la relation en paramètre
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Relation(RelationInterface r) {

		if ( r == null ) throw new IllegalArgumentException();

		for ( Elt x : r.depart() ) {

			ajouterDepart( x );

		}

		for ( Elt y : r.arrivee() ) {

			ajouterArrivee( y );

		}

		for ( Couple couple : r ) {

			ajouter( couple );

		}

	}

	//renvoie l'identité sur e
	//lance une IllegalArgumentException en cas de paramètre invalide
	public static Relation identite(EnsembleAbstrait e) {

		if ( e == null ) throw new IllegalArgumentException();

		Relation relation = new Relation( e, e );

		for ( Elt elt : e ) {

			relation.ajouter( elt, elt );

		}

		return relation;
	}

	//renvoie le produit cartésien de a et b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public static Relation produitCartesien(EnsembleAbstrait a, EnsembleAbstrait b) {

		if ( a == null || b == null ) throw new IllegalArgumentException();

		Relation produitCartesien = new Relation( a, b );

		for ( Elt x : a ){

			for ( Elt y : b ){

				produitCartesien.ajouter( x, y );

			}

		}

		return produitCartesien;

	}

} // class Relation
