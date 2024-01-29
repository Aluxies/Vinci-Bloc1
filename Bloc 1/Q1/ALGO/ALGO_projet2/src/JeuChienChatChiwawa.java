public class JeuChienChatChiwawa {

    public static void main(String[] args) {

        PlateauJeuChienChatChiwawa jeu = new PlateauJeuChienChatChiwawa();

        int positionChiwawa = 1;
        int positionChien = 3;
        int positionChat = 5;

        jeu.placerChiwawa(positionChiwawa);
        jeu.placerChat(positionChat);
        jeu.placerChien(positionChien);

        do {

            jeu.afficherInformation("Lancez le dé pour faire avancer le chat !");

            int lanceDeChat = jeu.lancerDe();

            if (positionChat + lanceDeChat >= 16) {

                positionChat = 16;
                jeu.supprimerChat();
                jeu.placerChatDansRepaire();

            } else {

                positionChat += lanceDeChat;

                jeu.afficherInformation("Le chat est en case " + positionChat);

                jeu.supprimerChat();
                jeu.placerChat(positionChat);

            }

            jeu.afficherInformation("Lancez le dé pour faire avancer le chien !");

            int lanceDeChien = jeu.lancerDe();

            if ( positionChat < 16 ){

                if (positionChien + lanceDeChien >= positionChat) {

                    jeu.supprimerChat();
                    jeu.supprimerChien();

                    positionChien += lanceDeChien;

                    jeu.placerChien(positionChat);

                } else {

                    positionChien += lanceDeChien;

                    jeu.afficherInformation("Le chien est en case " + positionChien);

                    jeu.supprimerChien();
                    jeu.placerChien(positionChien);

                }

            }

            jeu.afficherInformation("Lancez le dé pour faire avancer le chiwawa !");

            if (positionChat != 16 && positionChiwawa < positionChien && positionChien < positionChat ) {

                int lanceDeChiwawa = jeu.lancerDe();

                if (positionChiwawa + lanceDeChiwawa >= positionChien) {

                    jeu.supprimerChien();
                    jeu.supprimerChiwawa();

                    positionChiwawa += lanceDeChiwawa;

                    jeu.placerChiwawa(positionChien);

                } else {

                    positionChiwawa += lanceDeChiwawa;

                    jeu.afficherInformation("Le chiwawa est en case " + positionChiwawa);

                    jeu.supprimerChiwawa();
                    jeu.placerChiwawa(positionChiwawa);

                }

            }

            } while (positionChien < positionChat && positionChat != 16 && positionChiwawa < positionChien);

            if (positionChat == 16) {

                jeu.afficherInformation("Le chat est arrivé au repaire de la souri !");

            } else if (positionChien >= positionChat) {

                jeu.afficherInformation("Le chien a gagné car il a attrapé le chat !");

            } else if ( positionChiwawa >= positionChien ) {

                jeu.afficherInformation("Le chiwawa a gagné car il a attrapé le chien !");

            };

        }
    }