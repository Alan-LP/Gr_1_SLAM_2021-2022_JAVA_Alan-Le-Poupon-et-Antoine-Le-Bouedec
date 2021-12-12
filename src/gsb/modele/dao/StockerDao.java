package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Visiteur;

public class StockerDao {
	
    public static ArrayList<Medicament> retournerListeStock(String codeVisiteur ){
        ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from STOCKER where MATRICULE = '"+codeVisiteur+"'");
     try {
         while (reqSelection.next()) {
             collectionDesMedicaments.add(MedicamentDao.rechercher(reqSelection.getString(1)));
         }
     }
     catch (Exception e) {
         System.out.println("erreur reqSelection");
         e.printStackTrace();
     }
     ConnexionMySql.fermerConnexionBd();
     return collectionDesMedicaments;
    }
    
    public static ArrayList<Visiteur> retournerListeVisiteur(String unMedicament)
    {
        ArrayList<Visiteur> collectionDesVisiteurs = new ArrayList<Visiteur>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from STOCKER where DEPOTLEGAL = '"+unMedicament+"'");
     try {
         while (reqSelection.next()) {
             collectionDesVisiteurs.add(VisiteurDao.rechercher(reqSelection.getString(2)));
         }
     }
     catch (Exception e) {
         System.out.println("erreur reqSelection");
         e.printStackTrace();
     }
     ConnexionMySql.fermerConnexionBd();
     return collectionDesVisiteurs;
    }
    
}