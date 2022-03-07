package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.service.MedicamentService;
import gsb.service.OffrirService;
import gsb.service.VisiteService;

public class JIFVisiteOffres extends JInternalFrame implements ActionListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2154252726365388910L;
	
	protected JPanel p;
	
	protected JPanel pSaisie1;
	protected JLabel JLreference;
	protected JComboBox<String> JCreference;
	
	protected JPanel pSaisie2;
	protected JLabel JLdepotLegal;
	protected JComboBox<String> JCdepotLegal;
	protected JLabel JLquantite;
	protected JTextField JTquantite;
	
	protected JPanel pBoutons;
	protected JButton ajouter;
	
	protected JScrollPane scrollPane;
	private TreeMap<String, Offrir> dicoOffres;
	protected JTable table;
	
	protected JPanel pBoutonsBas;
	protected JButton visite;
	
	protected MenuPrincipal fenetreContainer;
	
	public JIFVisiteOffres(MenuPrincipal fenetreContainer, Visite uneVisite)
	{
		this.fenetreContainer = fenetreContainer;
		
		p = new JPanel(new GridBagLayout());
		
		JLreference = new JLabel("Référence : ");
		JCreference = new JComboBox<String>();
		TreeMap<String, Visite> lesVisites = VisiteService.rechercherListeVisites("", "");
		for(String key : lesVisites.keySet())
			JCreference.addItem(key);
		JCreference.setSelectedItem(uneVisite.getReference());
		JCreference.addActionListener(this);
		
		pSaisie1 = new JPanel(new GridLayout(1,2));
		pSaisie1.add(JLreference);
		pSaisie1.add(JCreference);
		
		JLdepotLegal = new JLabel("Dépôt légal : ");
		JCdepotLegal = new JComboBox<String>();
		TreeMap<String, Medicament> lesMedocs = MedicamentService.recupListe();
		for(String key : lesMedocs.keySet())
			JCdepotLegal.addItem(key);
		JLquantite = new JLabel("Quantité : ");
		JTquantite = new JTextField();
		
		pSaisie2 = new JPanel(new GridLayout(2,2));
		pSaisie2.add(JLdepotLegal);
		pSaisie2.add(JCdepotLegal);
		pSaisie2.add(JLquantite);
		pSaisie2.add(JTquantite);
		
		ajouter = new JButton("Ajouter");
		ajouter.addActionListener(this);
		pBoutons = new JPanel();
		pBoutons.add(ajouter);
		
		dicoOffres = OffrirService.rechercherOffresVisite(uneVisite);
		int nbLignes = dicoOffres.size();
		int i = 0;
		String[][] data = new String[nbLignes][2] ;
		for (Map.Entry<String,Offrir> uneEntree : dicoOffres.entrySet())
		{
			data[i][0] = uneEntree.getValue().getUnMedicament().getDepotLegal();
			data[i][1] = String.valueOf(uneEntree.getValue().getQteOfferte());
			i ++;
		}
		String[] columnNames = {"Dépôt Légal", "Quantité"};
		table = new JTable(data, columnNames);
		table.getSelectionModel().addListSelectionListener(this);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 60));
		scrollPane.setMinimumSize(new Dimension(300, 60));
		
		visite = new JButton("Fiche Visite");
		visite.addActionListener(this);
		pBoutonsBas = new JPanel();
		pBoutonsBas.add(visite);
		
		Container contentPane = getContentPane();
		GridBagConstraints pConstraint = new GridBagConstraints();
		pConstraint.insets = new Insets(7,0,7,0);
		pConstraint.gridwidth = 2;
		pConstraint.gridx = 0;
		pConstraint.gridy = 0;
		p.add(pSaisie1, pConstraint);
		pConstraint.gridwidth = 1;
		pConstraint.gridy = 1;
		p.add(pSaisie2, pConstraint);
		pConstraint.gridx = 1;
		pConstraint.gridy = 1;
		p.add(pBoutons, pConstraint);
		pConstraint.gridwidth = 2;
		pConstraint.gridx = 0;
		pConstraint.gridy = 2;
		p.add(scrollPane, pConstraint);
		pConstraint.gridy = 3;
		p.add(pBoutonsBas, pConstraint);
		contentPane.add(p);
	}
	
	private Offrir offreFromFields()
	{
		Medicament leMedicament = MedicamentService.rechercher((String) JCdepotLegal.getSelectedItem());
		Visite laVisite = VisiteService.rechercherVisite((String) JCreference.getSelectedItem());
		int quantite = Integer.parseInt(JTquantite.getText());
		Offrir offre = new Offrir(leMedicament, laVisite, quantite);
		return offre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == ajouter)
		{
			OffrirService.inserer(offreFromFields());
			fenetreContainer.ouvrirFenetre(new JIFVisiteOffres(fenetreContainer, VisiteService.rechercherVisite((String) JCreference.getSelectedItem())));
		}
		if(source == JCreference)
		{
			fenetreContainer.ouvrirFenetre(new JIFVisiteOffres(fenetreContainer, VisiteService.rechercherVisite((String) JCreference.getSelectedItem())));
		}
		if(source == visite)
		{
			fenetreContainer.ouvrirFenetre(new JIFVisiteRecapitulatif(fenetreContainer, VisiteService.rechercherVisite((String) JCreference.getSelectedItem())));
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		JCdepotLegal.setSelectedItem((String)table.getValueAt(table.getSelectedRow(), 0));
		
	}
	
	

}