package gsb.service;

import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Visiteur;
import gsb.modele.dao.StockerDao;

public class StockerService {
	
	public static  ArrayList<Medicament> retournerListeStock(String codeVisiteur){
		ArrayList<Medicament> collectionMedicament = new ArrayList<Medicament>();
		try{
		if (codeVisiteur==null) {
            throw new Exception("Code visiteur non renseigné");
        }
		collectionMedicament = StockerDao.retournerListeStock(codeVisiteur);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return collectionMedicament;
	}
	
	public static  ArrayList<Visiteur> retournerListeVisiteur(String unMedicament){
		ArrayList<Visiteur> collectionVisiteur = new ArrayList<Visiteur>();
		try{
		if (unMedicament==null) {
            throw new Exception("Un medicament non renseigné");
        }
		collectionVisiteur = StockerDao.retournerListeVisiteur(unMedicament);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return collectionVisiteur;
	}

}
