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
@Table(name="coup")
public class Coup {
	@Id
	@Column(name="COUP_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="COUP_DATE")
	private Date date;
	
	@Column(name="COUP_DEGATS")
	private int degats;

	@Column(name="COUP_PERSISTANCE")
	private int persistance;

	@ManyToOne
	@JoinColumn(name="COUP_COMBAT_ID")
	private Combat combat;
	

	@ManyToOne
	@JoinColumn(name="COUP_SOPRAMON_ATTAQUANT_ID")
	private Sopramon sopramonAttaquant;

	@ManyToOne
	@JoinColumn(name="COUP_BOSS_ATTAQUANT_ID")
	private Boss bossAttaquant;

	@ManyToOne
	@JoinColumn(name="COUP_SOPRAMON_VICTIME_ID")
	private Sopramon sopramonVictime;

	@ManyToOne
	@JoinColumn(name="COUP_BOSS_VICTIME_ID")
	private Boss bossVictime;
	
	
	
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

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public int getPersistance() {
		return persistance;
	}

	public void setPersistance(int persistance) {
		this.persistance = persistance;
	}
	
	
	public ICombattant getAttaquant() {
		if (this.sopramonAttaquant != null) {
			return this.sopramonAttaquant;
		}
		
		return bossAttaquant;
	}

	public void setAttaquant(ICombattant attaquant) {
		if (attaquant instanceof Sopramon) {
			this.sopramonAttaquant = (Sopramon)attaquant;
		}
		
		else {
			this.bossAttaquant = (Boss)attaquant;
		}
	}

	public ICombattant getVictime() {
		if (this.sopramonVictime != null) {
			return this.sopramonVictime;
		}
		
		return bossVictime;
	}

	public void setVictime(ICombattant victime) {
		if (victime instanceof Sopramon) {
			this.sopramonVictime = (Sopramon)victime;
		}
		
		else {
			this.bossVictime = (Boss)victime;
		}
	}
	
	
	
	public Coup() {
		this.date = new Date();
	}
}