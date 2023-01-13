package com.aplicacionRRHH.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.aplicacionRRHH.Dao.ConvocatoriaDao;
import com.aplicacionRRHH.modelos.Convocatoria;

import jakarta.validation.Valid;

@Controller
public class ConvocatoriaController {

	@Autowired
	private ConvocatoriaDao dao;

	@GetMapping("/convocatorias")
	public String inicio(Model model) {

		model.addAttribute("convocatoria", dao.findConvocatoria());
		return "ConvocatoriasVista";
	}

	@GetMapping("/formConvocatoria")
	public String crear(Map<String, Object> model) {

		Convocatoria convocatoria = new Convocatoria();
		model.put("convocatoria", convocatoria);
		return "FormularioConvocatorias";
	}

	@PostMapping("/formConvocatoria")
	public String crearConvocatoria(@Valid Convocatoria convocatoria, BindingResult result) {

		if(result.hasErrors()) {
			return "FormularioConvocatorias";
		}
		
		dao.save(convocatoria);
		return "redirect:/convocatorias";
	}
}
