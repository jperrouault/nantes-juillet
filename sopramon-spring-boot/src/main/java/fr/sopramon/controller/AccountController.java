package fr.sopramon.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
import fr.sopramon.security.UtilisateurPrincipal;

@Controller
@RequestMapping("/account")
public class AccountController implements AuthenticationSuccessHandler {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	
	@GetMapping("/login")
	public String login() {
		return "account/login";
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
		
		sopramon.setPassword(new BCryptPasswordEncoder().encode(sopramon.getPassword()));
		
		daoUtilisateur.save(sopramon);
		
		return "redirect:login";
	}


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if (((UtilisateurPrincipal)authentication.getPrincipal()).isAdmin()) {
			response.sendRedirect("/home");
		}
		
		else {
			response.sendRedirect("http://localhost:3000/");
		}
	}
}
