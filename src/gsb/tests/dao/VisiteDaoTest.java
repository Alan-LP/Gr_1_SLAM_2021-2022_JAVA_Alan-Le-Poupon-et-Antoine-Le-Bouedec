package gsb.tests.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteDao;
import junit.framework.TestCase;

public class VisiteDaoTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public final void testRechercherVisiteInexistante() {
		assertNull("Resultat recherche : ", VisiteDao.rechercher("v3630"));
	}
	
	@Test
	public final void testRechercherVisiteExistante() {
		assertNotNull("Resultat recherche : ", VisiteDao.rechercher("v0001"));
	}
	
	@Test
	public final void testRetournerListeVisitesVisiteurInexistant() {
		assertEquals("Resultat recherche : ", 0, VisiteDao.retournerListeVisites("efuzeofu", "2000-07-11").size());
	}
	
	@Test
	public final void testRetournerListeVisites() {
		assertEquals("Resultat recherche : ", 4, VisiteDao.retournerListeVisites("a131", "%").size());
	}
	
	@Test
	public final void testAjouterVisite() {
		VisiteDao.delete("v5000");
		assertEquals("Resultat ajout : ", true, VisiteDao.ajouter(new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testAjouterVisiteExistante() {
		VisiteDao.ajouter(new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", "")));
		assertEquals("Resultat ajout : ", false, VisiteDao.ajouter(new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testUpdateVisite() {
		VisiteDao.ajouter(new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", "")));
		assertEquals("Resultat mise à jour : ", true, VisiteDao.update(new Visite("v5000", "2002-01-20", "Test", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testDeleteVisite() {
		VisiteDao.ajouter(new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", "")));
		assertEquals("Resultat suppression : ", true, VisiteDao.delete("v5000"));
	}
	
	@Test
	public final void testUpdateVisiteInexistante() {
		VisiteDao.delete("v5000");
		assertEquals("Resultat mise à jour : ", false, VisiteDao.update(new Visite("v5000", "2002-01-20", "Test", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))));
	}
	
	@Test
	public final void testDeleteVisiteInexistante() {
		VisiteDao.delete("v5000");
		assertEquals("Resultat recherche : ", false, VisiteDao.delete("v5000"));
	}
	
}
