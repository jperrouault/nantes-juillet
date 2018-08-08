
package fr.sopramon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sopramon.model.Combat;

public interface IDAOCombat extends JpaRepository<Combat, Integer> {
}
