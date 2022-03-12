package gsb.vue;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gsb.modele.Visite;
import gsb.service.VisiteService;

public class JIFVisiteRecapitulatif extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 326597107518661843L;
	
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	
	protected JButton modifier;
	protected JButton offres;
	
	protected JLabel JLreference;
	protected JLabel JLdateVisite;
	protected JLabel JLmatricule;
	protected JLabel JLcodeMedecin;
	protected JLabel JLcommentaire;
	
	protected JComboBox<String> JCreference;
	protected JTextField JTdateVisite;
	protected JTextField JTmatricule;
	protected JTextField JTcodeMedecin;
	protected JTextArea JTcommentaire;
	
	protected MenuPrincipal fenetreContainer;
	
	public JIFVisiteRecapitulatif(MenuPrincipal uneFenetreContainer, Visite uneVisite) {
		fenetreContainer = uneFenetreContainer;
		p = new JPanel(new GridBagLayout()); // panneau principal de la fenêtre
		pBoutons = new JPanel(); // panneau supportant les boutons
		pTexte = new JPanel(new GridBagLayout());
		
		modifier = new JButton("Ajouter/modifier le commentaire de la visite");
		offres = new JButton("Les offres de la visite");
		
		JLreference = new JLabel("Référence");
		JLdateVisite = new JLabel("Date Visite");
		JLmatricule = new JLabel("Matricule Visiteur");
		JLcodeMedecin = new JLabel("Code Médecin");
		JLcommentaire = new JLabel("Commentaire");
		
		JCreference = new JComboBox<String>();
		TreeMap<String, Visite> lesVisites = VisiteService.rechercherListeVisites("", "");
		for(String key : lesVisites.keySet())
			JCreference.addItem(key);
		JCreference.setSelectedItem(uneVisite.getReference());
		JCreference.addActionListener(this);
		JTdateVisite = new JTextField(10);
		JTdateVisite.setMaximumSize(JTdateVisite.getPreferredSize());
		JTdateVisite.setEditable(false);
		JTmatricule = new JTextField(4);
		JTmatricule.setMaximumSize(JTmatricule.getPreferredSize());
		JTmatricule.setEditable(false);
		JTcodeMedecin = new JTextField(4);
		JTcodeMedecin.setMaximumSize(JTcodeMedecin.getPreferredSize());
		JTcodeMedecin.setEditable(false);
		JTcommentaire = new JTextArea(5, 20);
		JTcommentaire.setMaximumSize(JTcommentaire.getPreferredSize());
		JTcommentaire.setLineWrap(true);
		JTcommentaire.setWrapStyleWord(true);
		JTcommentaire.setEditable(false);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.insets = new Insets(5,10,5,10);
		constraint.gridx = 0;
		constraint.gridy = 0;
		pTexte.add(JLreference, constraint);
		constraint.gridy = 1;
		pTexte.add(JLdateVisite, constraint);
		constraint.gridy = 2;
		pTexte.add(JLmatricule, constraint);
		constraint.gridy = 3;
		pTexte.add(JLcodeMedecin, constraint);
		constraint.gridy = 4;
		pTexte.add(JLcommentaire, constraint);
		
		constraint.gridx = 1;
		constraint.gridy = 0;
		pTexte.add(JCreference, constraint);
		constraint.gridy = 1;
		pTexte.add(JTdateVisite, constraint);
		constraint.gridy = 2;
		pTexte.add(JTmatricule, constraint);
		constraint.gridy = 3;
		pTexte.add(JTcodeMedecin, constraint);
		constraint.gridy = 4;
		pTexte.add(JTcommentaire, constraint);
		
        pBoutons.add(modifier);
        pBoutons.add(offres);
        modifier.addActionListener(this);
        offres.addActionListener(this);
        
        setDefaultCloseOperation(HIDE_ON_CLOSE);
	    setTitle("Récapitulatif d'une visite");
		
		// mise en forme de la fenêtre

		Container contentPane = getContentPane();
		GridBagConstraints pConstraint = new GridBagConstraints();
		pConstraint.insets = new Insets(0,0,30,0);
		pConstraint.gridx = 0;
		pConstraint.gridy = 0;
		p.add(pTexte, pConstraint);
		pConstraint.gridy = 1;
		p.add(pBoutons, pConstraint);
		contentPane.add(p);
		
		remplirText(uneVisite);
	
	}
	
	public void remplirText(Visite uneVisite) 	
	{ // méthode qui permet de remplir les zones de texte à partir des valeurs passées en paramètres
		JTdateVisite.setText(uneVisite.getDate());
		JTmatricule.setText(uneVisite.getUnVisiteur().getMatricule());
		JTcodeMedecin.setText(uneVisite.getUnMedecin().getCodeMed());
		JTcommentaire.setText(uneVisite.getCommentaire());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == JCreference)
		{
			fenetreContainer.ouvrirFenetre(new JIFVisiteRecapitulatif(fenetreContainer, VisiteService.rechercherVisite((String) JCreference.getSelectedItem())));
		}
		if(source == modifier)
		{
			fenetreContainer.ouvrirFenetre(new JIFVisiteMaj(fenetreContainer, VisiteService.rechercherVisite((String) JCreference.getSelectedItem())));
		}
		if(source == offres)
		{
			fenetreContainer.ouvrirFenetre(new JIFVisiteOffres(fenetreContainer, VisiteService.rechercherVisite((String) JCreference.getSelectedItem())));
		}
	}

}
