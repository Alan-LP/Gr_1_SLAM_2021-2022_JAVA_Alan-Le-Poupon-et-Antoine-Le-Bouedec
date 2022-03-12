package gsb.tests.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.OffrirDao;
import gsb.modele.dao.VisiteDao;
import junit.framework.TestCase;

public class OffrirDaoTest extends TestCase {
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public final void testRetournerListeOffresVisiteInexistante() {
		VisiteDao.delete("v5000");
		assertEquals("Resultat recherche : ", 0, OffrirDao.retournerOffresVisite(new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""))).size());
	}
	
	@Test
	public final void testRetournerListeOffres() {
		assertEquals("Resultat recherche : ", 2, OffrirDao.retournerOffresVisite(VisiteDao.rechercher("v0001")).size());
	}
	
	@Test
	public final void testAjouterOffre() {
		Medicament leMedicament = new Medicament("3MYC7", "TRIMYCINE", "WindShield + Dash", "Risque de développer un cancer plus développé en cas de contact avec Katarina", "arrêter le traitement dès que les premiers symptomes d'une telle infection se produiraient.", (float) 50.0, "AD", "Anti Dépresseur");
		Visite laVisite = new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""));
		VisiteDao.ajouter(laVisite);
		assertEquals("Resultat ajout : ", true, OffrirDao.ajoutStock(new Offrir(leMedicament, laVisite, 10)));
		VisiteDao.delete("v5000");
	}
	
	@Test
	public final void testAjouterOffreVisiteInexistante() {
		VisiteDao.delete("v5000");
		Medicament leMedicament = new Medicament("EZHASAGI", "ANTI-CANCER-POUR-HASAGI-PLAYER", "WindShield + Dash", "Risque de développer un cancer plus développé en cas de contact avec Katarina", "arrêter le traitement dès que les premiers symptomes d'une telle infection se produiraient.", (float) 50.0, "AD", "Anti Dépresseur");
		Visite laVisite = new Visite("v5000", "2002-01-20", "", new Medecin("m001", "", "", "", "", "", "", new Localite("", "")), new Visiteur("b59", "", "", "", "", "", new Localite("", ""), "", "", ""));
		assertEquals("Resultat ajout : ", false, OffrirDao.ajoutStock(new Offrir(leMedicament, laVisite, 10)));
	}
	
}
