import java.util.*;

public class SessionDeVente {

    private PriorityQueue<Client> fileDePriorite;
    private HashSet<Client> ensembleClientsActuellementDansFile;
    private HashMap<Client, Commande> mapClientCommande;
    private ArrayList<Commande> listeCommandes;
    private int nombreCasiersRestants;
    private final static int MAX_CASIERS_CLIENT = 3;


    /**
     * debute une session de vente
     * @param nombreCasiersMisEnVente le nombre de casiers mis en vente
     * @throws IllegalArgumentException s'il n'y a pas au moins un casier a vendre
     */
    public SessionDeVente(int nombreCasiersMisEnVente) {
        if(nombreCasiersMisEnVente<=0)
            throw new IllegalArgumentException();
        this.nombreCasiersRestants = nombreCasiersMisEnVente;
        fileDePriorite = new PriorityQueue<Client>();
        ensembleClientsActuellementDansFile = new HashSet<Client>();
        mapClientCommande = new HashMap<Client, Commande>();
        listeCommandes = new ArrayList<Commande>();
    }


    public int getNombreCasiersRestants() {
        return nombreCasiersRestants;
    }

    /**
     * ajoute, si possible, le client dans la file d'attente
     * le client ne peut pas deja y etre
     * si client a deja une commande lors de cette session de vente, le max de casiers autorise n'est pas deja atteint
     * s'il reste encore des casiers a vendre
     * @param client le client a ajouter
     * @return true si l'ajout a pu se faire, false sinon
     * @throws IllegalArgumentException si le client est null ou vide
     */
    public boolean placerDansFilePriorite(Client client){

        if ( client == null ) throw new IllegalArgumentException();
        if ( ensembleClientsActuellementDansFile.contains( client ) ) return false;
        if ( nombreCasiersRestants == 0 ) return false;

        if ( mapClientCommande.containsKey( client ) ){

            Commande commandeClient = mapClientCommande.get( client );

            int nombreCasiersDemandes = commandeClient.getNombreCasiersDemandes();

            if ( nombreCasiersDemandes == MAX_CASIERS_CLIENT ) return false;

        }

        fileDePriorite.add( client );
        ensembleClientsActuellementDansFile.add( client );

        return true;

    }

    /**
     * retire de la file d'attente le client de tete
     * @return le client de tete ou null si la file est vide
     */
    public Client selectionnerClientSuivant(){

        if ( fileDePriorite.isEmpty() ) return null;

        Client client = fileDePriorite.remove();

        ensembleClientsActuellementDansFile.remove( client );

        return client;

    }

    /**
     * ajoute, si possible, une nouvelle commande
     * le nombre de casiers restants doit etre suffisant pour satisfaire completement la commande
     * (il n'y a pas de commande partielle)
     * le nombre de casiers demandes ne peut depasser le max autorise
     * @param client le client qui fait la demande
     * @param nombreCasiersDemandes le nombre de casiers demandes
     * @return true si la commande a pu etre faite, false sinon
     * @throws IllegalArgumentException si le client est null ou vide
     *  	ou si le nombre de casiers demandés est <=0
     * @throws IllegalStateException si le client a deja fait une commande
     */
    public boolean passerNouvelleCommande( Client client, int nombreCasiersDemandes){

        // Si le paramètre client est null,
        // on renvoie une IllegalArgumentException

        if ( client == null ) throw new IllegalArgumentException();

        // Si le paramètre nombreCasiersDemandes est plus petit ou égal à 0,
        // on renvoie une IllegalArgumentException

        if ( nombreCasiersDemandes <= 0 ) throw new IllegalArgumentException();

        // S'il y a déjà une commande pour le client,
        // on renvoie une IllegalStateException

        if ( mapClientCommande.containsKey( client ) ) throw new IllegalStateException();

        // Si le nombre de casiers demandés est supérieur au nombre de casiers maximum par client
        // on renvoie false

        if ( nombreCasiersDemandes > MAX_CASIERS_CLIENT ) return false;

        if ( nombreCasiersDemandes > nombreCasiersRestants ) return false;

        Commande commande = new Commande( client, nombreCasiersDemandes );

        client.setPriorite( client.getPriorite() - 1 );

        listeCommandes.add( commande );
        mapClientCommande.put( client, commande );

        nombreCasiersRestants -= nombreCasiersDemandes;

        return true;

    }


    /**
     * modifie, si possible, une commande existante
     * le nombre de casiers restants doit etre suffisant
     * (il n'y a pas de commande partielle)
     * le nombre total de casiers apres ajout de ce nombre de casiers supplementaires ne peut depasser le max autorise
     * @param client le client qui veut modifier sa commande
     * @param nombreCasiersDemandesEnPlus le nombre de casiers a ajouter au nombre de casiers deja commande
     * @return true si la commande a pu etre modifiee, false sinon
     * @throws IllegalArgumentException si le client est null ou vide
     *  	ou si le nombre de casiers demandes est <= 0
     * @throws IllegalStateException si le client n'a pas encore fait de commande lors de cette session de commande
     */
    public boolean modifierCommande(Client client,int nombreCasiersDemandesEnPlus){

        // Si le paramètre client est null,
        // on renvoie une IllegalArgumentException

        if ( client == null ) throw new IllegalArgumentException();

        // Si le paramètre nombreCasiersDemandes est plus petit ou égal à 0,
        // on renvoie une IllegalArgumentException

        if ( nombreCasiersDemandesEnPlus <= 0 ) throw new IllegalArgumentException();

        // S'il n'y a pas déjà une commande pour le client,
        // on renvoie une IllegalStateException

        if ( !mapClientCommande.containsKey( client ) ) throw new IllegalStateException();

        if ( nombreCasiersDemandesEnPlus > nombreCasiersRestants ) return false;

        Commande commandeClient = mapClientCommande.get( client );

        int nombreCasiersDemandes = commandeClient.getNombreCasiersDemandes();
        int nombreCasiersTotal = nombreCasiersDemandes + nombreCasiersDemandesEnPlus;

        if ( nombreCasiersTotal > MAX_CASIERS_CLIENT ) return false;

        client.setPriorite( client.getPriorite() - 1 );

        commandeClient.setNombreCasiersDemandes( nombreCasiersDemandes );

        nombreCasiersRestants -= nombreCasiersDemandes;

        return true;

    }

    public void cloturerSession(){

        for ( Client client : fileDePriorite ) {

            client.setPriorite( client.getPriorite() + 1 );

        }

    }


    public String toString(){
        // cette methode ne sera pas evaluee
        // elle peut-etre interessante a appeler en cas de bug
        // n'hesitez pas a la completer
        return "le nombre de casiers restants : "+ nombreCasiersRestants
                + "\nla file d'attente : "+ fileDePriorite +  "\nles commandes " + listeCommandes;
    }

}