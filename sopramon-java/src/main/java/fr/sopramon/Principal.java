package fr.sopramon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import fr.sopramon.dao.IDAOBoss;
import fr.sopramon.dao.IDAOCombat;
import fr.sopramon.dao.IDAOItem;
import fr.sopramon.dao.IDAOSopramon;
import fr.sopramon.dao.hibernate.DAOBossHibernate;
import fr.sopramon.dao.hibernate.DAOCombatHibernate;
import fr.sopramon.dao.hibernate.DAOHibernate;
import fr.sopramon.dao.hibernate.DAOItemHibernate;
import fr.sopramon.dao.hibernate.DAOSopramonHibernate;
import fr.sopramon.model.Boss;
import fr.sopramon.model.Capacite;
import fr.sopramon.model.Combat;
import fr.sopramon.model.Item;
import fr.sopramon.model.Sopramon;
import fr.sopramon.model.enumerateur.Arene;
import fr.sopramon.util.Astro;

public class Principal {
	private static Scanner sc = new Scanner(System.in);
	private static Sopramon user;
	

	public static void main(String[] args) throws ParseException {
		do {
			int choixMenu = printAndChooseMenu();

			switch (choixMenu) {
			// QUITTER L'APPLICATION
			case 0:
				exit();
				break;

			// LISTE DES SOPRAMONS
			case 1:
				printSopramons();
				break;

			// CREER UN COMPTE
			case 2:
				createAccount();
				break;

			// SE CONNECTER
			case 3:
				user = auth();
				break;

			// DEMARRER UN COMBAT
			case 4:
				startBattle();
				break;

			// LISTER LES ITEMS
			case 5:
				printItems();
				break;

			// AJOUTER UN ITEM
			case 6:
				addItem();
				break;

			// MODIFIER UN ITEM
			case 7:
				updateItem();
				break;

			// SUPPRIMER UN ITEM
			case 8:
				removeItem();
				break;
			}
		} while (true);
	}
	
	
	
	/**
	 * MENU
	 * @return
	 */
	private static int printAndChooseMenu() {
		int choixMenu = -1;

		while (choixMenu < 0 || choixMenu > 8) {
			System.out.println("-----------------------------");
			System.out.println("1. Liste des Sopramons");
			System.out.println("2. Créer un compte");
			System.out.println("3. Se connecter");
			System.out.println("4. Démarrer un combat avec un boss");
			System.out.println("5. Voir les items");
			System.out.println("6. Ajouter un item");
			System.out.println("7. Modifier un item");
			System.out.println("8. Supprimer un item");
			System.out.println("0. Quitter");
			System.out.println("-----------------------------");

			choixMenu = sc.nextInt();
		}

		return choixMenu;
	}
	
	
	
	/**
	 * Lister les Sopramons
	 */
	private static void printSopramons() {
		IDAOSopramon daoSopramon = new DAOSopramonHibernate();

		for (Sopramon s : daoSopramon.findAll()) {
			System.out.println(s.getNom());
		}
	}
	
	

	/**
	 * Créer un compte
	 */
	private static void createAccount() {
		IDAOSopramon daoSopramon = new DAOSopramonHibernate();
		Sopramon mySopramon = new Sopramon();
		Capacite myCapacite = new Capacite();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		try {
			System.out.println("Votre nom :");
			mySopramon.setNom(sc.next());

			System.out.println("Votre prénom :");
			mySopramon.setPrenom(sc.next());

			System.out.println("Votre nom d'utilisateur :");
			mySopramon.setUsername(sc.next());

			System.out.println("Votre mot de passe :");
			mySopramon.setPassword(sc.next());

			System.out.println("Votre date de naissance : (jj/mm/aaaa)");
			mySopramon.setDateNaissance(formatter.parse(sc.next()));
			mySopramon.setSigne(new Astro(mySopramon.getDateNaissance()).getSigne());
			
			myCapacite.setPointsDeVie(100);
			mySopramon.setCapacite(myCapacite);

			daoSopramon.save(mySopramon);
		}

		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Un problème est survenu... Désolé !");
		}
	}
	
	
	

	/**
	 * Se connecter
	 * @return Le sopramon utilisateur
	 */
	private static Sopramon auth() {
		IDAOSopramon daoSopramon = new DAOSopramonHibernate();

		try {
			System.out.println("Votre nom d'utilisateur :");
			String myUsername = sc.next();

			System.out.println("Votre mot de passe :");
			String myPassword = sc.next();

			return daoSopramon.auth(myUsername, myPassword);
		}

		catch (Exception e) {
			System.err.println("Impossible de se connecter... Désolé !");
		}

		return null;
	}
	
	
	
	/**
	 * Combat avec un boss
	 */
	private static void startBattle() {
		IDAOBoss daoBoss = new DAOBossHibernate();
		List<Boss> myBosses = daoBoss.findAll();
		int choixBoss = 0;
		boolean isDuelRunning = true;
		
		
		while (choixBoss <= 0 || choixBoss > myBosses.size()) {
			System.out.println("-- Choisir le Boss --");
			for (int i = 0; i < myBosses.size(); i++) {
				System.out.println((i + 1) + ". " + myBosses.get(i).getNom());
			}
			
			choixBoss = sc.nextInt();
		}
		

		IDAOCombat daoCombat = new DAOCombatHibernate();
		Boss selectedBoss = myBosses.get(choixBoss - 1);
		Combat myCombat = new Combat();

		System.out.println("Vous avez choisi " + selectedBoss.getNom());
		
		myCombat.setCombattant1(selectedBoss);
		myCombat.setCombattant2(user);
		
		myCombat.setArene(Arene.Donjon);
		myCombat.setType(selectedBoss.getType());
		
		//COMBAT AUTOMATIQUE
		while (isDuelRunning) {
			isDuelRunning = myCombat.duel();
		};
		
		//SAUVEGARDE DU COMBATS ET DES COUPS
		daoCombat.save(myCombat);
	}
	
	
	
	/**
	 * Lister les Items
	 */
	private static void printItems() {
		IDAOItem daoItem = new DAOItemHibernate();

		for (Item i : daoItem.findAll()) {
			System.out.println(i.getNom() + ", " + i.getPrix() + " pièces d'or");
		}
	}
	
	

	/**
	 * Ajouter un Item
	 */
	private static void addItem() {
		IDAOItem daoItem = new DAOItemHibernate();
		Item myItem = new Item();
		Capacite myCapacite = new Capacite();

		try {
			System.out.println("Son nom :");
			myItem.setNom(sc.next());

			System.out.println("Son prix :");
			myItem.setPrix(Float.parseFloat(sc.next()));

			System.out.println("Points d'attaque :");
			myCapacite.setAttaque(sc.nextInt());

			System.out.println("Points de défense :");
			myCapacite.setDefense(sc.nextInt());

			System.out.println("Points de vie :");
			myCapacite.setPointsDeVie(sc.nextInt());

			System.out.println("Points d'esquive :");
			myCapacite.setEsquive(sc.nextInt());
			
			myItem.setCapacite(myCapacite);
			daoItem.save(myItem);
		}

		catch (Exception e) {
			System.err.println("Un problème est survenu... Désolé !");
		}
	}
	
	
	
	/**
	 * Sélectionner un Item
	 */
	private static Item selectItem() {
		IDAOItem daoItem = new DAOItemHibernate();
		List<Item> myItems = daoItem.findAll();
		int choixItem = 0;
		
		
		while (choixItem <= 0 || choixItem > myItems.size()) {
			System.out.println("-- Choisir l'item --");
			for (int i = 0; i < myItems.size(); i++) {
				System.out.println((i + 1) + ". " + myItems.get(i).getNom());
			}
			
			choixItem = sc.nextInt();
		}
		
		return myItems.get(choixItem - 1);
	}
	
	
	
	/**
	 * Modifier un Item
	 */
	private static void updateItem() {
		IDAOItem daoItem = new DAOItemHibernate();
		Item myItem = selectItem();
		
		try {
			System.out.println("Son nom : [" + myItem.getNom() + "]");
			myItem.setNom(sc.next());

			System.out.println("Son prix : [" + myItem.getPrix() + "]");
			myItem.setPrix(Float.parseFloat(sc.next()));

			System.out.println("Points d'attaque : [" + myItem.getCapacite().getAttaque() + "]");
			myItem.getCapacite().setAttaque(sc.nextInt());

			System.out.println("Points de défense : [" + myItem.getCapacite().getDefense() + "]");
			myItem.getCapacite().setDefense(sc.nextInt());

			System.out.println("Points de vie : [" + myItem.getCapacite().getPointsDeVie() + "]");
			myItem.getCapacite().setPointsDeVie(sc.nextInt());

			System.out.println("Points d'esquive : [" + myItem.getCapacite().getEsquive() + "]");
			myItem.getCapacite().setEsquive(sc.nextInt());
			
			daoItem.save(myItem);
		}

		catch (Exception e) {
			System.err.println("Un problème est survenu... Désolé !");
		}
	}
	
	
	
	/**
	 * Supprimer un Item
	 */
	private static void removeItem() {
		IDAOItem daoItem = new DAOItemHibernate();
		daoItem.delete(selectItem());
	}
	
	
	
	/**
	 * Quitter l'application
	 */
	private static void exit() {
		sc.close();
		DAOHibernate.close();
		System.out.println("Au revoir !");
		System.exit(0);
	}
}
