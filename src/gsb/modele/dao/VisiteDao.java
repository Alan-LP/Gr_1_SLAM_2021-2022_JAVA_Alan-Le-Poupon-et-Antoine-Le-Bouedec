package gsb.modele.dao;

import java.sql.ResultSet;

import gsb.modele.Visite;


public class VisiteDao {
	public static Visite rechercher(String reference){
		Visite uneVisite = null;
		ResultSet reqSeleq = ConnexionMySql.execReqSelection("select * from VISITE where REFERENCE = '"+reference+"'");
		
		try {
			if(reqSeleq.next())
			{
				String unMedecin = reqSeleq.getString(4);
				String unVisiteur = reqSeleq.getString(5);
				uneVisite = new Visite(reqSeleq.getString(1), reqSeleq.getString(2), reqSeleq.getString(3),MedecinDao.rechercher(unMedecin), VisiteurDao.rechercher(unVisiteur));
						
			}
		}
		catch(Exception e) {
			System.out.println("erreur reqSeleq.next() pour la requête - select * from VISITE where REFERENCE ='"+reference+"'");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return uneVisite;
	}
	
	public static int ajouter(Visite uneVisite){
		int verificationAjout = 0;
		
		try {
		String reqInsertion = "insert into VISITE values ('"+uneVisite.getReference()+"','"+uneVisite.getDate()+"','"+uneVisite.getCommentaire()+"','"+uneVisite.getUnMedecin()+"','"+uneVisite.getUnVisiteur()+"')";
		verificationAjout = ConnexionMySql.execReqMaj(reqInsertion);
	
			
		}
		catch(Exception e) {
			System.out.println("erreur reqInsertion");
			e.printStackTrace();
		}
		return verificationAjout;
	}
}