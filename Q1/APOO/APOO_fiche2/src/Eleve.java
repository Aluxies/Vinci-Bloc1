public class Eleve {

    private String nom, prenom;
    private Cours coursPrincipal, coursComplementaire;

    public Eleve( String nom, String prenom, Cours coursPrincipal, Cours coursComplementaire ){

        this.nom = nom;
        this.prenom = prenom;
        this.coursPrincipal = coursPrincipal;
        this.coursComplementaire = coursComplementaire;

    };

    public String getNom(){

        return this.nom;

    };

    public String getPrenom(){

        return this.prenom;

    };

    public Cours getCoursPrincipal(){

        return this.coursPrincipal;

    };

    public Cours getCoursComplementaire(){

        return this.coursComplementaire;

    };

    public void setCoursComplementaire( Cours coursComplementaire ){

        this.coursComplementaire = coursComplementaire;

    };

    public String toString(){

        return this.prenom + " " + this.nom +
                "\nCours principal : " + this.coursPrincipal.getIntitule() +
                "\nCours complémentaire : " + this.coursComplementaire.getIntitule();

    };




};