package com.aplicacionRRHH.Controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FichajesController {
	
	@Autowired
	private UsuarioDao daoUsuario;

	@GetMapping("/fichar")
	public String inicio(Map<String, Object> model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "empleado");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		List<String> listaFichajes = new ArrayList<String>();
		listaFichajes.add("Fichajes 1 - Entrada");
		listaFichajes.add("Fichajes 2 - Salida");
		listaFichajes.add("Fichajes 3 - Entrada");
		
		model.put("listaFichajes", listaFichajes);
		return "Fichar";
	}
	
	
	
	@GetMapping("/fichar/entrada")
	public String ficharEntrada(Map<String, Object> model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "empleado");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		System.out.println("--------------");
		return "redirect:/fichar";
	}
	
	@GetMapping("/fichar/salida")
	public String ficharSalida(Map<String, Object> model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "empleado");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		System.out.println("--------------");
		return "redirect:/fichar";
	}
	
	
}
