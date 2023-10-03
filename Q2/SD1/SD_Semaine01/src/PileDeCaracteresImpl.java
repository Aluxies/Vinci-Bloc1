// implementation d'une pile en utilisant un tableau de taille variable redimensionnable

/**
 * @author 
 *
 */

public class PileDeCaracteresImpl implements PileDeCaracteres{

	private char[] table; 			// ne modifiez pas cet identifiant, va servir pour les tests
	private int nombreCaracteres; 	// taille logique
								  	//ne mofifiez pas cet identifiant, va servir pour les tests

	
	public PileDeCaracteresImpl(){
		table = new char[4];
		nombreCaracteres = 0;
	}

	
	public PileDeCaracteresImpl(int capacite){
		if (capacite <= 0)
			throw new IllegalArgumentException("la taille physique ne peut etre negative ou nulle");
		table = new char[capacite];
		nombreCaracteres = 0;
	}

	
	public int taille(){
		return nombreCaracteres;
	}

	
	public boolean estVide(){
		return nombreCaracteres == 0;
	}

	
	public void push(char c){

		int longueur = table.length;

		if ( nombreCaracteres == longueur ){

			char[] tableau = new char[longueur*2];

			for ( int i = 0; i < nombreCaracteres; i++ ){

				tableau[i] = table[i];

			}

			table = tableau;

		}

		int indice = nombreCaracteres;

		table[indice] = c;

		nombreCaracteres++;

		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
	}


	public char pop() throws PileVideException{

		int indice = nombreCaracteres;

		if ( nombreCaracteres == 0 ) throw new PileVideException();

		char caractere = table[indice-1];

		nombreCaracteres--;

		return caractere;

		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
	}


	public char sommet()throws PileVideException{

		int indice = nombreCaracteres;

		if ( nombreCaracteres == 0 ) throw new PileVideException();

		char caractere = table[indice-1];

		return caractere;

		// PENSEZ A CONSULTER LA JAVADOC (cfr Interface PileDeCaracteres)
	}

} 
