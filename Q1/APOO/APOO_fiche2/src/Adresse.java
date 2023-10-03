class Adresse {

    private String rue, numero, codePostal, ville;

    public Adresse( String rue, String numero, String codePostal, String ville ){

        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
        this.ville = ville;

    };

    public String getRue(){

        return this.rue;

    };

    public String getNumero(){

        return this.numero;

    };

    public String getCodePostal(){

        return this.codePostal;

    };

    public String getVille(){

        return this.ville;

    };

    public String toString(){

        return "";

    };

};
