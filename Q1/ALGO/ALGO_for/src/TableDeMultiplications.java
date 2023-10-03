public class TableDeMultiplications {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        System.out.println( "Entrez un entier : " );
        int entier = scanner.nextInt();

        for ( int i = 1; i<=10; i++ ){

            System.out.println( i + "x" + entier + " = " + entier*i);

        };

    };

};