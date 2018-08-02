
package fr.sopramon.dao;

import fr.sopramon.model.Sopramon;

public interface IDAOSopramon extends IDAO<Sopramon> {
	public Sopramon auth(String username, String password);
}
