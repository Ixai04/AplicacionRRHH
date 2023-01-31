package com.aplicacionRRHH.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.ConvocatoriaDao;
import com.aplicacionRRHH.Dao.EntrevistaDao;
import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.modelos.Entrevista;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Convocatoria;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class EntrevistaController {

	@Autowired
	private ConvocatoriaDao daoConvocatoria;
	
	@Autowired
	private EntrevistaDao daoEntrevista;
	
	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private UsuarioDao daoUsuario;

	@GetMapping("/convocatoria/{idConvocatoria}/candidatos/{idCandidato}/entrevista/nueva")
	public String nuevaEntrevista(@PathVariable("idConvocatoria") long idConvocatoria, @PathVariable("idCandidato") long idCandidato, Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			System.out.println("Usuario logeado: " + usuario.getNombre() + " " + usuario.getApellido1());
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		Entrevista entrevista = new Entrevista();
		entrevista.setCandidato(daoCandidato.findOne(idCandidato));
		entrevista.setConvocatoria(daoConvocatoria.findOne(idConvocatoria));
		model.addAttribute("entrevista", entrevista);
		model.addAttribute("convocatoria", daoConvocatoria.findOne(idConvocatoria));
		model.addAttribute("candidato", daoCandidato.findOne(idCandidato));
		model.addAttribute("listaGestores", daoUsuario.getAllGestores());
		return "NuevaEntrevista";
	}

	@PostMapping("/convocatoria/{idConvocatoria}/candidatos/{idCandidato}/entrevista/{idEntrevista}")
	public String crearEntrevista(@PathVariable("idConvocatoria") long idConvocatoria, @PathVariable("idCandidato") long idCandidato, Entrevista entrevista, Model model, BindingResult result, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		Convocatoria convocatoria = daoConvocatoria.findOne(idConvocatoria);
		Candidato candidato = daoCandidato.findOne(idCandidato);
		
		if(result.hasErrors()) {model.addAttribute("entrevista", entrevista);
			model.addAttribute("convocatoria", convocatoria);
			model.addAttribute("candidato", candidato);
			model.addAttribute("listaGestores", daoUsuario.getAllGestores());
			return "NuevaEntrevista";
		}else{
			entrevista.setConvocatoria(convocatoria);
			entrevista.setCandidato(candidato);
			entrevista.setEntrevistador(usuario);
			daoEntrevista.save(entrevista);
		}
		
		return "redirect:/convocatoria/"+idConvocatoria;
	}
	
	@GetMapping("/convocatoria/{idConvocatoria}/candidatos/{idCandidato}/entrevista/{idEntrevista}/valorar")
	public String valorarEntrevista(@PathVariable("idConvocatoria") long idConvocatoria, @PathVariable("idCandidato") long idCandidato, @PathVariable("idEntrevista") long idEntrevista, Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			System.out.println("Usuario logeado: " + usuario.getNombre() + " " + usuario.getApellido1());
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		model.addAttribute("entrevista", daoEntrevista.findOne(idEntrevista));
		model.addAttribute("convocatoria", daoConvocatoria.findOne(idConvocatoria));
		model.addAttribute("candidato", daoCandidato.findOne(idCandidato));
		return "ObservacionesEntrevista";
	}

	@PostMapping("/convocatoria/{idConvocatoria}/candidatos/{idCandidato}/entrevista/{idEntrevista}/valorar")
	public String valorarEntrevista(@PathVariable("idConvocatoria") long idConvocatoria,@PathVariable("idEntrevista") long idEntrevista, @PathVariable("idCandidato") long idCandidato, Model model, BindingResult result, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		Entrevista entrevista = daoEntrevista.findOne(idEntrevista);
		
		String observaciones = request.getParameter("observaciones");
		entrevista.setObservaciones(observaciones);
		daoEntrevista.save(entrevista);
		
		
	
		return "redirect:/convocatoria/"+idConvocatoria;
	}
	
}
