
public class JeuChienChat {
	
	public static void main(String[] args) {

		PlateauJeuChienChat jeu = new PlateauJeuChienChat();

		int positionChien = 1;
		int positionChat = 3;

		jeu.placerChat( positionChat );
		jeu.placerChien( positionChien );

		do {

			jeu.afficherInformation( "Lancez le dé pour faire avancer le chat !" );

			int lanceDeChat = jeu.lancerDe();

			if ( positionChat + lanceDeChat >= 16 ){

				positionChat = 16;
				jeu.supprimerChat();
				jeu.placerChatDansRepaire();

			} else {

				positionChat += lanceDeChat;

				jeu.afficherInformation( "Le chat est en case " + positionChat );

				jeu.supprimerChat();
				jeu.placerChat( positionChat );

			}

			jeu.afficherInformation( "Lancez le dé pour faire avancer le chien !" );

			if ( positionChat != 16 ){

				int lanceDeChien = jeu.lancerDe();

				if ( positionChien + lanceDeChien >= positionChat ){

					jeu.supprimerChat();
					jeu.supprimerChien();

					positionChien += lanceDeChien;

					jeu.placerChien( positionChat );

				} else {

					positionChien += lanceDeChien;

					jeu.afficherInformation( "Le chien est en case " + positionChien );

					jeu.supprimerChien();
					jeu.placerChien( positionChien );

				}

			}

		} while ( positionChien < positionChat && positionChat != 16 );

		if ( positionChat == 16 ){

			jeu.afficherInformation( "Le chat est arrivé au repaire de la souri !" );

		} else if ( positionChien >= positionChat ){

			jeu.afficherInformation( "Le chien a gagné car il a attrapé le chat !" );
		}

	}

}