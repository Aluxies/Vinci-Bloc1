
public class JeuGuerrier {
	
	public static void main(String[] args) {
		EquipeGuerriers equipe = new EquipeGuerriers(3, 10);
		int pointsDeVieDuMal = 30;

		while ( equipe.nombreGuerriersEnVie() > 0 && pointsDeVieDuMal > 0 ){

			System.out.println();
			System.out.println("L'equipe compte " + equipe.nombreGuerriersEnVie() + " guerriers en vie" );
			System.out.println();

			int lancer1 = lancerDe();
			Guerrier guerrier = equipe.jouer( lancer1 );

			System.out.println("Suite au combat entre la creature du mal et le guerrier n°" + guerrier.getNumero() );
			System.out.println("Le guerrier vient de perdre " + lancer1 + " points de vie");

			if ( guerrier.getPointsDeVie() <= 0 ) System.out.println("Le guerrier n°" + guerrier.getNumero() + " est mort");

			int lancer2 = lancerDe();
			pointsDeVieDuMal -= lancer2;

			System.out.println();

			System.out.println("La creature du mal vient de perdre " + lancer2 + " points de vie");
			System.out.println("Il lui reste " + pointsDeVieDuMal + " points de vie");

		}

		if ( pointsDeVieDuMal <= 0 ) System.out.println( "La creature du mal est morte" );
		else {

			System.out.println();
			if ( equipe.nombreGuerriersEnVie() <= 0 ) System.out.println( "Tous les guerriers sont morts");

		}

	}
	
	public static int lancerDe (){
		double nombreReel;
		nombreReel = Math.random();
		return (int) (nombreReel * 6) + 1;
	}
	
}
