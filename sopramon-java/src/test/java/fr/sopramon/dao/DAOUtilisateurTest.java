package fr.sopramon.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sopramon.config.JpaConfig;
import fr.sopramon.model.Administrateur;
import fr.sopramon.model.Sopramon;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JpaConfig.class)
public class DAOUtilisateurTest {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	
	@Test
	public void testLoginAdminSuccessful() {
		if (!(daoUtilisateur.auth("admin", "admin") instanceof Administrateur)) {
			fail("N'est pas un Adminitrateur");
		}
	}
	
	
	@Test
	public void testLoginSopramonSuccessful() {
		if (!(daoUtilisateur.auth("fd", "fd") instanceof Sopramon)) {
			fail("N'est pas un Sopramon");
		}
	}
	
	
	@Test
	public void testAccessDenied() {
		if (daoUtilisateur.auth("1", "2") != null) {
			fail("La connexion n'aurait pas du aboutir ...");
		}
	}
}
