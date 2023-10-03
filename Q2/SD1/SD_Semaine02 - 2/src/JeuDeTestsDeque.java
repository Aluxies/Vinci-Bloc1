public class JeuDeTestsDeque {

    /**
     * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
     *
     * @param messageErreur message a afficher en cas de probleme
     * @param attendu la valeur qu'on s'attendait a recevoir
     * @param recu la valeur qu'on a recu en realite
     */
    private static void assertEquals(String messageErreur, Object attendu, Object recu) {
        if (attendu==null) {
            if (recu!=null) {
                System.out.println(messageErreur+". Attendu="+attendu+" recu="+recu);
                System.exit(0);
            }
        } else if (!attendu.equals(recu)) {
            System.out.println(messageErreur+". Attendu="+attendu+" recu="+recu);

        }
    }

    public static void main(String[] args) {

        DequeImpl<Character> file = new DequeImpl<Character>();


        // test 1
        file.addFirst('a');
        assertEquals("test 1 ko", 1, file.taille());
        assertEquals("test 1 ko", " a", file.toString());

        //test 2
        file.addLast('b');
        assertEquals("test 2 ko", 2, file.taille());
        assertEquals("test 2 ko", " a b", file.toString());

        char p;

        //test 3
        p=file.removeFirst();
        assertEquals("test 3 ko", 'a', p);
        assertEquals("test 3 ko", 1, file.taille());
        assertEquals("test 3 ko", " b", file.toString());

        //test 4
        p=file.removeLast();
        assertEquals("test 4 ko", 'b', p);
        assertEquals("test 4 ko", 0, file.taille());
        assertEquals("test 4 ko", "", file.toString());

        //test 6
        try{
            file.removeFirst();
            System.out.println("test 6 ko");
            return;
        }catch (FileVideException ex){
        }

        //test 7
        try{
            file.removeLast();
            System.out.println("test 7 ko");
            return;
        }catch (FileVideException ex){
        }

        //test 8
        file.addFirst('c');
        assertEquals("test 8 ko", 1, file.taille());
        assertEquals("test 8 ko", " c", file.toString());

        System.out.println("tous les tests ont reussi!");
    }

}
