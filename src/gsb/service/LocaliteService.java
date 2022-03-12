package gsb.service;

import gsb.modele.Localite;
import gsb.modele.dao.LocaliteDao;
import gsb.utils.ServiceUtils;

public class LocaliteService {
	
	/**
	 * Fonction de recherche d'une localitée en base
	 * @param codePostal
	 * @return
	 */
	public static Localite rechercherLocalite(String codePostal)
	{
		Localite uneLocalite = null;
		
		try {
			if(codePostal == null)
				throw new Exception("Donnée obligatoire : codePostal");
			if(!ServiceUtils.isStringNumeric(codePostal))
				throw new Exception("Le Code Postal ne peut contenir que des chiffres.");
			if(codePostal.length() != 5)
				throw new Exception("Le Code Postal doit faire 5 charactères de long.");
			uneLocalite = LocaliteDao.rechercher(codePostal);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return uneLocalite;
	}
	
}
