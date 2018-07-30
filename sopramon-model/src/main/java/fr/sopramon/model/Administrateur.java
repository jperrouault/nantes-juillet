package fr.sopramon.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="administrateur")
@PrimaryKeyJoinColumn(name="ADM_ID", referencedColumnName="UTI_ID")
public class Administrateur extends Utilisateur
{

}