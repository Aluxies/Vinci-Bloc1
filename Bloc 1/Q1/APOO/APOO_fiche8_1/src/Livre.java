public class Livre extends Produit {

    private String titre, auteur;

    private int nombreDePages;

    public Livre(String reference, double prix, String titre, String auteur, int nombreDePages) {

        super(reference, prix);

        this.titre = titre;
        this.auteur = auteur;
        this.nombreDePages = nombreDePages;

    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getNombreDePages() {
        return nombreDePages;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nType : livre" +
                "\nTitre : " + titre +
                "\nAuteur : " + auteur +
                "\nNombreDePages : " + nombreDePages;
    }
}