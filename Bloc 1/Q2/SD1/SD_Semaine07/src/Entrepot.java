import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author ARNAUD ALEXIS
 *
 *
 */

public class Entrepot implements Iterable<Hangar> {

	private Hangar[] lesHangars;
	private int nombreHangarsLibres;

	private HashMap<Integer, Societe> societesPresentes;

	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre d'hangars
	 * @throws IllegalArgumentException si le nombre d'hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if(nombreHangars<=0)
			throw new IllegalArgumentException();

		lesHangars = new Hangar[nombreHangars];

		for (int i = 0; i < nombreHangars; i++){

			lesHangars[i] = new Hangar( i );

		}

		societesPresentes = new HashMap<Integer, Societe>();

		nombreHangarsLibres = nombreHangars;

	}

	/**
	 * renvoie le nombre d'hangars libres
	 * @return le nombre d'hangars libres
	 */
	public int nombreHangarsLibres(){

		return nombreHangarsLibres;

	}


	/**
	 * renvoie le nombre de societes presentes
	 * @return le nombre de societes presentes
	 */
	public int nombreSocietesPresentes(){

		return societesPresentes.size();

	}

	/**
	 * renvoie la societe dont le numero est passe en parametre
	 * @param numeroSociete le numero de la societe
	 * @return la societe recherchee ou null si aucune societe presente ne possede ce numero
	 */
	public Societe getSociete(int numeroSociete){

		return societesPresentes.get( numeroSociete );

	}

	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire :
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * @param numeroSociete le numero de la societe
	 * @param nomSociete le nom de la societe
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 */
	public int attribuerHangar(int numeroSociete, String nomSociete){

		if ( nombreHangarsLibres == 0 ) return -1;

		Societe societe;

		if ( getSociete( numeroSociete ) == null ){

			societe = new Societe( numeroSociete, nomSociete );

			societesPresentes.put( numeroSociete, societe );

		} else {

			societe = societesPresentes.get( numeroSociete );

		}

		int numeroHangar = numeroSociete % lesHangars.length;

		while ( lesHangars[numeroHangar].getSociete() != null ){

			numeroHangar++;

			if ( numeroHangar == lesHangars.length ) numeroHangar = 0;

		}

		lesHangars[numeroHangar].setSociete( societe );

		societe.ajouterHangar( numeroHangar );

		nombreHangarsLibres--;

		return numeroHangar;

	}

	/**
	 * @param numeroHangar
	 * libère un hangar déjà occupé
	 * @return true ou false
	 */
	public boolean libererHangar( int numeroHangar ){

		Hangar hangar = lesHangars[numeroHangar];

		if ( societesPresentes.size() == 0 ) return false;
		if ( hangar.getSociete() == null ) return false;

		Societe societe = hangar.getSociete();

		societesPresentes.remove( societe );

		hangar.setSociete( null );

		nombreHangarsLibres++;

		return true;

	}

	@Override
	public Iterator<Hangar> iterator() {
		return Arrays.stream(lesHangars).iterator();
	}

}
