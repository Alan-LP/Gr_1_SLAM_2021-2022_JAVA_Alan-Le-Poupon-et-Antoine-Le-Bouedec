package gsb.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;

public class JIFMedicamentCons extends JIFMedicament  implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton premier;
    private JButton suivant;
    private JButton precedent; 
    private JButton dernier; 
    private ArrayList<Medicament> lesMedicaments;
	private int indiceEnCours;
        
    public JIFMedicamentCons() {
        super();
        premier = new JButton("Premier");
        pBoutons.add(premier);
        suivant = new JButton("Suivant");
        pBoutons.add(suivant);
        precedent = new JButton("Precedent");
        pBoutons.add(precedent);
        dernier = new JButton("Dernier");
        pBoutons.add(dernier);
        // déclaration des sources d'évènements
        premier.addActionListener(this);
        suivant.addActionListener(this);
        precedent.addActionListener(this);
        dernier.addActionListener(this);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Consultation vos médicaments");
        
        // récupération des données Medecin dans la collection
        lesMedicaments = MedicamentDao.retournerCollectionDesMedicaments();
        indiceEnCours = 0;
        
        if (lesMedicaments.size()!=0){ // si collection non vide, affichage des données du premier Medecin
        	Medicament leMedicament = lesMedicaments.get(0);
	    	remplirText(leMedicament);
	    	}
        
        // ajout d'un écouteur d'évènement pour la fermeture de la fenêtre
        addInternalFrameListener(new InternalFrameAdapter(){
            public void  internalFrameClosing(InternalFrameEvent e) {
                          //le code que tu veux exécuter à la fermeture de la JInternalFrame
            }
        });
    }
	
    public void actionPerformed(ActionEvent evt) { 
		Object source = evt.getSource();
   		if (source == premier){
				indiceEnCours = 0;
				Medicament leMedicament = lesMedicaments.get(indiceEnCours);
		    	remplirText(leMedicament);				}
		 else if (source == dernier){
				indiceEnCours = lesMedicaments.size() - 1;
				Medicament leMedicament = lesMedicaments.get(indiceEnCours);
		    	remplirText(leMedicament);
				}
		 else if (source == precedent){
				if (indiceEnCours > 0) indiceEnCours = indiceEnCours - 1;
				Medicament leMedicament = lesMedicaments.get(indiceEnCours);
		    	remplirText(leMedicament);				}
		 else if (source == suivant){
				if (indiceEnCours < (lesMedicaments.size() - 1)) indiceEnCours = indiceEnCours + 1;
				Medicament leMedicament = lesMedicaments.get(indiceEnCours);
		    	remplirText(leMedicament);		    	}
 } // fin actionPerformed

}