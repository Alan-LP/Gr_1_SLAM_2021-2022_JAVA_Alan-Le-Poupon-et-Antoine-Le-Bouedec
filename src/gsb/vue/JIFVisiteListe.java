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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class JIFVisiteListe extends JInternalFrame  implements ActionListener {
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	// déclaration des panneaux qui contiendront les composants graphiques
	protected JPanel p; 
	protected JPanel pInputs;
	protected JPanel pList;
	protected JPanel pButtons;
	
	// déclaration des composants graphiques
	protected JLabel JLcode;
	protected JLabel JLdate;
	protected JLabel JLreference;
	
	protected JTextField JTcode;
	protected JTextField JTdate;
	protected JTextField JTreference;
	
	protected MenuPrincipal fenetreContainer;
	
	TreeMap<String, Visite> lesVisites;
	
	protected JButton JBvisite;
	protected JTable JTliste;
	
	public JIFVisiteListe(MenuPrincipal uneFenetreContainer) {

		fenetreContainer = uneFenetreContainer;
		
		p = new JPanel(new GridLayout(3,1));
		pInputs = new JPanel(new GridLayout(2,2));
		p.add(pInputs);
		pList = new JPanel(new GridLayout(1,1));
		p.add(pList);
		pButtons = new JPanel(new GridLayout(1,3));
		p.add(pButtons);
		
		JLcode = new JLabel("Code visiteur");
		pInputs.add(JLcode);
		JTcode = new JTextField();
		pInputs.add(JTcode);
		
		JLdate = new JLabel("Date visite");
		pInputs.add(JLdate);
		JTdate = new JTextField();
		pInputs.add(JTdate);
		
		lesVisites = VisiteDao.retournerLesVisites();
		
		String[][] lignes = new String[lesVisites.size()][3];
		
		int i = 0;
		for (Map.Entry<String, Visite> uneEntree : lesVisites.entrySet()) {
			lignes[i][0] = uneEntree.getValue().getReference();
			lignes[i][1] = uneEntree.getValue().getUnMedecin().getCodeMed();
			lignes[i][2] = uneEntree.getValue().getUnMedecin().getAdresse();
			i++;
		}
		
		String[] columnsName = {"Référence","Code medecin","Lieu"};
		JTliste = new JTable(lignes,columnsName);
		JTliste.getSelectionModel().addListSelectionListener(JTliste);
		
		JScrollPane scrollPane = new JScrollPane(JTliste);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		
		pList.add(scrollPane);
		
		JLreference = new JLabel("Référence");
		pInputs.add(JLreference);
		JTreference = new JTextField();
		pInputs.add(JTreference);
		JBvisite = new JButton("Visite détaillée");
		JBvisite.addActionListener(this);
		pButtons.add(JBvisite);
		
        Container contentPane = getContentPane();
        contentPane.add(p);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == JBvisite) {
			if(lesVisites.containsKey(JTreference.getText())) {
				fenetreContainer.ouvrirFenetre(new JIFVisiteCons(fenetreContainer, JTreference.getText()));
			}
		}
	}
}
