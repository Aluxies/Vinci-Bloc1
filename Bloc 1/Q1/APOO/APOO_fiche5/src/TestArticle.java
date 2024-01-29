public class TestArticle {

    public static void main(String[] args) {

        Article article1 = new Article( "1234-5678", "Vélo", "Vélo tout terrain", 345 );
        Article article2 = new Article( "5678-1234", "Ordinateur", "Ordinateur de bureau", 789, 24 );
        Article article3 = new Article( "4321-5678", "Ventilateur", "Ventilaeur avec air rafraichissant", 89, 22 );

        System.out.println( article1 );

        System.out.println();

        System.out.println( article2 );

        System.out.println();

        System.out.println( article3 );

        System.out.println();

        System.out.println( "Nombre d'instances d'articles : " + article1.getNombreArticles() );

    }

}