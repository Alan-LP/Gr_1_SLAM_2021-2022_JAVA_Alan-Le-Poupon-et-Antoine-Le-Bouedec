package gsb.service;

import java.util.TreeMap;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;
import gsb.utils.ServiceUtils;

public class MedicamentService {
	
	public static Medicament rechercher(String depotLegal)
	{
		Medicament unMedoc =  null;
		try {
			if(depotLegal == null)
				throw new Exception("Donnée obligatoire : code");
			if(!ServiceUtils.isADepotLegal(depotLegal))
				throw new Exception("Le dépot légal ne correspond pas au format 'XXXXXXXXXX'");
			unMedoc = MedicamentDao.rechercher(depotLegal);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unMedoc;
	}
	
	public static TreeMap<String, Medicament> recupListe()
	{
		TreeMap<String, Medicament> lesMedicaments = new TreeMap<String, Medicament>();
		try {
			 lesMedicaments = MedicamentDao.recuplist();
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return lesMedicaments;
	}
	
	public static boolean ajouter(Medicament unMedicament)
	{
		boolean success = false;
		try
		{
			MedicamentDao.addMedicament(unMedicament); 
			success = true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return success;
	}

}
