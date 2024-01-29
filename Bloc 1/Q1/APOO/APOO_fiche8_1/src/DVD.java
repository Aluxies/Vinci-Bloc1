public class DVD extends Produit {

    private String titre, realisateur;

    public DVD(String reference, double prix, String titre, String realisateur) {

        super(reference, prix);
        this.titre = titre;
        this.realisateur = realisateur;

    }

    public String getTitre() {
        return titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nType : DVD" +
                "\nTitre : " + titre +
                "\nRealisateur : " + realisateur;
    }


}