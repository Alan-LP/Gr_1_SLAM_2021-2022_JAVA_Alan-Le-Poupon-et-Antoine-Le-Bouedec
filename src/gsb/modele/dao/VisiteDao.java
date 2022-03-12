package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.TreeMap;

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;

public class VisiteDao {
	
	public static Visite rechercher(String reference)
	{
		Visite uneVisite = null;
		Medecin unMedecin = null;
		Visiteur unVisiteur = null;
		
		String req = "SELECT * FROM VISITE WHERE REFERENCE = '" + reference + "';";
		ResultSet results = ConnexionMySql.execReqSelection(req);
		
		try {
			if(results.next())
			{
				String dateVisite = results.getString(2);
				String commentaire = results.getString(3);
				unMedecin = MedecinDao.rechercher(results.getString(5));
				unVisiteur = VisiteurDao.rechercher(results.getString(4));
				uneVisite = new Visite(reference, dateVisite, commentaire, unMedecin, unVisiteur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uneVisite;
	}
	
	public static TreeMap<String, Visite> retournerListeVisites(String codeVisiteur, String dateVisite)
	{
		TreeMap<String, Visite> dicoVisites = new TreeMap<String, Visite>();
		
		String req = "SELECT * FROM VISITE WHERE MATRICULE LIKE '" + codeVisiteur + "' AND DATEVISITE LIKE '" + dateVisite + "' ORDER BY REFERENCE;";
		ResultSet results = ConnexionMySql.execReqSelection(req);
		
		try {
			while(results.next())
			{
				String reference = results.getString(1);
				String date = results.getString(2);
				String commentaire = results.getString(3);
				Visite uneVisite = new Visite(reference, date, commentaire, MedecinDao.rechercher(results.getString(5)), VisiteurDao.rechercher(results.getString(4)));
				dicoVisites.put(reference, uneVisite);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur dico visites.");
		}
		
		return dicoVisites;
	}
	
	public static boolean ajouter(Visite uneVisite)
	{
		boolean success = false;
		String req = "INSERT INTO VISITE VALUES ('" + uneVisite.getReference() + "', '" + uneVisite.getDate() + "', '" + uneVisite.getCommentaire() + "', '" + uneVisite.getUnVisiteur().getMatricule() + "', '" + uneVisite.getUnMedecin().getCodeMed() + "');";
		try
		{
			if(ConnexionMySql.execReqMaj(req) == 1)
			{
				success = true;
			}
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors de l'ajout de la visite !");
		}
		return success;
	}
	
	public static boolean update(Visite uneVisite)
	{
		boolean success = false;
		String req = "UPDATE VISITE SET DATEVISITE = '" + uneVisite.getDate() + "', COMMENTAIRE = '" + uneVisite.getCommentaire() + "', MATRICULE = '" + uneVisite.getUnVisiteur().getMatricule() + "', CODEMED = '" + uneVisite.getUnMedecin().getCodeMed() + "' WHERE REFERENCE = '" + uneVisite.getReference() + "';";
		try
		{
			if(ConnexionMySql.execReqMaj(req) == 1)
			{
				success = true;
			}
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors de la mise à jour d'une visite !");
		}
		
		return success;
	}
	
	public static boolean delete(String reference)
	{
		boolean success = false;
		try
		{
			String req = "DELETE FROM OFFRIR WHERE REFERENCE='" + reference + "';";
			ConnexionMySql.execReqMaj(req);
			req = "DELETE FROM VISITE WHERE REFERENCE='" + reference + "';";
			if(ConnexionMySql.execReqMaj(req) == 1)
			{
				success = true;
			}
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors de la suppression de la visite !");
		}
		return success;
	}
	
}
