class TestRectangle {

    public static void main(String args[]) {

        Rectangle rectangle1 = new Rectangle(5, 3 );
        Rectangle rectangle2 = new Rectangle(10, 6 );
        Rectangle rectangle3 = new Rectangle(8, 2 );

        System.out.println( "\nAire : " + rectangle1.calculerAire() );
        System.out.println( "\nPérimètre : " + rectangle2.calculerPerimetre() );
        System.out.println( "\nLongueur : " + rectangle3.getLongueur() + "\nLargeur : " + rectangle3.getLargeur() );

    };

}
