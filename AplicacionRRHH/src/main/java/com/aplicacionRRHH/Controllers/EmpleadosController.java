package com.aplicacionRRHH.Controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.Dao.RolDao;
import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Convocatoria;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.validation.Valid;

@Controller
public class EmpleadosController {

	@Autowired
	private UsuarioDao daoUsuario;
	
	@Autowired
	private LocalidadDao daoLocalidad;
	
	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private RolDao daoRol;
	
	private Usuario usuario;
	
	
	@GetMapping("/verEmpleado")
	public String inicio(Model model){
		model.addAttribute("usuario", daoUsuario.findOne(3L));
		return "VerEmpleado";
	}
	
	@PostMapping("/actualizarEmpleado")
	public String actualizar(@Valid Usuario usuario, BindingResult result){
		
		if(result.hasErrors()) {
			return "VerEmpleado";
			
		}
		
		usuario.setRol(daoRol.findOne(3L));
		usuario.setCandidato(daoCandidato.findOne(1L));
		usuario.setLocalidad(daoLocalidad.findOne(1L));
		daoUsuario.save(usuario);
		return "redirect:/verEmpleado";
	}
	
	@GetMapping("/nuevo3")
	public String nuevoEmpleado(Map<String, Object> model){

		Usuario usuario = new Usuario();
		usuario.setLocalidad(daoLocalidad.findOne(3L));
		model.put("usuario", usuario);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "nuevoEmpleado";
	}
	
	@PostMapping("/nuevo3")
	public String crearConvocatoria(Usuario usuario) {

		/*
		if(result.hasErrors()) {
			return "NuevoCandidato";
		}
		*/

		usuario.setLocalidad(daoLocalidad.findOne(3L));
		daoUsuario.save(usuario);
		return "redirect:/usuarios/todos";
	}
	

}
