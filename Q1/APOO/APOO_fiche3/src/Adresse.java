public class Adresse {

    private String ville;
    private String codePostal;
    private String rue;
    private String numero;

    public Adresse(String ville, String codePostal, String rue, String numero) {

        this.ville = ville;
        this.codePostal = codePostal;
        this.rue = rue;
        this.numero = numero;

    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getRue() {
        return rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return codePostal + " " + ville + ", " +
                rue + " " + numero;
    }
}
