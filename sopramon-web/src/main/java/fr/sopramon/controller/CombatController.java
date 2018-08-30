package fr.sopramon.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.sopramon.dao.IDAOCombat;


@Controller
@RequestMapping("/combats")
public class CombatController {
	@Autowired
	private IDAOCombat daoCombat;
	
	
	
	@GetMapping("/liste")
	@Transactional
	public String findAll(Model model) {
		model.addAttribute("combats", daoCombat.findAll());
		return "combats/liste";
	}
	
	
	
	@ModelAttribute("isPageCombats")
	public boolean isPageActive() {
		return true;
	}
}
