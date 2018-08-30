package fr.sopramon.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.sopramon.dao.IDAOUtilisateur;
import fr.sopramon.model.Capacite;
import fr.sopramon.model.Sopramon;
import fr.sopramon.model.Utilisateur;


@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	
	@GetMapping("/login")
	public String login() {
		return "account/login";
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
		Utilisateur utilisateur = daoUtilisateur.auth(username, password);
		
		
		if ((utilisateur == null) || (utilisateur instanceof Sopramon)) {
			model.addAttribute("loginError", true);
			return "account/login";
		}
		
		return "redirect:/home";
	}
	
	
	@GetMapping("/subscribe")
	public String subscribe() {
		return "account/subscribe";
	}
	
	
	@PostMapping("/subscribe")
	public String subscribe(@ModelAttribute Sopramon sopramon,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date dateNaissanceSopramon,
			Model model) {
		sopramon.setDateNaissance(dateNaissanceSopramon);
		sopramon.setCapacite(new Capacite());
		
		daoUtilisateur.save(sopramon);
		
		return "redirect:login";
	}
}
