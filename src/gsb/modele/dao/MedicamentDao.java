package gsb.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import gsb.modele.Medicament;

public class MedicamentDao {
	
	public static Medicament rechercher(String depotLegal) {
		
		Medicament unMedoc = null;
		String req = "SELECT * FROM MEDICAMENT WHERE MED_DEPOTLEGAL = '" + depotLegal + "';";
		ResultSet resultat = ConnexionMySql.execReqSelection(req);
		
		try {
			if(resultat.next())
			{
				String nomCom = resultat.getString(2);
				String compo = resultat.getString(3);
				String effets = resultat.getString(4);
				String contreIndic = resultat.getString(5);
				float prixEchant = resultat.getFloat(6);
				String code = resultat.getString(7);
				String libelle = resultat.getString(8);
				
				unMedoc = new Medicament(depotLegal, nomCom, compo, effets, contreIndic, prixEchant, code, libelle);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return unMedoc;
	}
	
	public static TreeMap<String, Medicament> recuplist()
	{
		TreeMap<String, Medicament> lesMedicaments = new TreeMap<String, Medicament>();
		String req = "SELECT * FROM MEDICAMENT;";
		ResultSet resultat = ConnexionMySql.execReqSelection(req);
		
		try {
			while(resultat.next())
			{
				String depleg = resultat.getString(1);
				String nomCom = resultat.getString(2);
				String compo = resultat.getString(3);
				String effets = resultat.getString(4);
				String contreIndic = resultat.getString(5);
				float prixEchant = resultat.getFloat(6);
				String code = resultat.getString(7);
				String libelle = resultat.getString(8);
				
				Medicament unMedicament = new Medicament(depleg, nomCom, compo, effets, contreIndic, prixEchant, code, libelle);
				lesMedicaments.put(depleg, unMedicament);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lesMedicaments;
	}
	
	public static boolean addMedicament(Medicament unMedicament) {
		boolean success = true;
		String req = "INSERT INTO MEDICAMENT VALUES `MED_DEPOTLEGAL`, `MED_NOMCOMMERCIAL`, `MED_COMPOSITION`, `MED_EFFETS`, `MED_CONTREINDIC`, `MED_PRIXECHANTILLON`, `FAM_CODE`,`FAM_LIBELLE`";
		if(ConnexionMySql.execReqMaj(req) == 0)
		{
			success = false;
		}
		return success;
	}
	
}
