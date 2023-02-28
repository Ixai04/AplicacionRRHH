/*package com.aplicacionRRHH.Controllers;
import java.time.LocalDate;
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
		
				
		model.addAttribute("convocatoria", daoConvocatoria.findOne(id));
		List<Curriculum> listaCurriculums = daoCurriculum.findCurriculum();
		System.out.println("Tamano de la lista de curriculums: " + listaCurriculums.size());
		model.addAttribute("listaCurriculums", listaCurriculums);
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		
		return "ConvocatoriaCandidatos";
	}
	
	@GetMapping("/convocatoria/{idConvocatoria}/cambiarFechaFin/{abrirCerrar}")
	public String cerrarConvocatoria(@PathVariable("idConvocatoria") long idConvocatoria, @PathVariable("abrirCerrar") String abrirCerrar, Model model, HttpServletRequest request){
		
		
		Convocatoria convocatoria = daoConvocatoria.findOne(idConvocatoria);	
		if (abrirCerrar.equals("abrir")) {
			convocatoria.setFechaFin(null);
		}
		if (abrirCerrar.equals("cerrar")) {
			convocatoria.setFechaFin(LocalDate.now());
		}
		
		daoConvocatoria.save(convocatoria);
		
		model.addAttribute("convocatoria", convocatoria);
		List<Curriculum> listaCurriculums = daoCurriculum.findCurriculum();
		System.out.println("Tamano de la lista de curriculums: " + listaCurriculums.size());
		model.addAttribute("listaCurriculums", listaCurriculums);
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "redirect:/convocatoria/" + idConvocatoria;
	}
	
	
	@GetMapping("/convocatoria/{idConvocatoria}/{idCandidato}")
	public String buscadorConvocatoriaVerCandidato(@PathVariable("idConvocatoria") long idConvocatoria,
			@PathVariable("idCandidato") long idCandidato, Model model, HttpServletRequest request){
		


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
	
	
	@GetMapping("/convocatoria/{idConvocatoria}/candidatos")
	public String buscadorConvocatoria2(@PathVariable("idConvocatoria") long idConvocatoria, Model model, HttpServletRequest request){
		
		
		model.addAttribute("listaParametros", daoParametro.findParametro());
		
		model.addAttribute("convocatoria", daoConvocatoria.findOne(idConvocatoria));
		List<Curriculum> listaCurriculums = daoCurriculum.findCurriculum();
		model.addAttribute("listaCurriculums", listaCurriculums);
		model.addAttribute("listaCandidatos", daoCandidato.buscarCandidatos(idConvocatoria, 0L, 0L));
		return "BuscarCandidatos";
	}
	
	@PostMapping("/convocatoria/{idConvocatoria}/candidatos")
	public String buscadorConvocatoriaPost(@PathVariable("idConvocatoria") long idConvocatoria, Model model, HttpServletRequest request){
		

		System.out.println(request.getParameter("parametroOrden"));
		System.out.println(request.getParameter("parametroFiltro"));
		
		Long idParametroOrden = Long.parseLong(request.getParameter("parametroOrden"));
		Long idParametroFiltro = Long.parseLong(request.getParameter("parametroFiltro"));
		
		String info = "Mostrando candidatos";
		
		if (idParametroOrden > 0){
			System.out.println("----- Parametro orden: " + daoParametro.findOne(idParametroOrden).getNombre());
			info += "\n Ordenados por " + daoParametro.findOne(idParametroOrden).getNombre();
		}
		if (idParametroFiltro > 0){
			System.out.println("----- Parametro filtro: " + daoParametro.findOne(idParametroFiltro).getNombre());
			info += "\n Filtrados según si " + daoParametro.findOne(idParametroFiltro).getNombre() + " es 6 o superior";
		}

		model.addAttribute("info", info);
		model.addAttribute("listaCandidatos", daoCandidato.buscarCandidatos(idConvocatoria, idParametroOrden, idParametroFiltro));
		model.addAttribute("listaParametros", daoParametro.findParametro());
		
		model.addAttribute("convocatoria", daoConvocatoria.findOne(idConvocatoria));
		List<Curriculum> listaCurriculums = daoCurriculum.findCurriculum();
		model.addAttribute("listaCurriculums", listaCurriculums);
		return "BuscarCandidatos";
	}
	
	
	@GetMapping("/convocatoria/{idConvocatoria}/candidatos/{idCandidato}")
	public String buscadorConvocatoriaVerCandidato2(@PathVariable("idConvocatoria") long idConvocatoria,
			@PathVariable("idCandidato") long idCandidato, Model model, HttpServletRequest request){
		
				
		model.addAttribute("listaParametros", daoParametro.findParametro());
		
		Candidato candidato = daoCandidato.findOne(idCandidato);
		Convocatoria convocatoria = daoConvocatoria.findOne(idConvocatoria);
		
		model.addAttribute("convocatoria", convocatoria);
		model.addAttribute("candidato", candidato);
		if(candidato.getCurriculum() != null) {
			model.addAttribute("listaCurriculumParametros", daoCurriculumParametros.findFromIDcurriculum(candidato.getCurriculum().getId()));
		}
		model.addAttribute("listaCandidatos", daoCandidato.buscarCandidatos(idConvocatoria, 0L, 0L));
		model.addAttribute("fecha", convocatoria.getFechaInicio());
		model.addAttribute("listaCurriculums", daoCurriculum.findCurriculum());
		model.addAttribute("candidatosEntrevista", daoCandidato.findCandidato());
		model.addAttribute("candidatosTodos", daoCandidato.findCandidato());
		return "BuscarCandidatos";
	}
	
	
}*/