public class GestionEntrepot {
    //private static Scanner scanner = new Scanner(System.in);
    private static MonScanner scanner = new MonScanner("commandes.txt");
    private static Entrepot entrepot;

    public static void main(String[] args) {
        System.out.println("*********************");
        System.out.println("Gestion d'un entrepot");
        System.out.println("*********************");
        System.out.println();
        System.out.print("Entrez le nombre d'hangars : ");
        int nombreHangars = scanner.nextInt();
        entrepot = new Entrepot(nombreHangars);
        int choix = 0;
        do {
            System.out.println();
            System.out.println("1 -> attribuer un hangar");
            System.out.println("2 -> lister les hangars d'une societe");
            System.out.println("3 -> libérer un hangar");
            System.out.println("4 -> ajouter un véhicule à une société");
            System.out.println("5 -> vérifier une plaque d'immatriculation");
            System.out.println("6 -> quitter");
            System.out.println();
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    attribuerUnHangar();
                    break;
                case 2:
                    listerLesHangars();
                    break;
                case 3:
                    libererUnHangar();
                    break;
                case 4:
                    ajouterUnVehicule();
                    break;
                case 5:
                    verifierUnVehicule();
                    break;
                case 6:
                    break;
            }

        } while (choix!=6);

        System.out.println("Fin");
    }

    private static void attribuerUnHangar() {
        if (entrepot.nombreHangarsLibres()==0){
            System.out.println("Desole, tous les hangars sont occupes !");
        } else {
            System.out.print("Entrez le numero de la societe : ");
            int numeroSociete = scanner.nextInt();
            Societe societe = entrepot.getSociete(numeroSociete);
            String nomSociete;
            if(societe==null){
                System.out.print("Entrez le nom de la societe : ");
                nomSociete = scanner.next();
            }else{
                nomSociete = societe.getNom();
            }
            System.out.println();
            int numeroHangar = entrepot.attribuerHangar(numeroSociete,nomSociete);
            System.out.println("Le numero du hangar attribue : " + numeroHangar);
        }
    }

    private static void listerLesHangars() {

        String string = "";

        for ( Hangar hangar : entrepot ){

            string += hangar.toString() + "\n";

        }

        System.out.println( string );

    }

    private static void libererUnHangar(){

        System.out.print("Entrez le numero du hangar : ");
        int numeroHangar = scanner.nextInt();

        boolean resultat = entrepot.libererHangar( numeroHangar );

        if ( resultat ) System.out.println( "Le hangar n°" + numeroHangar + " a été libéré" );
        else System.out.println( "Le hangar n°" + numeroHangar + " n'a pas été libéré" );

    }

    private static void ajouterUnVehicule(){

        System.out.print( "Entrez un numéro de société : " );

        int numeroSociete = scanner.nextInt();

        Societe societe = entrepot.getSociete( numeroSociete );

        if ( societe == null ){
            System.out.println( "La société n°" + numeroSociete + " n'existe pas" );
            return;
        }

        System.out.print( "Entrez un numéro de plaque : " );

        String numeroPlaque = scanner.next();

        boolean resultat = societe.ajouterPlaque( numeroPlaque );

        System.out.println();

        if ( resultat ) System.out.println( "L'ajout de la plaque " + numeroPlaque + " a réussi" );
        else System.out.println( "L'ajout de la plaque " + numeroPlaque + " n'a pas réussi" );

    }

    public static void verifierUnVehicule(){

        System.out.print( "Entrez un numéro de société : " );

        int numeroSociete = scanner.nextInt();

        Societe societe = entrepot.getSociete( numeroSociete );

        if ( societe == null ){
            System.out.println( "La société n°" + numeroSociete + " n'existe pas" );
            return;
        }

        System.out.print( "Entrez un numéro de plaque : " );

        String numeroPlaque = scanner.next();

        boolean resultat = societe.verifierPlaque( numeroPlaque );

        System.out.println();

        if ( resultat ) System.out.println( "La plaque " + numeroPlaque + " est autorisée" );
        else System.out.println( "La plaque " + numeroPlaque + " n'est pas autorisée" );

    }

}