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
				String unMedecin = reqSeleq.getString(5);
				String unVisiteur = reqSeleq.getString(4);
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
		String reqInsertion = "insert into VISITE values ('"+uneVisite.getReference()+"','"+uneVisite.getDate()+"','"+uneVisite.getCommentaire()+"','"+uneVisite.getUnMedecin().getCodeMed()+"','"+uneVisite.getUnVisiteur().getMatricule()+"')";
		verificationAjout = ConnexionMySql.execReqMaj(reqInsertion);
	
			
		}
		catch(Exception e) {
			System.out.println("erreur reqInsertion");
			e.printStackTrace();
		}
		return verificationAjout;
	}
	
	public static int modifier(Visite uneVisite){
		int verifModif = 0;
		
		try {
		String reqModif = "update VISITE set DATEVISITE='"+uneVisite.getDate()+"',COMMENTAIRE='"+uneVisite.getCommentaire()+"',CODEMED='"+uneVisite.getUnMedecin().getCodeMed()+"',MATRICULE='"+uneVisite.getUnVisiteur().getMatricule()+"' where REFERENCE='"+uneVisite.getReference()+"'";
		verifModif = ConnexionMySql.execReqMaj(reqModif);
	
			
		}
		catch(Exception e) {
			System.out.println("erreur reqModif");
			e.printStackTrace();
		}
		return verifModif;
	}
}