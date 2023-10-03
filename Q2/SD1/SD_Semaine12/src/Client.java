import java.util.Objects;

public class Client {

    private String nom;
    private int priorite;

    private final static int PRIORITE_PAR_DEFAUT = 3;

    public Client( String nom, int priorite ){

        this.nom = nom;
        setPriorite( priorite );

    }

    public Client( String nom ){

        this( nom, PRIORITE_PAR_DEFAUT );

    }

    public void setPriorite( int priorite ){

        this.priorite = priorite;

    }

    public int getPriorite(){

        return this.priorite;

    }

    public String getNom(){

        return this.nom;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(nom, client.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

}