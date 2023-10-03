public class calculBMI {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {

        System.out.print( "Entre votre taille : " );
        double taille = scanner.nextDouble();

        System.out.print( "Entre votre poids : " );
        int poids = scanner.nextInt();

        double BMI = poids / ( taille * taille );

        if ( BMI < 20 ){

            System.out.print( "Vous êtes de corpulence \"mince\". " );

        } else if ( BMI <= 25 ){

            System.out.print( "Vous êtes de corpulence \"normale\". " );

        } else if ( BMI <= 30 ){

            System.out.print( "Vous êtes de corpulence \"embonpoint\". " );

        } else System.out.print( "Vous êtes de corpulence \"obèse\". " );

    }

}
