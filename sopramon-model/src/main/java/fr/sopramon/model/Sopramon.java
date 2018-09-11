package fr.sopramon.model;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


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
		@AttributeOverride(name="pointsDeVie", column=@Column(name="SPMN_POINTS_VIE", nullable=false)),
		@AttributeOverride(name="attaque", column=@Column(name="SPMN_ATTAQUE", nullable=false)),
		@AttributeOverride(name="defense", column=@Column(name="SPMN_DEFENSE", nullable=false)),
		@AttributeOverride(name="esquive", column=@Column(name="SPMN_ESQUIVE", nullable=false)),
		@AttributeOverride(name="vitesse", column=@Column(name="SPMN_VITESSE", nullable=false)),
	})
	private Capacite capacite;
	
	@OneToMany(mappedBy="acheteur")
	@JsonIgnore
	private List<Achat> achats;
	
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="CET")
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
	
	
	@JsonIgnore
	public List<Item> getItems() {
		List<Item> myItems = new ArrayList<Item>();
		
		for (Achat a : this.achats) {
			myItems.add(a.getItem());
		}
		
		return myItems;
	}
	

	@Override
	public Coup attaquer(ICombattant victime) {
		Coup myCoup = new Coup();
		
		myCoup.setAttaquant(this);
		myCoup.setVictime(victime);
		
		myCoup.setDegats(100);
		
		return myCoup;
	}
}