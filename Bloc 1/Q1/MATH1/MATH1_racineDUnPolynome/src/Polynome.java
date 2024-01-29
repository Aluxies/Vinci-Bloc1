public class Polynome {

    private static final int MAX_ETAPE = 1000;
    private double[] coefficients; // Coefficients du polynôme
    private int degre; // Degré du polynôme

    // Crée le polynôme x^degre
    // Lance une IllegalArgumentException si degre est négatif
    public Polynome(int degre) throws IllegalArgumentException {
        if (degre < 0) throw new IllegalArgumentException("Le degré du polynôme doit être positif");
        this.degre = degre;
        this.coefficients = new double[degre + 1];
        for (int i = 0; i < degre; i++) {
            this.coefficients[i] = 0;
        }
        this.coefficients[this.degre] = 1;
    }

    // Crée le polynôme nul
    public Polynome() {
        this.degre = 0;
        this.coefficients = new double[1];
        this.coefficients[0] = 0;
    }

    // Assigne la valeur coefficient au coefficient de degré degre du polynome
    // Lance une IllegalArgumentException si degre est négatif ou supérieur au degré du polynôme
    // Lance une IllegalArgumentException si on tente de changer le degré du polynôme
    public void setCoefficient(int degre, double coefficient) throws IllegalArgumentException {
        if (degre < 0 || degre > this.degre) throw new IllegalArgumentException("degré invalide");
        if (degre == this.degre && coefficient == 0 && this.degre != 0) {
            throw new IllegalArgumentException("Le coefficient de degré dominant ne peut pas être nul");
        }
        this.coefficients[degre] = coefficient;
    }

    // Renvoie la valeur du polynôme évalué en x
    public double evalueEn(double x) {
        double result = this.coefficients[this.degre];
        for (int i = this.degre - 1; i >= 0; i--) {
            result = result * x + this.coefficients[i];
        }
        return result;
    }

    // Renvoie la valeur du degré dominant du polynôme
    public int getDegre() {
        return degre;
    }

    // Renvoie le polynôme dérivé de this
    public Polynome polynomeDerive() {
        try {
            Polynome p = new Polynome(this.degre - 1);
            for (int i = 0; i < this.degre; i++) {
                p.coefficients[i] = (i + 1) * this.coefficients[i + 1];
            }
            return p;
        } catch (IllegalArgumentException e) {
            return new Polynome();
        }
    }

    // renvoie une description du polynôme courant, sous forme de chaine de caractères
    public String toString() {
        if (degre == 0)
            return "" + this.coefficients[0];
        String result = "" + this.coefficients[degre] + " x^" + degre;
        for (int i = degre - 1; i > 0; i--) {
            if (this.coefficients[i] < 0) {
                result += " - " + Math.abs(this.coefficients[i])  ;
                if (i!=1) {
                    result += " x^" + i;
                } else {
                    result += " x" ;
                }
            } else if (this.coefficients[i] > 0) {
                result += " + " + this.coefficients[i] ;
                if (i!=1) {
                    result += " x^" + i;
                } else {
                    result += " x" ;
                }
            }
        }
        if (this.coefficients[0] < 0) {
            result += " - " + Math.abs(this.coefficients[0]);
        } else if (this.coefficients[0] > 0) {
            result += " + " + this.coefficients[0];
        }

        return result;
    }

    // Renvoie une approximation de la racine contenue dans l'intervalle [a ; b] ou [b ; a],
    // obtenue par n intération de la méthode de la bissection
    // Lance une NumeriqueException si la présence d'une racine n'est pas garantie
    public double racineParBissectionNIterations(double a, double b, int n) throws NumeriqueException {

        //1) Permutation des valeurs de a et b

        if ( a > b ) {

            double temp = a;

            a = b;
            b = temp;

        }

        //2) Vérification qu'une des bornes n'est pas la racine

        if ( evalueEn( a ) == 0 ) return a;
        if ( evalueEn( b ) == 0 ) return b;

        //3) Vérification de Bolzano

        if ( evalueEn( a ) * evalueEn( b ) > 0 ) throw new NumeriqueException();

        //4) Approximation initiale

        double racine = ( a + b ) / 2;

        //5) Itération

        for ( int i = 0; i < n; i++ ){

            //a) Vérification que l'approximation n'est pas la racine

            if ( evalueEn( racine ) == 0 ) return racine;

            //b) Mise à jour de l'intervalle [a, b]

            if ( evalueEn( a ) * evalueEn( racine ) > 0 ) a = racine;

            else b = racine;

            //c) Calcul de la nouvelle approximation

            racine = ( a + b ) / 2;

        }

        //6) Renvoie de l'approximation obtenue

        return racine;

    }

    // Renvoie une approximation à minimum d décimales exactes de la racine contenue dans
    // l'intervalle [a ; b] ou [b ; a], obtenue par la méthode de la bissection
    // Lance une NumeriqueException en cas de problème
    public double racineParBissection(double a, double b, int d) throws NumeriqueException {
        // TO DO
        //1) Permutation des valeurs de a et b
        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }

        //2) Vérification qu'une des bornes n'est pas la racine

        if ( evalueEn( a ) == 0 ) return a;
        if ( evalueEn( b ) == 0 ) return b;

        //3) Vérification de Bolzano

        if ( evalueEn( a ) * evalueEn( b ) > 0 ) throw new NumeriqueException();

        //4) Calcul du nombre d'itérations

            // log2(x) = log10(x) / log10(2)
            // Math.ceil() arrondir vers le bas

        double n = Math.ceil( Math.log10( Math.pow( 10, d ) * ( b - a ) ) / Math.log10( 2 ) );

        if ( n > MAX_ETAPE ) throw new NumeriqueException();

        //5) Approximation initiale

        double racine = ( a + b ) / 2;

        //6) Itération

        for ( int i = 0; i < n; i++ ){

            //a) Vérification que l'approximation n'est pas la racine

            if ( evalueEn( racine ) == 0 ) return racine;

            //b) Mise à jour de l'intervalle [a, b]

            if ( evalueEn( a ) * evalueEn( racine ) > 0 ) a = racine;

            else b = racine;

            //c) Calcul de la nouvelle approximation

            racine = ( a + b ) / 2;

        }

        //7) Vérification du nombre de décimales exactes

        if ( ( b - a ) > Math.pow( 10, -d ) ) throw new NumeriqueException();

        else return racine;

    }

    // Renvoie une approximation de la racine contenue dans l'intervalle [a ; b] ou [b ; a],
    // obtenue après n itération de la méthode de Newton
    // Lance une NumeriqueException si la présence d'une et une seule racine n'est pas garantie
    public double racineParNewtonNIterations(double a, double b, int n) throws NumeriqueException {

        //1) Vérification qu'une des bornes n'est pas la racine

        if ( evalueEn( a ) == 0 ) return a;
        if ( evalueEn( b ) == 0 ) return b;

        //2) Vérification de Bolzano

        if ( evalueEn( a ) * evalueEn( b ) > 0 ) throw new NumeriqueException();

        //3) Vérification des hypothèses supplémentaires (f' et f'' de même signe en a et b)

            // derivee1 : f'(x)

        Polynome derivee1 = polynomeDerive();

            // derivee2 : f''(x)

        Polynome derivee2 = derivee1.polynomeDerive();

            // Si f'(a) * f'(b) <= 9

        if ( derivee1.evalueEn( a ) * derivee1.evalueEn( b ) <= 0 ) throw new NumeriqueException();

            // Si f''(a)

        if ( derivee2.evalueEn( a ) * derivee2.evalueEn( b ) <= 0 ) throw new NumeriqueException();

        //4) Approximation initiale

        double racine;

        if ( evalueEn( a ) * derivee2.evalueEn( a ) > 0 ) racine = a;
        else racine = b;

        //5) Itération

        for (int i = 0; i < n; i++) {

            //a) Calcul de la nouvelle approximation

            racine = racine - evalueEn( racine ) / derivee1.evalueEn( racine );

            //b) Vérification que la nouvelle approximation n'est pas la racine

            if ( evalueEn( racine ) == 0 ) return racine;

        }

        //6) Renvoie de la racine

        return racine;
    }

    // Renvoie une approximation à minimum d décimales exactes de la racine contenue dans
    // l'intervalle [a ; b] ou [b ; a], obtenue par la méthode de Newton
    // Lance une NumeriqueException en cas de problème
    public double racineParNewton(double a, double b, int d) throws NumeriqueException {

        //1) Vérification qu'une des bornes n'est pas la racine

        if ( evalueEn( a ) == 0 ) return a;
        if ( evalueEn( b ) == 0 ) return b;

        //2) Vérification de Bolzano

        if ( evalueEn( a ) * evalueEn( b ) > 0 ) throw new NumeriqueException();

        //3) Vérification des hypothèses supplémentaires (f' et f'' de même signe en a et b)

        // derivee1 : f'(x)

        Polynome derivee1 = polynomeDerive();

        // derivee2 : f''(x)

        Polynome derivee2 = derivee1.polynomeDerive();

        // Si f'(a) * f'(b) <= 0

        if ( derivee1.evalueEn( a ) * derivee1.evalueEn( b ) <= 0 ) throw new NumeriqueException();

        // Si f''(a)

        if ( derivee2.evalueEn( a ) * derivee2.evalueEn( b ) <= 0 ) throw new NumeriqueException();

        //4) Approximation initiale et extrémité fixe

        double racine;
        double extremiteFixe;

        if ( evalueEn( a ) * derivee2.evalueEn( a ) > 0 ){

            racine = a;
            extremiteFixe = b;

        } else {

            racine = b;
            extremiteFixe = a;

        }

        //5) Calcul de la précision à obtenir

        double precision = 0.5 * Math.pow( 10, -d ) * Math.abs( derivee1.evalueEn( extremiteFixe ) );

        //6) Itération

        int n = 0;

        while ( n <= MAX_ETAPE && Math.abs( evalueEn(racine) ) > precision ){

            double racinePrecedente = racine;

            //a) Calcul de la nouvelle approximation

            racine -= evalueEn( racine ) / derivee1.evalueEn( racine );

            //b) Vérification que la nouvelle approximation n'est pas la racine

            if ( evalueEn( racine ) == 0 ) return racine;

            //c) Vérification hypothèses supplémentaires : f' et f'' de signe constant

            if ( derivee1.evalueEn( a ) * derivee1.evalueEn( racine ) <= 0 ) throw new NumeriqueException();
            if ( derivee2.evalueEn( a ) * derivee2.evalueEn( racine ) <= 0 ) throw new NumeriqueException();

            //d) Vérification instabilités numériques

            if ( Math.abs( evalueEn( racine ) ) > Math.abs( evalueEn( racinePrecedente ) ) ) throw new NumeriqueException();
            if ( evalueEn( racine ) * evalueEn( racinePrecedente ) < 0 ) throw new NumeriqueException();
            if ( Math.abs( extremiteFixe - racine ) > Math.abs( extremiteFixe - racinePrecedente ) ) throw new NumeriqueException();

            n++;

        }

        //7) Vérification finale de la précision

        if ( Math.abs( evalueEn( racine ) ) > precision ) throw new NumeriqueException();
        else return racine;

    }

}