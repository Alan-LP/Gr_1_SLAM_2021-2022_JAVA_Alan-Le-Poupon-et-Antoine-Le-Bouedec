package gsb.service;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;

public class VisiteurService {
	
	public static Visiteur rechercherVisiteur(String unCodeVisiteur){
		Visiteur unVisiteur = null;
		try{
		if (unCodeVisiteur==null) {
            throw new Exception("Code visiteur non renseigné");
        }
		unVisiteur = VisiteurDao.rechercher(unCodeVisiteur);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unVisiteur;
	}

}
