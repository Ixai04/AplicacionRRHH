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

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.ParametroDao;
import com.aplicacionRRHH.Dao.ConvocatoriaDao;
import com.aplicacionRRHH.Dao.CurriculumDao;
import com.aplicacionRRHH.Dao.CurriculumParametrosDao;
import com.aplicacionRRHH.Dao.EntrevistaDao;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Convocatoria;
import com.aplicacionRRHH.modelos.Curriculum;
import com.aplicacionRRHH.modelos.Localidad;

import jakarta.validation.Valid;

@Controller
public class BuscadorController {

	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private ConvocatoriaDao daoConvocatoria;
	
	@Autowired
	private EntrevistaDao daoEntrevista;
	
	@Autowired
	private ParametroDao daoParametro;
	
	@Autowired
	private CurriculumDao daoCurriculum;
	
	@Autowired
	private CurriculumParametrosDao daoCurriculumParametros;
	
	//----------------------------------------------------------------------------------------------
	//----------------------------------------- BUSCADOR -------------------------------------------
	//----------------------------------------------------------------------------------------------
	
	@GetMapping("/convocatoria/{id}/candidatos")
	public String buscadorConvocatoria(@PathVariable("id") long id, Model model){
		model.addAttribute("convocatoria", daoConvocatoria.findOne(id));
		
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "ConvocatoriaCandidatos";
	}
	
	
	@GetMapping("/convocatoria/{idConvocatoria}/candidatos/{idCandidato}")
	public String buscadorConvocatoriaverCandidato(@PathVariable("idConvocatoria") long idConvocatoria,
			@PathVariable("idCandidato") long idCandidato, Model model){

		Candidato candidato = daoCandidato.findOne(idCandidato);
		Convocatoria convocatoria = daoConvocatoria.findOne(idConvocatoria);
		
		model.addAttribute("convocatoria", convocatoria);
		model.addAttribute("candidato", candidato);
		if(candidato.getCurriculum() != null) {
			model.addAttribute("listaCurriculumParametros", daoCurriculumParametros.findFromIDcurriculum(candidato.getCurriculum().getId()));
		}
		model.addAttribute("fecha", convocatoria.getFechaInicio());
		model.addAttribute("listaCurriculums", daoCurriculum.findCurriculumDesdeFecha(convocatoria.getFechaInicio()));
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "ConvocatoriaCandidatos";
	}
	
	
	
	
	
	
	
	
	
	
	/*-------------------------- DE AQUÍ PARA ABAJO SOLO SON EJEMPLOS --------------------------------------*/
	
	@GetMapping("/candidatos/nuevoa")
	public String nuevoCandidato(Map<String, Object> model){

		Candidato candidato = new Candidato();
		model.put("candidato", candidato);
		return "NuevoCandidato";
	}
	
	@PostMapping("/candidatos/nuevoa")
	public String crearCandidato(Candidato candidato) {

		/*
		if(result.hasErrors()) {
			return "NuevoCandidato";
		}
		*/

		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping("candidato/ver/{id}/a")
	public String verCandidato(@PathVariable("id") long id, Map<String, Object> model){

		Candidato candidato = daoCandidato.findOne(id);
		model.put("candidato", candidato);
		return "VerCandidato";
	}
	
	@PostMapping("/actualizara")
	public String actualizarCandidato(@Valid Candidato candidato, BindingResult result) {

		
		if(result.hasErrors()) {
			return "VerCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping(value="candidato/eliminar/{id}/a")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id > 0) {
			daoCandidato.delete(id);
		}
		return "redirect:/candidatos";
	}
	
}