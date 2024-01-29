public class TestException {

    class Exc1 extends RuntimeException {

    }

    class Exc2 extends Exception {

    }

    public void f1(){

        // Il ne faut ni déclarer ni traîter l'exception car c'est une
        // unchecked exception

        throw new Exc1();

    }

    public void f2() throws Exc2 {

        // Il faut déclarer l'exception avec un "throws Exc2" car c'est une
        // checked exception
        throw new Exc2();

    }
}
