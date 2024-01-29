
public class Questionnaire {
	
	private QuestionCM[] tableQuestions;
	private int indiceSuivant = 0; // l'indice de la question a fournir
	
	
	public Questionnaire(QuestionCM[] tableARecopier) {
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		if(tableARecopier.length==0)
			throw new IllegalArgumentException();
		this.tableQuestions = new QuestionCM[tableARecopier.length];
		for (int i = 0; i < tableARecopier.length; i++) {
			if(tableARecopier[i]==null)
				throw new IllegalArgumentException();
			this.tableQuestions[i]=tableARecopier[i];
		}
	}
	
	/**
	 * renvoie la question suivante
	 * La question qui "suit" la derniere question est la premiere
	 * Precondition ( a ne pas verifier) : la table est non vide
	 * @return une question QuestionCM
	 */
	public QuestionCM fournirQuestion(){

		QuestionCM questionSuivante = tableQuestions[indiceSuivant];

		if ( indiceSuivant == tableQuestions.length-1 ){

			indiceSuivant = 0;

		} else {

			indiceSuivant++;

		}

		return questionSuivante;

	}	

}
