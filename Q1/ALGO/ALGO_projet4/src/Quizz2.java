public class Quizz2 {

    public static FenetreQuizz fenetreQuizz;

    public static void main(String[] args){

        // creation des 2 equipes

        Candidat[] candidatsF = new Candidat[6];
        for (int i = 0; i < candidatsF.length; i++) {
            candidatsF[i] = new Candidat("F"+(i+1));
        }
        Candidat[] candidatsM = new Candidat[6];
        for (int i = 0; i < candidatsM.length; i++) {
            candidatsM[i] = new Candidat("M"+(i+1));
        }

        Equipe equipeF = new Equipe(1, candidatsF);
        Equipe equipeM = new Equipe(2, candidatsM);

        // creation du questionnaire

        Questionnaire questionnaire = chargerQuestions();

        // creation de la fenetre de depart

        fenetreQuizz = new FenetreQuizz("Quizz - Capitales des pays de l'union européenne");
        fenetreQuizz.afficherEquipe(equipeF);
        fenetreQuizz.afficherEquipe(equipeM);

        Equipe equipeActuelle;

        boolean estEquipeFille = true;

        do {

            if ( estEquipeFille ) equipeActuelle = equipeF;
            else equipeActuelle = equipeM;

            fenetreQuizz.afficherEquipe( equipeF );
            fenetreQuizz.afficherEquipe( equipeM );

            fenetreQuizz.afficherInformation( "" );

            Candidat candidatActuel = equipeActuelle.selectionnerCandidat();

            fenetreQuizz.afficherCandidat( candidatActuel );

            QuestionCM question = questionnaire.fournirQuestion();

            fenetreQuizz.afficherQuestion( question );

            String choix = fenetreQuizz.cliquerChoix();

            if ( choix == question.getChoixCorrect() ){

                fenetreQuizz.afficherPouceOK();
                fenetreQuizz.afficherInformation( "Bonne réponse !" );

            } else {

                fenetreQuizz.afficherPouceKO();
                fenetreQuizz.afficherInformation( "Mauvaise réponse !" );

                equipeActuelle.remettreEnJeu( candidatActuel );

            }

            fenetreQuizz.afficherEquipe( equipeActuelle );
            fenetreQuizz.cliquerSuivant();

            estEquipeFille = !estEquipeFille;

        } while ( equipeF.getNombreCandidats() != 0 && equipeM.getNombreCandidats() != 0 );


        if ( equipeF.getNombreCandidats() == 0 ){

            fenetreQuizz.afficherEquipe( equipeF );
            fenetreQuizz.afficherInformation( "Bravo les filles !" );

        }

        if ( equipeM.getNombreCandidats() == 0 ){

            fenetreQuizz.afficherEquipe( equipeF );
            fenetreQuizz.afficherInformation( "Bravo les garçons !" );

        }

    }

    public static Questionnaire chargerQuestions(){

        QuestionCM[] questions = new QuestionCM[5];
        questions[0]= new QuestionCM("Allemagne","Amsterdam","Dublin","Berlin","Berlin");
        questions[1]= new QuestionCM("Autriche",	"Vienne","Prague", "Vilnius","Vienne");
        questions[2]= new QuestionCM("Belgique","Amsterdam", "Bruxelles","Paris","Bruxelles");
        questions[3]= new QuestionCM("Bulgarie"	,"Sofia","Budapest","Bucarest","Sofia");
        questions[4]= new QuestionCM("France",	"Nicosie","Riga","Paris","Paris");


//		Croatie		Zagreb
//		Danemark	Copenhague
//		Espagne		Madrid
//		Estonie		Tallinn
//		Finlande	Helsinki
//		France		Paris
//		Grece		Athenes
//		Hongrie		Budapest
//		Irlande		Dublin
//		Italie		Rome
//		Lettonie	Riga
//		Lituanie	Vilnius
//		Luxembourg	Luxembourg
//		Malte		La Valette
//		Pays-Bas	Amsterdam
//		Pologne		Varsovie
//		Portugal	Lisbonne
//		Republiquetcheque	Prague
//		Roumanie	Bucarest
//		Royaume-Uni	Londres
//		Slovaquie	Bratislava
//		Slovenie	Ljubljana
//		Suede		Stockholm

        return new Questionnaire(questions);

    }

}


