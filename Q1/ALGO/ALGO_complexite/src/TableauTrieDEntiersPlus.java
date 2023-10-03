import java.util.Arrays;

public class TableauTrieDEntiersPlus {

    private int [] tableDEntiers;  // table d'entiers triee par ordre croissant
    private int nombreDEntiers;    // nombre d'entiers dans tableDEntiers
    private static final int TAILLE = 10;

    public TableauTrieDEntiersPlus(){
        this.tableDEntiers = new int[TAILLE];
        this.nombreDEntiers = 0;
    }


    public int getNombreDEntiers(){
        return this.nombreDEntiers;
    }


    /**
     * methode qui recherche l'indice correspondant a une occurrence
     * de l'entier passe en parametre
     * si l�entier se trouve plusieurs fois, l�indice correspond a une
     * occurrence quelconque (pas necessairement la premiere)
     * @param entier l'entier recherche
     * @return indice correspondant a entier, -1 s'il n'est pas dans la table
     */
    private int trouverIndiceDicho(int entier){

        int indiceMilieu = nombreDEntiers/2;

        int indiceMin = 0;
        int indiceMax = nombreDEntiers-1;

        int indiceDicho = -1;

        for ( int i = indiceMin; i <= indiceMax; i++ ){

            if ( entier == tableDEntiers[i] ){

                indiceDicho = i;
                break;

            }

            if ( entier < tableDEntiers[indiceMilieu] ){

                indiceMax = indiceMilieu-1;

            } else {

                indiceMin = indiceMilieu+1;

            }

        }

        return indiceDicho;

    }



    /**
     * methode qui verifie si la table contient l'entier passe en parametre
     * @param entier l'entier recherche
     * @return boolean true si l'entier est present dans la table, false sinon
     */
    public boolean contient(int entier){
        return trouverIndiceDicho(entier)!=-1;
    }


    /**
     * methode qui supprime toutes les occurrences d'un entier
     * @param entier l'entier a supprimer
     * @return int le nombre de suppressions effectuees
     */
    public int supprimerToutesLesOccurrences(int entier){

        int indicePremiereOccurence = 0;
        int nombreOccurences = 0;

        for ( int i=0; i<nombreDEntiers; i++ ){

            if ( tableDEntiers[i] < entier ) indicePremiereOccurence++;
            else break;

        }

        for ( int i=indicePremiereOccurence; i<nombreDEntiers; i++ ){

            if ( entier == tableDEntiers[i] ) nombreOccurences++;
            else break;

        }

        for ( int i=indicePremiereOccurence+nombreOccurences; i < nombreDEntiers; i++ ){

            tableDEntiers[i-nombreOccurences] = tableDEntiers[i];

        }

        nombreDEntiers -= nombreOccurences;

        return nombreOccurences;

    }


    /**
     * supprime tous les entiers compris entre borneInf et borneSup (bornes comprises)
     * precondition (a ne pas verifier) : borneInf <= borneSup
     * @param borneInf l'entier le plus petit a supprimer
     * @param borneSup l'entier le plus grand a supprimer
     * @return le nombre de suppressions effectuees
     */
    public int supprimerEntre(int borneInf, int borneSup) {

        int indiceInf = 0;

        for ( int i=0; i < nombreDEntiers; i++ ){

            if ( tableDEntiers[i] < borneInf ) indiceInf++;
            else break;

        }

        int indiceSup = 0;

        for ( int i=0; i < nombreDEntiers; i++ ){

            if ( tableDEntiers[i] < borneSup ) indiceSup++;
            else break;

        }

        int nombreSuppressions = indiceSup-indiceInf+1;

        for ( int i=indiceInf; i <= indiceSup; i++ ){

            System.out.println( Arrays.toString( tableDEntiers ));

            tableDEntiers[i] = tableDEntiers[i+nombreSuppressions];

            System.out.println( i );

        }

        nombreDEntiers -= nombreSuppressions;

        System.out.println( "IndiceInf : " + indiceInf );
        System.out.println( "IndiceSup : " + indiceSup );

        return nombreSuppressions;

        // Si votre methode appelle la methode supprimerUneOccurrence()
        // ou si votre methode contient une instruction du style :
        // tableDEntiers[i-1] = tableDEntiers[i];
        // ou
        // tableDEntiers[i] = tableDEntiers[i+1];

        // Votre methode est en O(Ncarre) !

        // Piste :
        // algorithme assez similaire a l'algorithme supprimerToutesLesOccurrences()


    }



    /**
     * methode qui supprime tous les ex-aequos
     * (en cas d'ex-aequo, un seul entier est conserve)
     * @return int le nombre de suppressions effectuees
     */
    public int supprimerTousLesExAequos(){

        // TODO

        return 0;

        // cout O(N)

        // Si votre methode appelle la methode supprimerUneOccurrence()
        // ou si votre methode contient une instruction du style :
        // tableDEntiers[i-1] = tableDEntiers[i];
        // (ou tableDEntiers[i] = tableDEntiers[i+1];)
        // Votre methode est en O(Ncarre) !

        // Piste :
        // Votre methode devrait contenir une instruction du style :
        // tableDEntiers[i-nombreSuppressions] = tableDEntiers[i];
        // nombreSuppressions correspond au nombre d'ex-aequos rencontres avant de "traiter" tableDEntiers[i]
        // Ex :
        // 1 1 1 3 3 3 3 4 7 9 9 11
        // la table a obtenir
        // 1 3 4 7 9 11
        // Au moment de traiter le premier entier 3, on a deja rencontre 2 entiers a supprimer (2 x 1)  --> l'entier 3 devra etre ramene de 2 cases avant
        // Au moment de traiter l'entier 4, on a deja rencontre 5 entiers a supprimer ( 2 x 1 et 3 x 3) --> l'entier 4 devra etre ramene de 5 cases avant
        // ...

    }

    public String toString(){
        if(nombreDEntiers==0)
            return "[]";
        String aRenvoyer = "["+tableDEntiers[0];
        for (int i = 1; i < nombreDEntiers; i++)
            aRenvoyer = aRenvoyer + ", " + this.tableDEntiers[i];
        return aRenvoyer+"]";
    }

    // A NE PAS MODIFIER !
    // VA SERVIR POUR LES TESTS
    public TableauTrieDEntiersPlus(int[] tableARecopier){
        if (tableARecopier.length>TAILLE)
            throw new IllegalArgumentException();
        this.nombreDEntiers = tableARecopier.length;
        tableDEntiers= new int[TAILLE];
        if(tableARecopier.length!=0)
            tableDEntiers[0] = tableARecopier[0];
        for (int i = 1; i<nombreDEntiers; i++){
            if(tableARecopier[i]<tableARecopier[i-1]){
                throw new IllegalArgumentException();
            }
            tableDEntiers[i] = tableARecopier[i];
        }
    }

}
