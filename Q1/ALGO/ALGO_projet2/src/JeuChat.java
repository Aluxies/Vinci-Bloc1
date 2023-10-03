public class JeuChat {

    public static void main(String[] args) {

        PlateauJeuChat jeu = new PlateauJeuChat();
        int positionChat = 1;
        jeu.placerChat(positionChat);
        jeu.afficherInformation("Vas-y !");

        do {

            int lanceDe = jeu.lancerDe();

            if ( (positionChat + lanceDe) >= 16 ){

                jeu.supprimerChat();
                jeu.placerChatDansRepaire();

            } else {

                if ( positionChat == 10 ){

                    jeu.afficherInformation( "Le chat a pris le passage et est arrivé à la case 13" );
                    positionChat = 13;


                } else {

                    positionChat += lanceDe;

                }

                jeu.supprimerChat();
                jeu.placerChat( positionChat );

            }

        } while ( positionChat != 16 );

        jeu.afficherInformation( "Le chat a réussi à attraper la souri !" );

    }
}
