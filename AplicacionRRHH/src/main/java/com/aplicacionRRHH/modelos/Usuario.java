package com.aplicacionRRHH.modelos;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 5940502184821562650L;
	
	//Crear mas tarde las relaciones
	//Rol, Candidato, Localidad, Convocatoria, Curriculum, Entrevista, Habilidad, Provincia
	
	@Id
	@NotNull
	Long Id;
	
	@NotNull
	String nombre;
	
	@NotNull
	String apellido1;
	
	@NotNull
	String apellido2;
	
	@NotNull
	String usuario;
	
	@NotNull
	String contrasena;
	
	@NotNull
	String correo;
	
	@NotNull
	String telefono;
	
	@NotNull
	String dni;
	
	
	@NotNull
	String codigoPostal;
	
	
	public Usuario() {
		super();
	}


	public Usuario(Long id, String nombre, String apellido1, String apellido2) {
		super();
		Id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido1() {
		return apellido1;
	}


	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return apellido2;
	}


	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	
	

}
