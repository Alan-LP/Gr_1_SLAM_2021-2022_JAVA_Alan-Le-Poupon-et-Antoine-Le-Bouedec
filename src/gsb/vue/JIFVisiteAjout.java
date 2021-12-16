package gsb.vue;

import gsb.modele.Visite;
import gsb.modele.Medecin;
import gsb.modele.Visiteur;

import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteurDao;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JIFVisiteAjout extends JInternalFrame  implements ActionListener {
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
	protected JLabel JLcode;
    
	protected JTextField JTreference;
	protected JTextField JTdate;
	protected JTextField JTcommentaire;
	protected JTextField JTmatricule;
	protected JTextField JTcode;
	
	protected JButton JBajout;
	
	public JIFVisiteAjout() {
		p = new JPanel();	// panneau principal de la fenêtre
		pBoutons = new JPanel();	// panneau supportant les boutons
		pTexte = new JPanel(new GridLayout(6,2));
		
		JLreference = new JLabel("Référence");
		pTexte.add(JLreference);
		JTreference = new JTextField(20);
		pTexte.add(JTreference);
		
		JLdate = new JLabel("Date visite");
		pTexte.add(JLdate);
		JTdate = new JTextField();
		pTexte.add(JTdate);
		
		JLcommentaire = new JLabel("Commentaire");
		pTexte.add(JLcommentaire);
		JTcommentaire = new JTextField();
		pTexte.add(JTcommentaire);
		
		JLmatricule = new JLabel("Matricule visiteur");
		pTexte.add(JLmatricule);
		JTmatricule = new JTextField();
		pTexte.add(JTmatricule);
		
		JLcode = new JLabel("Code Médecin");
		pTexte.add(JLcode);
		JTcode = new JTextField();
		pTexte.add(JTcode);
		
		JBajout = new JButton("Ajouter");
		JBajout.addActionListener(this);
		pBoutons.add(JBajout);
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	    setTitle("Ajout d'une visite"); 
		
		// mise en forme de la fenêtre
        p.add(pTexte);
        p.add(pBoutons);
        Container contentPane = getContentPane();
        contentPane.add(p);
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == JBajout) {
			String reference = JTreference.getText();
			String date = JTdate.getText();
			String commentaire = JTcommentaire.getText();
			String codeMed = JTcode.getText();
			String matricule = JTmatricule.getText();
			Medecin medecin = MedecinDao.rechercher(codeMed);
			Visiteur visiteur = VisiteurDao.rechercher(matricule);
			Visite visite = new Visite(reference, date, commentaire, medecin, visiteur);
			
			VisiteDao.ajouter(visite);
		}
	}
}
