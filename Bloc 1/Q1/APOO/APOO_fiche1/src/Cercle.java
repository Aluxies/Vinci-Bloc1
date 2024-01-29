class Cercle{

    private double rayon;

    public Cercle( double rayon ){

        this.rayon = rayon;

    };

    public double calculerAire(){

        return this.rayon * this.rayon * Math.PI;

    };

    public String toString(){

        return "Cercle de rayon " + this.rayon;

    };

};


