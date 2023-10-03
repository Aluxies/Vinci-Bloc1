import java.util.Objects;

public class Article {
	private String reference;
	private String nom;
	private String description;
	private double prixHTVA;
	private double tauxTVA;
	private double reduction;

	private static int nombreArticles = 0;

	final static double TAUX_TVA = 21;

	public Article(String reference, String nom, String description, double prixHTVA, double tauxTVA) {

		setTauxTVA(tauxTVA);
		setDescription(description);
		setPrixHTVA(prixHTVA);

		if ( reference.equals( null ) ) throw new IllegalArgumentException( "Référence ne peut être 'null'" );
		if ( reference.equals( "" ) ) throw new IllegalArgumentException( "Référence ne peut être 'String vide'" );
		this.reference = reference;

		if ( nom.equals( null ) ) throw new IllegalArgumentException( "Nom ne peut être 'null'" );
		if ( nom.equals( "" ) ) throw new IllegalArgumentException( "Nom ne peut être 'String vide'" );

		this.nom = nom;

		nombreArticles++;

	}
	
	public Article(String reference, String nom, String description, double prixHTVA) {

		this(reference,nom,description,prixHTVA,TAUX_TVA);

	}

	public String getReference() {
		return reference;
	}

	public String getNom() {
		return nom;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrixHTVA() {
		return prixHTVA;
	}
	
	public void setPrixHTVA(double prixHTVA) {

		if ( prixHTVA <= 0 ) throw new IllegalArgumentException( "PrixHTVA ne peut être 'nul ou négatif'" );

		this.prixHTVA = prixHTVA;

	}

	public double getTauxTVA() {
		return tauxTVA;
	}

	public void setTauxTVA(double tauxTVA) {

		if ( tauxTVA < 0 || tauxTVA > 100 ) throw new IllegalArgumentException( "TauxTVA ne peut être 'inférieur à 0 ou supérieur à 100'" );

		this.tauxTVA = tauxTVA;

	}

	public double getReduction(){ return reduction; }

	public void setReduction( double reduction ){

		if ( reduction <= 0 || reduction >= 100 ) throw new IllegalArgumentException( "Reduction ne peut être 'inférieur à 1 ou supérieur à 99'" );

		this.reduction = reduction;

	}


	public double calculerPrixTVAComprise() {
		return prixHTVA * (1+ tauxTVA/100);
	}

	public double calculerPrixTVAComprise(int reduction) {
		return calculerPrixTVAComprise() * (1-reduction/100.0);
	}

	public static int getNombreArticles(){ return nombreArticles; }

	public String toString() {
		return "Référence : " + reference + "\nNom : " + nom + " (prix HTVA : " + prixHTVA + ", taux de TVA : " + tauxTVA +"%)";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Article article = (Article) o;
		return reference.equals(article.reference);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
	}
}
