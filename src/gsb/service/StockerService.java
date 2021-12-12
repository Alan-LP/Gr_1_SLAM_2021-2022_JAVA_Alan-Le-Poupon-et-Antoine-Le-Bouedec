package gsb.service;

import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Visiteur;
import gsb.modele.dao.StockerDao;

public class StockerService {
	
	public static  ArrayList<Medicament> retournerListeStock(String codeVisiteur){
		ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
		try{
		if (codeVisiteur==null) {
            throw new Exception("Code visiteur non renseigné");
        }
		collectionDesMedicaments = StockerDao.retournerListeStock(codeVisiteur);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return collectionDesMedicaments;
	}
	
	public static  ArrayList<Visiteur> retournerListeVisiteur(String unMedicament){
		ArrayList<Visiteur> collectionDesVisiteurs = new ArrayList<Visiteur>();
		try{
		if (unMedicament==null) {
            throw new Exception("Un medicament non renseigné");
        }
		collectionDesVisiteurs = StockerDao.retournerListeVisiteur(unMedicament);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return collectionDesVisiteurs;
	}

}
