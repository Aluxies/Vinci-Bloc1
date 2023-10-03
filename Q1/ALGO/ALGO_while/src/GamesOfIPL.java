public class GamesOfIPL {

    public static void main(String[] args) {

        int niveauAleatoire1 = unEntierAuHasardEntre( 0, 3 );
        int niveauAleatoire2 = unEntierAuHasardEntre( 0, 3 );

        Guerrier guerrier1 = new Guerrier( "CogneDur", 25, niveauAleatoire1 );
        Guerrier guerrier2 = new Guerrier( "FrappeFort", 25, niveauAleatoire2 );

        Guerrier attaquant = guerrier1;
        Guerrier attaque = guerrier2;

        System.out.print( "\nBienvenue au combat entre " + guerrier1.getNom() + " et " + guerrier2.getNom() );

        int tirageAuSort = unEntierAuHasardEntre( 1, 2 );

        while ( guerrier1.estVivant() && guerrier2.estVivant() ){

            tirageAuSort++;

            if ( tirageAuSort % 2 == 0 ){

                attaquant = guerrier1;
                attaque = guerrier2;

            } else {

                attaquant = guerrier2;
                attaque = guerrier1;

            };

            if ( attaquant.estVivant() ){

                int coupEpee = unEntierAuHasardEntre( 1, 6 );

                attaque.retierPointsDeVie( coupEpee );

                System.out.print( "\n\n " + attaquant.getNom() + " inflige " + coupEpee + " point(s) de dégâts à " + attaque.getNom() );
                System.out.print( "\nIl reste " + attaque.getPointsDeVie() + " point(s) de vie à " + attaque.getNom() );

            };

        };

        if ( !guerrier2.estVivant() ){

            System.out.print( "\n\n---------------------------------------------\n\n" + guerrier2.getNom() + " est mort. Paix à son âme il est mort en brave." );

        };

        if ( !guerrier1.estVivant() ){

            System.out.print( "\n\n---------------------------------------------\n\n" + guerrier1.getNom() + " est mort. Paix à son âme il est mort en brave." );

        };

    };

    public static int unEntierAuHasardEntre (int valeurMinimale, int valeurMaximale){

        double nombreReel;
        int resultat;

        nombreReel = Math.random();
        resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
        return resultat;

    };

};