package gsb.tests.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.service.LocaliteService;
import junit.framework.TestCase;

public class LocaliteServiceTest extends TestCase {
	
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
		assertNotNull("Resultat recherche : ", LocaliteService.rechercherLocalite("13012"));
	}
	
	@Test
	public final void testRechercherLocaliteInexistant() {
		assertNull("Resultat recherche : ", LocaliteService.rechercherLocalite("56100"));
	}
	
	@Test
	public final void testRechercherLocaliteNull() {
		assertNull("Resultat recherche : ", LocaliteService.rechercherLocalite(null));
	}
	
	@Test
	public final void testRechercherLocaliteMauvaisCode1() {
		assertNull("Resultat recherche : ", LocaliteService.rechercherLocalite("56A00"));
	}
	
	@Test
	public final void testRechercherLocaliteMauvaisCode2() {
		assertNull("Resultat recherche : ", LocaliteService.rechercherLocalite("561000"));
	}
	
}
