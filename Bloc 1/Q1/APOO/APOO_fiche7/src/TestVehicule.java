import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestVehicule {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");


        LocalDate miseEnCirculation = LocalDate.now();

        LocalDate premierJanvier2022 = LocalDate.parse("1/01/2022", formatter );

        Voiture voiture1 = new Voiture( "1-TNP-124", miseEnCirculation );
        Voiture voiture2 = new Voiture( "1-AOE-532", premierJanvier2022 );

        Utilitaire utilitaire1 = new Utilitaire( "1-AFV-302", miseEnCirculation, 10000 );
        Utilitaire utilitaire2 = new Utilitaire( "1-FAV-532", miseEnCirculation );


        System.out.println( "Voiture :" );
        System.out.println();

        System.out.println( voiture1 );
        System.out.println();
        System.out.println( voiture2 );
        System.out.println();

        System.out.println( "Utilitaire :" );
        System.out.println();

        System.out.println( utilitaire1 );
        System.out.println();
        System.out.println( utilitaire2 );
        System.out.println();

        System.out.println( "Test voiture :" );
        System.out.println();

        System.out.println( "Test 1 : contrôle technique LocalDate.now() : " + voiture1.verifierControleTechnique() );
        System.out.println();

        System.out.println( "Test 2 : contrôle technique LocalDate 1/01/2022 : " + voiture2.verifierControleTechnique() );
        System.out.println();

        System.out.println( "Test utilitaire :" );
        System.out.println();

        utilitaire1.setKilometrage( 9000 );

        System.out.println( "Test 1 : set kilométrage à 9000" );
        System.out.println();

        System.out.println( utilitaire1 );
        System.out.println();

        System.out.println( "Test 1 : contrôle technique : " + utilitaire1.verifierControleTechnique() );
        System.out.println();

        utilitaire1.setKilometrage( 12000 );

        System.out.println( "Test 2 : set kilométrage à 12000" );
        System.out.println();

        System.out.println( utilitaire1 );
        System.out.println();

        System.out.println( "Test 2 : contrôle technique : " + utilitaire1.verifierControleTechnique() );
        System.out.println();

    }

}