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
        Localite uneLocaliteMed = new Localite("45000","Orleans");
        Medecin unMedecin = new Medecin("MEC001","RAOUL","Medecin","24 rue du bled",uneLocaliteMed,"0617222222","100%","pied");
        Localite uneLocaliteVis = new Localite("45000","Orleans");
        Visiteur unVisiteur = new Visiteur("VISITEUR01","MAN","ANT","ANT-MAN","Avengers","Film Marvel",uneLocaliteVis,"10 NOVEMBRE","101","Battaillon");
        Visite uneVisite = new Visite("REF45","3 Mars","Commenter avec un point",unMedecin,unVisiteur);

        Offrir uneOffre = new Offrir(unMedicament,uneVisite,3);

        OffrirTest.afficherOffrir(uneOffre);
    }

    private static void afficherOffrir(Offrir uneOffre) {
        // TODO Auto-generated method stub
        System.out.println("Medicament = "+uneOffre.getUnMedicament().getNomCommercial());
        System.out.println("Reference de la visite = "+uneOffre.getUneVisite().getReference());
        System.out.println("Quantité = "+uneOffre.getQteOfferte());
    }

}