package fr.sopramon.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.sopramon.model.enumerateur.Type;



@Entity
@Table(name="boss")
public class Boss implements ICombattant
{
	@Id
	@Column(name="BOSS_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="BOSS_NOM")
	private String nom;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="BOSS_TYPE")
	private Type type;
	
	@Column(name="BOSS_NIVEAU")
	private int niveau;
	
	@Embedded
	private Capacite capacite;
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public Capacite getCapacite() {
		return capacite;
	}

	public void setCapacite(Capacite capacite) {
		this.capacite = capacite;
	}
	
	
	
	
	
	@Override
	public Coup attaquer(ICombattant victime) {
		return null;
	}
}