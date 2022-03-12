package gsb.tests.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.service.MedicamentService;
import junit.framework.TestCase;

public class MedicamentServiceTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception{
		super.setUp();
	}
	
	@After
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	
	@Test
	public final void testChercherMedicamentDepotLegalNull() {
		assertNull("Resultat recherche : ", MedicamentService.rechercher(null));
	}
	
	@Test
	public final void testChercherMedicamentExistant() {
		assertNotNull("Resultat recherche : ",MedicamentService.rechercher("CLAZER6"));
	}
	
	@Test
	public final void testChercherMedicamentInexistant( ) {
		assertNull("Resultat recherche : ",MedicamentService.rechercher("L8OP21"));
	}
	
	
	@Test
	public final void testChercherMedicamentCaractereSpe( ) {
		assertNull("Resultat recherche : ",MedicamentService.rechercher("#@{[]%"));
	}
	
	public final void testChercherMedicamentRecupList() {
		assertEquals("Resultat recherche : ", 27, MedicamentService.recupListe().size());
	}

}
