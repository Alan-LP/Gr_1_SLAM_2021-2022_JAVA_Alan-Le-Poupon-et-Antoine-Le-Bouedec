package gsb.tests.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.modele.dao.LocaliteDao;
import junit.framework.TestCase;

public class LocaliteDaoTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public final void testRechercherLocalite() {
		assertNotNull("Resultat recherche : ", LocaliteDao.rechercher("13012"));
	}
	
	@Test
	public final void testRechercherLocaliteInexistant() {
		assertNull("Resultat recherche : ", LocaliteDao.rechercher("56100"));
	}
	
}
