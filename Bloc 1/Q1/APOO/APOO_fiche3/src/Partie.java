public class Partie {

    private Date dateDebut;

    private Joueur joueurBlanc;
    private Joueur joueurNoir;

    private Tournoi tournoi;

    private double pointNoir;
    private double pointBlanc;

    public Partie(Date dateDebut, Joueur joueurBlanc, Joueur joueurNoir, Tournoi tournoi) {
        this.dateDebut = dateDebut;
        this.joueurBlanc = joueurBlanc;
        this.joueurNoir = joueurNoir;
        this.tournoi = tournoi;
        this.pointNoir = 0;
        this.pointBlanc = 0;
    }

    public Date getDateDebut() {
        return this.dateDebut;
    }

    public Joueur getJoueurBlanc() {
        return this.joueurBlanc;
    }

    public Joueur getJoueurNoir() {
        return this.joueurNoir;
    }

    public Tournoi getTournoi() {
        return this.tournoi;
    }

    public double getPointNoir(){
        return this.pointNoir;
    }

    public double getPointBlanc(){
        return this.pointBlanc;
    }

    public Joueur getGagnant() {

        if ( pointNoir == pointBlanc ){

            return null;

        } else if ( pointNoir > pointBlanc ){

            return this.joueurNoir;

        } else {

            return this.joueurBlanc;

        }

    }

    public boolean terminerPartie( double pointsBlanc ) {
        if ( pointsBlanc < 0 || pointsBlanc > 100 ) return false;

        this.pointBlanc = pointsBlanc;
        this.pointNoir = 100 - pointsBlanc;

        this.joueurBlanc.incrementerNombreParties();
        this.joueurNoir.incrementerNombreParties();

        return true;

    }

    @Override
    public String toString() {

        String string = "DateDebut : " + dateDebut +
                "\nJoueurBlanc : " + joueurBlanc.getNom() + " " + joueurBlanc.getPrenom() +
                "\nJoueurNoir : " + joueurNoir.getNom() + " " + joueurNoir.getPrenom() +
                "\nTournoi : " + tournoi.getNom() +
                "\nPointNoir : " + pointNoir +
                "\nPointBlanc : " + pointBlanc;

        if ( getGagnant() != null ) string += "\nGagnant : " + getGagnant().getPrenom();

        return string;

    }
}
