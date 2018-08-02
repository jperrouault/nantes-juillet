package fr.sopramon.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Capacite implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int pointsDeVie = 0;
	private int attaque = 0;
	private int defense = 0;
	private int esquive = 0;
	private int vitesse = 0;
	

	public int getPointsDeVie() {
		return pointsDeVie;
	}

	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getEsquive() {
		return esquive;
	}

	public void setEsquive(int esquive) {
		this.esquive = esquive;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}
}