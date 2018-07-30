package fr.sopramon.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import fr.sopramon.model.enumerateur.Arene;
import fr.sopramon.model.enumerateur.Type;

@Entity
@Table(name="combat")
public class Combat {
	@Id
	@Column(name="COM_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="COM_DATE")
	private Date date;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="COM_ARENE")
	private Arene arene;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="COM_TYPE")
	private Type type;
	
	@Column(name="COM_TOURS")
	private int tours;
	

	@ManyToOne
	@JoinColumn(name="COM_SOPRAMON_1_ID")
	private Sopramon sopramon1;
	
	@ManyToOne
	@JoinColumn(name="COM_SOPRAMON_2_ID")
	private Sopramon sopramon2;
	
	@ManyToOne
	@JoinColumn(name="COM_BOSS_ID")
	private Boss boss;
	
	@OneToMany(mappedBy="combat")
	private List<Coup> coups;
	
	@ManyToOne
	@JoinColumn(name="COM_SOPRAMON_VAINQUEUR_ID")
	private Sopramon sopramonVainqueur;
	
	@ManyToOne
	@JoinColumn(name="COM_BOSS_VAINQUEUR_ID")
	private Boss bossVainqueur;
	
	
	@Transient
	private ICombattant combattant1;
	
	@Transient
	private ICombattant combattant2;
	
	@Transient
	private ICombattant vainqueur;
	
	
	
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

	public Arene getArene() {
		return arene;
	}

	public void setArene(Arene arene) {
		this.arene = arene;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getTours() {
		return tours;
	}

	public void setTours(int tours) {
		this.tours = tours;
	}

	
	public ICombattant getCombattant1() {
		return combattant1;
	}

	public void setCombattant1(ICombattant combattant1) {
		this.combattant1 = combattant1;
	}

	public ICombattant getCombattant2() {
		return combattant2;
	}

	public void setCombattant2(ICombattant combattant2) {
		this.combattant2 = combattant2;
	}

	public List<Coup> getCoups() {
		return coups;
	}

	public void setCoups(List<Coup> coups) {
		this.coups = coups;
	}

	public ICombattant getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(ICombattant vainqueur) {
		this.vainqueur = vainqueur;
	}
	
	
	
	public boolean duel() {
		if (tours % 2 == 0) {
			System.out.println("JOUEUR 1");
		}
		
		else {
			System.out.println("JOUEUR 2");
		}
		
		return true;
	}
}