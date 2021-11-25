package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;


public class VisiteTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Localite uneLocaliteMed = new Localite("45000","Orleans");
        Medecin unMedecin = new Medecin("MEC001","RAOUL","Medecin","24 rue du bled",uneLocaliteMed,"0617222222","100%","pied");
        Localite uneLocaliteVis = new Localite("45000","Orleans");
        Visiteur unVisiteur = new Visiteur("VISITEUR01","MAN","ANT","ANT-MAN","Avengers","Film Marvel",uneLocaliteVis,"10 NOVEMBRE","101","Battaillon");
        Visite uneVisite = new Visite("REF45","3 Mars","Commenter avec un point",unMedecin,unVisiteur);
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
