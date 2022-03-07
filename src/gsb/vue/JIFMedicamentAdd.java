package gsb.vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import gsb.modele.Medicament;
import gsb.service.MedicamentService;

public class JIFMedicamentAdd extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	//private static final Object JBajouterMedicament = null;
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBouton;
	protected JPanel pSaisie;
	
	protected JLabel JLdepotLegal;
	protected JLabel JLnomCommercial;
	protected JLabel JLcomposition;
	protected JLabel JLeffets;
	protected JLabel JLcontreIndications;
	protected JLabel JLprixEchantillon;
	protected JLabel JLcodeFamille;
	protected JLabel JLlibelleFamille;
	
	protected JTextField JTdepotLegal;
	protected JTextField JTnomCommercial;
	protected JTextField JTcomposition;
	protected JTextField JTeffets;
	protected JTextField JTcontreIndications;
	protected JTextField JTprixEchantillon;
	protected JTextField JTcodeFamille;
	protected JTextField JTlibelleFamille;
	
	protected JButton JBajouterMedicament; 
	protected MenuPrincipal fenetreContainer;
	
	
	public JIFMedicamentAdd(MenuPrincipal uneFenetreContainer) {
		
		fenetreContainer = uneFenetreContainer;
		
		p = new JPanel();  // panneau principal de la fenêtre
        pBouton = new JPanel();    // panneau supportant les boutons
        pTexte = new JPanel(new GridLayout(9,2));
        
        JLdepotLegal = new JLabel("Dépôt légal");
        JLnomCommercial = new JLabel("Nom commercial");
        JLcomposition = new JLabel("Composition");
        JLeffets = new JLabel("Effets");
        JLcontreIndications = new JLabel("Contre indications");
        JLprixEchantillon = new JLabel("Prix échantillon");
        JLcodeFamille = new JLabel("Code famille");
        JLlibelleFamille = new JLabel("Libellé famille");
    	
        p = new JPanel();
        pSaisie = new JPanel(new GridLayout(8,2));
        JTdepotLegal = new JTextField(20);
        JTdepotLegal.setMaximumSize(JTdepotLegal.getPreferredSize());
        JTnomCommercial = new JTextField();
        JTnomCommercial.setMaximumSize(JTnomCommercial.getPreferredSize());
        JTcomposition = new JTextField();
        JTcomposition.setMaximumSize(JTcomposition.getPreferredSize());
        JTeffets = new JTextField();    
        JTeffets.setMaximumSize(JTeffets.getPreferredSize());
        JTcontreIndications = new JTextField();
        JTcontreIndications.setMaximumSize(JTcontreIndications.getPreferredSize());
        JTprixEchantillon = new JTextField();
        JTprixEchantillon.setMaximumSize(JTprixEchantillon.getPreferredSize());
        JTcodeFamille = new JTextField();
        JTcodeFamille.setMaximumSize(JTcodeFamille.getPreferredSize());
        JTlibelleFamille = new JTextField();
        JTlibelleFamille.setMaximumSize(JTlibelleFamille.getPreferredSize());
        
        JBajouterMedicament = new JButton("Ajouter Medicament");
        JBajouterMedicament.addActionListener(this);
        p.add(pSaisie);
        
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
        pTexte.add(JLprixEchantillon);
        pTexte.add(JTprixEchantillon);
        pTexte.add(JLcodeFamille);
        pTexte.add(JTcodeFamille);
        pTexte.add(JLlibelleFamille);
        pTexte.add(JTlibelleFamille);
        
        pBouton.add(JBajouterMedicament);
		p.add(pTexte);
		p.add(pBouton);
        
		// mise en forme de la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(p);
        
        setTitle("Ajout d'un médicament");

	}
	
	
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if (source == JBajouterMedicament)
		{
			Medicament unMedicament = new Medicament(JTdepotLegal.getText(), JTnomCommercial.getText(), JTcomposition.getText(), JTeffets.getText(), JTcontreIndications.getText(), Float.valueOf(JTprixEchantillon.getText()),JTcodeFamille.getText(), JTlibelleFamille.getText());
			MedicamentService.ajouterMedicament(unMedicament);
			this.viderText();
		}
	}
	
     
      public void viderText() 	
    {  // méthode qui permet de vider les zones de texte 
        JTdepotLegal.setText("");        
        JTnomCommercial.setText("");
        JTcomposition.setText("");
        JTeffets.setText("");    
        JTcontreIndications.setText("");
        JTprixEchantillon.setText("");
        JTcodeFamille.setText("");
        JTlibelleFamille.setText("");
     }


    
}
	
