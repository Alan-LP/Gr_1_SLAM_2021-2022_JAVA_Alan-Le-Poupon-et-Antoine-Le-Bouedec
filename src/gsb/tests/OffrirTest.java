package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.modele.Visiteur;

public class OffrirTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Medicament unMedicament = new Medicament("DEP002","Viagra","cachet bleu","secondaire","a gober",1.01,"FAM001","Les gobeurs");
        Localite uneLocaliteMed = new Localite("75000","Paris");
        Medecin unMedecin = new Medecin("MED001","Louis","Pasteur","12 rue de la sainte",uneLocaliteMed,"0617423699","100%","vaccin");
        Localite uneLocaliteVis = new Localite("75000","Paris");
        Visiteur unVisiteur = new Visiteur("VIS001","Boucham","Abdel","AbdelBouch","Avengers","10 rue de la fortune",uneLocaliteVis,"17 Février","99","Battaillon");
        Visite uneVisite = new Visite("REF001","3 Mars","Visite URGENTE",unMedecin,unVisiteur);

        Offrir uneOffre = new Offrir(unMedicament,uneVisite,2);

        OffrirTest.afficherOffrir(uneOffre);
    }

    private static void afficherOffrir(Offrir uneOffre) {
        // TODO Auto-generated method stub
        System.out.println("Medicament = "+uneOffre.getUnMedicament().getNomCommercial());
        System.out.println("Reference de la visite = "+uneOffre.getUneVisite().getReference());
        System.out.println("Quantité = "+uneOffre.getQteOfferte());
    }

}