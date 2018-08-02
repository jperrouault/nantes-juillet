package fr.sopramon.dao.hibernate;

import java.util.List;

import fr.sopramon.model.Sopramon;
import fr.sopramon.dao.IDAOSopramon;

public class DAOSopramonHibernate extends DAOHibernate implements IDAOSopramon {
	@Override
	public List<Sopramon> findAll() {
		return em.createQuery("select s from Sopramon s", Sopramon.class).getResultList();
	}

	@Override
	public Sopramon findById(int id) {
		return em.find(Sopramon.class, id);
	}

	@Override
	public Sopramon save(Sopramon entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		
		return entity;
	}

	@Override
	public void delete(Sopramon entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Sopramon mySopramon = new Sopramon();
		mySopramon.setId(id);
		delete(mySopramon);
	}

	@Override
	public Sopramon auth(String username, String password) {
		return em
				.createQuery("select s from Sopramon s where s.username = :username and s.password = :password", Sopramon.class)
				.setParameter("username", username)
				.setParameter("password", password)
				.getSingleResult();
	}
}
