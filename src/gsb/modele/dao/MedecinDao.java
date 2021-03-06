package gsb.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import gsb.modele.Localite;
import gsb.modele.Medecin;

public class MedecinDao {
	
	public static Medecin rechercher(String codeMedecin)
	{
		Medecin unMedecin = null;
		Localite uneLocalite = null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("SELECT * FROM MEDECIN WHERE CODEMED ='" + codeMedecin + "';");
		
		try
		{
			if (reqSelection.next())
			{
				uneLocalite = LocaliteDao.rechercher(reqSelection.getString(5));
				unMedecin = new Medecin(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(6), reqSelection.getString(7), reqSelection.getString(8), uneLocalite );	
			}
		}
		catch(Exception e)
		{
			System.out.println("erreur reqSelection.next() pour la requ?te - select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
			e.printStackTrace();
		}
		return unMedecin;
	}
	
	public static ArrayList<Medecin> retournerCollectionDesMedecins()
	{
		ArrayList<Medecin> collectionDesMedecins = new ArrayList<Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");
		try
		{
			while (reqSelection.next())
			{
				String codeMedecin = reqSelection.getString(1);
			    collectionDesMedecins.add(MedecinDao.rechercher(codeMedecin));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedecins()");
		}
		return collectionDesMedecins;
	}
	
	public static HashMap<String,Medecin> retournerDictionnaireDesMedecins()
	{
		HashMap<String, Medecin> diccoDesMedecins = new HashMap<String, Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");
		try
		{
			while (reqSelection.next())
			{
				String codeMedecin = reqSelection.getString(1);
			    diccoDesMedecins.put(codeMedecin, MedecinDao.rechercher(codeMedecin));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("erreur retournerDiccoDesMedecins()");
		}
		return diccoDesMedecins;
	}
	
}
