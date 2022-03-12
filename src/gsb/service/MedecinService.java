/*
 * Créé le 23 févr. 2015
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package gsb.service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;
import gsb.utils.ServiceUtils;

/**
 * @author Isabelle
 * 23 févr. 2015
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class MedecinService {
	
	public static Medecin rechercherMedecin(String unCodeMedecin){
		Medecin unMedecin = null;
		try {
			if (unCodeMedecin == null)
	            throw new Exception("Donnée obligatoire : code");
			if(!ServiceUtils.isACodeMed(unCodeMedecin))
				throw new Exception("Le code médecin ne correspond pas au format M000.");
			
			unMedecin = MedecinDao.rechercher(unCodeMedecin);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unMedecin;
	}
	
	public static TreeMap<String, Medecin> recupListe()
	{
		HashMap<String, Medecin> lesMedecinsDesor = MedecinDao.retournerDictionnaireDesMedecins();
		TreeMap<String, Medecin> lesMedecins = new TreeMap<String, Medecin>();
		
		for (Map.Entry<String, Medecin> uneEntree : lesMedecinsDesor.entrySet())
		{
			String codeMed = uneEntree.getKey();
			Medecin leMedecin = uneEntree.getValue();
			lesMedecins.put(codeMed, leMedecin);
		}
		
		return lesMedecins;
	}
	
}
