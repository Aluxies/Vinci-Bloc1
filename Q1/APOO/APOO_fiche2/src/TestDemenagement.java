public class TestDemenagement {

	public static void main(String[] args) {

		Date dateBrad = new Date(18,12, 1963 );
		Date dateAngelina = new Date(4,6,1975 );

		Adresse adresse = new Adresse("Rue des stars", "52","1000","Bruxelles");

		Personne brad = new Personne("Pitt","Brad", dateBrad, adresse );
		Personne angelina = new Personne("Jolie", "Angelina", dateAngelina, adresse);

		brad.demenager("Rue des étoiles", "32", "1000", "Bruxelles");
		angelina.demenager("Rue des étoiles", "32", "1000", "Bruxelles");

		System.out.println( brad.getDomicile() );
		System.out.println( angelina.getDomicile() );

	};

};