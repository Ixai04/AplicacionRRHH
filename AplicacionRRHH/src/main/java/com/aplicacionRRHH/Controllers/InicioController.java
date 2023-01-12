package com.aplicacionRRHH.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.modelos.Usuario;

@Controller
public class InicioController {
	
	@Autowired
	private UsuarioDao dao;

	@GetMapping("/inicio")
	public String inicio() {
		
		return "Inicio";
	}
	
	
	@PostMapping("/login")
	public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		Usuario usuario = dao.login(username, password);
		model.addAttribute("usuario", usuario);
		
		if(usuario == null) {
		model.addAttribute("error", "Login incorrecto");
		}
		return "Inicio";
	}
	
}
