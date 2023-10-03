public class TestErrors {

    public static void main(String[] args) {

        Article article1 = new Article( "", "", "Vélo tout terrain", 0, -1 );
        Article article2 = new Article( null, null, "Ordinateur de bureau", 0, 101 );

        article1.setReduction( 0 );

    }
}