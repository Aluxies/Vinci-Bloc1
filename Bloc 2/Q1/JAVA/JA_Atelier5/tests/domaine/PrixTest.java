package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.sql.Types.NULL;
import static org.junit.jupiter.api.Assertions.*;

class PrixTest {

    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;

    @BeforeEach
    void beforeEach() {

        this.prixAucune = new Prix();
        this.prixAucune.definirPrix( 1, 20 );
        this.prixAucune.definirPrix( 10, 10 );

        this.prixPub = new Prix( TypePromo.PUB, 25 );

        this.prixPub.definirPrix( 3, 15 );

        this.prixSolde = new Prix( TypePromo.SOLDE, 22.5 );

    }

    @DisplayName( "Test du constructeur Prix avec typePromo à 'null'")
    @Test

    void testTypePromo() {

        assertThrows(IllegalArgumentException.class,
                () -> new Prix( null, 10 ) );

    }

    @DisplayName( "Test du constructeur Prix avec des valeurPromo qui est invalide")
    @ParameterizedTest
    @ValueSource( doubles = {-10, 0, -5} )
    void testValeurPromo( double valeurPromo ) {

        assertThrows(IllegalArgumentException.class,
                () -> new Prix( TypePromo.SOLDE, valeurPromo ) );

    }

    @DisplayName( "Test de getValeurPromo lorsque le contructeur est appelé sans paramètre" )
    @Test
    void testDefaultValeurPromo() {

        assertEquals( 0, prixAucune.getValeurPromo() );

    }

    @DisplayName( "Test de getValeurPromo lorsque la valeur est définie via un paramètre du contructeur" )
    @Test
    void testDefinedValeurPromo() {

        assertEquals( 25, prixPub.getValeurPromo() );
        assertEquals( 22.5, prixSolde.getValeurPromo() );

    }

    @DisplayName( "Test de getTypePromo à null" )
    @Test
    void testDefaultTypePromo() {

        assertEquals( null, prixAucune.getTypePromo() );

    }

    @DisplayName( "Test de getTypePromo correspond à la valeur du paramètre passé dans le constructeur" )
    @Test
    void testDefinedTypePromo() {

        assertAll(
            () -> assertEquals( TypePromo.SOLDE, prixSolde.getTypePromo() ),
            () -> assertEquals( TypePromo.PUB, prixPub.getTypePromo() )
        );

    }

    @DisplayName( "Test de definirPrix avec des données invalides" )
    @ParameterizedTest
    @ValueSource( ints = { 0, -10, -5 } )
    void testQuantite( int quantite ) {

        assertThrows(IllegalArgumentException.class,
                () -> prixAucune.definirPrix( quantite, 10 ) );

    }

    @DisplayName( "Test de definirPrix avec des données invalides" )
    @ParameterizedTest
    @ValueSource( ints = { 0, -10, -5 } )
    void testPrixUnitaire( int prixUnitaire ) {

        assertThrows(IllegalArgumentException.class,
                () -> prixAucune.definirPrix( 5, prixUnitaire ) );

    }

    @DisplayName( "Test des valeurs définies par definirPrix" )
    @Test
    void testDefinirPrix() {

        prixAucune.definirPrix( 10, 6 );

        assertEquals( 6, prixAucune.getPrix( 10 ) );

    }

    @DisplayName( "Test de getPrix avec des paramètres invalides" )
    @ParameterizedTest
    @ValueSource( ints = {NULL, 0, -1, -7})
    void testGetPrixQuantite( int quantite ) {

        assertThrows( IllegalArgumentException.class, () -> prixAucune.getPrix( quantite ) );

    }

    @DisplayName( "Test des valeurs renvoyées par getPrix" )
    @ParameterizedTest
    @ValueSource( ints = {1, 5, 9, 10, 15, 20, 25})
    void testGetPrix( int quantite ) {

        if ( quantite < 10 ) {

            assertEquals( 20, prixAucune.getPrix( quantite ) );

        } else {

            assertEquals( 10, prixAucune.getPrix( quantite ) );

        }

    }

    @DisplayName( "Test du renvoi d'une exception par getPrix" )
    @Test
    void testGetPrixException() {

        assertAll(
            () -> assertThrows( QuantiteNonAutoriseeException.class, () -> prixPub.getPrix( 2 ) ),
            () -> assertThrows( QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix( 1 ) )
        );

    }
}