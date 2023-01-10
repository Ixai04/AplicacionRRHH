package com.aplicacionRRHH.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InicioController {
	//prueba Luis

	@GetMapping("/inicio")
	public String inicio(Model model){
		
		model.addAttribute("logueado", false);
		return "Inicio";
	}
	
	@PostMapping("/inicio")
	public String login(Model model, @RequestParam("usuario") String usuario, @RequestParam("contrasena") String contrasena) {
		Boolean logueado = false;
		
		if(usuario.equals("a") && contrasena.equals("a")) {
			logueado = true;
		}
		

		model.addAttribute("usuario", usuario);
		model.addAttribute("contrasena", contrasena);
		model.addAttribute("nombre", "Jeff Bezos");
		model.addAttribute("msgError", "He entrado al POST inicio");
		model.addAttribute("logueado", logueado);
		return "Inicio";
	}
}
