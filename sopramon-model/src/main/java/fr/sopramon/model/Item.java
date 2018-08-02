package fr.sopramon.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item {
	@Id
	@Column(name="ITEM_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ITEM_NOM")
	private String nom;
	
	@Column(name="ITEM_PRIX")
	private double prix;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="pointsDeVie", column=@Column(name="ITEM_POINTS_VIE", nullable=false)),
		@AttributeOverride(name="attaque", column=@Column(name="ITEM_ATTAQUE", nullable=false)),
		@AttributeOverride(name="defense", column=@Column(name="ITEM_DEFENSE", nullable=false)),
		@AttributeOverride(name="esquive", column=@Column(name="ITEM_ESQUIVE", nullable=false)),
		@AttributeOverride(name="vitesse", column=@Column(name="ITEM_VITESSE", nullable=false)),
	})
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Capacite getCapacite() {
		return capacite;
	}

	public void setCapacite(Capacite capacite) {
		this.capacite = capacite;
	}
}