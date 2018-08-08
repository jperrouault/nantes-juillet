
package fr.sopramon.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.sopramon.model.Item;

public interface IDAOItem extends JpaRepository<Item, Integer> {
}
