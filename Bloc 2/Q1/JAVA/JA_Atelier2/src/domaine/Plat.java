package domaine;

import java.time.Duration;
import java.util.*;
public class Plat {

    public enum Type {

        ENTREE ( "Entrée" ), PLAT ( "Plat" ), DESSERT ( "Dessert" );

        private String nom;

        Type( String nom ) {

            this.nom = nom;

        }

        public String getNom() { return this.nom; }

        @Override
        public String toString() {

            return getNom();

        }

    }

    public enum Difficulte {

        X, XX, XXX, XXXX, XXXXX;

        @Override
        public String toString() {

            return super.toString().replace( "X", "*" );

        }

    }

    public enum Cout {

        $, $$, $$$, $$$$, $$$$$;

        @Override
        public String toString() {

            return super.toString().replace( "$", "€" );

        }

    }

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    private List<Instruction> recette;
    private Set<IngredientQuantifie> ingredients;
    private Type type;

    public Plat ( String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout, Type type ) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ZERO;
        this.ingredients = new HashSet<IngredientQuantifie>();
        this.recette = new ArrayList<Instruction>();
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public Type getType() { return type; };
    public void insererInstruction ( int position, Instruction instruction ) {

        util.Util.checkStrictlyPositive( position );

        if ( position > recette.size() ) throw new IllegalArgumentException( "La position ne peut être supérieure au nombre d'instructions." );

        util.Util.checkObject( instruction );

        this.dureeEnMinutes.plusMinutes( instruction.getDureeEnMinutes().toMinutes() );

        recette.add( position-1, instruction );

    }

    public void ajouterInstruction ( Instruction instruction ) {

        util.Util.checkObject( instruction );

        dureeEnMinutes = dureeEnMinutes.plusMinutes( instruction.getDureeEnMinutes().toMinutes() );

        recette.add( instruction );

    }

    public Instruction remplacerInstruction ( int position, Instruction instruction ) {

        util.Util.checkStrictlyPositive( position );

        if ( position > recette.size() ) throw new IllegalArgumentException( "La position ne peut être supérieure au nombre d'instructions." );

        util.Util.checkObject( instruction );

        Instruction instructionRemplacee = recette.get( position - 1 );

        dureeEnMinutes = dureeEnMinutes.minusMinutes( instructionRemplacee.getDureeEnMinutes().toMinutes() );
        dureeEnMinutes = dureeEnMinutes.plusMinutes( instruction.getDureeEnMinutes().toMinutes() );

        return recette.set( position - 1, instruction );

    }

    public Instruction supprimerInstruction ( int position ) {

        util.Util.checkStrictlyPositive( position );

        if ( position > recette.size() ) throw new IllegalArgumentException( "La position ne peut être supérieure au nombre d'instructions." );

        Instruction instructionSupprimee = recette.remove( position - 1 );

        dureeEnMinutes = dureeEnMinutes.minusMinutes( instructionSupprimee.getDureeEnMinutes().toMinutes() );

        return instructionSupprimee;

    }

    public boolean ajouterIngredient ( Ingredient ingredient, int quantite, Unite unite ) {

        util.Util.checkObject( ingredient );
        util.Util.checkStrictlyPositive( quantite );
        util.Util.checkObject( unite );

        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie( ingredient, quantite, unite );

        return ingredients.add( ingredientQuantifie );

    }

    public boolean ajouterIngredient ( Ingredient ingredient, int quantite ) {

        util.Util.checkObject( ingredient );
        util.Util.checkStrictlyPositive( quantite );

        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie( ingredient, quantite, Unite.NEANT );

        return ingredients.add( ingredientQuantifie );

    }

    public boolean modifierIngredient ( Ingredient ingredient, int quantite, Unite unite ) {

        util.Util.checkObject( ingredient );
        util.Util.checkStrictlyPositive( quantite );
        util.Util.checkObject( unite );

        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie( ingredient, quantite, Unite.NEANT );

        if ( !ingredients.contains( ingredientQuantifie ) ) return false;

        Object[] listObjects = ingredients.toArray();

        for ( int i=0; i<listObjects.length; i++ ) {

            if ( listObjects[i].equals( ingredientQuantifie ) ) {

                listObjects[i] = ingredientQuantifie;
                return true;

            }

        }

        return false;

    }

    public boolean supprimerIngredient( Ingredient ingredient ) {

        util.Util.checkObject( ingredient );

        for ( IngredientQuantifie ingredientQuantifie : ingredients ) {

            if ( ingredientQuantifie.getIngredient().equals( ingredient ) ) {

                return ingredients.remove( ingredientQuantifie );

            }

        }

        return false;

    }

    public IngredientQuantifie trouverIngredientQuantifie ( Ingredient ingredient ) {

        util.Util.checkObject( ingredient );

        for ( IngredientQuantifie ingredientQuantifie : ingredients ) {

            if ( ingredientQuantifie.getIngredient().equals( ingredient ) ) {

                return ingredientQuantifie;

            }

        }

        return null;
    }

    public Iterator<Instruction> instructions() {

        return Collections.unmodifiableList( recette ).iterator();

    }

    @Override
    public String toString() {

        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }

}