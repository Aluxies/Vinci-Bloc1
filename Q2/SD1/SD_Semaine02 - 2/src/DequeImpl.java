public class DequeImpl <E> implements Deque<E>{

    private int taille;
    private int indiceTete;
    private int indiceQueue;

    private Object[] table;
    public DequeImpl(){

        table = new Object[10];
        indiceTete = 0;
        indiceQueue = 0;
        taille = 0;

    };

    public int taille() {

        return taille;

    };

    public boolean estVide() {

        return taille == 0;

    };

    public void addFirst(E element) throws IllegalArgumentException {

        if ( element == null ) throw new IllegalArgumentException();

        int indice;

        if ( table[indiceTete] == null ){

            indice = indiceTete;

        } else {

            indice = indiceTete-1;


        };

        table[indice] = element;

        indiceQueue++;

        if ( indiceTete-1 < 0 ){

            indiceTete = table.length;

        };

        indiceTete--;

        taille++;

    };

    public void addLast(E element) throws IllegalArgumentException {

        if ( element == null ) throw new IllegalArgumentException();

        int indice;

        if ( table[indiceQueue] == null ){

            indice = indiceQueue;

        } else {

            if ( indiceQueue == table.length-1 ){

                indiceQueue = 0;

            };

            indice = indiceQueue-1;


        };

        table[indice] = element;
        indiceQueue++;

        taille++;

    };

    public E removeFirst() throws FileVideException {

        if ( estVide() ) throw new FileVideException();

        if ( indiceTete+1 == table.length ) indiceTete = -1;

        int indice = indiceTete + 1;

        E tete = (E) table[indice];

        table[indice] = null;
        indiceTete++;

        taille--;

        return tete;

    };

    public E removeLast() throws FileVideException {

        if ( estVide() ) throw new FileVideException();

        if ( indiceQueue-1 == -1 ) indiceTete = table.length;

        int indice = indiceQueue - 1;

        E queue = (E) table[indice];

        table[indice] = null;
        indiceQueue--;

        taille--;

        return queue;

    };

    public String toString(){

        String string = "";

        for ( Object objet : table ) {

            if ( objet != null ) string += " " + objet;

        };

        return string;

    }

};