package fr.sopramon.dao.hibernate;

import java.util.List;

import fr.sopramon.model.Boss;
import fr.sopramon.dao.IDAOBoss;

public class DAOBossHibernate extends DAOHibernate implements IDAOBoss {
	@Override
	public List<Boss> findAll() {
		return em.createQuery("select b from Boss b", Boss.class).getResultList();
	}

	@Override
	public Boss findById(int id) {
		return em.find(Boss.class, id);
	}

	@Override
	public Boss save(Boss entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		
		return entity;
	}

	@Override
	public void delete(Boss entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Boss myBoss = new Boss();
		myBoss.setId(id);
		delete(myBoss);
	}
}
