package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Medicament;
import gsb.modele.Stocker;
import gsb.modele.Visiteur;

public class StockerTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Medicament unMedicament = new Medicament("DEP002","Viagra","cachet bleu","secondaire","a gober",1.01,"FAM001","Les gobeurs");
        Localite uneLocaliteVis = new Localite("75000","Paris");
        Visiteur unVisiteur = new Visiteur("VIS001","Boucham","Abdel","AbdelBouch","Avengers","10 rue de la fortune",uneLocaliteVis,"17 Février","99","Battaillon");


        Stocker unStock = new Stocker(445,unVisiteur,unMedicament);

        StockerTest.afficherStocker(unStock);
    }

    private static void afficherStocker(Stocker unStock) {
        // TODO Auto-generated method stub
        System.out.println("Quantité = "+unStock.getQteStock());
        System.out.println("Nom du visiteur= "+unStock.getUnVisiteur().getNom());
        System.out.println("Medicament = "+unStock.getUnMedicament().getNomCommercial());
    }

}