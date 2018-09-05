package fr.sopramon.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@PreAuthorize("hasRole('ADMIN')")
public class HomeController {
	@GetMapping({ "/", "/home" })
	public String home() {
		return "home";
	}
	
	
	
	@ModelAttribute("isPageHome")
	public boolean isPageActive() {
		return true;
	}
}
