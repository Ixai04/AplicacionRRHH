package com.aplicacionRRHH.Controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.modelos.Candidato;

@Controller
@RequestMapping("candidatos")
public class CandidatoController {

	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private LocalidadDao daoLocalidad;
	
	
	@GetMapping("/todos")
	public String inicio(Model model){

		model.addAttribute("candidatos", daoCandidato.findCandidato());
		return "VerCandidatos";
	}
	
	@GetMapping("/nuevo")
	public String nuevoCandidato(Model model){

		Candidato candidato = new Candidato();
		model.addAttribute("candidato", candidato);
		model.addAttribute("localidades", daoLocalidad.findLocalidad());
		return "NuevoCandidato";
	}
	
	
	
	/*
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
	*/
}