package gsb.tests.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.modele.dao.MedecinDao;
import junit.framework.TestCase;

public class MedecinDaoTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public final void testRechercherMedecin() {
		assertNotNull("Resultat recherche : ", MedecinDao.rechercher("m001"));
	}
	
	@Test
	public final void testRechercherMedecinInexistant() {
		assertNull("Resultat recherche : ", MedecinDao.rechercher("m000"));
	}
	
	@Test
	public final void testRetournerCollectionDesMedecins() {
		assertEquals("Resultat retour : ", 20, MedecinDao.retournerCollectionDesMedecins().size());
	}
	
	@Test
	public final void testRetournerDictionnaireDesMedecins() {
		assertEquals("Resultat retour : ", 20, MedecinDao.retournerDictionnaireDesMedecins().size());
	}
	
}
