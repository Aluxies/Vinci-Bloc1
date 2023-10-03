public class Etudiant {

    private String matricule, nom, prenom;

    private Serie serie;

    public Etudiant( String matricule, String nom, String prenom, Serie serie ){

        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.serie = serie;

    }

    public String getMatricule(){

        return this.matricule;

    }

    public String getNom(){

        return this.nom;

    }

    public String getPrenom(){

        return this.prenom;

    }

    public Serie getSerie(){

        return this.serie;

    }

    public Boolean changerSerie( Serie serie ){

        if ( this.getSerie() == serie ) return false;
        if ( this.getSerie().getDelegue() == this ) return false;

        this.serie = serie;

        return true;

    }

    public String toString(){

        return this.getMatricule() +
                ", " + this.getNom() +
                " " + this.getPrenom() +
                " (série " + this.getSerie().getNom() + ")";

    }

}
