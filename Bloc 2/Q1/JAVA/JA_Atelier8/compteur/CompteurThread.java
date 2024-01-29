package compteur;

public class CompteurThread extends Thread {

    private final String nom;
    private final int max;
    private static CompteurThread gagnant;

    public CompteurThread(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {

        for ( int i=1; i<=max; i++ ) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                interrupt();
            }

            System.out.println( "Compteur " + nom + " : " + i );

        }

        synchronized ( CompteurThread.class ){
            System.out.println( "Le compteur " + nom + " a fini de compter." );

            if (getGagnant() == null) {
                try {
                    Thread.sleep(10);
                    gagnant = this;
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }


    }

    public static CompteurThread getGagnant() {
        return gagnant;
    }
}
