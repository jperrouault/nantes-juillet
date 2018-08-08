package fr.sopramon.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="achat")
public class Achat {
	@Id
	@Column(name="ACH_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACH_DATE")
	private Date date;
	
	@Column(name="ACH_PRIX")
	private double prix;
	
	@ManyToOne
	@JoinColumn(name="ACH_ITEM_ID")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name="ACH_SOPRAMON_ID")
	private Sopramon acheteur;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Sopramon getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Sopramon acheteur) {
		this.acheteur = acheteur;
	}
	
	
	public Achat() {
		this.date = new Date();
	}
}