public class ClientEnAttente extends Client {
    private int numeroArrivee;

    private static int numero_suivant = 1;

    public ClientEnAttente( String nom, int priorite ){

        super( nom, priorite );
        this.numeroArrivee = numero_suivant;
        numero_suivant++;

    }

    public int getNumeroArrivee(){

        return this.numeroArrivee;

    }

}