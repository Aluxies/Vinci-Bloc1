import java.util.Comparator;

public class ComparateurViaNom implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant etudiant1, Etudiant etudiant2) {

        if ( etudiant1 == etudiant2 ) return etudiant1.getPrenom().compareTo(etudiant2.getPrenom());
        else return etudiant1.getNom().compareTo(etudiant2.getNom());

    }

}