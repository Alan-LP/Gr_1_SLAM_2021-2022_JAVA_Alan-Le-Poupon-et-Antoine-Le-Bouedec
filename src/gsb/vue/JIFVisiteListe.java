package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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

import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.service.VisiteService;
import gsb.service.VisiteurService;

public class JIFVisiteListe extends JInternalFrame implements ActionListener, ListSelectionListener {
	
	private static final long serialVersionUID = 3630L;
	
	private TreeMap<String,Visite> dicoVisite;

	protected JPanel p;
	protected JPanel pSaisie1;
	protected JPanel pSaisie2;
	
	protected JButton JBafficherVisite;
	
	protected JLabel JLmatricule;
	protected JLabel JLdate;
	protected JLabel JLrefVisite;
	
	protected JTextField JTdate;
	
	protected JComboBox<String> JCmatricule;
	protected JComboBox<String> JCrefVisite;
	
	protected JTable table;
	
	protected JScrollPane scrollPane;
	
	protected MenuPrincipal fenetreContainer;

	public JIFVisiteListe(MenuPrincipal uneFenetreContainer, String matricule, String date) {

		fenetreContainer = uneFenetreContainer;
		
		dicoVisite = VisiteService.rechercherListeVisites(matricule,date);
		int nbLignes = dicoVisite.size();
		
		p = new JPanel(); // panneau principal de la fen?tre
		
		//champs de saisie visiteur et date
		
		pSaisie1 = new JPanel(new GridLayout(2,2));
		JLmatricule = new JLabel("Matricule Visiteur : ");
		JCmatricule = new JComboBox<String>();
		TreeMap<String, Visiteur> lesVisiteurs = VisiteurService.recupListe();
		for(String key : lesVisiteurs.keySet())
			JCmatricule.addItem(key);
		JCmatricule.setSelectedItem(matricule);
		JCmatricule.addActionListener(this);
		JLdate = new JLabel("Date Visite : ");
		JTdate = new JTextField(date);
		JTdate.addActionListener(this);
		pSaisie1.add(JLmatricule);
		pSaisie1.add(JCmatricule);
		pSaisie1.add(JLdate);
		pSaisie1.add(JTdate);
		p.add(pSaisie1);
		
		//table des visites

		int i = 0;
		String[][] data = new String[nbLignes][3];
		for (Map.Entry<String,Visite> uneEntree : dicoVisite.entrySet())
		{
			data[i][0] = uneEntree.getValue().getReference();
			data[i][1] = uneEntree.getValue().getUnMedecin().getCodeMed() ;
			data[i][2] = uneEntree.getValue().getUnMedecin().getLaLocalite().getVille();
			i ++;
		}
		String[] columnNames = {"R?f?rence", "Code m?decin", "Lieu"};
		table = new JTable(data, columnNames);
		table.getSelectionModel().addListSelectionListener(this);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		p.add(scrollPane);
		
		pSaisie2 = new JPanel();
		JLrefVisite = new JLabel("R?f?rence : ");
		JCrefVisite = new JComboBox<String>();
		for(String key : dicoVisite.keySet())
			JCrefVisite.addItem(key);
		JBafficherVisite = new JButton("Visite d?taill?e");
		JBafficherVisite.addActionListener(this); // source d'?venement
		pSaisie2.add(JLrefVisite);
		pSaisie2.add(JCrefVisite);
		pSaisie2.add(JBafficherVisite);
		p.add(pSaisie2);
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	    setTitle("Liste des visites");
		
		// mise en forme de la fen?tre
	    
		Container contentPane = getContentPane();
		contentPane.add(p);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
   		if (source == JBafficherVisite)
   		{
   			if (dicoVisite.containsKey((String) JCrefVisite.getSelectedItem()))
   			{
   	   			Visite uneVisite = dicoVisite.get((String) JCrefVisite.getSelectedItem());
   	   			fenetreContainer.ouvrirFenetre(new JIFVisiteRecapitulatif(fenetreContainer, uneVisite));
   			}
   		}
   		
   		if(source == JCmatricule || source == JTdate)
   		{
   			fenetreContainer.ouvrirFenetre(new JIFVisiteListe(fenetreContainer, (String) JCmatricule.getSelectedItem(), JTdate.getText()));
   		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JCrefVisite.setSelectedItem((String)table.getValueAt(table.getSelectedRow(), 0));
	}
}
