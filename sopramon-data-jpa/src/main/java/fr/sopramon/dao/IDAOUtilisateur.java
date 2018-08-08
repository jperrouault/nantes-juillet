package fr.sopramon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.sopramon.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
	@Query("select s from Utilisateur s where s.username = :username and s.password = :password")
	public Utilisateur auth(@Param("username") String username, @Param("password") String password);
}
