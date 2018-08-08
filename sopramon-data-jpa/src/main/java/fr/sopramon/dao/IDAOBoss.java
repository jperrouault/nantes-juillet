
package fr.sopramon.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.sopramon.model.Boss;

public interface IDAOBoss extends JpaRepository<Boss, Integer> {
}
