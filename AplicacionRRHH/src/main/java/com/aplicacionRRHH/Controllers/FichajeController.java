package com.aplicacionRRHH.Controllers;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacionRRHH.Dao.FichajeDao;
import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.modelos.Fichaje;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FichajeController {
	
	@Autowired
	private FichajeDao daoFichajes;

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
		
		model.put("listaFichajes", daoFichajes.findFichajes());
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
		
		System.out.println("--- FICHANDO ENTRADA ---");
		
		Fichaje fichaje = new Fichaje();
		fichaje.setUsuario(usuario);
		fichaje.setDia(LocalDate.now());
		fichaje.setHora(LocalTime.now());
		fichaje.setEsEntrada(true);
		
		daoFichajes.save(fichaje);
		
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

		System.out.println("--- FICHANDO SALIDA ---");
		
		Fichaje fichaje = new Fichaje();
		fichaje.setUsuario(usuario);
		fichaje.setDia(LocalDate.now());
		fichaje.setHora(LocalTime.now());
		fichaje.setEsEntrada(false);
		
		daoFichajes.save(fichaje);
		
		return "redirect:/fichar";
	}
	
	
}
