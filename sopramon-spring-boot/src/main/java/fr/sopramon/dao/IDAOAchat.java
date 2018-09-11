
package fr.sopramon.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.sopramon.model.Achat;


@CrossOrigin("*")
public interface IDAOAchat extends JpaRepository<Achat, Integer> {
}
