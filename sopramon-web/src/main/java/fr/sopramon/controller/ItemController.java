package fr.sopramon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.sopramon.dao.IDAOItem;
import fr.sopramon.model.Item;


@Controller
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private IDAOItem daoItem;
	
	
	
	@GetMapping("/liste")
	public String findAll(Model model) {
		model.addAttribute("items", daoItem.findAll());
		return "items/liste";
	}
	
	
	
	@GetMapping("/ajouter")
	public String ajouter() {
		return "items/formulaire";
	}
	
	@PostMapping("/ajouter")
	public String ajouter(@ModelAttribute Item item) {
		daoItem.save(item);
		return "redirect:liste";
	}
	
	
	
	@GetMapping("/editer")
	public String editer(@RequestParam int id, Model model) {
		model.addAttribute("item", daoItem.findById(id).get());
		return "items/formulaire";
	}
	
	@PostMapping("/editer")
	public String editer(@ModelAttribute Item item) {
		daoItem.save(item);
		return "redirect:liste";
	}
	
	
	
	@GetMapping("/supprimer")
	public String supprimer(@RequestParam int id) {
		daoItem.deleteById(id);
		return "redirect:liste";
	}
	
	
	
	@ModelAttribute("isPageItems")
	public boolean isPageActive() {
		return true;
	}
}
