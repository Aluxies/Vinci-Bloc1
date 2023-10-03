import java.time.Duration;
import java.time.LocalDateTime;

public class AppelTelephonique {

    private LocalDateTime dateHeureAppel;

    private String numeroAppele;

    private Duration dureeAppel;

    private static double tarifParMinute = 0.15;

    public AppelTelephonique( LocalDateTime dateHeureAppel, String numeroAppele, Duration dureeAppel, double tarifParMinute ){

        if ( dateHeureAppel.equals( null ) ) throw new IllegalArgumentException( "DateHeureAppel ne peut être 'null'" );
        if ( dateHeureAppel.isAfter( LocalDateTime.now() ) ) throw new IllegalArgumentException( "DateHeureAppel ne peut être 'Date future'" );

        this.dateHeureAppel = dateHeureAppel;

        if ( numeroAppele.equals( null ) ) throw new IllegalArgumentException( "NumeroAppele ne peut être 'null'" );

        this.numeroAppele = numeroAppele;

        if ( dureeAppel.equals( null ) ) throw new IllegalArgumentException( "DureeAppel ne peut être 'null'" );
        if ( dureeAppel.isZero() ) throw new IllegalArgumentException( "DureeAppel ne peut être 'égal à 0'" );
        if ( dureeAppel.isNegative() ) throw new IllegalArgumentException( "DureeAppel ne peut être 'négatif'" );

        this.dureeAppel = dureeAppel;

        if ( tarifParMinute <= 0 ) throw new IllegalArgumentException( "TarifParMInute ne peut être 'inférieur ou égal à 0'" );

        setTarifParMinute( tarifParMinute );

    }

    public AppelTelephonique( LocalDateTime dateHeureAppel, String numeroAppele, Duration dureeAppel ){

        this( dateHeureAppel, numeroAppele, dureeAppel, tarifParMinute );

    }

    public LocalDateTime getDateHeureAppel() {
        return dateHeureAppel;
    }

    public String getNumeroAppele() {
        return numeroAppele;
    }

    public Duration getDureeAppel() {
        return dureeAppel;
    }

    public static double getTarifParMinute() {
        return tarifParMinute;
    }

    public double calculerCoutAppelTelephonique(){

        double cout = tarifParMinute * ( dureeAppel.getSeconds() / 60 );

        return cout;
    }

    public static void setTarifParMinute(double newTarifParMinute ){

        if ( newTarifParMinute <= 0 ) throw new IllegalArgumentException( "TarifParMinute ne peut être 'inférieur ou égal à 0'" );

        tarifParMinute = newTarifParMinute;

    }

    @Override
    public String toString() {
        return "AppelTelephonique :\n" +
                "\nDateHeureAppel : " + dateHeureAppel +
                "\nNumeroAppele : " + numeroAppele +
                "\nDureeAppel : " + dureeAppel.getSeconds() + "s";
    }
}