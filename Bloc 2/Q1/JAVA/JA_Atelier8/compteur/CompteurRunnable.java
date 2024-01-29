package compteur;

import java.util.concurrent.atomic.AtomicInteger;

public class CompteurRunnable implements Runnable {

    private String nom;
    private int max;
    private static final AtomicInteger positionThread = new AtomicInteger();

    @Override
    public void run() {

        for ( int i=1; i<=max; i++ ) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println( "Compteur " + nom + " : " + i );

        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int position = positionThread.addAndGet(1);

        System.out.println( "Le compteur " + nom + " a terminé à la position " + position );

    }

    public CompteurRunnable(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

}