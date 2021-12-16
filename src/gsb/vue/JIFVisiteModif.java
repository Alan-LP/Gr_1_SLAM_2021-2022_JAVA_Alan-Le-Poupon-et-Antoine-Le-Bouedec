package gsb.vue;

import gsb.modele.Visite;
import gsb.modele.Medecin;
import gsb.modele.Offrir;
import gsb.modele.Visiteur;

import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.OffrirDao;
import gsb.modele.dao.VisiteurDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

public class JIFVisiteModif extends JInternalFrame  implements ActionListener {
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
	
	protected JTable JToffres;
	protected JPanel JPoffres;
	
	protected String reference;
	
	protected JTable JTstocker;
	
	protected ArrayList<Offrir> lesOffres;
	
	public JIFVisiteModif(MenuPrincipal fenetreContainer, String reference) {
		
		this.reference = reference;
		
		p = new JPanel();	// panneau principal de la fenêtre
		pBoutons = new JPanel();	// panneau supportant les boutons
		pTexte = new JPanel(new GridLayout(7,1));
		
		this.fenetreContainer = fenetreContainer;
		
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
		
		JLmatricule = new JLabel("Matricule");
		pTexte.add(JLmatricule);
		JTmatricule = new JTextField(uneVisite.getUnVisiteur().getMatricule());
		JTmatricule.setEditable(false);
		pTexte.add(JTmatricule);
		
		JLcode = new JLabel("Code");
		pTexte.add(JLcode);
		JTcode = new JTextField(uneVisite.getUnMedecin().getCodeMed());
		JTcode.setEditable(false);
		pTexte.add(JTcode);
		
		JBmodif = new JButton("Modifier");
		JBmodif.addActionListener(this);
		pBoutons.add(JBmodif);
		
		JPanel listPanel = new JPanel();
		
		lesOffres = OffrirDao.retournerListeOffre(uneVisite.getReference());
		
		String[][] lignes = new String[lesOffres.size()][2];
		
		if (lesOffres.size() == 1) {
			lignes[0][0] = lesOffres.get(0).getUnMedicament().getDepotLegal();
			lignes[0][1] = String.valueOf(lesOffres.get(0).getQteOfferte());
		}
		if (lesOffres.size() == 2) {
			lignes[1][0] = lesOffres.get(1).getUnMedicament().getDepotLegal();
			lignes[1][1] = String.valueOf(lesOffres.get(1).getQteOfferte());
		}
		
		String[] columnsName = {"Depot legal","Quantité offerte"};
		JToffres = new JTable(lignes,columnsName);
		JToffres.getSelectionModel().addListSelectionListener(JToffres);
		
		JScrollPane scrollPane = new JScrollPane(JToffres);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		
		listPanel.add(scrollPane);
		
		JPoffres = new JPanel();
		JPoffres.add(JToffres);
		p.add(JPoffres);
		
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
			String reference = JTreference.getText();
			String date = JTdate.getText();
			String commentaire = JTcommentaire.getText();
			String codeMed = JTcode.getText();
			String matricule = JTmatricule.getText();
			Medecin medecin = MedecinDao.rechercher(codeMed);
			Visiteur visiteur = VisiteurDao.rechercher(matricule);
			Visite visite = new Visite(reference, date, commentaire, medecin, visiteur);
			
			VisiteDao.modifier(visite);
			
			fenetreContainer.ouvrirFenetre(new JIFVisiteCons(fenetreContainer, reference));
		}
	}
}
