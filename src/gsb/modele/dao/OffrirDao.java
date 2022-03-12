package gsb.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Visite;

public class OffrirDao {
	
	public static Offrir rechercher(String depotLegal, String reference)
	{
		Offrir uneOffre = null;
		Medicament unMedoc = null;
		Visite uneVisite = null;
		String req = "SELECT * FROM OFFRIR where MED_DEPOTLEGAL = '" + depotLegal + "' AND REFERENCE='" + reference + "';";
		ResultSet resultat = ConnexionMySql.execReqSelection(req);
		
		try {
			if(resultat.next())
			{
				int qteOfferte = resultat.getInt(1);
				uneVisite = VisiteDao.rechercher(resultat.getString(2));
				unMedoc = MedicamentDao.rechercher(resultat.getString(3));
				
				uneOffre = new Offrir(unMedoc, uneVisite, qteOfferte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uneOffre;
	}
	
	private static boolean offreExists(String depotLegal, String reference)
	{
		boolean success = false;
		
		String req = "SELECT EXISTS(SELECT * FROM OFFRIR WHERE MED_DEPOTLEGAL='" + depotLegal + "' AND REFERENCE='" + reference + "');";
		ResultSet resultat = ConnexionMySql.execReqSelection(req);
		
		try {
			if(resultat.next())
			{
				boolean leSucces = resultat.getBoolean(1);
				if(leSucces)
					success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return success;
	}
	
	public static boolean ajoutStock(Offrir uneOffre)
	{
		boolean success = false;
		
		if(offreExists(uneOffre.getUnMedicament().getDepotLegal(), uneOffre.getUneVisite().getReference()))
		{
			Offrir ancienneOffre = rechercher(uneOffre.getUnMedicament().getDepotLegal(), uneOffre.getUneVisite().getReference());
			uneOffre.setQteOfferte(uneOffre.getQteOfferte() + ancienneOffre.getQteOfferte());
			success = update(uneOffre);
		}
		else
		{
			success = insert(uneOffre);
		}
		
		return success;
	}
	
	private static boolean update(Offrir uneOffre)
	{
		boolean success = false;
		
		String req = "UPDATE OFFRIR SET QTTDON='" + uneOffre.getQteOfferte() + "' WHERE MED_DEPOTLEGAL='" + uneOffre.getUnMedicament().getDepotLegal() + "' AND REFERENCE='" + uneOffre.getUneVisite().getReference() + "';";
		
		if(ConnexionMySql.execReqMaj(req) == 1)
			success = true;
		
		return success;
	}
	
	private static boolean insert(Offrir uneOffre)
	{
		boolean success = false;
		
		String req = "INSERT INTO OFFRIR VALUES ('" + uneOffre.getQteOfferte() + "','" + uneOffre.getUneVisite().getReference() + "','" + uneOffre.getUnMedicament().getDepotLegal() + "');";
		
		if(ConnexionMySql.execReqMaj(req) == 1)
			success = true;
		
		return success;
	}
	
	public static TreeMap<String, Offrir> retournerOffresVisite(Visite laVisite)
	{
		TreeMap<String, Offrir> lesOffres = new TreeMap<String, Offrir>();
		
		String req = "SELECT * FROM OFFRIR WHERE REFERENCE = '" + laVisite.getReference() + "';";
		ResultSet results = ConnexionMySql.execReqSelection(req);
		
		try {
			while(results.next())
			{
				int quantite = results.getInt(1);
				Medicament leMedicament = MedicamentDao.rechercher(results.getString(3));
				Offrir offre = new Offrir(leMedicament, laVisite, quantite);
				lesOffres.put(leMedicament.getDepotLegal(), offre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur retourner offres visite.");
		}
		
		return lesOffres;
	}
	
	public static int nombreOffresVisite(Visite uneVisite)
	{
		int nmb = 0;
		String req = "SELECT COUNT(*) FROM OFFRIR WHERE REFERENCE = '" + uneVisite.getReference() + "';";
		ResultSet results = ConnexionMySql.execReqSelection(req);
		try {
			if(results.next())
			{
				nmb = results.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erreur lors de la requête : " + req);
			e.printStackTrace();
		}
		return nmb;
	}

}
