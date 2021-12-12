package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Visite;

public class OffrirDao {
	
	//retourne une collection contenant les visites concernant le m�dicament donn� en param�tre
	public static ArrayList<Visite> retournerListeOffre(String unCodeMedicament)
	{
		ArrayList<Visite> collectionDesVisites = new ArrayList<Visite>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select REFERENCE from OFFRIR where DEPOTLEGAL = '"+unCodeMedicament+"'");
		try {
			while (reqSelection.next())
			{
				collectionDesVisites.add(VisiteDao.rechercher(reqSelection.getString(1)));
			}
		}
		catch (Exception e) {
			System.out.println("erreur de la requ�te de selection");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return collectionDesVisites;
	}
	
	//retourne une collection contenant les m�dicaments offert lors d'une visite en param�tre
	public static ArrayList<Medicament> retournerListeMedicament(String unCodeVisite)
	{
		ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from OFFRIR where REFERENCE = '"+unCodeVisite+"'");
		try {
			while (reqSelection.next())
			{
				collectionDesMedicaments.add(MedicamentDao.rechercher(reqSelection.getString(1)));
			}
		}
		catch (Exception e) {
			System.out.println("erreur de la requ�te de selection");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return collectionDesMedicaments;
	}

}
