
package fr.sopramon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.sopramon.model.Combat;

@CrossOrigin("*")
public interface IDAOCombat extends JpaRepository<Combat, Integer> {
	@Query("select distinct c from Combat c left join fetch c.coups")
	public List<Combat> findAllFetchCoups();
	
	@Query("select c from Combat c where c.sopramon1.id = :id or c.sopramon2.id = :id")
	@RestResource(path="by-sopramon-id")
	public List<Combat> findBySopramonId(@Param("id") int id);
}
