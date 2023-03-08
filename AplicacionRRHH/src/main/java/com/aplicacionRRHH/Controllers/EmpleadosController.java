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
import com.lowagie.text.List;

import jakarta.servlet.http.HttpServletRequest;
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
	
	
	//private Usuario usuario;
	
	
	@GetMapping("/verEmpleado")
	public String inicio(Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "empleado");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		return "VerEmpleado";
	}
	
	@PostMapping("/actualizarEmpleado")
	public String actualizar(@Valid Usuario empleado, Model model, BindingResult result, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "empleado");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		
		if(result.hasErrors()) {
			return "VerEmpleado";
			
		}
		
		usuario.setRol(daoRol.findOne(3L));
		usuario.setCandidato(daoCandidato.findOne(1L));
		usuario.setLocalidad(daoLocalidad.findOne(1L));
		daoUsuario.save(empleado);
		return "redirect:/verEmpleado";
	}
	
	
	@GetMapping("/empleados")
	public String empleados(Model model, HttpServletRequest request){
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}	
		
		// -- FIN AUTENTICACIÓN
		model.addAttribute("listaempleados", daoUsuario.getAllEmpleados());
		return "Empleados";
	
	}
	
	
	@GetMapping("empleados/ver/{id}")
	public String verUsuario(@PathVariable("id") long id, Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		Usuario usuario1 = daoUsuario.findOne(id);
		model.put("usuario", usuario1);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "VerEmpleado";
	}
	
	
	@GetMapping("/empleados/nuevo")
	public String nuevoUsuario(Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
			
		}
		// -- FIN AUTENTICACIÓN

		Usuario usuario1 = new Usuario();
		usuario1.setLocalidad(daoLocalidad.findOne(3L));
		model.put("usuario1", usuario1);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "NuevoEmpleado";
	}
	
	@PostMapping("/empleados/nuevo")
	public String crearUsuario(Usuario usuario1, BindingResult result, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario1 == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN


		usuario1.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "NuevoEmpleado";
		}
		
		daoCandidato.save(usuario1);
		return "redirect:/empleados";
	}
	
	@GetMapping(value="empleados/eliminar/{id}")
	public String eliminarempleado(@PathVariable(value="id") Long id, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		if(id > 0) {
			daoUsuario.delete(id);
		}
		return "redirect:/empleados";
	}
	
	
}
