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

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.service.MedecinService;
import gsb.service.VisiteService;
import gsb.service.VisiteurService;

public class JIFVisiteAjout extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 4976466054485124215L;
	
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	
	protected JButton JBajouter;
	
	protected JLabel JLreference;
	protected JLabel JLdateVisite;
	protected JLabel JLcommentaire;
	protected JLabel JLmatricule;
	protected JLabel JLcodeMedecin;
	
	protected JTextField JTreference;
	protected JTextField JTdateVisite;
	protected JTextArea JTcommentaire;
	protected JComboBox<String> JCmatricule;
	protected JComboBox<String> JCcodeMedecin;
	
	protected MenuPrincipal leMenu;
	
	public JIFVisiteAjout(MenuPrincipal leMenu) {
		this.leMenu = leMenu;
		
		p = new JPanel(new GridBagLayout()); // panneau principal de la fenêtre
		pBoutons = new JPanel(); // panneau supportant les boutons
		pTexte = new JPanel(new GridBagLayout());
		
		JBajouter = new JButton("Ajouter");
		
		JLreference = new JLabel("Référence");
		JLdateVisite = new JLabel("Date Visite");
		JLcommentaire = new JLabel("Commentaire");
		JLmatricule = new JLabel("Matricule Visiteur");
		JLcodeMedecin = new JLabel("Code Médecin");
		
		JTreference = new JTextField();
		JTreference.setMaximumSize(JTreference.getPreferredSize());
		JTdateVisite = new JTextField();
		JTdateVisite.setMaximumSize(JTdateVisite.getPreferredSize());
		JTcommentaire = new JTextArea(3, 25);
		JTcommentaire.setMaximumSize(JTcommentaire.getPreferredSize());
		JTcommentaire.setEditable(false);
		JCmatricule = new JComboBox<String>();
		TreeMap<String, Visiteur> lesVisiteurs = VisiteurService.recupListe();
		for(String key : lesVisiteurs.keySet())
			JCmatricule.addItem(key);
		JCcodeMedecin = new JComboBox<String>();
		TreeMap<String, Medecin> lesMedecins = MedecinService.recupListe();
		for(String key : lesMedecins.keySet())
			JCcodeMedecin.addItem(key);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.insets = new Insets(5,10,5,10);
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
		pTexte.add(JCmatricule, constraint);
		constraint.gridy = 4;
		pTexte.add(JCcodeMedecin, constraint);
		
        pBoutons.add(JBajouter);
        JBajouter.addActionListener(this);
        
        setDefaultCloseOperation(HIDE_ON_CLOSE);
	    setTitle("Ajout d'une visite");
		
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
	
	}
	
	public void remplirText(Visite uneVisite) 	
	{ // méthode qui permet de remplir les zones de texte à partir des valeurs passées en paramètres
		JTreference.setText(uneVisite.getReference());
		JTdateVisite.setText(uneVisite.getDate());
		JTcommentaire.setText(uneVisite.getCommentaire());
	}
	
	public void viderText() 	
	{ // méthode qui permet de vider les zones de texte 
		JTreference.setText("");
		JTdateVisite.setText("");
		JTcommentaire.setText("");
	}
	
	public Visite recupVisiteDuText()
	{ // méthode qui permet de créer un objet visite à partir des champs de saisie
		String reference = JTreference.getText();
		String dateVisite = JTdateVisite.getText();
		String commentaire = JTcommentaire.getText();
		Visiteur visiteur = VisiteurService.rechercherVisiteur((String) JCmatricule.getSelectedItem());
		Medecin medecin = MedecinService.rechercherMedecin((String) JCcodeMedecin.getSelectedItem());
		Visite uneVisite = new Visite(reference, dateVisite, commentaire, medecin, visiteur);
		return uneVisite;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == JBajouter)
		{
			Visite laVisite = recupVisiteDuText();
			VisiteService.ajouterVisite(laVisite);
			leMenu.ouvrirFenetre(new JIFVisiteRecapitulatif(leMenu, laVisite));
		}
	}

}
