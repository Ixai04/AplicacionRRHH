package com.aplicacionRRHH.Controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class InicioController {
	
	@Autowired
	private UsuarioDao dao;

	@GetMapping("/inicio")
	public String inicio(Model model) {
		model.addAttribute("date", LocalDate.now());
		model.addAttribute("time", LocalDate.now() + "-" + LocalTime.now().getHour() + ":"+LocalTime.now().getMinute() + ":"+LocalTime.now().getSecond());
		return "Inicio";
	}
	
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		Usuario usuario = dao.login(username, password);
	
		if(usuario == null) {
			model.addAttribute("error", "Login incorrecto");
		}else {
			request.getSession().setAttribute("usuario",usuario);
			model.addAttribute("usuario", usuario);
		}
		
		
		return "Inicio";
	}
	
	@GetMapping("/logout")
	public String cerrarSesion(HttpServletRequest request) {
		
		request.getSession().invalidate();
		return "redirect:/inicio";
	}

	
	// -------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------
	// ------------------------------ MÉTODO DE AUTENTICACIÓN DE USUARIO -------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------
	
	public static Usuario autenticar(HttpServletRequest request, String nombreRol) {
		
		System.out.println("Se intenta autenticar como " + nombreRol);
		if (request.getSession() == null) {
			return null;
		}
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		
		if (usuario == null) {
			System.out.println("Pero no estás logueado");
			return null;
		}
		
		System.out.println("Rol del usuario: " + usuario.getRol().getNombre());
		
		if (usuario != null && usuario.getRol().getNombre().equals(nombreRol)) {
			System.out.println("Autenticado con éxito");
			return usuario;
		}else {
			System.out.println("Pero el usuario actual es " + usuario.getRol());
			return null;
		}
		
	}
	
}
