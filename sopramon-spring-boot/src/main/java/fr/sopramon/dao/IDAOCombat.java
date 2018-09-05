
package fr.sopramon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.sopramon.model.Combat;

public interface IDAOCombat extends JpaRepository<Combat, Integer> {
	@Query("select distinct c from Combat c left join fetch c.coups")
	public List<Combat> findAllFetchCoups();
}
