
package fr.sopramon.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.sopramon.model.Boss;

@CrossOrigin("*")
public interface IDAOBoss extends JpaRepository<Boss, Integer> {
}
