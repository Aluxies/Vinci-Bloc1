import java.util.HashSet;

public class RallyeAutomobile {

    private ListeSDImpl<String> pilotesEnCourse;
    private ListeSDImpl<String> pilotesQualifies;
    private HashSet<String> pilotesDisqualifies;
    public RallyeAutomobile( String[] pilotes ){

        if ( pilotes == null ) throw new IllegalArgumentException();
        if ( pilotes.length == 0 ) throw new IllegalArgumentException();

        pilotesDisqualifies = new HashSet<String>();

        for ( String string : pilotes ){

            if ( string == null ) throw new IllegalArgumentException();

        }

        pilotesEnCourse = new ListeSDImpl<String>();
        pilotesQualifies = new ListeSDImpl<String>();

        for (int i = 0; i < pilotes.length; i++) {

            pilotesEnCourse.insererEnQueue( pilotes[i] );

        }

    }

    public int nombrePilotesEnCourse(){

        return pilotesEnCourse.taille();

    }

    public String donnerPiloteEnTete(){

        return pilotesEnCourse.premier();

    }

    public boolean disqualifierPilote( String pilote ){

        if ( pilote == null ) throw new IllegalArgumentException();

        if ( nombrePilotesEnCourse() == 0 ) return false;

        if ( !pilotesEnCourse.contient( pilote ) ) return false;

        pilotesDisqualifies.add( pilote );

        return pilotesEnCourse.supprimer( pilote );

    }

    public boolean effectuerDepassement(String pilote){

        if ( pilote == null ) throw new IllegalArgumentException();

        if ( nombrePilotesEnCourse() == 0 || nombrePilotesEnCourse() == 1 ) return false;

        if ( !pilotesEnCourse.contient( pilote ) ) return false;

        if ( donnerPiloteEnTete().equals( pilote ) ) return false;

        String piloteSuivant = pilotesEnCourse.donnerPrecedent( pilote );

        pilotesEnCourse.permuter( pilote, piloteSuivant );

        return true;

    }

    public String afficherCourse(){

        return pilotesEnCourse.toString();

    }

    public int donnerPositionPilote( String pilote ){

        if ( pilote == null ) throw new IllegalArgumentException();

        if ( !pilotesEnCourse.contient( pilote ) ) return -1;

        int position = 1;

        for ( String p : pilotesEnCourse ){

            if ( p.equals( pilote ) ) break;

            position++;

        }

        return position;

    }

    public boolean franchirLigneDArrivee(){

        if ( nombrePilotesEnCourse() == 0 ) return false;

        pilotesQualifies.insererEnQueue( pilotesEnCourse.premier() );

        pilotesEnCourse.supprimer( pilotesEnCourse.premier() );

        return true;

    }

    public boolean remettreEnCourse( String pilote, String pilotePrecedant ){

        if ( pilote == null ) throw new IllegalArgumentException();
        if ( pilotePrecedant == null ) throw new IllegalArgumentException();

        if ( pilotesEnCourse.contient( pilote ) ) return false;
        if ( !pilotesDisqualifies.contains( pilote ) ) return false;
        if ( !pilotesEnCourse.contient( pilotePrecedant ) ) return false;

        pilotesEnCourse.insererApres( pilotePrecedant, pilote );

        pilotesDisqualifies.remove( pilote );

        return true;

    }

    public String afficherPilotesHorsCourse(){

        return pilotesDisqualifies.toString();

    }

    public String afficherClassement(){

        return pilotesQualifies.toString();

    }

}