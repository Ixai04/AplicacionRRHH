package com.aplicacionRRHH.Controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.modelos.Localidad;

@Controller
public class PruebaController {

	@Autowired
	private UsuarioDao dao;
	
	@Autowired
	private LocalidadDao daoLocalidad;
	
	
	@GetMapping("/pruebas")
	public String inicio(Model model){
		
		model.addAttribute("usuarios", dao.findUsuario());
		model.addAttribute("candidatos", dao.findCandidato());
		model.addAttribute("provincias", dao.findProvincia());
		model.addAttribute("localidades", daoLocalidad.findLocalidad());
		System.out.println(daoLocalidad.findLocalidad().toString());
		return "Pruebas";
	}
	
//	@GetMapping("/form")
//	public String crear(Map<String, Object> model){
//		
//		Provincia provincia = new Provincia();
//		model.put("provincia", provincia);
//		return "CrearProvincias";
//	}
//	
//	
//	@PostMapping("/form")
//	public String crearProvincia(Provincia provincia) {
//		dao.save(provincia);
//	return "redirect:inicio";
//	}
//	
//	@GetMapping(value="/form/{id}")
//	public String editarProvincia(@PathVariable(value="id") Long id, Map<String, Object> model) {
//		
//		Provincia provincia = null;
//		
//		if(id > 0) {
//			provincia = dao.findOne(id);
//		}
//		else {
//			return "redirect:/inicio";
//		}
//		
//		model.put("provincia", provincia);
//	
//		return "CrearProvincias";
//	}
//	
//	@GetMapping(value="/eliminar/{id}")
//	public String eliminar(@PathVariable(value="id") Long id) {
//		if(id > 0) {
//			dao.delete(id);
//		}
//		return "redirect:/inicio";
//	}
	
	@GetMapping("/formlocalidad")
	public String crear(Map<String, Object> model){
		
		Localidad localidad = new Localidad();
		localidad.setProvincia(dao.findOne(1L));
		model.put("localidad", localidad);
		return "CrearLocalidades";
	}
	
	
	@PostMapping("/formlocalidad")
	public String crearLocalidad(Localidad localidad) {
		System.out.println(localidad);
		daoLocalidad.save(localidad);
	return "redirect:/pruebas";
	}
	
	@GetMapping(value="/formlocalidad/{id}")
	public String editarLocalidad(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Localidad localidad = null;
		
		if(id > 0) {
			localidad = daoLocalidad.findOne(id);
		}
		else {
			return "redirect:/pruebas";
		}
		
		model.put("localidad", localidad);
		model.put("provincias", dao.findProvincia());
		return "EditarLocalidades";
	}
	
	@GetMapping(value="/eliminarlocalidad/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id > 0) {
			daoLocalidad.delete(id);
		}
		return "redirect:/pruebas";
	}
		
		
	
	
	
}
