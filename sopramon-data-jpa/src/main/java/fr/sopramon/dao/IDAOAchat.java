
package fr.sopramon.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.sopramon.model.Achat;

public interface IDAOAchat extends JpaRepository<Achat, Integer> {
}
