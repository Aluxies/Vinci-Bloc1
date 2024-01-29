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

    public void changerSerie( Serie serie ){

        if ( this.getSerie() == serie ) throw new IllegalArgumentException( "La série de l'étudiant ne peut êtree identique à la nouvelle série !" );
        if ( this.getSerie().getDelegue() == this ) throw new IllegalArgumentException( "L'étudiant ne peut être changé de série car il est délégué !" );

        this.serie = serie;

    }

    public String toString(){

        return this.getMatricule() +
                ", " + this.getNom() +
                " " + this.getPrenom() +
                " (série " + this.getSerie().getNom() + ")";

    }

}
