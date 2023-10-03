
public class TestListeSolides {

    public static void main(String[] args) {
        ListeSolides l = new ListeSolides();
        System.out.println("Liste vide : " + l.estVide());
        Cube cube1 = new Cube(8);
        System.out.println("Cube de côté 8 ajouté : " + l.ajouter(cube1));
        System.out.println("Liste contient cube1 : " + l.contient(cube1));
        Sphere sphere = new Sphere(6);
        System.out.println("Sphère de rayon 6 ajoutée : " + l.ajouter(sphere));
        System.out.println("Nombre de solides : " + l.nombreDeSolides());
        Cylindre cylindre = new Cylindre(6,8 );
        System.out.println("Cylindre de rayon 6 et de hauteur 8 ajouté : " + l.ajouter(cylindre));
        System.out.println(l);
        Cube cube2 = new Cube(6);
        System.out.println("Cube de côté 6 ajouté : " + l.ajouter(cube2));
        System.out.println("Nombre de solides : " + l.nombreDeSolides());
        cube1 = new Cube(7);
        System.out.println("Cube de côté 7 ajouté : " + l.ajouter(cube1));
        System.out.println("Nombre de solides : " + l.nombreDeSolides());
        System.out.println("Cube de côté 6 supprimé : " + l.supprimer(cube2));
        System.out.println(l);
        System.out.println("Plus grand(s) solide(s) de la liste : " );
        for (Solide solide : l.trouverPlusGrand()) {
            System.out.println(solide);
        }

        Cube cube3 = new Cube( 20 );
        Sphere sphere2 = new Sphere( 15 );
        Cylindre cylindre2 = new Cylindre( 10, 5 );

        l.ajouter( cube3 );
        l.ajouter( sphere2 );
        l.ajouter( cylindre2 );

        System.out.println();
        System.out.println( "Solides dont le volume est supérieur à 200" );
        System.out.println();

        int superieursA200 = 0;

        for ( Solide solide : l ){

            if ( solide.donnerVolume() > 200 ){

                superieursA200++;
                System.out.println( solide + " -> volume : " + solide.donnerVolume() );

            }

        }

        if ( superieursA200 == 0 ) System.out.println( "Aucun solide n'a un volume supérieur à 200" );

        int comprisEntre100ET500 = 0;

        System.out.println();
        System.out.println( "Solides dont la surface est comprise entre 100 et 500" );
        System.out.println();

        for ( Solide solide : l ){

            if ( solide.donnerSurface() > 100 && solide.donnerSurface() < 500 ){

                comprisEntre100ET500++;
                System.out.println( solide + " -> surface : " + solide.donnerSurface() );

            }

        }

        if ( comprisEntre100ET500 == 0 ) System.out.println( "Aucun solide n'a une surface comprise entre 100 et 500" );


    }

}
