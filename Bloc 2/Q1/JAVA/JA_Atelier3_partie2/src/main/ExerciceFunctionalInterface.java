package main;

import domaine.Employe;
import domaine.Genre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class ExerciceFunctionalInterface {
    public static List<Employe> employes;
    public static void main(String[] args) {
        employes = new ArrayList<>();

        employes.add(new Employe(Genre.HOMME, 185, "Bob"));
        employes.add(new Employe(Genre.FEMME, 225, "Alice"));
        employes.add(new Employe(Genre.HOMME, 155, "John"));
        employes.add(new Employe(Genre.FEMME, 165, "Carole"));
        employes.add(new Employe(Genre.HOMME, 185, "Alex"));
        employes.add(new Employe(Genre.HOMME, 185, "Bart"));

        exMap();

        exComparator();

        exForEach();

    }

    /**
     * Replacer l'instatiation de la classe EmployeComparator par un lambda
     */
    private static void exComparator() {
        employes.sort(Comparator.comparingInt(Employe::getTaille));
        System.out.println("Employés triés:");
        System.out.println(employes);


    }

    /**
     * Trouver le type du paramètre de la méthode map.
     * Ensuite créer une classe implémentant la functional interface correspondante pour
     * remplacer le lambda en paramètre par une instance de celle-ci.
     */
    private static void exMap() {

        class PredicatGenreHomme implements Predicate<Employe> {
            @Override
            public boolean test(Employe e) {
                return e.getGenre() == Genre.HOMME;
            }
        }

        class FunctionNom implements Function<Employe, String> {

            @Override
            public String apply(Employe employe) {
                return employe.getNom();
            }

        }

        Stream<String> listeNom = employes.stream()
                .filter(new PredicatGenreHomme())
                .sorted(Comparator.comparingInt(Employe::getTaille)
                        .reversed())
                .map(new FunctionNom());
        listeNom.forEach(System.out::println);

    }


    /**
     * Trouver le type du paramètre de la méthode foreach.
     * Ensuite créer une classe implémentant la functional interface correspondante pour
     * remplacer le lambda en paramètre par une instance de celle-ci.
     */
    private static void exForEach(){

        class ConsumerEmploye implements Consumer<Employe> {

            @Override
            public void accept(Employe employe) {
                System.out.println( employe );
            }
        }
        employes.forEach(new ConsumerEmploye());


    }
}
