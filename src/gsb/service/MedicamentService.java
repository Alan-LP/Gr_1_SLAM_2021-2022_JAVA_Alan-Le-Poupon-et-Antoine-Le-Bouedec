package gsb.service;

import java.util.HashMap;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

public class MedicamentService {
	
	public static Medicament rechercherMedicament(String unCodeMedicament){
		Medicament unMedicament=null;
		try{
		if (unCodeMedicament==null) {
            throw new Exception("Code médicament non renseigné");
        }
		unMedicament = MedicamentDao.rechercher(unCodeMedicament);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unMedicament;
	}
	
	public static int ajouterMedicament(Medicament unMedicament){
		int resultAjoutMedicament = 0;
		try{
		if (unMedicament==null) {
            throw new Exception("Medicament à ajouter non renseigné");
        }
		resultAjoutMedicament = MedicamentDao.ajouter(unMedicament);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return resultAjoutMedicament;
	}
	public static HashMap<String, Medicament> retournerMedicament()
	{
		HashMap<String,Medicament> dicMedicament = new HashMap<String, Medicament>();
		dicMedicament =MedicamentDao.retournerMedicament();
		return dicMedicament;
	}

}
