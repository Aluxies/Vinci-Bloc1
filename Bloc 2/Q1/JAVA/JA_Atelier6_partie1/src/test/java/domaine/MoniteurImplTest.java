package domaine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    private Moniteur moniteur;
    private Sport sportCompetent;
    private Stage stageValide;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        moniteur = new MoniteurImpl( "David" );
        sportCompetent = new SportStub( true );
        stageValide = new StageStub( 8, null, sportCompetent);

    }

    void amenerALEtat( int etat, Moniteur moniteur ) {

        for ( int i=1; i<=etat; i++ ) {

            StageStub stage = new StageStub( i, null, sportCompetent );

            moniteur.ajouterStage( stage );

        }

    }

    @Test
    void testCase1() {

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages())
        );

    }

    @Test
    void testCase2() {

        amenerALEtat( 1, moniteur );

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages())
        );

    }

    @Test
    void testCase3() {

        amenerALEtat( 2, moniteur );

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages())
        );

    }

    @Test
    void testCase4() {

        amenerALEtat( 3, moniteur );

        assertAll(() -> assertTrue(moniteur.ajouterStage(stageValide)),
                () -> assertTrue(moniteur.contientStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );

    }

    @Test
    void testCase5() {

        amenerALEtat( 3, moniteur );

        moniteur.ajouterStage(stageValide);

        assertAll(() -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(3, moniteur.nombreDeStages())
        );

    }

    @Test
    void testCase6() {

        amenerALEtat( 2, moniteur );

        moniteur.ajouterStage(stageValide);

        assertAll(() -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(2, moniteur.nombreDeStages())
        );

    }

    @Test
    void testCase7() {

        amenerALEtat( 1, moniteur );

        moniteur.ajouterStage(stageValide);

        assertAll(() -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(1, moniteur.nombreDeStages())
        );

    }

    @Test
    void testCase8() {

        moniteur.ajouterStage(stageValide);

        assertAll(() -> assertTrue(moniteur.supprimerStage(stageValide)),
                () -> assertFalse(moniteur.contientStage(stageValide)),
                () -> assertEquals(0, moniteur.nombreDeStages())
        );

    }

    @Test
    void testMoniteurTC9() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stageValide);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    void testMoniteurTC10() {
        amenerALEtat(4, moniteur);
        Stage stageMemeSemaine = new StageStub(1, null, sportCompetent);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageMemeSemaine)),
                () -> assertFalse(moniteur.contientStage(stageMemeSemaine)),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    void testMoniteurTC11() {
        amenerALEtat(4, moniteur);
        assertAll(() -> assertFalse(moniteur.supprimerStage(stageValide)),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    void testMoniteurTC12() {
        amenerALEtat(4, moniteur);
        Moniteur autreMoniteur = new MoniteurImpl("Snake");
        Stage stageAutreMoniteur = new StageStub(8, autreMoniteur, sportCompetent);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageAutreMoniteur)),
                () -> assertFalse(moniteur.contientStage(stageAutreMoniteur)),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

    @Test
    void testMoniteurTC13() {
        amenerALEtat(4, moniteur);
        Stage stageBonMoniteur = new StageStub(8, moniteur, sportCompetent);
        assertAll(() -> assertTrue(moniteur.ajouterStage(stageBonMoniteur)),
                () -> assertTrue(moniteur.contientStage(stageBonMoniteur)),
                () -> assertEquals(5, moniteur.nombreDeStages())
        );
    }

    @Test
    void testMoniteurTC14() {
        amenerALEtat(4, moniteur);
        Sport sportHorsCompetence = new SportStub(false);
        Stage stageMauvaisSport = new StageStub(8, null, sportHorsCompetence);
        assertAll(() -> assertFalse(moniteur.ajouterStage(stageMauvaisSport)),
                () -> assertFalse(moniteur.contientStage(stageMauvaisSport)),
                () -> assertEquals(4, moniteur.nombreDeStages())
        );
    }

}