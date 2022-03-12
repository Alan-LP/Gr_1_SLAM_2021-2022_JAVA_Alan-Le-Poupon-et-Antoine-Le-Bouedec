package gsb.tests.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.service.MedicamentService;
import gsb.service.OffrirService;
import gsb.service.VisiteService;
import junit.framework.TestCase;

public class OffrirServiceTest extends TestCase {
	
	private Medicament leMedicament = MedicamentService.rechercher("BACTI10G");
	private Visite laVisite = VisiteService.rechercherVisite("v0017");
	private Offrir uneOffre = new Offrir(leMedicament, laVisite, 10);
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public final void testInsererOffrirNull() {
		assertEquals("Resultat insertion : ", false, OffrirService.inserer(new Offrir(null, null, 0)));
	}
	
	@Test
	public final void testInsererOffrirVisiteInexistante() {
		assertEquals("Resultat insertion : ", false, OffrirService.inserer(new Offrir(leMedicament, VisiteService.rechercherVisite("v9999"), 10)));
	}
	
	@Test
	public final void testInsererOffrirVisiteFausse() {
		assertEquals("Resultat insertion : ", false, OffrirService.inserer(new Offrir(leMedicament, VisiteService.rechercherVisite("HFUIFF"), 10)));
	}
	
	@Test
	public final void testInsererOffrirMedicamentInexistant() {
		assertEquals("Resultat insertion : ", false, OffrirService.inserer(new Offrir(MedicamentService.rechercher("AXEGTX"), laVisite, 10)));
	}
	
	@Test
	public final void testInsererOffrirMedicamentFaux() {
		assertEquals("Resultat insertion : ", false, OffrirService.inserer(new Offrir(MedicamentService.rechercher("%NDFO[4"), laVisite, 10)));
	}
	
	@Test
	public final void testInsererOffrirQttStockInsuffisante() {
		assertEquals("Resultat insertion : ", false, OffrirService.inserer(new Offrir(leMedicament, laVisite, 999)));
	}
	
	@Test
	public final void testInsererOffrirCorrect() {
		assertEquals("Resultat insertion : ", true, OffrirService.inserer(new Offrir(leMedicament, laVisite, 10)));
	}
	
}
