package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    Prix prixAucune;
    Prix prixPub;
    Prix prixSolde;
    Produit produit1;
    Produit produit2;

    @BeforeEach
    void beforeEach() {

        this.prixAucune = new Prix();
        this.prixAucune.definirPrix( 1, 20 );
        this.prixAucune.definirPrix( 10, 10 );

        this.prixPub = new Prix( TypePromo.PUB, 25 );

        this.prixPub.definirPrix( 3, 15 );

        this.prixSolde = new Prix( TypePromo.SOLDE, 22.5 );

        this.produit1 = new Produit( "Coca Cola Regular", "Coca Cola", "Boissons" );

        this.produit2 = new Produit( "Lion", "Kellog's", "Petit déjeuner" );

        produit2.ajouterPrix( LocalDate.of( 2022, 7, 28 ), prixAucune );
        produit2.ajouterPrix( LocalDate.of( 2020, 3, 7 ), prixSolde );
        produit2.ajouterPrix( LocalDate.of( 2017, 12, 11 ), prixPub );

    }

    @DisplayName( "Test des paramètres du constructeur de la classe Produit" )
    @Test

    void testConstructor() {

        assertAll(

            () -> assertThrows(IllegalArgumentException.class,
                    () -> new Produit( null, null, null ) ),
            () -> assertThrows(IllegalArgumentException.class,
                    () -> new Produit( " ", " ", " " ) )

        );

    }

    @DisplayName( "Test des valeurs définies par le constructeur de la classe Produit" )
    @Test

    void testConstructorValues() {

        assertAll(

            () -> assertEquals( "Coca Cola Regular", produit1.getNom() ),
            () -> assertEquals( "Coca Cola", produit1.getMarque() ),
            () -> assertEquals( "Boissons", produit1.getRayon() ),
            () -> assertEquals( "Lion", produit2.getNom() ),
            () -> assertEquals( "Kellog's", produit2.getMarque() ),
            () -> assertEquals( "Petit déjeuner", produit2.getRayon() )

        );

    }

    @DisplayName( "Test de l'exception IllegalArgumentException attendue lors de paramètres invalides dans la méthode ajouterPrix" )
    @Test

    void testAjouterPrixIllegalArgumentException() {

        assertAll(

            () -> assertThrows(IllegalArgumentException.class,
                () -> produit1.ajouterPrix( null, prixPub ) ),
            () -> assertThrows(IllegalArgumentException.class,
                () -> produit1.ajouterPrix( LocalDate.of( 20017, 12, 11 ), null ) )

        );

    }

    @DisplayName( "Test de l'exception DateDejaPresenteException attendue lors de paramètres invalides dans la méthode ajouterPrix" )
    @Test

    void testAjouterPrixDateDejaPresenteException() {

        assertThrows(DateDejaPresenteException.class,
                () -> produit2.ajouterPrix( LocalDate.of( 2017, 12, 11 ), prixPub ) );

    }

    @DisplayName( "Test de l'ajout du prix par la méthode ajouterPrix" )
    @Test

    void testAjouterPrixFonctionnel() {

        produit1.ajouterPrix( LocalDate.of( 2017, 12, 11 ), prixSolde );

        assertEquals( prixSolde, produit1.getPrix( LocalDate.of( 2017, 12, 11 ) ) );

    }

    @DisplayName( "Test de l'exception PrixNonDisponibleException attendue lorsque la date est antérieure à la définition d’un prix dans la méthode getPrix" )
    @Test

    void testGetPrixDateAnterieureDefinitionPrix() {

        assertThrows(PrixNonDisponibleException.class,
            () -> produit2.getPrix( LocalDate.of( 2011, 12, 11 ) ) );

    }

    @DisplayName( "Test de l'exception PrixNonDisponibleException attendue lorsque demande un prix pour un produit qui n’en n’a pas dans la méthode getPrix" )
    @Test

    void testGetPrixPasDePrixDefini() {

        assertThrows(PrixNonDisponibleException.class,
            () -> produit1.getPrix( LocalDate.of( 2011, 12, 11 ) ) );

    }

    @DisplayName( "Test lorsque l'on demande un prix pour une date comprise entre deux dates, le prix de la date antérieure est renvoyé dans la méthode getPrix" )
    @Test

    void testGetPrixEntreDeuxDates() {

        assertEquals(prixPub,
                produit2.getPrix( LocalDate.of( 2018, 10, 11 ) ) );

    }

    @DisplayName( "Test fonctionne pour 2 instances de Produit différentes mais qui ont les même marque, nom et rayon dans la méthode getPrix" )
    @Test

    void testEqualsDeuxProduitsDifferents() {

        Produit produit3 = new Produit( "Pepsi Max", "Pepsi", "Boissons" );
        Produit produit4 = new Produit( "Pepsi Max", "Pepsi", "Boissons" );

        assertTrue(produit3.equals(produit4));

    }

    @DisplayName( "Test renvoie faux pour deux produits ayant la même marque et le même rayon mais ayant des noms différents dans la méthode getPrix" )
    @Test

    void testEqualsDeuxProduitsNomsDifferents() {

        Produit produit3 = new Produit( "Pepsi Max", "Pepsi", "Boissons" );
        Produit produit4 = new Produit( "Pepsi Light", "Pepsi", "Boissons" );

        assertFalse(produit3.equals(produit4));

    }

    @DisplayName( "Test renvoie faux pour deux produits ayant le même nom et le même rayon mais ayant des marques différentes dans la méthode getPrix" )
    @Test

    void testEqualsDeuxProduitsMarquesDifferentes() {

        Produit produit3 = new Produit( "Pepsi Max", "Pepsi", "Boissons" );
        Produit produit4 = new Produit( "Pepsi Light", "Coca", "Boissons" );

        assertFalse(produit3.equals(produit4));

    }

    @DisplayName( "Test renvoie faux pour deux produits ayant le même nom et la même marque mais ayant des rayons différents dans la méthode getPrix" )
    @Test

    void testEqualsDeuxProduitsRayonsDifferents() {

        Produit produit3 = new Produit( "Pepsi Max", "Pepsi", "Boissons" );
        Produit produit4 = new Produit( "Pepsi Light", "Pepsi", "Boucherie" );

        assertFalse(produit3.equals(produit4));

    }

    @DisplayName( "Test renvoie bien la même valeur pour 2 instances de Produit différentes mais qui ont les même marque, nom et rayon dans la méthode getPrix" )
    @Test

    void testHashCodeDeuxProduitsDifferents() {

        Produit produit3 = new Produit( "Pepsi Max", "Pepsi", "Boissons" );
        Produit produit4 = new Produit( "Pepsi Max", "Pepsi", "Boissons" );

        assertEquals( produit3.hashCode(), produit4.hashCode() );

    }

}