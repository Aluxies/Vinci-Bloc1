import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbreDEntiers implements Iterable<Integer>{
	protected NoeudEntier racine; 
	protected int taille;
	
	public ArbreDEntiers () {
		this.racine=null ;
		this.taille=0;
	}

	public ArbreDEntiers (int entier) {
		this.racine=new NoeudEntier(entier) ;
		this.taille=1;
	}
	
	public ArbreDEntiers (ArbreDEntiers gauche, int entier, ArbreDEntiers droit) {
		this.racine=new NoeudEntier(gauche.racine,entier,droit.racine) ;
		this.taille= 1 + gauche.taille + droit.taille;
	}

	public boolean estVide () {
		return  this.racine == null;
	}

	public int taille () {
		return taille;
	}

	public Iterator<Integer> preIterateur () {
		return new PreIterateur();
	}
	
	public Iterator<Integer> postIterateur () {
		class PostIterateur implements Iterator<Integer> {

			private ArrayDeque<Integer> fileDEntiers;

			public PostIterateur () {
				fileDEntiers = new ArrayDeque<Integer>(taille);
				remplirFile(racine);
			}

			private void remplirFile (NoeudEntier n) {

				if ( n == null ) return;

				remplirFile( n.gauche );
				remplirFile( n.droit );
				fileDEntiers.addLast( n.entier );
			}

			public boolean hasNext () {

				return !fileDEntiers.isEmpty();
			}

			public Integer next () {

				return fileDEntiers.removeFirst();

			}
		}
		return new PostIterateur();

	}

	// iterateur in ordre
	// Cet iterateur a ete selectionne comme iterateur par defaut
	public Iterator<Integer> iterator () {

		class InIterateur implements Iterator<Integer> {

			private ArrayDeque<Integer> fileDEntiers;

			public InIterateur () {
				fileDEntiers = new ArrayDeque<Integer>(taille);
				remplirFile(racine);
			}

			private void remplirFile (NoeudEntier n) {

				if ( n == null ) return;

				remplirFile( n.gauche );
				fileDEntiers.addLast( n.entier );
				remplirFile( n.droit );
			}

			public boolean hasNext () {

				return !fileDEntiers.isEmpty();
			}

			public Integer next () {

				if ( !hasNext() ) throw new NoSuchElementException();

				return fileDEntiers.removeFirst();

			}
		}

		return new InIterateur();

	}
	
	public Iterator<Integer> iterateurParNiveau () {

		class NivIterateur implements Iterator<Integer> {

			private ArrayDeque<NoeudEntier> fileDeNoeuds;

			public NivIterateur () {
				fileDeNoeuds = new ArrayDeque<NoeudEntier>(taille);
				if ( racine == null ) return;
				fileDeNoeuds.addLast( racine );
			}

			public boolean hasNext () {

				return !fileDeNoeuds.isEmpty();
			}

			public Integer next () {

				if ( !hasNext() ) throw new NoSuchElementException();

				NoeudEntier noeudCourant = fileDeNoeuds.removeFirst();

				if ( noeudCourant.gauche != null ) fileDeNoeuds.addLast( noeudCourant.gauche );
				if ( noeudCourant.droit != null ) fileDeNoeuds.addLast( noeudCourant.droit );

				return noeudCourant.entier;

			}
		}

		return new NivIterateur();

	}
	
	protected class NoeudEntier {
		protected int entier; 
		protected NoeudEntier gauche;
		protected NoeudEntier droit;

		private NoeudEntier (int entier) {
			this.entier = entier;
			this.gauche = null;
			this.droit = null;
		}
		
		private NoeudEntier (NoeudEntier g,int entier,NoeudEntier d) {
			this.entier = entier;
			this.gauche = g;
			this.droit = d;
		}
	}

	private class PreIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public PreIterateur () {
			fileDEntiers = new ArrayDeque<Integer>(taille);
			remplirFile(racine);
			//La classe PreIterator possede un attribut : une file d’entiers (ArrayDeque<Integer>).
			//Le constructeur de la classe va s’occuper de remplir cette file avec tous les entiers contenus dans l’arbre.
			//Il construit la file et appelle la methode recursive remplirFile()
		}

		private void remplirFile (NoeudEntier n) {

			if ( n == null ) return;

			fileDEntiers.addLast( n.entier );
			remplirFile( n.gauche );
			remplirFile( n.droit );

			//C’est la methode remplirFile() qui se charge de remplir la file.
			//Il s’agit d’une methode recursive !
			//Le but de cet iterateur est de parcourir l’arbre en pre-ordre !
			//Il faut donc « enfiler » les objets dans la file de façon a respecter ce parcours.
		}

		public boolean hasNext () {

			return !fileDEntiers.isEmpty();

			//cette methode verifie si la file est non vide.
		}

		public Integer next () {

			if ( !hasNext() ) throw new NoSuchElementException();

			return fileDEntiers.removeFirst();

			//cette methode "defile"
		}
	}

}
