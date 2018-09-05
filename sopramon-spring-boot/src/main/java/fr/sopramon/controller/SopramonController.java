package fr.sopramon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.sopramon.dao.IDAOSopramon;


@Controller
@RequestMapping("/sopramons")
@PreAuthorize("hasRole('ADMIN')")
public class SopramonController {
	@Autowired
	private IDAOSopramon daoSopramon;
	
	
	
	@GetMapping("/liste")
	public String findAll(Model model) {
		model.addAttribute("sopramons", daoSopramon.findAll());
		return "sopramons/liste";
	}
	
	
	
	@ModelAttribute("isPageSopramons")
	public boolean isPageActive() {
		return true;
	}
}
