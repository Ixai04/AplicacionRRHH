package com.aplicacionRRHH.Controllers;
import java.util.List;
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
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;
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
	
	@GetMapping("/convocatoria/{id}")
	public String buscadorConvocatoria(@PathVariable("id") long id, Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
				
		model.addAttribute("convocatoria", daoConvocatoria.findOne(id));
		List<Curriculum> listaCurriculums = daoCurriculum.findCurriculum();
		System.out.println("Tamano de la lista de curriculums: " + listaCurriculums.size());
		model.addAttribute("listaCurriculums", listaCurriculums);
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "ConvocatoriaCandidatos";
	}
	
	
	@GetMapping("/convocatoria/{idConvocatoria}/{idCandidato}")
	public String buscadorConvocatoriaVerCandidato(@PathVariable("idConvocatoria") long idConvocatoria,
			@PathVariable("idCandidato") long idCandidato, Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN


		Candidato candidato = daoCandidato.findOne(idCandidato);
		Convocatoria convocatoria = daoConvocatoria.findOne(idConvocatoria);
		
		model.addAttribute("convocatoria", convocatoria);
		model.addAttribute("candidato", candidato);
		if(candidato.getCurriculum() != null) {
			model.addAttribute("listaCurriculumParametros", daoCurriculumParametros.findFromIDcurriculum(candidato.getCurriculum().getId()));
		}
		model.addAttribute("fecha", convocatoria.getFechaInicio());
		model.addAttribute("listaCurriculums", daoCurriculum.findCurriculum());
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "ConvocatoriaCandidatos";
	}
	
	
	@GetMapping("/convocatoria/{id}/candidatos")
	public String buscadorConvocatoria2(@PathVariable("id") long id, Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		model.addAttribute("listaParametros", daoParametro.findParametro());
		
		model.addAttribute("convocatoria", daoConvocatoria.findOne(id));
		List<Curriculum> listaCurriculums = daoCurriculum.findCurriculum();
		model.addAttribute("listaCurriculums", listaCurriculums);
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "BuscarCandidatos";
	}
	
	@PostMapping("/convocatoria/{id}/candidatos")
	public String buscadorConvocatoriaPost(@PathVariable("id") long id, Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		System.out.println(request.getParameter("parametroOrden"));
		System.out.println(request.getParameter("parametroFiltro"));
		
		Long idParametroOrden = Long.parseLong(request.getParameter("parametroOrden"));
		Long idParametroFiltro = Long.parseLong(request.getParameter("parametroFiltro"));
		
		String info = "Mostrando candidatos";
		
		if (idParametroOrden > 0){
			info += "\n Ordenados por " + daoParametro.findOne(idParametroOrden).getNombre();
		}
		if (idParametroFiltro > 0){
			info += "\n Filtrados según si " + daoParametro.findOne(idParametroOrden).getNombre() + " es 6 o superior";
		}

		model.addAttribute("info", info);
		model.addAttribute("candidatosEntrevista", daoCandidato.buscarCandidatos(idParametroOrden, idParametroFiltro));
		model.addAttribute("listaParametros", daoParametro.findParametro());
		
		model.addAttribute("convocatoria", daoConvocatoria.findOne(id));
		List<Curriculum> listaCurriculums = daoCurriculum.findCurriculum();
		model.addAttribute("listaCurriculums", listaCurriculums);
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "BuscarCandidatos";
	}
	
	
	@GetMapping("/convocatoria/{idConvocatoria}/candidatos/{idCandidato}")
	public String buscadorConvocatoriaVerCandidato2(@PathVariable("idConvocatoria") long idConvocatoria,
			@PathVariable("idCandidato") long idCandidato, Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
				
		model.addAttribute("listaParametros", daoParametro.findParametro());
		
		Candidato candidato = daoCandidato.findOne(idCandidato);
		Convocatoria convocatoria = daoConvocatoria.findOne(idConvocatoria);
		
		model.addAttribute("convocatoria", convocatoria);
		model.addAttribute("candidato", candidato);
		if(candidato.getCurriculum() != null) {
			model.addAttribute("listaCurriculumParametros", daoCurriculumParametros.findFromIDcurriculum(candidato.getCurriculum().getId()));
		}
		model.addAttribute("fecha", convocatoria.getFechaInicio());
		model.addAttribute("listaCurriculums", daoCurriculum.findCurriculum());
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "BuscarCandidatos";
	}
	
	
}