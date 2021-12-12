package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Visiteur;

public class VisiteurTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Localite uneLocalite = new Localite("75000","Paris");
        Visiteur unVisiteur = new Visiteur("VIS001","Boucham","Abdel",
        		"AbdelBouch","Avengers","10 rue de la fortune",uneLocalite,"17 F�vrier",
        		"99","Battaillon");
        VisiteurTest.afficherVisiteur(unVisiteur);
    }

    private static void afficherVisiteur(Visiteur unVisiteur) {
        // TODO Auto-generated method stub
        System.out.println("Matricule = "+unVisiteur.getMatricule());
        System.out.println("Nom = "+unVisiteur.getNom());
        System.out.println("Prenom = "+unVisiteur.getPrenom());
        System.out.println("Login = "+unVisiteur.getLogin());
        System.out.println("Mot de passe = "+unVisiteur.getMdp());
        System.out.println("Adresse = "+unVisiteur.getAdresse());
        System.out.println("Ville = "+unVisiteur.getUneLocalite().getVille());
        System.out.println("Code Postal = "+unVisiteur.getUneLocalite().getCodePostal());
        System.out.println("Date d'entr�e ="+unVisiteur.getDateEntree());
        System.out.println("code D'unite ="+unVisiteur.getCodeUnite());
        System.out.println("nom unitaire ="+unVisiteur.getNomUnite());
    }

}