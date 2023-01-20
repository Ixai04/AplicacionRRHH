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
import com.aplicacionRRHH.Dao.CurriculumDao;
import com.aplicacionRRHH.Dao.CurriculumParametrosDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.Dao.ParametroDao;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Convocatoria;
import com.aplicacionRRHH.modelos.Curriculum;
import com.aplicacionRRHH.modelos.CurriculumParametros;
import com.aplicacionRRHH.modelos.Localidad;
import com.aplicacionRRHH.modelos.Parametro;

import jakarta.validation.Valid;

@Controller
public class CandidatoController {

	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private CurriculumDao daoCurriculum;
	
	@Autowired
	private LocalidadDao daoLocalidad;
	
	@Autowired
	private ParametroDao daoParametro;
	
	@Autowired
	private CurriculumParametrosDao daoCurriculumParametros;
	
	//----------------------------------------------------------------------------------------------
	//----------------------------------------- CANDIDATOS -----------------------------------------
	//----------------------------------------------------------------------------------------------
	
	@GetMapping("/candidatos")
	public String inicio(Model model){
		model.addAttribute("candidatos", daoCandidato.findCandidato());
		return "Candidatos";
	}
	
	@GetMapping("/candidatos/nuevo")
	public String nuevoCandidato(Map<String, Object> model){

		Candidato candidato = new Candidato();
		candidato.setLocalidad(daoLocalidad.findOne(3L));
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "NuevoCandidato";
	}
	
	@PostMapping("/candidatos/nuevo")
	public String crearCandidato(Candidato candidato, BindingResult result) {


		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "NuevoCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping("candidato/ver/{id}")
	public String verCandidato(@PathVariable("id") long id, Map<String, Object> model){

		Candidato candidato = daoCandidato.findOne(id);
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "VerCandidato";
	}
	
	@PostMapping("/actualizar")
	public String actualizarCandidato(@Valid Candidato candidato, BindingResult result) {

		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "VerCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping(value="candidato/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id > 0) {
			daoCandidato.delete(id);
		}
		return "redirect:/candidatos";
	}
	
	//----------------------------------------------------------------------------------------------
	//----------------------------------------- CURRICULUM -----------------------------------------
	//----------------------------------------------------------------------------------------------
	
	@GetMapping("/nuevoCurriculum/{id}")
	public String nuevoCurriculum(@PathVariable(value="id") Long id, Map<String, Object> model){

		Curriculum curriculum = new Curriculum();
		model.put("curriculum", curriculum);
		model.put("candidato", daoCandidato.findOne(id));
		model.put("parametros", daoParametro.findParametro());
		return "NuevoCurriculum";
	}
	
	@PostMapping("/nuevoCurriculum/{id}")
	public String crearCurriculum(@PathVariable(value="id") Long id, Curriculum curriculum) {

		/*
		if(result.hasErrors()) {
			return "NuevoCandidato";
		}
		*/

		curriculum.setCandidato(daoCandidato.findOne(id));
		daoCurriculum.save(curriculum);
		
		Long lastCurriculumID = daoCurriculum.findLastCurriculumID();
		Curriculum lastCurriculum = daoCurriculum.findOne(lastCurriculumID);
		
		for (Parametro parametro : daoParametro.findParametro()) {
			CurriculumParametros curriculumParametro = new CurriculumParametros();

			curriculumParametro.setParametro(daoParametro.findOne(parametro.getId()));
			curriculumParametro.setCurriculum(lastCurriculum);
			curriculumParametro.setValoracion(5);
			
			daoCurriculumParametros.save(curriculumParametro);
		}
		
		return "redirect:/candidatos";
	}
	
	@GetMapping(value="/eliminarCurriculum/{id}")
    public String eliminarCurriculum(@PathVariable(value="id") Long id) {
        if(id > 0) {
        	
        	for (CurriculumParametros currParr : daoCandidato.findOne(id).getCurriculum().getCurriculumParametros()) {
				daoCurriculumParametros.delete(currParr.getId());
			}
			
            daoCurriculum.delete(daoCandidato.findOne(id).getCurriculum().getId());
        }
        return "redirect:/candidatos";
    }
	
}