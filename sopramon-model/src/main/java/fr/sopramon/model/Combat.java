package fr.sopramon.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;

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
	@JsonSetter
	private Sopramon sopramon1;
	
	@ManyToOne
	@JoinColumn(name="COM_SOPRAMON_2_ID")
	@JsonSetter
	private Sopramon sopramon2;
	
	@ManyToOne
	@JoinColumn(name="COM_BOSS_ID")
	@JsonSetter
	private Boss boss;
	
	@OneToMany(mappedBy="combat", cascade= { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Coup> coups;
	
	@ManyToOne
	@JoinColumn(name="COM_SOPRAMON_VAINQUEUR_ID")
	private Sopramon sopramonVainqueur;
	
	@ManyToOne
	@JoinColumn(name="COM_BOSS_VAINQUEUR_ID")
	private Boss bossVainqueur;
	
	@Transient
	private int combattant1PointsDeVie;
	
	@Transient
	private int combattant2PointsDeVie;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="CET")
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

	public List<Coup> getCoups() {
		return coups;
	}

	public void setCoups(List<Coup> coups) {
		this.coups = coups;
	}
	
	
	public ICombattant getCombattant1() {
		if (this.boss != null) {
			return this.boss;
		}
		
		return this.sopramon1;
	}

	public void setCombattant1(ICombattant combattant1) {
		if (combattant1 instanceof Boss) {
			this.boss = (Boss)combattant1;
		}
		
		else {
			this.sopramon1 = (Sopramon)combattant1;
		}
	}

	public ICombattant getCombattant2() {
		return this.sopramon2;
	}

	public void setCombattant2(ICombattant combattant2) {
		this.sopramon2 = (Sopramon)combattant2;
	}

	public ICombattant getVainqueur() {
		if (this.bossVainqueur != null) {
			return this.bossVainqueur;
		}
		
		return this.sopramonVainqueur;
	}

	public void setVainqueur(ICombattant vainqueur) {
		if (vainqueur instanceof Boss) {
			this.bossVainqueur = (Boss)vainqueur;
		}
		
		else {
			this.sopramonVainqueur = (Sopramon)vainqueur;
		}
	}
	
	
	
	
	public Combat() {
		this.date = new Date();
		this.coups = new ArrayList<Coup>();
	}
	
	
	public boolean duel() {
		Coup myCoup = null;
		
		//INITIALISATION DES PV SI DEMARRAGE DU COMBAT
		if (this.combattant1PointsDeVie == 0) {
			this.combattant1PointsDeVie = this.getCombattant1().getCapacite().getPointsDeVie();
			this.combattant2PointsDeVie = this.getCombattant2().getCapacite().getPointsDeVie();
		}
		
		//ON FAIT JOUER LE BON COMBATTANT
		if (tours % 2 == 0) {
			myCoup = this.getCombattant1().attaquer(this.getCombattant2());
			this.combattant2PointsDeVie -= myCoup.getDegats();
		}
		
		else {
			myCoup = this.getCombattant2().attaquer(this.getCombattant1());
			this.combattant1PointsDeVie -= myCoup.getDegats();
		}
		
		
		//ON INCREMENTE LE NOMBRE DE TOURS, ET ON AJOUTE LE COUP A LA LISTE DES COUPS
		this.tours++;
		this.coups.add(myCoup);
		
		
		//SI LE COMBAT EST TERMINE
		if (this.combattant1PointsDeVie <= 0 || this.combattant2PointsDeVie <= 0) {
			//ON CHERCHE ET ON AFFECTE LE VAINQUEUR
			if (this.combattant1PointsDeVie > 0) {
				this.setVainqueur(this.getCombattant1());
			}
			
			else {
				this.setVainqueur(this.getCombattant2());
			}
			
			return false;
		}
		
		return true;
	}
}