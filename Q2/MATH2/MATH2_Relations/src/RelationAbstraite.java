

public abstract class RelationAbstraite implements RelationInterface {
	
	// Ex 3
	// renvoie true ssi this est inclus dans r
	public boolean inclusDans(RelationAbstraite r) {

		// Si les ensembles de départ ou d'arrivée sont différents, this n'est pas inclus dans r

		if ( !r.depart().equals( this.depart() ) || !r.arrivee().equals( this.arrivee() ) ) return false;

		for ( Couple couple : this ) {

			if ( !r.contient( couple ) ) return false;

		}

		return true;

	}

	// renvoie true ssi this est égale à o
	public boolean equals(Object o) {
		if (o== null) return false;
		if (o==this) return true;
		if (! (o instanceof RelationAbstraite)) return false;
		RelationAbstraite r = (RelationAbstraite) o;

		if ( !r.depart().equals( depart() ) || !r.arrivee().equals( arrivee() ) ) return false;

		for ( Couple couple1 : r ) {

			if ( !contient( couple1 ) ) return false;

			for ( Couple couple2 : this ) {

				if ( !r.contient( couple2 ) ) return false;

			}

		}

		return true;

	}
	
	//renvoie un hashCode associé à la relation
	
	public int hashCode(){
		int hash = this.depart().hashCode();
		hash = hash * 31 + this.arrivee().hashCode();
		for (int i = 1; i <= MAX; i++) {
			Elt d = new Elt(i);
			if (this.depart().contient(d)){
				for (int j = 1; j <=MAX; j++ ){
					Elt a = new Elt(j);
					Couple c = new Couple(d,a);
					if (this.contient(c))
						hash = hash *31 + c.hashCode();
				}
			}
		}
		return hash;
	}
}
