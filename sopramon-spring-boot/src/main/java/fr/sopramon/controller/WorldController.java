package fr.sopramon.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@PreAuthorize("hasRole('JOUEUR')")
public class WorldController {
	@GetMapping({ "/world" })
	public String home() {
		return "world";
	}
}
