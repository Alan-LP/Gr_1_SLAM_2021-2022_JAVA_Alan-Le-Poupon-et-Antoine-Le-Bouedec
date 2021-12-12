package gsb.tests;

import gsb.modele.Medicament;

public class MedicamentTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Medicament unMedicament = new Medicament("DEP001","AntiDouleur","poudre blanche",
                "secondaire","a gober",2.01,"FAM001","Les gobeurs");
        MedicamentTest.afficherMedicament(unMedicament);

    }

    private static void afficherMedicament(Medicament unMedicament) {
        // TODO Auto-generated method stub
        System.out.println("Depot Legal ="+unMedicament.getDepotLegal());
        System.out.println("Nom commercial ="+unMedicament.getNomCommercial());
        System.out.println("Composition ="+unMedicament.getComposition());
        System.out.println("Effets ="+unMedicament.getEffets());
        System.out.println("Contre indication ="+unMedicament.getContreIndication());
        System.out.println("Prix d'un echantillon ="+unMedicament.getPrixEchantillon());
        System.out.println("Code Famille ="+unMedicament.getCodeFamille());
        System.out.println("Libelle famille ="+unMedicament.getLibelleFamille());

    }

}