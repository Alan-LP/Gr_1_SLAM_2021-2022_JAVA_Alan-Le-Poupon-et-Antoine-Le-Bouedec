package gsb.service;

import gsb.modele.Localite;
import gsb.modele.dao.LocaliteDao;

public class LocaliteService {
	
	public static Localite rechercherLocalite(String unCodeLocalite){
		Localite uneLocalite=null;
		try{
		if (unCodeLocalite==null) {
            throw new Exception("Code localite non renseigné");
        }
		uneLocalite = LocaliteDao.rechercher(unCodeLocalite);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return uneLocalite;
	}

}
