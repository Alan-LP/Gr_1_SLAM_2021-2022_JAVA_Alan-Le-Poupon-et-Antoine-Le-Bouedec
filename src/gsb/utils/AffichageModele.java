package gsb.utils;

import gsb.modele.Stocker;
import gsb.modele.Visite;

public class AffichageModele {
	
	public static void afficher(Visite uneVisite)
	{
		System.out.println("Visite : " + uneVisite.getReference());
	}
	
	public static void afficher(Stocker unStock)
	{
		System.out.println("STOCK ----- Matricule : " + unStock.getUnVisiteur().getMatricule() + " | Quantité : " + unStock.getQteStock() + " | Dépôt Légal : " + unStock.getUnMedicament().getDepotLegal());
	}
	
}
