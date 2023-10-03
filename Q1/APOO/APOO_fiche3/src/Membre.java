
public class Membre {

    private String nom, prenom, numeroTelephone;

    private Membre parrain;
		

	public Membre( String nom, String prenom, String numeroTelephone ){

        this.nom = nom;
        this.prenom = prenom;
        this.numeroTelephone = numeroTelephone;
        this.parrain = null;

    }

    public String getNom(){

        return this.nom;

    }

    public String getPrenom(){

        return this.prenom;

    }

    public String getNumeroTelephone(){

        return this.numeroTelephone;

    }

    public Membre getParrain(){

        return this.parrain;

    }

    public void setNumeroTelephone( String numeroTelephone ){

        this.numeroTelephone = numeroTelephone;

    }

    public boolean initialiserParain( Membre parrain ){

        if ( this.parrain != null || this == parrain ) return false;

        this.parrain = parrain;
        return true;

    }

    public String toString(){

        return "Prenom : " + this.prenom +
                "\nNom : " + this.nom +
                "\nTelephone : " + this.numeroTelephone +
                "\nParain : " + this.parrain;

    }

}