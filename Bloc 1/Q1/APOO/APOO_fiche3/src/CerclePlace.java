
public class CerclePlace {
	private double rayon;
	private Point centre;
	
	public CerclePlace( double rayon, Point centre ) {

		this.rayon = rayon;
		this.centre = centre;

	}

	public CerclePlace( Point centre ){

		this( 1, centre );

	}

	public CerclePlace(){

		this( 1, new Point( 0, 0 ) );

	}

	public double getRayon() {

		return this.rayon;

	}

	public void setRayon(double rayon) {

		this.rayon = rayon;

	}

	public Point getCentre() {

		return this.centre;

	}

	public void setCentre(Point centre) {

		this.centre = centre;

	}
	
	public String toString() {

		return "Cercle de rayon : " + this.rayon + " de centre " + this.centre;

	}
	

}
