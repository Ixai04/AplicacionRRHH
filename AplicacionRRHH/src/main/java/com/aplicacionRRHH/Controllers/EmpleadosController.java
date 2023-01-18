package com.aplicacionRRHH.Controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Convocatoria;

import jakarta.validation.Valid;

@Controller
public class EmpleadosController {

	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private LocalidadDao daoLocalidad;
	
	
	@GetMapping("/verEmpleado")
	public String inicio(Model model){

		//model.addAttribute("candidatos", daoCandidato.findCandidato());
		return "VerEmpleado";
	}
	
	@GetMapping("/nuevo3")
	public String nuevoCandidato(Map<String, Object> model){

		Candidato candidato = new Candidato();
		candidato.setLocalidad(daoLocalidad.findOne(3L));
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "NuevoCandidato";
	}
	
	@PostMapping("/nuevo3")
	public String crearConvocatoria(Candidato candidato) {

		/*
		if(result.hasErrors()) {
			return "NuevoCandidato";
		}
		*/

		candidato.setLocalidad(daoLocalidad.findOne(3L));
		daoCandidato.save(candidato);
		return "redirect:/candidatos/todos";
	}
	

}
