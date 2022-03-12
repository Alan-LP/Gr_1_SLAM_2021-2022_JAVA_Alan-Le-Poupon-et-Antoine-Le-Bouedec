package gsb.tests.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.modele.dao.VisiteurDao;
import junit.framework.TestCase;

public class VisiteurDaoTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public final void testRechercherVisiteur() {
		assertNotNull("Resultat recherche : ", VisiteurDao.rechercher("a131"));
	}
	
	@Test
	public final void testRechercherVisiteurInexistant() {
		assertNull("Resultat recherche : ", VisiteurDao.rechercher("H5698"));
	}
	
}
