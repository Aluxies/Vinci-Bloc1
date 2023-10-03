public class TestMembre {

    public static void main(String[] args) {

        Membre membre1 = new Membre( "Leconte", "Emmeline", "007" );

        System.out.println( "\n2) " + membre1.initialiserParain( membre1 ) );
        System.out.println( "\n3) " + membre1 );

        Membre membre2 = new Membre( "Cambron", "Isabelle", "006" );

        System.out.println( "\n5) " + membre1.initialiserParain( membre2 ) );

        System.out.println( "\n6) " + membre1 );

        Membre membre3 = new Membre( "Baroni", "Raphaël", "005" );

        System.out.println( "\n8) " + membre1.initialiserParain( membre3 ) );

        System.out.println( "\n9) " + membre1 );

    }

}