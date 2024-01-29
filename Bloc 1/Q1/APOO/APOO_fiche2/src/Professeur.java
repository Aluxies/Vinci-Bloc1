public class Professeur {

    private String matricule, nom, prenom, specialisation;

    public Professeur( String matricule, String nom, String prenom, String specialisation ){

        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.specialisation = specialisation;

    };

    public String getNom(){

        return this.nom;

    };

    public String getPrenom(){

        return this.prenom;

    };

    public String getMatricule(){

        return this.matricule;

    };

    public String getSpecialisation(){

        return this.specialisation;

    };

    public String toString(){

        return this.matricule + " " +
                this.prenom + " " +
                this.nom + "\nSpécialisation : " +
                this.specialisation;

    };

};