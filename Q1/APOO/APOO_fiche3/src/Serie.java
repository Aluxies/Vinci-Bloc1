public class Serie {

    private char nom;
    private Etudiant delegue;

    public Serie( char nom ){

        this.nom = nom;
        this.delegue = null;

    }

    public char getNom(){

        return this.nom;

    }

    public Etudiant getDelegue(){

        return this.delegue;

    }

    public Boolean elireDelegue( Etudiant delegue ){

        if ( this == delegue.getSerie() ) return false;
        if ( this.delegue != null ) return false;

        this.delegue = delegue;

        return true;
    }

    public String toString(){

        String string = "Série " + this.nom;

        if ( this.delegue != null ){

            string += " (délégué : " + this.delegue.getNom() + ")";

        }

        return string;

    }

}