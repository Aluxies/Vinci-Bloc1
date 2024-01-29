import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestAppelTelephonique {

    public static void main(String[] args) {

        LocalDate date1 = LocalDate.of( 2022, 6, 25);
        LocalTime time1 = LocalTime.of( 22, 23, 54 );
        LocalDateTime localDateTime1 = LocalDateTime.of( date1, time1 );

        Duration duree1 = Duration.between( localDateTime1, LocalDateTime.now() );

        AppelTelephonique appelTelephonique1 = new AppelTelephonique( localDateTime1, "0472055103", duree1, 0.25 );

        System.out.println( appelTelephonique1 );

        System.out.println();

        System.out.println( "Cout de l'appel : " + appelTelephonique1.calculerCoutAppelTelephonique() + "$");

        System.out.println();

        System.out.println( "Tarif par minute : " + appelTelephonique1.getTarifParMinute() );

        System.out.println();

        appelTelephonique1.setTarifParMinute( 0.35 );

        System.out.println( "Tarif par minute : " + appelTelephonique1.getTarifParMinute() );
    }
}
