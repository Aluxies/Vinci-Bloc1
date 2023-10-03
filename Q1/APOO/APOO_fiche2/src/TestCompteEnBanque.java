public class TestCompteEnBanque {

    public static void main(String[] args) {

        Adresse adresse1 = new Adresse( "Rue de la gare", "34", "5000", "Namur" );
        Date naissance1 = new Date(4, 2, 2004 );
        Personne personne1 = new Personne("Leconte", "Frédérick", naissance1,  adresse1 );

        Date dateDeValidite = new Date( 14, 9, 2030 );
        Date dateOuverture = new Date( 14, 9, 2015 );
        CompteEnBanque compte1 = new CompteEnBanque( personne1, "000000000089-89", dateDeValidite, dateOuverture, 1200 );

        System.out.println( compte1 );

        compte1.deposer( 100 );

        System.out.println( compte1 );
    };

};