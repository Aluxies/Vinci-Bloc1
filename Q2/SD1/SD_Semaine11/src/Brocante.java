import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Brocante {

    private int phase = 1;

    private Emplacement[] tableEmplacements;
    private HashMap<String, Integer> mapRiverains;
    private HashMap<Character, ArrayDeque<Emplacement>> mapPilesEmplacementsLibres;

    private int nombreEmplacementsLibres;

    private HashMap<String, Exposant> map;

    //private String tableRiverains[] 
    //inutile, regardez bien les schemas, cette table n'apparait pas !

    /**
     * initialise une brocante avec nombre emplacements
     * @param 'nombreEmplacements le nombre d'emplacements
     * @param tableRiverains la table des riverains
     * @throws IllegalArgumentException si le nombre d'emplacements est negatif ou nul ou si la table des riverains est null
     */
    public Brocante(char[] tableTypeEmplacements, String[] tableRiverains) {

        // Vérification de la validité des arguments du constructeur

        if ( tableTypeEmplacements == null ) throw new IllegalArgumentException();
        if ( tableRiverains == null ) throw new IllegalArgumentException();

        // On initialise les attributs de la classe Brocante

        int nombreEmplacements = tableTypeEmplacements.length;

        nombreEmplacementsLibres = nombreEmplacements;
        tableEmplacements = new Emplacement[nombreEmplacements];
        mapRiverains = new HashMap<String, Integer>();
        map = new HashMap<String, Exposant>();

        // On remplit la table des emplacements avec des emplacements

        for ( int i=0; i<nombreEmplacements; i++ ) {

            Emplacement emplacement = new Emplacement( i, tableTypeEmplacements[i] );
            tableEmplacements[i] = emplacement;

        }

        // On remplit la map des riverains en mettant
        // leurs noms et leurs nombres d'emplacements

        for ( int i=0; i<tableRiverains.length; i++ ){

            mapRiverains.put( tableRiverains[i], 0 );

        }

    }

    public int getPhase() {
        return phase;
    }

    public Exposant getExposant( String nom ){

        // Methode qui donne l'exposant en fonction de son nom

        return map.get( nom );

    }

    public Iterator<Exposant> tousLesExposants(){

        // Iterateur pour parcourir la map des exposants

        return map.values().iterator();

    }

    /**
     * reserve l'emplacement qui porte le numero passe en parametre au demandeur passe en parametre
     * La reservation reussit si
     *     l'emplacement est libre
     *     le demandeur est bien un riverain
     *     le riverain n'a pas encore 3 emplacements
     * @param demandeur le riverain qui demande un emplacement
     * @param numeroEmplacement le numero de l'emplacement souhaite
     * @return true si la reservation a reussi, false sinon
     * @throws IllegalArgumentException si le numero de l'emplacement n'existe pas
     * @throws IllegalStateException si on n'est pas en phase 1
     */
    public boolean reserver(Exposant demandeur, int numeroEmplacement) {

        // Si on n'est pas dans la phase 1, on renvoie une exception

        if ( phase != 1 ) throw new IllegalStateException();

        // Vérification de la validité des arguments

        if ( demandeur == null ) throw new IllegalArgumentException();
        if ( numeroEmplacement < 0 || numeroEmplacement >= tableEmplacements.length ) throw new IllegalArgumentException();

        // Si le demandeur n'est pas un riverain, on renvoie false

        if ( !estUnRiverain( demandeur.getNom() ) ) return false;

        // On prend le nombre d'emplacements de l'exposant

        int nombreEmplacements = nombreEmplacementsRiverain( demandeur.getNom() );

        // Si l'exposant a déjà 3 emplacements, on renvoie false

        if ( nombreEmplacements == 3 ) return false;

        // Si l'emplacement n'est pas libre on renvoie false

        if ( !estLibre( numeroEmplacement ) ) return false;

        // On augmente le nombre d'emplacements de l'exposant

        nombreEmplacements++;

        // On diminue le nombre d'emplacements libres de la brocante

        nombreEmplacementsLibres--;

        // On prend l'emplacement dans la table d'emplacements en fonction
        // du numéro de l'emplacement

        Emplacement emplacement = tableEmplacements[numeroEmplacement];

        // On met le demandeur comme exposant de l'emplacement

        emplacement.setExposant( demandeur );

        // On ajoute l'emplacement à la liste d'emplacements de l'exposant

        demandeur.ajouterEmplacement( emplacement );

        // Si le riverain n'est pas encore un exposant,
        // on l'ajoute en tant qu'exposant dans la map

        if ( mapRiverains.get( demandeur.getNom() ) == 0 ) map.put( demandeur.getNom(), demandeur );

        // On actualise le nombre d'emplacements de l'exposant

        mapRiverains.put( demandeur.getNom(), nombreEmplacements );

        return true;

    }

    public boolean desister( Exposant demandeur, int numeroEmplacement ){

        // Vérification de la validité des arguments

        if ( demandeur == null ) throw new IllegalArgumentException();
        if ( numeroEmplacement < 0 || numeroEmplacement >= tableEmplacements.length ) throw new IllegalArgumentException();

        // Tous les emplacements sont libres

        if ( nombreEmplacementsLibres == tableEmplacements.length ) return false;

        // L'emplacement en question est libre

        if ( estLibre( numeroEmplacement ) ) return false;

        // On prend l'emplacement dans la table d'emplacements en fonction du numéro d'emplacement

        Emplacement emplacement = tableEmplacements[numeroEmplacement];

        // On vérifie via la méthode retirerEmplacement si l'emplacement appartient au demandeur
        // si elle lui appartient, on retire l'emplacement

        boolean resultatSuppression = demandeur.retirerEmplacement( emplacement );

        // L'emplacement n'appartient pas à l'exposant

        if ( !resultatSuppression ) return false;

        // Si on est dans la phase 2, on ajoute l'emplacement à la pile d'emplacements libres

        if ( phase == 2 ) mapPilesEmplacementsLibres.get( emplacement.getType() ).addLast( emplacement );

        // On prend le nombre le nombre d'emplacements du demandeur

        int nombreEmplacements = nombreEmplacementsRiverain( demandeur.getNom() );

        // On diminue son nombre d'emplacements

        nombreEmplacements--;

        // On augmente le nombre d'emplacements libres

        nombreEmplacementsLibres++;

        // On met le nombre d'emplacements dans la map pour l'actualiser

        mapRiverains.put( demandeur.getNom(), nombreEmplacements );

        // On réinitialise l'exposant de l'emplacement

        emplacement.setExposant( null );

        return true;

    }

    public boolean estLibre( int numeroEmplacement ){

        // On prend l'emplacement dans la table d'emplacements en fonction de son numéro d'emplacement

        Emplacement emplacement = tableEmplacements[numeroEmplacement];

        // S'il y a déjà un exposant, on renvoie false

        if ( emplacement.getExposant() != null ) return false;

        return true;

    }

    public boolean typeEmplacementVide( char type ){

        // On regarde si il reste encore des emplacements libres de ce type

        if ( mapPilesEmplacementsLibres.get( type ) == null ) return true;

        return mapPilesEmplacementsLibres.get( type ).size() == 0;

    }

    public boolean emplacementLibre( char type ){

        // Le type n'existe pas

        if ( mapPilesEmplacementsLibres.get( type ) == null ) return false;

        if ( typeEmplacementVide( type ) ) return false;

        return true;

    }

    public boolean estUnRiverain( String nom ){

        // Si le nom n'est pas présent en tant que clé dans la map de riverains,
        // on renvoie false

        if ( !mapRiverains.containsKey( nom ) ) return false;

        return true;

    }

    public int nombreEmplacementsRiverain( String nom ){

        // On renvoie le nombre d'emplacements du riverain
        // en utilisant le nom de riverain comme clé de la map de riverains

        return mapRiverains.get( nom );

    }

    public boolean emplacementLibre(){

        // Si le nombre d'emplacements libres
        // n'est pas égal à 0, on renvoie
        // true pour dire qu'il reste encore
        // des emplacements libres

        return nombreEmplacementsLibres != 0;

    }

    /**
     * attribue automatiquement un emplacement libre au demandeur passe en parametre
     * @param demandeur le demandeur d'un emplacement
     * @return le numero de l'emplacement attribue ou -1 si plus d'emplacement libre
     * @throws IllegalStateException si on n'est pas en phase 2
     */
    public int attribuerAutomatiquementEmplacement(char type, Exposant demandeur) {

        // Si on n'est pas à la phase 2, on envoie une exception

        if ( phase != 2 ) throw new IllegalStateException();

        // S'il n'y a plus aucun emplacement libre, on renvoie -1 comme numéro d'emplacement

        if ( nombreEmplacementsLibres == 0 ) return -1;

        // On vérifie s'il y a encore des emplacements libres du type désiré

        if ( !emplacementLibre( type ) ) return -1;

        // On prend l'emplacement qui se trouve en haut de la pile (LIFO)

        Emplacement emplacement = mapPilesEmplacementsLibres.get( type ).pop();

        // On met le demandeur en tant qu'exposant de l'emplacement

        emplacement.setExposant( demandeur );

        // On diminue le nombre d'emplacements libres

        nombreEmplacementsLibres--;

        // Si le riverain n'est pas encore un exposant,
        // on l'ajoute en tant qu'exposant dans la map

        if ( mapRiverains.get( demandeur.getNom() ) == 0 ) map.put( demandeur.getNom(), demandeur );

        // On renvoie le numéro de l'emplacement réservé

        return emplacement.getNumero();

    }

    /**
     * a comme effet de passer de la phase 1 a la phase 2
     * si deja en phase 2, rien ne doit etre fait
     */
    public void changerPhase() {

        // Si on est à la phase 1

        if ( phase == 1 ) {

            // On passe à la phase 2

            phase = 2;

            // On initialise la pile d'emplacements libres

            mapPilesEmplacementsLibres = new HashMap<Character, ArrayDeque<Emplacement>>();

            // On rempli la pile d'emplacements libre
            // avec tous les emplacements libre
            // de la phase 1

            // On parcourt toute la table des emplacements

            for (int i = 0; i < tableEmplacements.length; i++) {

                // Si l'emplacement est libre, on ajoute l'emplacement
                // à la pile d'emplacements libres

                if ( estLibre( i ) ){

                    if ( mapPilesEmplacementsLibres.get( tableEmplacements[i].getType() ) == null ){

                        mapPilesEmplacementsLibres.put( tableEmplacements[i].getType(), new ArrayDeque<Emplacement>() );

                    }

                    mapPilesEmplacementsLibres.get( tableEmplacements[i].getType() ).addFirst( tableEmplacements[i] );

                }

            }

        }

    }

    @Override
    public String toString() {
        String aRenvoyer="";
        for (int i = 0; i < tableEmplacements.length; i++) {
            if(tableEmplacements[i].getExposant()==null){
                aRenvoyer +=  ("\n"+i+" : /");
            }else{
                aRenvoyer +=  ("\n"+i+" : "+tableEmplacements[i].getExposant());
            }
        }
        return aRenvoyer;
    }

    public String donnerMapExposants(){

        if ( map == null ) return null;

        return map.toString();

    }

    //Pour le debug
    public String donnerTableEmplacements() {
        if(tableEmplacements==null)
            return "null";
        return Arrays.toString(tableEmplacements);
    }

    //Pour le debug
    public String donnerMapRiverains() {
        if(mapRiverains==null)
            return "null";
        return mapRiverains.toString();
    }

    //Pour le debug
    public String donnerPileEmplacementsLibres() {
        if(mapPilesEmplacementsLibres==null)
            return "null";
        return mapPilesEmplacementsLibres.toString();
    }
  
}
