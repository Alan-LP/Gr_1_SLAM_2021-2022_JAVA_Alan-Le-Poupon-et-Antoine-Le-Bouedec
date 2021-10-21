package gsb.modele.dao;

import java.sql.ResultSet;

import gsb.modele.Visiteur;

public class VisiteurDao {
	
	public static Visiteur rechercher(String codeVisiteur){
		Visiteur unVisiteur = null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from VISITEUR where MATRICULE ='"+codeVisiteur+"'");
		try {
			if (reqSelection.next()) {
				String unCp = reqSelection.getString(7);
				unVisiteur = new Visiteur(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5), reqSelection.getString(6), LocaliteDao.rechercher(unCp), reqSelection.getString(8), reqSelection.getString(9), reqSelection.getString(10));
				};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from VISITEUR where MATRICULE ='"+codeVisiteur+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return unVisiteur;
	}
}
