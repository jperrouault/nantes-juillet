package fr.sopramon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.sopramon.model.enumerateur.Type;

@Entity
@Table(name="signe")
public class Signe {
	@Id
	@Column(name="SIG_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="SIG_NOM")
	private String nom;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="SIG_TYPE")
	private Type type;
	
	@ManyToOne
	@JoinColumn(name="SIG_AVANTAGE_ID")
	private Signe avantage;
	
	@ManyToOne
	@JoinColumn(name="SIG_FAIBLESSE_ID")
	private Signe faiblesse;
	
	
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

	public Signe getAvantage() {
		return avantage;
	}

	public void setAvantage(Signe avantage) {
		this.avantage = avantage;
	}

	public Signe getFaiblesse() {
		return faiblesse;
	}

	public void setFaiblesse(Signe faiblesse) {
		this.faiblesse = faiblesse;
	}
}