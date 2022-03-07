package gsb.vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import gsb.modele.Medicament;
import gsb.service.MedicamentService;

public class JIFMedicamentListe extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Medicament> dicMedicament;
	
	protected JPanel p;
	protected JPanel pSaisie;
	
	protected JScrollPane scrollPane;
	protected JTextField JTcodeMedicament;
	protected JLabel JLcodeMedicament;
	protected JButton JBafficherFiche;
	protected MenuPrincipal fenetreContainer;
	protected JTable table;
	
	public JIFMedicamentListe(MenuPrincipal uneFenetreContainer) {
		
		fenetreContainer = uneFenetreContainer;
		// récupération des données Medicament dans la collection
		dicMedicament = MedicamentService.retournerMedicament();
		
		int nbLignes = dicMedicament.size();
		
		
		p = new JPanel(); // panneau principal de la fenetre
		
		int i=0;
		String[][] data = new String[nbLignes][3];
		
		for(String cle : dicMedicament.keySet()) {
			data[i][0] =  dicMedicament.get(cle).getDepotLegal();
			data[i][1] =  dicMedicament.get(cle).getNomCommercial();
			data[i][2] =  dicMedicament.get(cle).getLibelleFamille();
			i++;
		}
		String[] columnNames = {"Code", "Nom", "Famille"};
		table = new JTable(data, columnNames);
		table.getSelectionModel().addListSelectionListener(table);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400,200));
		p.add(scrollPane);
		
		pSaisie = new JPanel();
		JLcodeMedicament = new JLabel("Code");
		JTcodeMedicament = new JTextField(15);
		JTcodeMedicament.setMaximumSize(JTcodeMedicament.getPreferredSize());
		JBafficherFiche = new JButton("Fiche médicament détaillée");
		JBafficherFiche.addActionListener(this);
		pSaisie.add(JLcodeMedicament);
		pSaisie.add(JTcodeMedicament);
		pSaisie.add(JBafficherFiche);
		p.add(pSaisie);
		
		// mise en forme de la fenetre
		Container contentPane = getContentPane();
		contentPane.add(p);
	
		setTitle("Liste des médicaments");
	}
		
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Object source = arg0.getSource();
			
			if (source == JBafficherFiche) {
				
				if (dicMedicament.containsKey(JTcodeMedicament.getText())) {
					Medicament unMedicament = dicMedicament.get(JTcodeMedicament.getText());
					fenetreContainer.ouvrirFenetre(new JIFMedicamentFiche(unMedicament));
				}
				if (source == table)
				{
					JTcodeMedicament.setText((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
				}
			}
		}
		
}

	
	
	

