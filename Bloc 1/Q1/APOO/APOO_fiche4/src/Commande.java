import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
public class Commande {
	private ArrayList<LigneDeCommande> ligneDeCommandes;
	private LocalDate date;

	public Commande(){

		this.ligneDeCommandes = new ArrayList<LigneDeCommande>();
		this.date = LocalDate.now();

	}

	public LocalDate getDate(){

		return this.date;

	}

	public void ajouter( Article article ){

		ajouter(article, 1);

	}

	public void ajouter( Article article, int quantite ){

		ligneDeCommandes.add( new LigneDeCommande(article, quantite) );

	}

	public void retirer( Article article ){

		for ( LigneDeCommande ligneDeCommande : ligneDeCommandes ){

			if ( ligneDeCommande.getArticle().equals( article ) ){
				ligneDeCommandes.remove( ligneDeCommande );
			}

		}

	}

	public boolean dejaCommande( Article article ){

		for ( LigneDeCommande ligneDeCommande : ligneDeCommandes ){

			if ( ligneDeCommande.getArticle().equals( article ) ) return true;

		}

		return false;

	}

	public boolean modifierQuantite( Article article, int quantite ){

		if ( quantite < 1 ) return false;

		for ( LigneDeCommande ligneDeCommande : ligneDeCommandes ){

			if ( ligneDeCommande.getArticle().equals( article ) ){

				ligneDeCommande.setQuantite( quantite );
				return true;

			}

		}

		return false;

	}

	public int trouverQuantite( Article article ){

		for ( LigneDeCommande ligneDeCommande : ligneDeCommandes ){

			if ( ligneDeCommande.getArticle().equals( article ) ){

				return ligneDeCommande.getQuantite();

			}

		}

		return 0;

	}

	public ArrayList<LigneDeCommande> getLigneDeCommandes() {

		return ligneDeCommandes;

	}

	public double calculerPrixTotal(){

		double prixTotal = 0;

		for ( LigneDeCommande ligneDeCommande : ligneDeCommandes ){

			prixTotal += ligneDeCommande.calculerPrixTotalTVAComprise();

		}

		return prixTotal;

	}
	
	public String toString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		String infosCommande = date.format(formatter)+" \n";

		for ( LigneDeCommande ligneDeCommande : ligneDeCommandes ){

			infosCommande += ligneDeCommande + "\n";

		}

		return infosCommande + "\nPrix total : " + calculerPrixTotal();
	}
}
