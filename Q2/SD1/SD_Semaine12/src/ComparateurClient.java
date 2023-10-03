import java.util.Comparator;

public class ComparateurClient implements Comparator<ClientEnAttente> {

    @Override
    public int compare(ClientEnAttente o1, ClientEnAttente o2) {

        if ( o1.getPriorite() > o2.getPriorite() ) return -1;
        if ( o1.getPriorite() < o2.getPriorite() ) return 1;
        if ( o1.getNumeroArrivee() > o2.getNumeroArrivee() ) return -1;

        return 1;

    }

}