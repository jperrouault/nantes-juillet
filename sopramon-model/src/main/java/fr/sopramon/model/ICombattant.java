package fr.sopramon.model;

public interface ICombattant
{
	public Coup attaquer(ICombattant victime);
	public Capacite getCapacite();
}