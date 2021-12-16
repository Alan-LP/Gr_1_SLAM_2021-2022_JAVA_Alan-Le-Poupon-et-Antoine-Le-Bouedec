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

public class JIFVisiteCons extends JInternalFrame  implements ActionListener {
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	// déclaration des panneaux qui contiendront les composants graphiques
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	
	protected MenuPrincipal fenetreContainer;
	
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
	
	protected JButton JBmodif;
	protected JButton JBoffres;
	
	protected String reference;
	
	public JIFVisiteCons(MenuPrincipal uneFenetreContainer, String reference) {
		
		this.reference = reference;
		
		p = new JPanel();	// panneau principal de la fenêtre
		pBoutons = new JPanel();	// panneau supportant les boutons
		pTexte = new JPanel(new GridLayout(7,1));
		
		fenetreContainer = uneFenetreContainer;
		
		Visite uneVisite = VisiteDao.rechercher(reference);
		
		JLreference = new JLabel("Référence");
		pTexte.add(JLreference);
		JTreference = new JTextField(uneVisite.getReference());
		JTreference.setEditable(false);
		pTexte.add(JTreference);
		
		JLdate = new JLabel("Date visite");
		pTexte.add(JLdate);
		JTdate = new JTextField(uneVisite.getDate());
		JTdate.setEditable(false);
		pTexte.add(JTdate);
		
		JLcommentaire = new JLabel("Commentaire");
		pTexte.add(JLcommentaire);
		JTcommentaire = new JTextField(uneVisite.getCommentaire());
		JTcommentaire.setEditable(false);
		pTexte.add(JTcommentaire);
		
		JLmatricule = new JLabel("Matricule visiteur");
		pTexte.add(JLmatricule);
		JTmatricule = new JTextField(uneVisite.getUnVisiteur().getMatricule());
		JTmatricule.setEditable(false);
		pTexte.add(JTmatricule);
		
		JLcode = new JLabel("Code Médecin");
		pTexte.add(JLcode);
		JTcode = new JTextField(uneVisite.getUnMedecin().getCodeMed());
		JTcode.setEditable(false);
		pTexte.add(JTcode);
		
		JBoffres = new JButton("Offres");
		JBoffres.addActionListener(this);
		pBoutons.add(JBoffres);
		
		JBmodif = new JButton("Modifier");
		JBmodif.addActionListener(this);
		pBoutons.add(JBmodif);
		
		// mise en forme de la fenêtre
        p.add(pTexte);
        p.add(pBoutons);
        Container contentPane = getContentPane();
        contentPane.add(p);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == JBmodif) {
			fenetreContainer.ouvrirFenetre(new JIFVisiteModif(fenetreContainer, reference));
		}
	}
}
