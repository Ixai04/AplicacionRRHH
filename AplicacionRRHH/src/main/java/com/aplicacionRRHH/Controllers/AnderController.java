package com.aplicacionRRHH.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnderController {
	

	@GetMapping("/hola")
	public String inicio() {
		
		return "Hola";
	}
	
	
}
