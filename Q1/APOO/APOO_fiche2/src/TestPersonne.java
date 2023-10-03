public class TestPersonne {

    public static void main(String[] args) {

        Date naissance1 = new Date( 6, 2, 1968 );
        Adresse adresse1 = new Adresse( "Rue de la gare", "34", "5000", "Namur" );
        Personne personne1 = new Personne( "Paul", "Schmidt", naissance1, adresse1 );

        Date naissance2 = new Date( 7, 3, 1970 );
        Personne personne2 = new Personne( "Valérie", "Gobert", naissance2, adresse1 );


        System.out.println( personne1 );
        System.out.println( personne2 );

    }
}
