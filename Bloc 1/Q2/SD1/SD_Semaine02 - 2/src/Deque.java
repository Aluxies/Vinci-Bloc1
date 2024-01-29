public interface Deque<E> {

    /**
     * renvoie le nombre d’elements qui se trouvent dans le Deque
     * @ return nombre d’elements
     */

    public int taille();

    /**
     * verifie si la Deque est vide
     * @return true si la Deque est vide, false sinon
     */

    public boolean estVide();

    /**
     * ajoute un element en tete de la Deque
     * @param element l’element a ajouter
     */

    public void addFirst(E element) throws IllegalArgumentException;

    /**
     * ajoute un element en queue de la Deque
     * @param element l’element a ajouter
     */

    public void addLast(E element) throws IllegalArgumentException;

    /**
     * retire un element en tete de la Deque
     * @return le premier element et le retire
     */

    public E removeFirst() throws FileVideException;



    /**
     * retire un element en queue de la Deque
     * @return le dernier element et le retire
     */

    public E removeLast() throws FileVideException;

}