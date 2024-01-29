
public class TestMessageCrypte {

	public static void main(String[] args) {
		System.out.println("Mission 1: implementes la methode decalage");
		
		MessageCrypte mission2Crypte = new MessageCrypte("LPSOHPHQWHV" + (char)(' ' + 3) + "OD"
				+ (char)(' ' + 3) + "PHWKRGH" 
				+ (char)(' ' + 3) + "SHUPXWDWLRQ");
		mission2Crypte.decalerCaracteres(-3);

		MessageCrypte missionPerso = new MessageCrypte( "CNGAKU" );

		missionPerso.decalerCaracteres( -2 );

		System.out.println("Mission perso : " + missionPerso);
		
		System.out.println("Mission 2: " + mission2Crypte);
		
		MessageCrypte c3 = new MessageCrypte("MILPMENEET SALM TEOHEDM RIIOR");
		c3.permutation();
		System.out.println("Mission 3: " + c3.toString());
		
		MessageCrypte c4 = new MessageCrypte("ontitutibssur_pat_enemfrifChi/ik/wrg.oiaedipik.wfr//s:tpht: ONTITUTIBSSUE ODTHMEA  LNTTAENEMPLIMN  EFIDEE  LNTNATEINMAS TEAI!FANAWAD PNEEU JVORAB");
		c4.permutation();
		c4.miroir();
		System.out.println(c4);

		MessageCrypte c5 = new MessageCrypte("MESSAGECRYPTEPARSUBTITUTION");

		char[] alphabetDeSubtitution = {
				'A', 'Z', 'E', 'R', 'T',
				'Y', 'U', 'I', 'O', 'P',
				'Q', 'S', 'D', 'F', 'G',
				'H', 'J', 'K', 'L', 'M',
				'W', 'X', 'C', 'V', 'B', 'N'
		};

		c5.substitution( alphabetDeSubtitution );
		System.out.println( c5 );

		c5.decrypteSubtitution( alphabetDeSubtitution );
		System.out.println( c5 );

	}

}
