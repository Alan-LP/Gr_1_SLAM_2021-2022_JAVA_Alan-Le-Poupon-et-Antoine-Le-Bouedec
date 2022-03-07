package gsb.vue;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.service.MedecinService;
import gsb.service.VisiteService;
import gsb.service.VisiteurService;

public class JIFVisiteMaj extends JInternalFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5391373108308058156L;
	
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	
	protected JButton maj;
	protected JButton offres;
	
	protected JLabel JLreference;
	protected JLabel JLdateVisite;
	protected JLabel JLcommentaire;
	protected JLabel JLmatricule;
	protected JLabel JLcodeMedecin;
	
	protected JTextField JTreference;
	protected JTextField JTdateVisite;
	protected JTextArea JTcommentaire;
	protected JTextField JTmatricule;
	protected JTextField JTcodeMedecin;
	
	protected MenuPrincipal fenetreContainer;
	
	public JIFVisiteMaj(MenuPrincipal fenetreContainer, Visite uneVisite) {
		this.fenetreContainer = fenetreContainer;
		
		p = new JPanel(new GridBagLayout()); // panneau principal de la fenêtre
		pBoutons = new JPanel(); // panneau supportant les boutons
		pTexte = new JPanel(new GridBagLayout());
		
		maj = new JButton("Mettre à Jour");
		offres = new JButton("Offres de la Visite");
		
		JLreference = new JLabel("Référence");
		JLdateVisite = new JLabel("Date Visite");
		JLcommentaire = new JLabel("Commentaire");
		JLmatricule = new JLabel("Matricule Visiteur");
		JLcodeMedecin = new JLabel("Code Médecin");
		
		JTreference = new JTextField(5);
		JTreference.setEditable(false);
		JTreference.setMaximumSize(JTreference.getPreferredSize());
		JTdateVisite = new JTextField(10);
		JTdateVisite.setMaximumSize(JTdateVisite.getPreferredSize());
		JTcommentaire = new JTextArea(5, 20);
		JTcommentaire.setMaximumSize(JTcommentaire.getPreferredSize());
		JTcommentaire.setLineWrap(true);
		JTmatricule = new JTextField(4);
		JTmatricule.setMaximumSize(JTmatricule.getPreferredSize());
		JTmatricule.setEditable(false);
		JTcodeMedecin = new JTextField(4);
		JTcodeMedecin.setMaximumSize(JTcodeMedecin.getPreferredSize());
		JTcodeMedecin.setEditable(false);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.insets = new Insets(2,10,2,10);
		constraint.gridx = 0;
		constraint.gridy = 0;
		pTexte.add(JLreference, constraint);
		constraint.gridy = 1;
		pTexte.add(JLdateVisite, constraint);
		constraint.gridy = 2;
		pTexte.add(JLcommentaire, constraint);
		constraint.gridy = 3;
		pTexte.add(JLmatricule, constraint);
		constraint.gridy = 4;
		pTexte.add(JLcodeMedecin, constraint);
		constraint.gridx = 1;
		constraint.gridy = 0;
		pTexte.add(JTreference, constraint);
		constraint.gridy = 1;
		pTexte.add(JTdateVisite, constraint);
		constraint.gridy = 2;
		pTexte.add(JTcommentaire, constraint);
		constraint.gridy = 3;
		pTexte.add(JTmatricule, constraint);
		constraint.gridy = 4;
		pTexte.add(JTcodeMedecin, constraint);
		
        pBoutons.add(maj);
        pBoutons.add(offres);
        maj.addActionListener(this);
        offres.addActionListener(this);
		
		// mise en forme de la fenêtre

		Container contentPane = getContentPane();
		GridBagConstraints pConstraint = new GridBagConstraints();
		pConstraint.insets = new Insets(10,0,10,0);
		pConstraint.gridx = 0;
		pConstraint.gridy = 0;
		p.add(pTexte, pConstraint);
		pConstraint.gridy = 1;
		p.add(pBoutons, pConstraint);
		contentPane.add(p);
		
		remplirText(uneVisite);
	
	}
	
	private void remplirText(Visite uneVisite) 	
	{ // méthode qui permet de remplir les zones de texte à partir des valeurs passées en paramètres
		JTreference.setText(uneVisite.getReference());
		JTdateVisite.setText(uneVisite.getDate());
		JTcommentaire.setText(uneVisite.getCommentaire());
		JTmatricule.setText(uneVisite.getUnVisiteur().getMatricule());
		JTcodeMedecin.setText(uneVisite.getUnMedecin().getCodeMed());
	}
	
	private Visite recupVisiteDuText()
	{ // méthode qui permet de créer un objet visite à partir des champs de saisie
		String reference = JTreference.getText();
		String dateVisite = JTdateVisite.getText();
		String commentaire = JTcommentaire.getText();
		Visiteur visiteur = VisiteurService.rechercherVisiteur(JTmatricule.getText());
		Medecin medecin = MedecinService.rechercherMedecin(JTcodeMedecin.getText());
		Visite uneVisite = new Visite(reference, dateVisite, commentaire, medecin, visiteur);
		return uneVisite;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == maj)
		{
			VisiteService.majVisite(recupVisiteDuText());
			fenetreContainer.ouvrirFenetre(new JIFVisiteRecapitulatif(fenetreContainer, VisiteService.rechercherVisite(JTreference.getText())));
		}
		if(source == offres)
		{
			fenetreContainer.ouvrirFenetre(new JIFVisiteOffres(fenetreContainer, VisiteService.rechercherVisite(JTreference.getText())));
		}
	}

}