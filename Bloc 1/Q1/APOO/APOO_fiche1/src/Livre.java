class Livre {

    private String isbn, titre, nomAuteur, prenomAuteur;

    private double prixConseille;

    private int anneeEdition, nombreDePages;

    public Livre( String newIsbn, String newTitre, String newNomAuteur, String newPrenomAuteur, double newPrixConseille, int newAnneeEdition, int newNombreDePages ){

        isbn = newIsbn;

        titre = newTitre;
        nomAuteur = newNomAuteur;
        prenomAuteur = newPrenomAuteur;

        prixConseille = newPrixConseille;

        anneeEdition = newAnneeEdition;
        nombreDePages = newNombreDePages;

    };

    public String getIsbn(){

        return this.isbn;

    }

    public String getTitre(){

        return this.titre;

    }

    public String getNomAuteur(){

        return this.nomAuteur;

    }

    public String getPrenomAuteur(){

        return this.prenomAuteur;
        
    }

};