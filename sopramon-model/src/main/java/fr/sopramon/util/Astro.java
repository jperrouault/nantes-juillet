package fr.sopramon.util;

import java.util.Calendar;
import java.util.Date;

import fr.sopramon.model.Signe;

public class Astro {
	private int day;
	private int month;
	
	public Astro(Date date) {
		Calendar myCalendar = Calendar.getInstance();
		
		myCalendar.setTime(date);
		this.day = myCalendar.get(Calendar.DAY_OF_MONTH);
		this.month = myCalendar.get(Calendar.MONTH) + 1;
	}
	
	
	public Signe getSigne() {
		//Démarre par Capricorne (Janvier), s'arrête à Sagittaire (Décembre)
		Signe mySigne = new Signe();
		int[] myChangementsSigne = { 20, 19, 20, 19, 20, 21, 22, 22, 22, 23, 22, 21 };
	    
	    if (this.day <= myChangementsSigne[this.month - 1]) {
	    	mySigne.setId(this.month);
	    }
		
	    else {
	    	mySigne.setId((this.month % 12) + 1);
	    }
	    
		return mySigne;
	}
}