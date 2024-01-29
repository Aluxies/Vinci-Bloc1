
public class Hangar {
	
	private int numeroHangar;
	private Societe societe;
	
	public Hangar(int numeroHangar) {
		this.numeroHangar = numeroHangar;
	}

	public int getNumeroHangar() {
		return numeroHangar;
	}
	
	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	@Override
	public String toString() {

		String nomSociete;

		if ( societe == null ) nomSociete = "null";
		else nomSociete = societe.getNom();

		return "Hangar :\nNumeroHangar : " + numeroHangar + "\nSociete : " + nomSociete + "\n" ;
	}	
	
}
