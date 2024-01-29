package domaine;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CaisseTest {

    private Trajet trajet;

    @Before
    public void setUp() throws Exception {
        trajet = new Trajet( "num1", LocalDate.now().plusDays(1), "Paris", "Bruxelles");
    }

    @Test
    public void peutAjouterNull() {

        assertAll(
                () -> assertThrows( IllegalArgumentException.class,
                        () -> trajet.peutAjouter( null ) )
        );

    }

    @Test
    public void peutAjouterFalse() {

        String reference = "CAI1";
        LocalDate date = LocalDate.now().plusDays(3);
        String villeDepart = "Stockholm";
        String villeArrivee = "Bruxelles";
        double poids = 10.8;

        Caisse caisseFalse = new Caisse( reference, date, villeDepart, villeArrivee, poids );

        assertAll(
                () -> assertFalse( trajet.peutAjouter( caisseFalse ) )
        );

    }

    @Test
    public void peutAjouterTrue() {

        String reference = "CAI2";
        LocalDate date = LocalDate.now().plusDays(2);
        String villeDepart = "Paris";
        String villeArrivee = "Bruxelles";
        double poids = 5.7;

        Caisse caisseTrue = new Caisse( reference, date, villeDepart, villeArrivee, poids );

        assertAll(
                () -> assertTrue( trajet.peutAjouter( caisseTrue ) )
        );

    }

}