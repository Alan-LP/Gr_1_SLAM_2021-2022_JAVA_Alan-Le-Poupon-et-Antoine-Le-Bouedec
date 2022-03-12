package gsb.service;

import java.util.ArrayList;
import java.util.TreeMap;

import gsb.modele.Medicament;
import gsb.modele.Stocker;
import gsb.modele.Visiteur;
import gsb.modele.dao.StockerDao;
import gsb.utils.ServiceUtils;

public class StockerService {

	public static ArrayList<Stocker> rechercherLesStocks(String matricule)
	{
		ArrayList<Stocker> lesStock =  null;
		try {
			if(matricule == null || matricule == "")
				throw new Exception("Donnée obligatoire : code");
			if(!ServiceUtils.isAMatricule(matricule))
				throw new Exception("Le matricule ne respecte pas le format A00...");
			lesStock = StockerDao.rechercherLesStocks(matricule);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return lesStock;
	}
	
	public static TreeMap<String, Stocker> getStocksMedicament(String depotLegal)
	{
		TreeMap<String, Stocker> lesStock = null;
		try
		{
			if(depotLegal == null || depotLegal == "")
				throw new Exception("Donnée obligatoire : depotLegal");
			if(!ServiceUtils.isADepotLegal(depotLegal))
				throw new Exception("Le dépot légal ne respecte pas le format XXXXXXX...");
			lesStock = StockerDao.getStocksMedicament(depotLegal);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return lesStock;
	}
	
	public static int getStock(String matricule, String depotLegal)
	{
		Visiteur unVisiteur = VisiteurService.rechercherVisiteur(matricule);
		Medicament unMedicament = MedicamentService.rechercher(depotLegal);
		int stock = 0;
		try {
			if(unVisiteur == null || unMedicament == null)
				throw new Exception("Le visiteur et le médicament ne peuvent pas être null.");
			stock = StockerDao.getStock(unVisiteur, unMedicament);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return stock;
	}
	
	public static boolean retirer(Stocker unStock)
	{
		boolean success = false;
		
		try {
			if(unStock.getQteStock() == 0 || unStock.getUnVisiteur().getMatricule() == null || unStock.getUnMedicament().getDepotLegal() == null)
				throw new Exception("Données obligatoire : quantite, visiteur, medicament");
			if(!ServiceUtils.isAMatricule(unStock.getUnVisiteur().getMatricule()))
				throw new Exception("Le matricule ne respecte pas le format A00...");
			if(!ServiceUtils.isADepotLegal(unStock.getUnMedicament().getDepotLegal()))
				throw new Exception("Le depot legal ne respecte pas le format du depot legal");
			success = StockerDao.retirer(unStock);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		
		return success;
	}
	
	public static boolean ajouter(Stocker unStock)
	{
		boolean success = false;
		
		try {
			if(unStock.getQteStock() == 0 || unStock.getUnVisiteur().getMatricule() == null || unStock.getUnMedicament().getDepotLegal() == null)
				throw new Exception("Données obligatoire : quantite, visiteur, medicament");
			if(!ServiceUtils.isAMatricule(unStock.getUnVisiteur().getMatricule()))
				throw new Exception("Le matricule ne respecte pas le format A00...");
			if(!ServiceUtils.isADepotLegal(unStock.getUnMedicament().getDepotLegal()))
				throw new Exception("Le depot legal ne respecte pas le format du depot legal");
			success = StockerDao.ajouter(unStock);
		}
		catch(Exception e)
		{
			System.out.println( e.getMessage());
		}
		
		return success;
	}
	
	public static boolean modifier(Stocker unStock)
	{
		boolean success = true;
		
		try
		{
			if(unStock.getUnVisiteur().getMatricule() == null || unStock.getUnMedicament().getDepotLegal() == null)
				throw new Exception("Données obligatoire : quantite, visiteur, medicament");
			if(!ServiceUtils.isAMatricule(unStock.getUnVisiteur().getMatricule()))
				throw new Exception("Le matricule ne respecte pas le format A00...");
			if(!ServiceUtils.isADepotLegal(unStock.getUnMedicament().getDepotLegal()))
				throw new Exception("Le depot legal ne respecte pas le format du depot legal");
			success = StockerDao.modifier(unStock);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return success;
	}
}
