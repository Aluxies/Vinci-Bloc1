class Rectangle {

    private double longueur, largeur;

    public Rectangle( double longueur, double largeur ){

        this.longueur = longueur;
        this.largeur = largeur;

    };

    public double getLongueur(){

        return this.longueur;

    }

    public double getLargeur(){

        return this.largeur;

    }

    public double calculerAire(){

        return this.longueur * this.largeur;

    };

    public double calculerPerimetre(){

        return 2 * ( this.longueur + this.largeur );

    };

};