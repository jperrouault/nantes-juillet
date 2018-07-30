package fr.sopramon.model;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sopramon")
@PrimaryKeyJoinColumn(name="SPMN_ID", referencedColumnName="UTI_ID")
public class Sopramon extends Utilisateur implements ICombattant {
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SPMN_DATE_NAISSANCE")
	private Date dateNaissance;
	
	@ManyToOne
	@JoinColumn(name="SPMN_SIGNE_ID")
	private Signe signe;
	
	@Column(name="SPMN_EXPERIENCE")
	private int experience;
	
	@Column(name="SPMN_NIVEAU")
	private int niveau;
	
	@Column(name="SPMN_ARGENT")
	private int argent;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="pointsDeVie", column=@Column(name="SPMN_POINTS_VIE")),
		@AttributeOverride(name="attaque", column=@Column(name="SPMN_ATTAQUE")),
		@AttributeOverride(name="defense", column=@Column(name="SPMN_DEFENSE")),
		@AttributeOverride(name="esquive", column=@Column(name="SPMN_ESQUIVE")),
		@AttributeOverride(name="vitesse", column=@Column(name="SPMN_VITESSE")),
	})
	private Capacite capacite;
	
	@OneToMany
	private List<Item> items;
	
	

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Signe getSigne() {
		return signe;
	}

	public void setSigne(Signe signe) {
		this.signe = signe;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
	}

	public Capacite getCapacite() {
		return capacite;
	}

	public void setCapacite(Capacite capacite) {
		this.capacite = capacite;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public Coup attaquer(ICombattant victime) {
		return null;
	}
}