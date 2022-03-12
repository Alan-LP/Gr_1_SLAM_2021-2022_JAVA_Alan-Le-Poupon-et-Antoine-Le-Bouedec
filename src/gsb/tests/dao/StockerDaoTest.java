package gsb.tests.dao;

import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.modele.Medicament;
import gsb.modele.Stocker;
import gsb.modele.Visiteur;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.StockerDao;
import gsb.modele.dao.VisiteurDao;
import junit.framework.TestCase;

public class StockerDaoTest extends TestCase {
	
	private Visiteur leVisiteur = VisiteurDao.rechercher("a131");
	private Medicament leMedicament = MedicamentDao.rechercher("LIDOXY23");
	private Medicament leMedicament2 = MedicamentDao.rechercher("LITHOR12");
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		StockerDao.ajouter(new Stocker(40, leVisiteur, leMedicament));
		StockerDao.ajouter(new Stocker(40, leVisiteur, leMedicament2));
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		StockerDao.retirer(new Stocker(35, leVisiteur, leMedicament));
		StockerDao.retirer(new Stocker(40, leVisiteur, leMedicament2));
	}
	
	@Test
	public final void testRechercherStocks() {
		assertNotEquals("Resultat recherche : ", 0, StockerDao.rechercherLesStocks("a131").size());
	}
	
	@Test
	public final void testRechercherStocksInexistants() {
		assertEquals("Resultat recherche : ", 0, StockerDao.rechercherLesStocks("z3630").size());
	}
	
	@Test
	public final void testRetirerStock() {
		assertEquals("Resultat retrait : ", true, StockerDao.retirer(new Stocker(20, leVisiteur, leMedicament)));
	}
	
	@Test
	public final void testAjouterStock() {
		assertEquals("Resultat ajout : ", true, StockerDao.ajouter(new Stocker(15, leVisiteur, leMedicament)));
	}
	
	@Test
	public final void testGetStock() {
		assertEquals("Resultat ajout : ", 40, StockerDao.getStock(leVisiteur, leMedicament2));
	}
	
}
