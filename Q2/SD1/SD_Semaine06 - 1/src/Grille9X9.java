import java.util.Arrays;

public class Grille9X9 {

	private int[][] table;

	public Grille9X9(int[][] tableARecopier)throws IllegalArgumentException{
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		if(tableARecopier.length!=9)
			throw new IllegalArgumentException();
		for(int i = 0;i<9;i++){
			if(tableARecopier[i]==null||tableARecopier[i].length!=9)throw new IllegalArgumentException();
		}
		table = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(tableARecopier [i][j]<1||tableARecopier[i][j]>9)throw new IllegalArgumentException();
				table[i][j]=tableARecopier[i][j];
			}
		}
	}

	private boolean ligneCorrecte(int ligne){

		Ensemble1A9 ensemble1A9 = new Ensemble1A9();

		for (int i = 0; i < 9; i++){

			if ( !ensemble1A9.ajouter( table[ligne][i] ) ) return false;

		}

		return true;
	
	}

	private boolean colonneCorrecte(int colonne){

		Ensemble1A9 ensemble1A9 = new Ensemble1A9();

		for (int i = 0; i < 9; i++){

			if ( !ensemble1A9.ajouter( table[i][colonne] ) ) return false;

		}

		return true;

	}
	
	// verifie le bloc qui commence a la ligne et a la colonne passees en parametre
	private boolean blocCorrect(int ligne, int colonne){

		Ensemble1A9 ensemble1A9 = new Ensemble1A9();

		for (int i = 0; i < 3; i++){

			for (int j = 0; j < 3; j++){

				if ( !ensemble1A9.ajouter( table[ligne+i][colonne+j] ) ) return false;

			}

		}

		return true;
	
	}
	public boolean estUnSudoku(){

		for (int i = 0; i < 9; i++){

			if ( !colonneCorrecte( i ) ) return false;
			if ( !ligneCorrecte( i ) ) return false;

		}

		for (int i = 0; i < 9; i+=3 ){

			if ( !blocCorrect( i, i ) ) return false;

		}

		return true;
	
	}
	
	
	
	public boolean estUnSudokuDiagonal(){
		// TODO
		// cette methode est proposee en ex supplementaire
		return false;

	}
	
	

	public boolean estUnHyperSudoku(){
		// TODO
		// cette methode est proposee en ex supplementaire
		return false;

	}



}
