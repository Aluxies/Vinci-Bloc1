public class Carte {

    private int cout;

    public Carte( int cout ){

        this.cout = cout;

    }

    public int getCout(){

        return cout;

    }

    public String fournirDetail(){

        return "coût : " + cout;

    }


}
