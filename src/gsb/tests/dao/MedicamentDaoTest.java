package gsb.tests.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.modele.dao.MedicamentDao;
import junit.framework.TestCase;

public class MedicamentDaoTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public final void testRechercherMedicament() {
		assertNotNull("Resultat recherche : ", MedicamentDao.rechercher("3MYC7"));
	}
	
	@Test
	public final void testRechercherMedicamentInexistant() {
		assertNull("Resultat recherche : ", MedicamentDao.rechercher("EZHASAGI"));
	}
	
	@Test
	public final void testRetournerListeMedicament() {
		assertEquals("Resultat recherche : ", 28, MedicamentDao.recuplist().size());
	}
	
}
