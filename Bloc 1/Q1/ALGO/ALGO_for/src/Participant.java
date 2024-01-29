public class Participant {

    private int numero;
    private int resultat;

    public Participant( int newNumero, int newResultat ){

        numero = newNumero;
        resultat = newResultat;

    };

    public int getNumero(){

        return numero;

    };

    public int getResultat(){

        return resultat;

    };

    public void setNumero( int numero ){

        this.numero = numero;

    };

    public void setResultat(int resultat){

        this.resultat = resultat;

    };

};