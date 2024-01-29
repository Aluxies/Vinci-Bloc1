import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class Pizza implements Iterable<Ingredient> {

    private final static double PRIX_BASE = 5;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre, String description) {

        if ( titre == null ) throw new IllegalArgumentException( "L'objet 'titre' ne peut être 'null'." );
        if ( titre.equals( " " ) ) throw new IllegalArgumentException( "L'objet 'titre' ne peut être égal à 'blanc'." );
        if ( description == null ) throw new IllegalArgumentException( "L'objet 'description' ne peut être 'null'." );
        if ( description.equals( " " ) ) throw new IllegalArgumentException( "L'objet 'description' ne peut être égal à 'blanc'." );

        this.titre = titre;
        this.description = description;
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {

        this( titre, description );

        if ( ingredients.isEmpty() ) throw new IllegalArgumentException( "Il est impossible d'indiquer une liste sans ingrédients." );

        for ( Ingredient ingredient : ingredients ) {

            if ( this.ingredients.contains( ingredient ) ) {

                throw new IllegalArgumentException( "Il ne peut y avoir deux fois le même ingrédient dans une pizza" );

            }

            this.ingredients.add( ingredient );

        }

    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter ( Ingredient ingredient ) {

        if ( ingredient == null ) return false;

        if ( !ingredients.contains( ingredient ) ){

            ingredients.add( ingredient );
            return true;

        }

        return false;

    }

    public boolean supprimer ( Ingredient ingredient ) {

        return ingredients.remove( ingredient );

    }

    public double calculerPrix () {

        double prix = this.PRIX_BASE;

        for ( Ingredient ingredient : ingredients ) {

            prix += ingredient.getPrix();

        }

        return prix;

    }

    public Iterator<Ingredient> iterator () {

        return this.iterator();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza that = (Pizza) o;
        return Objects.equals(titre, that.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }

}