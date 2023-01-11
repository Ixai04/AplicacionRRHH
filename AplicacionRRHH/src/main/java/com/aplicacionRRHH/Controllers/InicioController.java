package com.aplicacionRRHH.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacionRRHH.Dao.UsuarioDao;

@Controller
public class InicioController {

	@Autowired
	private UsuarioDao dao;
	
	
	@GetMapping("/inicio")
	public String inicio(Model model){
		
		model.addAttribute("usuarios", dao.findUsuario());
		model.addAttribute("candidatos", dao.findCandidato());
		model.addAttribute("provincias", dao.findProvincia());
		model.addAttribute("localidades", dao.findLocalidad());
		return "Inicio";
	}
	
	@GetMapping("/crearProvincia")
	public String crear(){
		return "CrearProvincias";
	}
	
	
	@PostMapping("/crearProvincia")
	public String crearProvincia(Model model, @RequestParam("nombre") String nombre) {
		dao.save(nombre);
	return "redirect:/inicio";
	}
	/*@PostMapping("/inicio")
	*public String login(Model model, @RequestParam("usuario") String usuario, @RequestParam("contrasena") String contrasena) {
		Boolean logueado = false;
		
	if(usuario.equals("a") && contrasena.equals("a")) {
			//logueado = true;
		}
		

		model.addAttribute("usuario", usuario);
		model.addAttribute("contrasena", contrasena);
		model.addAttribute("nombre", "Jeff Bezos");
		model.addAttribute("msgError", "He entrado al POST inicio");
		model.addAttribute("logueado", logueado);
		return "Inicio";
	}*/
}
