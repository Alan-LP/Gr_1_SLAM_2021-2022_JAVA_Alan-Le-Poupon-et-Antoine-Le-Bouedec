package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gsb.modele.Medicament;

public class JIFMedicament extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	
	protected JLabel JLdepotLegal;
	protected JLabel JLnomCommercial;
	protected JLabel JLcomposition;
	protected JLabel JLeffets;
	protected JLabel JLcontreIndications;
	protected JLabel JLcodeFamille;
	protected JLabel JLlibelleFamille;
	
	protected JTextField JTdepotLegal;
	protected JTextField JTnomCommercial;
	protected JTextField JTcomposition;
	protected JTextField JTeffets;
	protected JTextField JTcontreIndications;
	protected JTextField JTcodeFamille;
	protected JTextField JTlibelleFamille;
	
	
	
	public JIFMedicament() {
    	p = new JPanel();  // panneau principal de la fenêtre
        pBoutons = new JPanel();    // panneau supportant les boutons
        pTexte = new JPanel(new GridLayout(9,2));
    	
    	 JLdepotLegal = new JLabel("Depot Legal");
         JLnomCommercial = new JLabel("Nom Commercial");
         JLcomposition = new JLabel("Composition");
         JLeffets = new JLabel("Effets");
         JLcontreIndications = new JLabel("Contre Indications");
         JLcodeFamille = new JLabel("Code Famille");
         JLlibelleFamille = new JLabel("Libelle Famille");
         
         JTdepotLegal = new JTextField(20);
         JTnomCommercial = new JTextField();
         JTcomposition = new JTextField();
         JTeffets = new JTextField();    
         JTcontreIndications = new JTextField();
         JTcodeFamille = new JTextField();
         JTlibelleFamille = new JTextField();
         
         pTexte.add(JLdepotLegal);
         pTexte.add(JTdepotLegal);
         pTexte.add(JLnomCommercial);
         pTexte.add(JTnomCommercial);
         pTexte.add(JLcomposition);
         pTexte.add(JTcomposition);
         pTexte.add(JLeffets);
         pTexte.add(JTeffets);
         pTexte.add(JLcontreIndications);
         pTexte.add(JTcontreIndications);
         pTexte.add(JLcodeFamille);
         pTexte.add(JTcodeFamille);
         pTexte.add(JLlibelleFamille);
         pTexte.add(JTlibelleFamille);
		
        // mise en forme de la fenêtre

         p.add(pTexte);
         p.add(pBoutons);
         Container contentPane = getContentPane();
         contentPane.add(p);

	}
    
    public void remplirText(Medicament unMedicament) 	
    {  // méthode qui permet de remplir les zones de texte à partir des valeurs passées en paramètres
        JTdepotLegal.setText(unMedicament.getDepotLegal());        
        JTnomCommercial.setText(unMedicament.getNomCommercial());
        JTcomposition.setText(unMedicament.getComposition());
        JTeffets.setText(unMedicament.getEffets());    
        JTcontreIndications.setText(unMedicament.getContreIndication());
        JTcodeFamille.setText(unMedicament.getCodeFamille());
        JTlibelleFamille.setText(unMedicament.getLibelleFamille());
    }
     
      public void viderText() 	
    {  // méthode qui permet de vider les zones de texte 
        JTdepotLegal.setText("");        
        JTnomCommercial.setText("");
        JTcomposition.setText("");
        JTeffets.setText("");    
        JTcontreIndications.setText("");
        JTcodeFamille.setText("");
        JTlibelleFamille.setText("");
     }


    
}
	
