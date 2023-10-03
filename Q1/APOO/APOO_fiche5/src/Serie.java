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

    public void elireDelegue( Etudiant delegue ){

        if ( this == delegue.getSerie() ) throw new IllegalArgumentException( "L'étudiant est déjà délégué de cette série !" );
        if ( this.delegue != null ) throw new IllegalArgumentException( "L'étudiant ne peut être 'null' !" );

        this.delegue = delegue;

    }

    public String toString(){

        String string = "Série " + this.nom;

        if ( this.delegue != null ){

            string += " (délégué : " + this.delegue.getNom() + ")";

        }

        return string;

    }

}