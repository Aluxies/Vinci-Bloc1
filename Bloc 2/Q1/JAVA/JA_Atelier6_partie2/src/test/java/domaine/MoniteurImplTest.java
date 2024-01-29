package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    Moniteur moniteur;
    Stage stageValide;
    Sport sportCompetent;

    @BeforeEach
    void setUp() {

        this.moniteur = new MoniteurImpl("David");
        this.sportCompetent = Mockito.mock(Sport.class);
        Mockito.when(sportCompetent.contientMoniteur(moniteur)).thenReturn(true);

        this.stageValide = Mockito.mock(Stage.class);
        Mockito.when(stageValide.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(stageValide.getMoniteur()).thenReturn(null);
        Mockito.when(stageValide.getSport()).thenReturn(sportCompetent);

    }

    void amenerALEtat(int etat, Moniteur moniteur) {

        for ( int i=1; i<=etat; i++ ) {

            Stage stage = Mockito.mock(Stage.class);

            Mockito.when(stage.getNumeroDeSemaine()).thenReturn( i );
            Mockito.when(stage.getMoniteur()).thenReturn( null );
            Mockito.when(stage.getSport()).thenReturn(sportCompetent );

            moniteur.ajouterStage( stage );

        }

    }

    @Test
    void testCase1() {

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageValide ).enregistrerMoniteur( moniteur )
        );

    }

    @Test
    void testCase2() {

        amenerALEtat( 1, moniteur );

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageValide ).enregistrerMoniteur( moniteur )
        );

    }

    @Test
    void testCase3() {

        amenerALEtat( 2, moniteur );

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageValide ).enregistrerMoniteur( moniteur )
        );


    }

    @Test
    void testCase4() {

        amenerALEtat( 3, moniteur );

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageValide ).enregistrerMoniteur( moniteur )
        );

    }

    @Test
    void testCase5() {

        amenerALEtat( 3, moniteur );

        moniteur.ajouterStage( stageValide );

        assertAll(() -> assertFalse(moniteur.ajouterStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageValide ).enregistrerMoniteur( moniteur )
        );

    }

    @Test
    void testCase6() {

        amenerALEtat( 4, moniteur );

        Stage stageInvalide = Mockito.mock(Stage.class);

        Mockito.when(stageInvalide.getNumeroDeSemaine()).thenReturn( 1 );
        Mockito.when(stageInvalide.getMoniteur()).thenReturn( null );
        Mockito.when(stageInvalide.getSport()).thenReturn(sportCompetent );

        assertAll(() -> assertFalse(moniteur.ajouterStage(stageInvalide)),
                () -> assertFalse(moniteur.contientStage(stageInvalide)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageInvalide, Mockito.never() ).enregistrerMoniteur( moniteur )
        );

    }

    @Test
    void testCase7() {

        amenerALEtat( 4, moniteur );

        Moniteur autreMoniteur = new MoniteurImpl("Pas David" );

        Stage stageAutreMoniteur = Mockito.mock(Stage.class);

        Mockito.when(stageAutreMoniteur.getNumeroDeSemaine()).thenReturn( 8 );
        Mockito.when(stageAutreMoniteur.getMoniteur()).thenReturn( autreMoniteur );
        Mockito.when(stageAutreMoniteur.getSport()).thenReturn(sportCompetent );

        assertAll(() -> assertFalse(moniteur.ajouterStage(stageAutreMoniteur)),
                () -> assertFalse(moniteur.contientStage(stageAutreMoniteur)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageAutreMoniteur, Mockito.never() ).enregistrerMoniteur( moniteur )
        );

    }

    @Test
    void testCase8() {

        amenerALEtat( 4, moniteur );

        Stage stageMoniteur = Mockito.mock(Stage.class);

        Mockito.when(stageMoniteur.getNumeroDeSemaine()).thenReturn( 8 );
        Mockito.when(stageMoniteur.getMoniteur()).thenReturn( moniteur );
        Mockito.when(stageMoniteur.getSport()).thenReturn(sportCompetent );

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageMoniteur)),
                () -> assertTrue(moniteur.contientStage(stageMoniteur)),
                () -> assertEquals(5, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageMoniteur, Mockito.never() ).enregistrerMoniteur( moniteur )
        );

    }

    @Test
    void testCase9() {

        Stage stageInvalide = Mockito.mock(Stage.class);
        Sport sportNonCompetent = Mockito.mock(Sport.class);

        Mockito.when(sportNonCompetent.contientMoniteur(moniteur)).thenReturn(false);

        Mockito.when(stageInvalide.getNumeroDeSemaine()).thenReturn( 8 );
        Mockito.when(stageInvalide.getMoniteur()).thenReturn( null );
        Mockito.when(stageInvalide.getSport()).thenReturn(sportNonCompetent);

        assertAll(() -> assertFalse(moniteur.ajouterStage(stageInvalide)),
                () -> assertFalse(moniteur.contientStage(stageInvalide)),
                () -> assertEquals(0, moniteur.nombreDeStages()),
                () -> Mockito.verify( stageInvalide, Mockito.never() ).enregistrerMoniteur( moniteur )
        );

    }
}