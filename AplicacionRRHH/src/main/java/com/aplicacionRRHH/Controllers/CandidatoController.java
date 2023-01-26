package com.aplicacionRRHH.Controllers;
import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpHeaders;
import java.util.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.CurriculumDao;
import com.aplicacionRRHH.Dao.CurriculumParametrosDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.Dao.ParametroDao;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Curriculum;
import com.aplicacionRRHH.modelos.CurriculumParametros;
import com.aplicacionRRHH.modelos.Parametro;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
	public String inicio(Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		model.addAttribute("candidatos", daoCandidato.findCandidato());
		return "Candidatos";
	}
	
	@GetMapping("/candidatos/nuevo")
	public String nuevoCandidato(Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		Candidato candidato = new Candidato();
		candidato.setLocalidad(daoLocalidad.findOne(3L));
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "NuevoCandidato";
	}
	
	@PostMapping("/candidatos/nuevo")
	public String crearCandidato(Candidato candidato, BindingResult result, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN


		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "NuevoCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping("candidato/ver/{id}")
	public String verCandidato(@PathVariable("id") long id, Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		Candidato candidato = daoCandidato.findOne(id);
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "VerCandidato";
	}
	
	@PostMapping("/actualizar")
	public String actualizarCandidato(@Valid Candidato candidato, BindingResult result, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "VerCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping(value="candidato/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		if(id > 0) {
			daoCandidato.delete(id);
		}
		return "redirect:/candidatos";
	}
	
	//----------------------------------------------------------------------------------------------
	//----------------------------------------- CURRICULUM -----------------------------------------
	//----------------------------------------------------------------------------------------------
	
	@GetMapping("/nuevoCurriculum/{id}")
	public String nuevoCurriculum(@PathVariable(value="id") Long id, Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		Curriculum curriculum = new Curriculum();
		model.put("curriculum", curriculum);
		model.put("candidato", daoCandidato.findOne(id));
		model.put("parametros", daoParametro.findParametro());
		return "NuevoCurriculum";
	}
	
	@PostMapping("/nuevoCurriculum/{id}")
	public String crearCurriculum(@PathVariable(value="id") Long id, Curriculum curriculum, @RequestParam("file") MultipartFile file, @RequestParam("valoraciones") Integer[] valoraciones, Map<String, Object> model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		curriculum.setNombre(daoCandidato.findOne(id).getNombre() + "-cv.pdf");
		curriculum.setCandidato(daoCandidato.findOne(id));
		curriculum.setFecha(LocalDate.now());
		
		if(file.getSize() > 37386) {
			
			model.put("curriculum", curriculum);
			model.put("candidato", daoCandidato.findOne(id));
			model.put("parametros", daoParametro.findParametro());
			model.put("errorArchivo","Demasiado grande, intentelo de nuevo con uno mas pequeño");
			return "NuevoCurriculum";
		}
		
			 String fileName = curriculum.getNombre() + ".pdf";
				try {
					file.transferTo(new File("C:\\Curriculums\\" + fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
		 

		model.put("file", file);
		model.put("filesize", file.getSize());

		
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
		
		return "redirect:/curriculum/" + curriculum.getId();
	}
	
	@GetMapping("curriculum/{id}")
	public String verCurriculum(@PathVariable("id") long id, Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		Curriculum curriculum = daoCurriculum.findOne(id);
		
		model.put("curriculum", curriculum);
		model.put("candidato", curriculum.getCandidato());
		model.put("listaCurriculumParametros", daoCurriculumParametros.findFromIDcurriculum(id));
		return "VerCurriculum";
	}
	
	@PostMapping("curriculum/{id}/actualizar")
	public String actualizarCurriculum(@Valid Candidato candidato, BindingResult result, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		

		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "VerCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping(value="/eliminarCurriculum/{id}")
    public String eliminarCurriculum(@PathVariable(value="id") Long id, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN


        if(id > 0) {
        	
        	for (CurriculumParametros currParr : daoCandidato.findOne(id).getCurriculum().getCurriculumParametros()) {
				daoCurriculumParametros.delete(currParr.getId());
			}
			
            daoCurriculum.delete(daoCandidato.findOne(id).getCurriculum().getId());
        }
        return "redirect:/candidatos";
    }
	
	@GetMapping("curriculm/descargar/{id}")
	public String descargarCurriculum(@PathVariable("id") long id, Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		

		//EMPEZAR ACÁ
		
		//-----> TO-DO: ENVIAR DESCARGA DEL ARCHIVO CON EL NOMBRE: CURRICULUM.NOMBRE
			
		
		//ACABAR ACÁ

		
		return "redirect:/curriculum/"+id;
	}
	
	
	 @RequestMapping(value="/curriculum/descargar/{id}")
	    public void getLogFile(HttpSession session,HttpServletResponse response) throws Exception {
	        try {

	            String fileName="a.png";
	            String filePathToBeServed = "C:\\a\\";
	            File fileToDownload = new File(filePathToBeServed+fileName);

	            InputStream inputStream = new FileInputStream(fileToDownload);
	            response.setContentType("application/force-download");
	            response.setHeader("Content-Disposition", "attachment; filename=curriculim.png"); 
	            IOUtils.copy(inputStream, response.getOutputStream());
	            response.flushBuffer();
	            inputStream.close();
	        } catch (Exception exception){
	            System.out.println(exception.getMessage());
	        }

	    }
	
}