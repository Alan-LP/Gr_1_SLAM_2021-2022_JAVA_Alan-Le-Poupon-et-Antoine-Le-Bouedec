package gsb.service;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;

public class VisiteService {
	
	public static Visite rechercherVisite(String reference){
		Visite uneVisite = null;
		try{
		if (reference==null) {
            throw new Exception("Reference non renseign�");
        }
		uneVisite = VisiteDao.rechercher(reference);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return uneVisite;
	}
	
	public static int ajouterVisite(Visite uneVisite){
		int ajoutVisite = 0;
		try{
		if (uneVisite==null) {
            throw new Exception("Visite � ajouter non renseign�");
        }
		ajoutVisite = VisiteDao.ajouter(uneVisite);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return ajoutVisite;
	}
	
	public static int modifierVisite(Visite uneVisite){
		int modifVisite = 0;
		try{
		if (uneVisite==null) {
            throw new Exception("Visite � modifier non renseign�");
        }
		modifVisite = VisiteDao.modifier(uneVisite);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return modifVisite;
	}

}
