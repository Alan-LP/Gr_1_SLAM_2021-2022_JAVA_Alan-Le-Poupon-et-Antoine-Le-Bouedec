package gsb.tests.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.service.VisiteService;

public class VisiteServiceTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		VisiteService.deleteVisite("v5000");
	}
	
	@Test
	public final void testCreerVisiteNullArguments() {
		assertEquals("Resultat insertion : ", false, VisiteService.ajouterVisite(new Visite(null, null, null, null, null)));
	}
	
	@Test
	public final void testCreerVisiteFausseReference() {
		assertEquals("Resultat insertion : ", false, VisiteService.ajouterVisite(new Visite("H67649", "", "", new Medecin("", "", "", "", "", "", "", new Localite("", "")), new Visiteur("", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testCreerVisiteExistante() {
		assertEquals("Resultat insertion : ", false, VisiteService.ajouterVisite(new Visite("v0001", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testCreerVisiteFausseDate() {
		assertEquals("Resultat insertion : ", false, VisiteService.ajouterVisite(new Visite("v0001", "200-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testCreerVisiteFauxMedecin() {
		assertEquals("Resultat insertion : ", false, VisiteService.ajouterVisite(new Visite("v5000", "2001-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("j098", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testCreerVisiteFauxVisiteur() {
		assertEquals("Resultat insertion : ", false, VisiteService.ajouterVisite(new Visite("v5000", "2001-01-20", "", new Medecin("m666", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testCreerVisiteValide() {
		assertEquals("Resultat insertion : ", true, VisiteService.ajouterVisite(new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testUpdateVisiteNullArguments() {
		assertEquals("Resultat insertion : ", false, VisiteService.majVisite(new Visite(null, null, null, null, null)));
	}
	
	@Test
	public final void testUpdateVisiteFausseReference() {
		assertEquals("Resultat insertion : ", false, VisiteService.majVisite(new Visite("H67649", "", "", new Medecin("", "", "", "", "", "", "", new Localite("", "")), new Visiteur("", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testUpdateVisiteExistante() {
		assertEquals("Resultat insertion : ", true, VisiteService.majVisite(new Visite("v0001", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testUpdateVisiteFausseDate() {
		assertEquals("Resultat insertion : ", false, VisiteService.majVisite(new Visite("v0001", "200-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testUpdateVisiteFauxMedecin() {
		assertEquals("Resultat insertion : ", false, VisiteService.majVisite(new Visite("v5000", "2001-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("j098", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testUpdateVisiteFauxVisiteur() {
		assertEquals("Resultat insertion : ", false, VisiteService.majVisite(new Visite("v5000", "2001-01-20", "", new Medecin("m666", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testUpdateVisiteInexistanteValide() {
		assertEquals("Resultat insertion : ", false, VisiteService.majVisite(new Visite("v5000", "2002-01-20", "test", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
}
