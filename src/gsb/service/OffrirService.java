package gsb.service;

import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Visite;
import gsb.modele.dao.OffrirDao;

public class OffrirService {

	public static ArrayList<Visite> retournerListeOffre(String unCodeMedicament){
		ArrayList<Visite> collectionDesVisites = new ArrayList<Visite>();
		try{
		if (unCodeMedicament==null) {
            throw new Exception("Code médicament non renseigné");
        }
		collectionDesVisites = OffrirDao.retournerListeOffre(unCodeMedicament);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return collectionDesVisites;
	}
	
	public static ArrayList<Medicament> retournerListeMedicament(String unCodeVisite){
		ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
		try{
		if (unCodeVisite==null) {
            throw new Exception("Code visite non renseigné");
        }
		collectionDesMedicaments = OffrirDao.retournerListeMedicament(unCodeVisite);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return collectionDesMedicaments;
	}
	
	
}
