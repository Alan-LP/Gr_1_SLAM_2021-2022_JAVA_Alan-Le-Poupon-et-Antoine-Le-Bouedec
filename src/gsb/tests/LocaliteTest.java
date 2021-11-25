package gsb.tests;

import gsb.modele.Localite;

public class LocaliteTest {

    public static void main(String[] args) {

        Localite uneLocalite = new Localite("45000","Orleans");
        LocaliteTest.afficherLocalite(uneLocalite);
    }

    private static void afficherLocalite(Localite uneLocalite) {
        // TODO Auto-generated method stub
        System.out.println("Code Postal ="+uneLocalite.getCodePostal());
        System.out.println("Ville ="+uneLocalite.getVille());
    }
}
