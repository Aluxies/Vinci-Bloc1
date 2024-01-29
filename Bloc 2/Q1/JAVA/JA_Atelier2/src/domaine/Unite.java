package domaine;

public enum Unite {

    GRAMME( 1, "gr" ), KILOGRAMME( 2, "kg" ), LITRE( 3, "l" ),
    MILLILITRE( 4, "ml" ), CENTILITRE( 5, "cl" ), DECILITRE( 6, "dl" ),
    CUILLER_A_CAFE( 7, "cc" ), CUILLER_A_THE( 8, "ct" ), CUILLER_A_DESSERT( 9, "cd" ),
    CUILLER_A_SOUPE( 10, "cs" ), PINCEE( 11, "pincée" ), UN_PEU( 12, "peu" ),
    NEANT( 13, " " );

    private int numeroUnite;
    private String abreviationUnite;

    Unite ( int numeroUnite, String abreviationUnite ) {

        this.numeroUnite = numeroUnite;
        this.abreviationUnite = abreviationUnite;

    }

    @Override
    public String toString() {
        return abreviationUnite;
    }

}