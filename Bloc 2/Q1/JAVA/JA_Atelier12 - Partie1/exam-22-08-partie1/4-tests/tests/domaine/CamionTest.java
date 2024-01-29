package domaine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class CamionTest {

    Camion camion;

    @Before
    public void setUp() {

        camion = new Camion( "CAM1", 100, 1100 );

    }

    @Test
    public void ajouterTrajet(){

        Trajet trajet = Mockito.mock(Trajet.class);

        Mockito.when(trajet.getDate()).thenReturn( LocalDate.now() );
        Mockito.when(trajet.calculerPoidsTotal()).thenReturn( 1000.0 );
        Mockito.when(trajet.nbCaisses()).thenReturn( 120 );

        assertAll(
                () -> assertFalse(camion.ajouterTrajet( trajet ) )
        );

    }
}
