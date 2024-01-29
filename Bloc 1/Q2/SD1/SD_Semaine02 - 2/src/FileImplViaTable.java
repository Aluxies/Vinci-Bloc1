import java.util.Arrays;

// implementation de l'interface File via une table circulaire

/**
 * @author 
 *
 */

public class FileImplViaTable<E> implements File<E>{

	private Object[] table;  // ne modifiez pas cet identifiant, la classe test l'utilise					
	private int indiceTete;  // ne modifiez pas cet identifiant, la classe test l'utilise			
	private int taille;		// ne modifiez pas cet identifiant, la classe test l'utilise	
	// N'ajoutez pas d'autres attributs, la classe test risquerait de ne pas fonctionner
	

	public FileImplViaTable(){
		table = new Object[4];
		taille = 0;
		indiceTete = 0;
	}
	

	public boolean estVide(){
		return taille == 0;
	}


	public int taille(){
		return taille;
	}

	public E premier()throws FileVideException{

		if ( taille == 0 ) throw new FileVideException();

		E tete = (E) table[indiceTete];

		return tete;

	}

	public E defile() throws FileVideException{

		if ( taille == 0 ) throw new FileVideException();

		E tete = premier();

		table[indiceTete] = null;

		if ( indiceTete == table.length-1 ) indiceTete = 0;
		else indiceTete++;

		taille--;

		return tete;

	}


	public void enfile(E element) {

		int indice = indiceTete + taille;

		if (indice >= table.length) {

			indice -= table.length;

		};

		if (taille == table.length) {

			Object[] temp = new Object[table.length * 2];

			for (int i = 0; i < table.length; i++) {

				temp[i] = defile();

				taille++;

			};

			indice = taille;

			indiceTete = 0;
			table = temp;

		};

		table[indice] = element;

		taille++;

	};

};