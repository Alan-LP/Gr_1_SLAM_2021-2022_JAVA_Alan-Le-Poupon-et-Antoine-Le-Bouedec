package gsb.modele.dao;

import java.sql.ResultSet;

import gsb.modele.Medicament;

public class MedicamentDao {

	public static Medicament rechercher(String codeMedicament){
		Medicament unMedicament = null;
		
		ResultSet reqSelect = ConnexionMySql.execReqSelection("select * from MEDICAMENT where DEPOTLEGAL = '"+codeMedicament+"'");
		try {
			if (reqSelect.next())
			{
				unMedicament = new Medicament(reqSelect.getString(1), reqSelect.getString(2), reqSelect.getString(3), reqSelect.getString(4), reqSelect.getString(5), reqSelect.getFloat(6), reqSelect.getString(7), reqSelect.getString(8));
				
			}
		}
		catch(Exception e) {
			System.out.println("erreur reqSelect.next() pour la requête - select * from MEDICAMENT where DEPOTLEGAL ='"+codeMedicament+"'");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return unMedicament;
}
	public static int ajouter(Medicament unMedicament){
		int verifAjout = 0;
		
		try {
		String reqInsert = "insert into MEDICAMENT values ('"+unMedicament.getDepotLegal()+"','"+unMedicament.getNomCommercial()+"','"+unMedicament.getComposition()+"','"+unMedicament.getEffets()+"','"+unMedicament.getContreIndication()+"','"+unMedicament.getPrixEchantillon()+"','"+unMedicament.getCodeFamille()+"','"+unMedicament.getLibelleFamille()+"')";
		verifAjout = ConnexionMySql.execReqMaj(reqInsert);
	
			
		}
		catch(Exception e) {
			System.out.println("erreur reqInsert ");
			e.printStackTrace();
		}
		return verifAjout;
	}
}