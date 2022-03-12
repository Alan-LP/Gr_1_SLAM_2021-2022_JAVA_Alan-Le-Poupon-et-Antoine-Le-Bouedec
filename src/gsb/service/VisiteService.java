package gsb.service;

import java.util.TreeMap;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;
import gsb.utils.ServiceUtils;

public class VisiteService {
	
	public static Visite rechercherVisite(String reference)
	{
		Visite uneVisite = null;
		
		try
		{
			if(reference == null)
				throw new Exception("Il faut transmettre une référence.");
			if(!ServiceUtils.isAReference(reference))
				throw new Exception("La référence  n'est pas conforme au format A0000.");
			
			uneVisite = VisiteDao.rechercher(reference);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return uneVisite;
	}
	
	
	public static boolean ajouterVisite(Visite uneVisite)
	{
		boolean pass = true;
		try
		{
			if(uneVisite.getReference() == null || uneVisite.getDate() == null || uneVisite.getCommentaire() == null || uneVisite.getUnMedecin() == null || uneVisite.getUnVisiteur() == null)
				throw new Exception("Visite Error : Aucun paramètre ne peut être null !");
			if(!ServiceUtils.isAReference(uneVisite.getReference()))
				throw new Exception("Visite Error : La référence ne respecte pas le format V0000.");
			if(!ServiceUtils.isADate(uneVisite.getDate()))
				throw new Exception("Visite Error : La date ne respecte pas le format YYYY-MM-DD.");
			if(uneVisite.getCommentaire().length() > 100)
				throw new Exception("Visite Error : Le commentaire doit faire moins de 100 caractères.");
			if(!ServiceUtils.isAMatricule(uneVisite.getUnVisiteur().getMatricule()))
				throw new Exception("Visite Error : Le matricule ne respecte pas le format X000.");
			if(!ServiceUtils.isACodeMed(uneVisite.getUnMedecin().getCodeMed()))
				throw new Exception("Visite Error : Le code médecin ne respecte pas le format M000.");
			
			
			if(VisiteDao.ajouter(uneVisite) == false)
				throw new Exception("Erreur lors de l'ajout de la visite en base !");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			pass = false;
		}
		return pass;
	}
	
	public static boolean majVisite(Visite uneVisite)
	{
		boolean pass = true;
		try
		{
			if(uneVisite.getReference() == null || uneVisite.getDate() == null || uneVisite.getCommentaire() == null || uneVisite.getUnMedecin() == null || uneVisite.getUnVisiteur() == null)
				throw new Exception("Aucun paramètre ne peut être null !");
			if(!ServiceUtils.isAReference(uneVisite.getReference()))
				throw new Exception("La référence ne respecte pas le format A0000.");
			if(!ServiceUtils.isADate(uneVisite.getDate()))
				throw new Exception("La date ne respecte pas le format YYYY-MM-DD.");
			if(uneVisite.getCommentaire().length() > 100)
				throw new Exception("Le commentaire doit faire moins de 100 caractères.");
			if(!ServiceUtils.isAMatricule(uneVisite.getUnVisiteur().getMatricule()))
				throw new Exception("Le matricule ne respecte pas le format X000.");
			if(!ServiceUtils.isACodeMed(uneVisite.getUnMedecin().getCodeMed()))
				throw new Exception("Le code médecin ne respecte pas le format M000.");
			
			if(VisiteDao.update(uneVisite) == false)
				throw new Exception("Erreur lors de l'update de la visite en base !");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			pass = false;
		}
		return pass;
	}
	
	public static TreeMap<String,Visite> rechercherListeVisites(String matricule, String date)
	{
		if(matricule == "" || matricule == null)
		{
			matricule = "%";
		} else {
			matricule = "%" + matricule + "%";
		}
		if(date == "" || date == null)
		{
			date = "%";
		} else {
			date = "%" + date + "%";
		}
		return VisiteDao.retournerListeVisites(matricule, date);
	}
	
	public static boolean deleteVisite(String reference)
	{
		boolean pass = true;
		try {
			if(reference == null)
				throw new Exception("Aucun paramètre ne peut être null !");
			if(!ServiceUtils.isAReference(reference))
				throw new Exception("La référence ne respecte pas le format A0000.");
			if(!VisiteDao.delete(reference))
				throw new Exception("Echec lors de la suppression.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
			pass = false;
		}
		return pass;
	}
	
}