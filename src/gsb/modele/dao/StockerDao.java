package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Visiteur;

public class StockerDao {
    public static ArrayList<Medicament> retournerListeStock(String codeVisteur ){
        ArrayList<Medicament> collectionMedicament = new ArrayList<Medicament>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from STOCKER where MATRICULE = '"+codeVisteur+"'");
     try {
         while (reqSelection.next()) {
             collectionMedicament.add(MedicamentDao.rechercher(reqSelection.getString(1)));
         }
     }
     catch (Exception e) {
         System.out.println("erreur reqSelection");
         e.printStackTrace();
     }
     ConnexionMySql.fermerConnexionBd();
     return collectionMedicament;
    }
    public static ArrayList<Visiteur> retournerListeVisiteur(String unMedicament)
    {
        ArrayList<Visiteur> collectionVisiteur = new ArrayList<Visiteur>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from STOCKER where MED_DEPOTLEGAL = '"+unMedicament+"'");
     try {
         while (reqSelection.next()) {
             collectionVisiteur.add(VisiteurDao.rechercher(reqSelection.getString(2)));
         }
     }
     catch (Exception e) {
         System.out.println("erreur reqSelection");
         e.printStackTrace();
     }
     ConnexionMySql.fermerConnexionBd();
     return collectionVisiteur;
    }
}