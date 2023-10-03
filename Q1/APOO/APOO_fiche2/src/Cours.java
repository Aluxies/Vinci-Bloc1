public class Cours {

    private String intitule, niveau;

    private int nombreElevesInscrits = 0;
    private Professeur professeur;

    public Cours( String intitule, String niveau, Professeur professeur ){

        this.intitule = intitule;
        this.niveau = niveau;
        this.professeur = professeur;

    };

    public String getIntitule(){

        return this.intitule;

    };

    public String getNiveau(){

        return this.niveau;

    };

    public int getNombreElevesInscrits(){

        return this.nombreElevesInscrits;

    };

    public Professeur getProfesseur(){

        return this.professeur;

    };

    public void changerProfesseur( Professeur professeur ){

        this.professeur = professeur;

    };

    public void inscriptionEleve(){

        this.nombreElevesInscrits += 1;

    };

    public void desinscriptionEleve(){

        this.nombreElevesInscrits -= 1;

    };

    public String toString(){

        return this.intitule + "\nCours de niveau " + this.niveau
                + " donné par " + this.professeur.getPrenom() + " " + this.professeur.getNom()
                + "\nNombre d'élèves inscrits : " + this.nombreElevesInscrits;

    };



};