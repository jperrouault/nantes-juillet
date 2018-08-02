package fr.sopramon.dao.hibernate;

import java.util.List;

import fr.sopramon.model.Item;
import fr.sopramon.dao.IDAOItem;

public class DAOItemHibernate extends DAOHibernate implements IDAOItem {
	@Override
	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class).getResultList();
	}

	@Override
	public Item findById(int id) {
		return em.find(Item.class, id);
	}

	@Override
	public Item save(Item entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		
		return entity;
	}

	@Override
	public void delete(Item entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public void deleteById(int id) {
		Item myItem = new Item();
		myItem.setId(id);
		delete(myItem);
	}
}
