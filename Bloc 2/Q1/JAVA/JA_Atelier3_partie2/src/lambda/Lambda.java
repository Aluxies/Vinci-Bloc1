package lambda;

import domaine.Employe;
import domaine.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda {

    /**
     * Retourne une liste contenant uniquement les Integer qui correspondent
     * au predicat match
     * @param list La liste d'Integer originale
     * @param match le predicat à respecter
     * @return une liste contenant les integer qui respectent match
     */
    public static <E> List<E> allMatches(List<E> list, Predicate<E> match) {

        List<E> newList = new ArrayList<E>();

        list.forEach( e -> {

            if ( match.test( e ) ) newList.add( e );

        });

        return newList;
    }

    /**
     * Retourne une liste contenant tous les éléments de la liste originale, transformés
     * par la fonction transform
     * @param list La liste d'Integer originale
     * @param transform la fonction à appliquer aux éléments
     * @return une liste contenant les integer transformés par transform
     */
    public static <T,E> List<T> transformAll(List<E> list, Function<E, T> transform) {

        List<T> newList = new ArrayList<T>();

        list.forEach( e -> {

            T transformedGeneric = transform.apply( e );

            newList.add( transformedGeneric );

        });

        return newList;
    }

    public static <E> List<E> filter(List<E> list, Predicate<E> match) {

        return list.stream().filter(match).toList();
    }

    public static <T,E> List<T> map(List<E> list, Function<E, T> transform) {

        return list.stream().map(transform).toList();
    }
}
