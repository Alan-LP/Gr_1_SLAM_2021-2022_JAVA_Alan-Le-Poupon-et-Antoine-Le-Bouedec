package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;


public class VisiteTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Localite uneLocaliteMed = new Localite("75000","Paris");
        Medecin unMedecin = new Medecin("MED001","Louis","Pasteur","12 rue de la sainte",uneLocaliteMed,"0617423699","100%","vaccin");
        Localite uneLocaliteVis = new Localite("75000","Paris");
        Visiteur unVisiteur = new Visiteur("VIS001","Boucham","Abdel","AbdelBouch","Avengers","10 rue de la fortune",uneLocaliteVis,"17 Février","99","Battaillon");
        Visite uneVisite = new Visite("REF001","3 Mars","Visite URGENTE",unMedecin,unVisiteur);
        VisiteTest.afficherVisite(uneVisite);
    }

    private static void afficherVisite(Visite uneVisite) {
        // TODO Auto-generated method stub
        System.out.println("Reference = "+uneVisite.getReference());
        System.out.println("Date = "+uneVisite.getDate());
        System.out.println("Commentaire = "+uneVisite.getCommentaire());
        System.out.println("Nom du Medecin = "+uneVisite.getUnMedecin().getNom());
        System.out.println("Nom du visiteur = "+uneVisite.getUnVisiteur().getNom());

    }

}
