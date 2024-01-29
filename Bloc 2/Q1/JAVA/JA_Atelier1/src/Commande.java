import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande> {

    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesDeCommande;

    public Commande(Client client) {

        if ( client == null ) throw new IllegalArgumentException( "L'objet 'client' ne peut être 'null'." );

        if ( client.getCommandeEnCours() != null ) throw new IllegalArgumentException( "impossible de créer une commande pour un client ayant encore une commande en cours" );

        this.numero = numeroSuivant;
        this.client = client;
        this.date = LocalDateTime.now();
        this.lignesDeCommande = new ArrayList<LigneDeCommande>();

        numeroSuivant++;

        client.enregistrer( this );

    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter ( Pizza pizza, int quantite ) {

        if ( pizza == null ) throw new IllegalArgumentException( "L'objet 'pizza' ne peut être 'null'." );
        if ( quantite <= 0 ) throw new IllegalArgumentException( "La quantite ne peut être plus petite ou égale à 0." );

        if ( this.client.getCommandeEnCours() == null ) return false;

        LigneDeCommande ligneDeCommande = new LigneDeCommande( pizza, quantite );

        for ( LigneDeCommande ligne : lignesDeCommande ) {

            if ( ligne.getPizza().equals( pizza ) ) {

                ligne.setQuantite( ligne.getQuantite() + quantite );
                return true;

            }

        }

        lignesDeCommande.add( ligneDeCommande );

        return true;

    }

    public boolean ajouter ( Pizza pizza ) {
        return ajouter(pizza,1);
    }

    public boolean retirer ( Pizza pizza, int quantite ) {

        if ( pizza == null ) throw new IllegalArgumentException( "L'objet 'pizza' ne peut être 'null'." );
        if ( quantite <= 0 ) throw new IllegalArgumentException( "La quantite ne peut être plus petite ou égale à 0." );

        if ( client.getCommandeEnCours() != this ) return false;

        for ( LigneDeCommande ligneDeCommande : lignesDeCommande ) {

            if ( ligneDeCommande.getPizza().equals( pizza ) ) {

                if ( quantite > ligneDeCommande.getQuantite() ) return false;

                if ( quantite == ligneDeCommande.getQuantite() ) {

                    lignesDeCommande.remove( ligneDeCommande );

                } else {

                    ligneDeCommande.setQuantite( ligneDeCommande.getQuantite() - quantite );

                }

                return true;

            }

        }

        return false;

    }

    public boolean retirer ( Pizza pizza ) {

        return retirer( pizza, 1 );

    }

    public boolean supprimer ( Pizza pizza ) {

        if ( pizza == null ) throw new IllegalArgumentException( "L'objet 'pizza' ne peut être 'null'." );

        if ( client.getCommandeEnCours() != this ) return false;

        for ( LigneDeCommande ligneDeCommande : lignesDeCommande ) {

            if ( ligneDeCommande.getPizza().equals( pizza ) ) {

                lignesDeCommande.remove( ligneDeCommande );

                return true;

            }

        }

        return false;

    }

    public double caculerMontantTotal(){
        double resultat = 0;
        for(LigneDeCommande a : lignesDeCommande){
            resultat += a.calculerPrixTotal();
        }
        return resultat;
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }

    public String detailler() {

        String string = "";

        for ( LigneDeCommande ligneDeCommande : lignesDeCommande ) {

            string += ligneDeCommande.getQuantite() + " " + ligneDeCommande.getPizza().getTitre() + " " + ligneDeCommande.getPizza().calculerPrix();

        }

        return string;

    }

    public Iterator<LigneDeCommande> iterator() {

        return this.iterator();

    }

}