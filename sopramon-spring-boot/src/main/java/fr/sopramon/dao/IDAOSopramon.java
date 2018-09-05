package fr.sopramon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sopramon.model.Sopramon;

public interface IDAOSopramon extends JpaRepository<Sopramon, Integer> {
}
