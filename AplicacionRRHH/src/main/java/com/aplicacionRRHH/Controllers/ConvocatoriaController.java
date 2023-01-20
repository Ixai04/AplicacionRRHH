package com.aplicacionRRHH.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aplicacionRRHH.Dao.ConvocatoriaDao;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Convocatoria;

import jakarta.validation.Valid;

@Controller
public class ConvocatoriaController {

	@Autowired
	private ConvocatoriaDao daoConvocatoria;

	@GetMapping("/convocatorias")
	public String inicio(Model model) {

		model.addAttribute("convocatorias", daoConvocatoria.findConvocatoria());
		return "Convocatorias";
	}

	@GetMapping("/convocatoria/nueva")
	public String crear(Map<String, Object> model) {

		Convocatoria convocatoria = new Convocatoria();
		model.put("convocatoria", convocatoria);
		return "NuevaConvocatoria";
	}

	@PostMapping("/convocatoria/nueva")
	public String crearConvocatoria(@Valid Convocatoria convocatoria, BindingResult result) {

		if(result.hasErrors()) {
			return "NuevaConvocatoria";
		}
		
		daoConvocatoria.save(convocatoria);
		return "redirect:/convocatorias";
	}
	
	@GetMapping("convocatoria/ver/{id}")
	public String verConvocatoria(@PathVariable("id") long id, Map<String, Object> model){

		Convocatoria convocatoria = daoConvocatoria.findOne(id);
		model.put("convocatoria", convocatoria);
		return "VerConvocatoria";
	}
	
	@PostMapping("/convocatoria/actualizar")
	public String actualizarConvocatoria(@Valid Convocatoria convocatoria, BindingResult result) {

		
		if(result.hasErrors()) {
			return "VerConvocatoria";
		}
		
		daoConvocatoria.save(convocatoria);
		return "redirect:/convocatorias";
	}
	
	@GetMapping(value="convocatoria/eliminar/{id}")
	public String eliminarConvocatoria(@PathVariable(value="id") Long id) {
		if(id > 0) {
			daoConvocatoria.delete(id);
		}
		return "redirect:/convocatorias";
	}
	
}
