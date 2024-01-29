import java.util.Iterator;

public class Ordre extends RelationAbstraite {

	private Relation couples;

	//construit l'identité sur e
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Ordre(EnsembleAbstrait e) {
		
		if ( e == null ) throw new IllegalArgumentException();

		couples = new Relation( e, e );

		for ( Elt elt : e ){

			couples.ajouter( elt, elt );

		}

	}

	//construit le plus petit ordre contenant r
	//lance une IllegalArgumentException si cette construction n'est pas possible
	public Ordre(Relation r) {

		if ( r == null || !r.depart().equals( r.arrivee() ) ) throw new IllegalArgumentException();

		couples = r.clone();

		couples.cloTrans();
		couples.cloReflex();

		if ( !couples.antisymetrique() ) throw new IllegalArgumentException();

	}
	
	//constructeur pas recopie
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Ordre(Ordre or) {

		if ( or == null ) throw new IllegalArgumentException();

		couples = or.couples.clone();

	}

	//ajoute x à l'ensemble sous-jacent de la relation d'ordre
	//ne fait rien si x est déjà dans l'ensemble sous-jacent
	//lance une IllegalArgumentException en cas de paramètre invalide
	public void ajouterAuSousJacent(Elt x) {

		if ( x == null ) throw new IllegalArgumentException();

		couples.ajouterArrivee( x );
		couples.ajouterDepart( x );
		couples.ajouter( x, x );

	}

	//enlève x de l'ensemble sous-jacent de la relation d'ordre
	//ainsi que toutes les flêches liées à x
	//ne fait rien si x n'est pas dans l'ensemble sous-jacent 
	//lance une IllegalArgumentException en cas de paramètre invalide
	public void enleverDuSousJacent(Elt x) {

		if ( x == null ) throw new IllegalArgumentException();

		couples.supprimerArrivee( x );
		couples.supprimerDepart( x );

	}
	
	@Override
	public Iterator<Couple> iterator() {
		return couples.iterator();
	}

	@Override
	public boolean estVide() {
		return couples.estVide();
	}

	@Override
	public boolean contient(Couple c) {
		if (c== null) throw new IllegalArgumentException();
		if (!couples.depart().contient(c.getX())||!couples.arrivee().contient(c.getY())) throw new IllegalArgumentException();
		return couples.contient(c.getX(), c.getY());
	}

	@Override
	//crée (si possible) le plus petit ordre contenant this et c
	//lance une IllegalArgumentException en cas de paramètre invalide
	public void ajouter(Couple c) {

		if ( c == null ) throw new IllegalArgumentException();
		if ( couples.contient( c ) ) return;
		if ( couples.contient( c.reciproque() ) ) throw new IllegalArgumentException();

		couples.ajouter( c );
		couples.cloReflex();
		couples.cloTrans();

	}


	@Override
	//Enlève (si possible) l'arête de Hasse c du la relation d'ordre
	//lance une IllegalArgumentException en cas de si le paramètre est invalide ou si c n'est pas une arête de Hasse
	//ne fait rien sinon
	public void enlever(Couple c) {
		if (c== null) throw new IllegalArgumentException();
		if (!this.depart().contient(c.getX())) throw new IllegalArgumentException();
		if (!this.depart().contient(c.getY())) throw new IllegalArgumentException();
		if (!this.contient(new Couple(c.getX(), c.getY()))) return;
		if (!estUneAreteDeHasse(c.getX(), c.getY()))
			throw new IllegalArgumentException();
		Ensemble plusPttX = this.plusPetitQue(c.getX());
		Ensemble plusGrdY = this.plusGrandQue(c.getY());
		for (Elt eX : plusPttX) {
			for (Elt eY : plusGrdY) {
				this.couples.enlever(eX, eY);
			}
		}
		this.couples.cloTrans();
	}
	
	private Ensemble plusPetitQue(Elt e){
		Ensemble min = new Ensemble();
		for (Elt eC : couples.depart()){
			if (couples.contient(eC, e)) min.ajouter(eC);
		}
		return min;
	}
	
	private Ensemble plusGrandQue(Elt e){
		Ensemble maj = new Ensemble();
		for (Elt eC : couples.depart()){
			if (couples.contient(e,eC)) maj.ajouter(eC);
		}
		return maj;
	}

	private boolean estUneAreteDeHasse(Elt x, Elt y) {
		if (!this.contient(new Couple(x, y)))
			return false;
		if (x.equals(y))
			return false;
		EnsembleAbstrait aParcourir = this.depart();
		aParcourir.enlever(x);
		aParcourir.enlever(y);
		for (Elt e : aParcourir) {
			if (this.contient(new Couple(x, e)) && this.contient(new Couple(e, y)))
				return false;
		}
		return true;
	}

	public boolean estUneAreteDeHasse(Couple c) {
		if (c== null) throw new IllegalArgumentException();
		return estUneAreteDeHasse(c.getX(), c.getY());
	}

	@Override
	public EnsembleAbstrait depart() {
		return couples.depart();
	}

	@Override
	public EnsembleAbstrait arrivee() {
		return couples.arrivee();
	}

	//renvoie true ssi x et y sont comparables pour l'ordre courant
	//lance une IllegalArgumentException en cas de paramètre invalide
	public boolean comparables(Elt x, Elt y) {

		if( !couples.depart().contient( x ) || !couples.arrivee().contient( y ) ) throw new IllegalArgumentException();

		if ( couples.contient( x, y ) || couples.contient( y, x ) ) return true;

		return false;

	}

	// Renvoie l'ensemble des éléments minimaux de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait minimaux( EnsembleAbstrait b ){

		if ( b == null ) throw new IllegalArgumentException();

		if ( !b.inclusDans( depart() ) || !b.inclusDans( arrivee() ) ) throw new IllegalArgumentException();

		Ensemble minimaux = new Ensemble(b);

		for ( Elt x : b ){

			for ( Elt y : b ){

				if ( couples.contient( x, y ) && !x.equals( y ) ) minimaux.enlever( y );

			}

		}

		return minimaux;

	}
	
	// Renvoie l'ensemble des éléments maximaux de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait maximaux(EnsembleAbstrait b) {

		if ( b == null ) throw new IllegalArgumentException();

		if ( !b.inclusDans( depart() ) || !b.inclusDans( arrivee() ) ) throw new IllegalArgumentException();

		Ensemble maximaux = new Ensemble(b);

		for ( Elt x : b ){

			for ( Elt y : b ){

				if ( couples.contient( x, y  ) && !x.equals( y ) ) maximaux.enlever( x );

			}

		}

		return maximaux;

	}

	// Renvoie le minimum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt minimum(EnsembleAbstrait b) {

		if ( b == null ) throw new IllegalArgumentException();

		if ( !b.inclusDans( depart() ) || !b.inclusDans( arrivee() ) ) throw new IllegalArgumentException();

		if ( minimaux( b ).cardinal() > 1 ) return null;

		for ( Elt x : b ){

			if ( minimaux(b).contient( x ) ) return x;

		}

		return null;
	}
	
	// Renvoie le maximum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt maximum(EnsembleAbstrait b) {

		if ( b == null ) throw new IllegalArgumentException();

		if ( !b.inclusDans( depart() ) || !b.inclusDans( arrivee() ) ) throw new IllegalArgumentException();

		if ( maximaux( b ).cardinal() > 1 ) return null;

		for ( Elt x : b ){

			if ( maximaux(b).contient( x ) ) return x;

		}

		return null;

	}

	// Renvoie l'ensemble des minorants de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait minor(EnsembleAbstrait b) {

		if ( b == null ) throw new IllegalArgumentException();

		if ( !b.inclusDans( depart() ) || !b.inclusDans( arrivee() ) ) throw new IllegalArgumentException();

		Ensemble ensemble = new Ensemble( couples.depart() );

		for ( Elt x : couples.depart() ){

			for ( Elt y : b ){

				if  ( !x.equals( y ) && ( !comparables( x, y ) || couples.contient( y, x ) ) ) ensemble.enlever( x );

			}
				
		}

		return ensemble;

	}
	
	// Renvoie l'ensemble des majorants de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait major(EnsembleAbstrait b) {

		if ( b == null ) throw new IllegalArgumentException();

		if ( !b.inclusDans( depart() ) || !b.inclusDans( arrivee() ) ) throw new IllegalArgumentException();

		Ensemble ensemble = new Ensemble( couples.arrivee() );

		for ( Elt x : b ){

			for ( Elt y : couples.arrivee() ){

				if ( !x.equals( y ) && ( !comparables( x, y ) || couples.contient( y, x ) ) ) ensemble.enlever( y );

			}

		}

		return ensemble;

	}

	// Renvoie l'infimum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt infimum(EnsembleAbstrait b) {

		if ( b == null ) throw new IllegalArgumentException();

		if ( !b.inclusDans( depart() ) || !b.inclusDans( arrivee() ) ) throw new IllegalArgumentException();

		return maximum( minor( b ) );

	}
	
	// Renvoie le supremum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt supremum(EnsembleAbstrait b) {

		if ( b == null ) throw new IllegalArgumentException();

		if ( !b.inclusDans( depart() ) || !b.inclusDans( arrivee() ) ) throw new IllegalArgumentException();

		return minimum( major( b ) );

	}

	//Renvoie true ssi this est un treillis
	public boolean treillis(){

		for ( Elt x : couples.depart() ){

			for ( Elt y : couples.arrivee() ){

				if ( !comparables( x, y ) ){

					EnsembleAbstrait ensembleAbstrait = new Ensemble();

					ensembleAbstrait.ajouter( x );
					ensembleAbstrait.ajouter( y );

					if ( supremum( ensembleAbstrait ) == null ) return false;
					if ( infimum( ensembleAbstrait ) == null ) return false;

				}

			}

		}

		return true;

	}

	public String toString() {
		return couples.toString();
	}
	
}
