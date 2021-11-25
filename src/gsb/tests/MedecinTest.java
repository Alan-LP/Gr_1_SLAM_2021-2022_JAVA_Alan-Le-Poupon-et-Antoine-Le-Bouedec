package gsb.tests;

import gsb.modele.Localite;
import gsb.modele.Medecin;

public class MedecinTest {

        public static void main(String[] args) {
            Localite uneLocalite = new Localite("45000","Orleans");
            Medecin unMedecin = new Medecin("MEC001","RAOUL","Medecin",
                    "24 rue du bled",uneLocalite,"0617222222",
                    "100%","pied");
            MedecinTest.afficherMedecin(unMedecin);
        }

        private static void afficherMedecin(Medecin unMedecin) {
            // TODO Auto-generated method stub
            System.out.println("Code Medecin ="+unMedecin.getCodeMed());
            System.out.println("Nom ="+unMedecin.getNom());
            System.out.println("Prenom ="+unMedecin.getPrenom());
            System.out.println("Adresse ="+unMedecin.getAdresse());
            System.out.println("ville ="+unMedecin.getLaLocalite().getVille());
            System.out.println("code postal ="+unMedecin.getLaLocalite().getCodePostal());
            System.out.println("Telephone ="+unMedecin.getTelephone());
            System.out.println("Potentiel ="+unMedecin.getPotentiel());
            System.out.println("Specialite ="+unMedecin.getSpecialite());

        }
}
