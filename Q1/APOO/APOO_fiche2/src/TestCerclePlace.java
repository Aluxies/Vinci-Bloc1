public class TestCerclePlace {

    public static void main(String[] args) {

        Point point1 = new Point(4, 2.5);
        CerclePlace cercle1 = new CerclePlace( 4, point1 );

        CerclePlace cercle2 = new CerclePlace( 12.5, point1 );

        cercle2.setCentre( 8, point1.getY() );

        System.out.println( cercle1 );
        System.out.println( cercle2 );

    };

};