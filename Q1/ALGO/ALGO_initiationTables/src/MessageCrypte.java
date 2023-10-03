import java.util.Arrays;

public class MessageCrypte {
	private char [] message;
	
	/**
	 * Construit un nouveau message a partir d'une String
	 * @param message
	 */
	public MessageCrypte(String message){
		this.message = message.toCharArray();
	}
	
	public String toString(){
		return new String(this.message) ;
	}
	
	/**
	 * Cette methode implemente le code de Cesar: http://www.dcode.fr/chiffre-cesar
	 * Remplace le code de chaque caractere dans le message par son code plus le decalage
	 * en parametre.
	 * Par exemple si le message contient {'B', 'O', 'N', 'D' }  et que le decalage est 2,
	 * apres remplacement, le message deviendra  {'D', 'Q', 'P', 'F'}.
	 * Le decalage peut etre negatif.
	 * Un decalage de -2 sur le message {'L', 'C', 'O', 'G', 'U'} donnera
	 * {'J', 'A', 'M', 'E', 'S' }
	 * 
	 * @param decalage
	 */
	public void decalerCaracteres(int decalage){

		char[] messageCrypte = new char[message.length];

		int asciiMin = 65;
		int asciiMax = 90;

		for (int i = 0; i < message.length; i++) {

			int asciiChar = message[i];
			int newChar;

			if ( ( asciiChar + decalage ) > asciiMax ){

				newChar = (char) asciiMin + ( ( asciiChar + decalage ) - asciiMax );

			} else if ( ( asciiChar + decalage ) < asciiMin ){

				newChar = (char) asciiMax + ( ( asciiChar + decalage ) - asciiMin );

			} else {

				newChar = (message[i] + decalage);

			}

			messageCrypte[i] = (char) newChar;

		}

		this.message = messageCrypte;

	}
	
	/**
	 * Permute chaque lettre a une position impaire avec celle qui la precede
	 * Par exemple le message {'C', 'O', 'O', 'T', 'U', 'P', 'S'}
	 * deviendra {'O', 'C', 'T', 'O', 'P', 'U', 'S'}
	 */
	public void permutation(){

		char[] messageCrypte = new char[message.length];

		for (int i = 1; i < message.length; i+=2) {

			messageCrypte[i-1] = message[i];
			messageCrypte[i] = message[i-1];

		}

		if ( message.length % 2 == 1 ){

			messageCrypte[message.length-1] = message[message.length-1];

		}

		this.message = messageCrypte;

	}
	
	/**
	 * Echange chaque paire de caracteres du message de la facon suivante:
	 * Le premier est echange avec le dernier
	 * L'avant-dernier avec le 2eme
	 * L'avant-avant-dernier avec le 3eme
	 * etc.
	 * Par exemple le message {'R', 'U', 'O', 'J', 'N', 'O', 'B'}
	 * deviendra  {'B', 'O', 'N', 'J', 'O', 'U', 'R'}
	 */
	public void miroir(){

		char[] messageCrypte = new char[message.length];

		for (int i = 0; i < message.length; i++) {

			messageCrypte[i] = message[message.length-1-i];

		}

		this.message = messageCrypte;

	}
	
	/**
	 * Crypte le message en implementant la methode de l'alphabet de substitution.
	 * Lisez la description du la substitution monoalphabetique ici:
	 * https://fr.wikipedia.org/wiki/Chiffrement_par_substitution
	 * On supposera que seule les lettre 'A' a 'Z' sont presentes le caractere espace n'est pas remplace
	 * alphabetDeSubstitution[0] contient la lettre de substitution pour le caractere 'A'
	 * alphabetDeSubstitution[1] contient la lettre de substitution pour le caractere 'B
	 * etc...
	 * @param alphabetDeSubstitution
	 */
	public void substitution(char []alphabetDeSubstitution){

		char[] newMessage = new char[message.length];

		for (int i = 0; i < message.length; i++) {

			char currentChar = message[i];

			int currentAsciiChar = (int) currentChar;
			int newCharIndice = currentAsciiChar - 65;

			char newChar = alphabetDeSubstitution[newCharIndice];

			newMessage[i] = newChar;

		}

		this.message = newMessage;
		
	}

	public void decrypteSubtitution(char[] alphabetDeSubstitution){

		char[] newMessage = new char[message.length];
		char[] alphabet = {
				'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z'
		};

		for (int i = 0; i < message.length; i++) {

			char currentChar = message[i];

			for ( int j = 0; j < alphabetDeSubstitution.length; j++ ){

				if ( currentChar == alphabetDeSubstitution[j] ){

					newMessage[i] = alphabet[j];

				}
			}

		}

		this.message = newMessage;

	}

}
