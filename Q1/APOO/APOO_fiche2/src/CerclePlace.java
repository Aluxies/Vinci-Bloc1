public class CerclePlace {

    private double rayon;
    private Point centre;

    public CerclePlace( double rayon, Point centre ){

        this.rayon = rayon;
        this.centre = centre;

    };

    public double getRayon(){

        return this.rayon;

    };

    public void setRayon( double rayon ){

        this.rayon = rayon;

    };

    public Point getCentre(){

        return this.centre;

    };

    public void setCentre( double x, double y ){

        Point point = new Point( x, y );
        this.centre = point;

    };

    public String toString(){

        return "Cercle de rayon " + this.rayon + " et de centre " + this.centre;

    };

};