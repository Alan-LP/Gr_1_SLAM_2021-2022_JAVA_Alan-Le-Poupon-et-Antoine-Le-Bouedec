package gsb.vue;

import gsb.modele.Visite;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JIFVisite extends JInternalFrame  {
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	// déclaration des panneaux qui contiendront les composants graphiques
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	
	// déclaration des composants graphiques
	protected JLabel JLreference;
	protected JLabel JLdate;
	protected JLabel JLcommentaire;
	protected JLabel JLmatricule;
	protected JLabel JLcodeMed;
    
	protected JTextField JTreference;
	protected JTextField JTdate;
	protected JTextField JTcommentaire;
	protected JTextField JTmatricule;
	protected JTextField JTcodeMed;
	
	public JIFVisite() {
		p = new JPanel();	// panneau principal de la fenêtre
		pBoutons = new JPanel();	// panneau supportant les boutons
		pTexte = new JPanel(new GridLayout(9,2));
		
		 JLreference = new JLabel("Référence");	// instanciation des composants graphiques
		 JLdate = new JLabel("Date visite");
		 JLcommentaire = new JLabel("Commentaire");
		 JLmatricule = new JLabel("Matricule visiteur");	// les labels
		 JLcodeMed = new JLabel("Code Médecin");
		 
		 JTreference = new JTextField(20);
         JTreference.setMaximumSize(JTreference.getPreferredSize());
         JTdate = new JTextField();
         JTcommentaire = new JTextField();
         JTmatricule = new JTextField();					// les zones de texte
         JTcodeMed = new JTextField();
         
         pTexte.add(JLreference);
         pTexte.add(JTreference);
         pTexte.add(JLdate);
         pTexte.add(JTdate);
         pTexte.add(JLcommentaire);
         pTexte.add(JTcommentaire);							// placement des labels et des zones de texte
         pTexte.add(JLmatricule);							// le layout étant un gridLayout, les composants vont
         pTexte.add(JTmatricule);							// être placés les uns à la suite des autres dans la grille
         pTexte.add(JLcodeMed);
         pTexte.add(JTcodeMed);
         
         // mise en forme de la fenêtre
         p.add(pTexte);
         p.add(pBoutons);
         Container contentPane = getContentPane();
         contentPane.add(p);
	}
	
	public void remplirText(Visite uneVisite) 	
    {  // méthode qui permet de remplir les zones de texte à partir des valeurs passées en paramètres
        JTreference.setText(uneVisite.getReference());        
        JTdate.setText(uneVisite.getDate());
        JTcommentaire.setText(uneVisite.getCommentaire());
        JTmatricule.setText(uneVisite.getUnVisiteur().getMatricule());
        JTcodeMed.setText(uneVisite.getUnMedecin().getCodeMed());    
     }
	
	public void viderText() 	
    {  // méthode qui permet de vider les zones de texte 
        JTreference.setText("");        
        JTdate.setText("");
        JTcommentaire.setText("");
        JTmatricule.setText("");    
        JTcodeMed.setText("");
     }
	
	

}