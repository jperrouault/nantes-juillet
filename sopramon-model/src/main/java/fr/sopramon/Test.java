package fr.sopramon;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SopramonPU");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("OK !");
	}

}
