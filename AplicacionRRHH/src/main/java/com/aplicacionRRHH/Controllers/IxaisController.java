package com.aplicacionRRHH.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IxaisController {
	
	@Autowired
	private CandidatoDao daoCandidato;
	@GetMapping("/ixai")
	public String ixai(Model model) {
		
	model.addAttribute("a", 2);
	model.addAttribute("listacandidatonombre", daoCandidato.listacandiodatonombre());
		return "Ixais";
	}
}
