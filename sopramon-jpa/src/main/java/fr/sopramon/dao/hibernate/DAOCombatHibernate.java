package fr.sopramon.dao.hibernate;

import java.util.List;

import fr.sopramon.model.Combat;
import fr.sopramon.dao.IDAOCombat;

public class DAOCombatHibernate extends DAOHibernate implements IDAOCombat {
	@Override
	public List<Combat> findAll() {
		return em.createQuery("select c from Combat c", Combat.class).getResultList();
	}

	@Override
	public Combat findById(int id) {
		return em.find(Combat.class, id);
	}

	@Override
	public Combat save(Combat entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		
		return entity;
	}

	@Override
	public void delete(Combat entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Combat myCombat = new Combat();
		myCombat.setId(id);
		delete(myCombat);
	}
}
