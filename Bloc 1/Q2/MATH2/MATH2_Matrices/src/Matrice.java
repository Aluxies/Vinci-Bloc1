import java.util.ArrayList;
import java.util.Arrays;

public class Matrice {
    private final int nbLignes;              // nombre de lignes
    private final int nbColonnes;            // nombre de colonnes
    private final double[][] data;           // matrice (nbLignes,nbColonnes)

    // ce constructeur cree la matrice nulle de genre (a,b)
    public Matrice(int a, int b) throws IllegalArgumentException {

        if ( a <= 0 || b <= 0 ) throw new IllegalArgumentException();

        //TODO : Les lignes ci-dessous sont là uniquement pour qu'il n'y ait pas d'erreur. Elles doivent être modifiées

        nbLignes=a ;
        nbColonnes=b ;
        data = new double[a][b];

    }

    //  Ce constructeur permet de construire la matrice correspondant 
    //  au tableau en parametre. 
    public Matrice(double[][] tab) throws IllegalArgumentException {

        // On vérifie si le tableau entier n'est pas null ou s'il n'est pas vide

        if ( tab == null || tab.length == 0 ) throw new IllegalArgumentException();

        // On vérifie pour chaque ligne du tableau si elles ne sont pas null ou vides

        for ( double[] ligne : tab) {

            if ( ligne == null ) throw new IllegalArgumentException();
            if ( ligne.length == 0 ) throw new IllegalArgumentException();
            if ( ligne.length != tab[0].length ) throw new IllegalArgumentException();

        }

        nbLignes = tab.length;
        nbColonnes = tab[0].length;
        data = new double[nbLignes][nbColonnes];

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                data[i][j] = tab[i][j];

            }

        }

    }

    // constructeur par recopie
    public Matrice(Matrice a)  throws IllegalArgumentException {

        if ( a == null ) throw new IllegalArgumentException();

        nbLignes = a.nbLignes;
        nbColonnes = a.nbColonnes;
        data = new double[nbLignes][nbColonnes];

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                data[i][j] = a.data[i][j];

            }

        }
    }

    // cree la matrice identite d'ordre a
    public static Matrice identite(int a)  throws IllegalArgumentException {

        if ( a <= 0 ) throw new IllegalArgumentException();

        Matrice matrice = new Matrice( new double[a][a] );

        for (int i = 0; i < a; i++) {

            matrice.data[i][i] = 1.0;

        }

        return matrice;

    }

    //Cette methode renvoie l'element de la ligne numLigne et de la
    //colonne numColonne de la matrice. Si cet element n'existe pas, la
    //methode lance une IllegalArgumentException
    public double getElement(int numLigne, int numColonne)
            throws IllegalArgumentException {

        if ( numLigne <= 0 || numColonne <= 0 ) throw new IllegalArgumentException();
        if ( numLigne > nbLignes || numColonne > nbColonnes ) throw new IllegalArgumentException();

        return data[numLigne-1][numColonne-1];

    }

    // ajoute b a la matrice courante si c'est possible
    public Matrice somme(Matrice b)  throws IllegalArgumentException {

        if ( b == null ) throw new IllegalArgumentException();

        if ( nbLignes != b.nbLignes || nbColonnes != b.nbColonnes ) throw new IllegalArgumentException();

        Matrice somme = new Matrice( nbLignes, nbColonnes );

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                somme.data[i][j] = b.data[i][j] + data[i][j];

            }

        }

        return somme;
    }

    // calcule le produit scalaire.this de la matrice courante avec scalaire
    public Matrice produitParScalaire(double scalaire){

        Matrice produit = new Matrice( nbLignes, nbColonnes );

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                produit.data[i][j] = data[i][j] * scalaire;

            }

        }

        return produit;

    }

    // calcule le produit this*b de la matrice courante avec b si possible
    public Matrice produitAGauche(Matrice b)  throws IllegalArgumentException {

        if (b == null ) throw new IllegalArgumentException();

        if ( nbColonnes != b.nbLignes ) throw new IllegalArgumentException();

        Matrice produit = new Matrice( nbLignes, b.nbColonnes );

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < b.nbColonnes; j++) {

                for (int k = 0; k < b.nbLignes; k++) {

                    produit.data[i][j] = produit.data[i][j] + data[i][k] * b.data[k][j];

                }

            }

        }

        return produit;

    }

    // calcule le produit b*this de b avec la matrice courante si possible
    public Matrice produitADroite(Matrice b)  throws IllegalArgumentException {

        if ( b == null ) throw new IllegalArgumentException();

        return b.produitAGauche( this );
    }

    // renvoie true si la matrice courante est carrée
    public boolean carree(){

        return nbLignes == nbColonnes;

    }
    
    // Calcule this^n. Lance une Mathexception si this n'est pas carrée
    public Matrice puissance(int n) throws  IllegalArgumentException {

        if ( !carree() ) throw new MathException();

        if ( n < 0 ) throw new IllegalArgumentException();

        if ( n == 0 ) return identite( nbLignes );

        Matrice matrice = new Matrice( this.data );

        for (int i = 1; i < n; i++) {

            matrice = matrice.produitAGauche( this );

        }

        return matrice;
    }

    //Calcule this^T : la tranposée de this
    public Matrice transposee() {

        Matrice transposee = new Matrice( nbColonnes, nbLignes );

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                transposee.data[j][i] = data[i][j];

            }

        }

        return transposee;

    }

    // affiche la matrice en format standard
    public String toString(){

        String string = "";

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                string += data[i][j] + "\t";

            }

            string += "\n";


        }

        return string;
    }

    public Matrice pageRank() {

        // On vérifie si la matrice est bien carrée

        if ( !carree() ) throw new MathException();

        // On vérifie si la matrice ne contient que des 0 et des 1

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                if ( data[i][j] != 0 && data[i][j] != 1 ) throw new MathException();
                
            }
            
        }

        // On crée la matrice de transition

        Matrice transition = new Matrice( nbLignes, nbColonnes );

        // On la fait correspondre à la matrice du réseau

        // 1 - On fait la somme des colonnes

        double[] somme = new double[nbLignes];

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                somme[j] += data[i][j];

            }

        }

        // 2 - On rend la matrice colonne-stochastique

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                if ( somme[j] == 0 ) transition.data[i][j] = 1 / nbLignes;

                else if ( data[i][j] == 1 ) transition.data[i][j] = data[i][j] / somme[j];

            }

        }

        Matrice google = new Matrice( nbLignes, nbColonnes );

        double alpha = 0.85;

        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                google.data[i][j] = alpha * transition.data[i][j] + ( 1 - alpha ) * (1.0 / nbLignes);

            }

        }

        System.out.println( transition );

        Matrice distribution = new Matrice( nbLignes, 1 );

        for (int i = 0; i < 85; i++) {

            

        }


        // A FAIRE QUAND MARKOV AURA ÉTÉ VU

        return transition;

    }
  }