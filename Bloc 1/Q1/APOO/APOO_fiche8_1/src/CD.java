public class CD extends Produit {

    private String titre, auteur;

    private int nombreDeMorceaux;

    public CD(String reference, double prix, String titre, String auteur, int nombreDeMorceaux) {

        super(reference, prix);
        this.titre = titre;
        this.auteur = auteur;
        this.nombreDeMorceaux = nombreDeMorceaux;

    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getNombreDeMorceaux() {
        return nombreDeMorceaux;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nType : CD" +
                "\nTitre : " + titre +
                "\nAuteur : " + auteur +
                "\nNombreDeMorceaux : " + nombreDeMorceaux;
    }
}