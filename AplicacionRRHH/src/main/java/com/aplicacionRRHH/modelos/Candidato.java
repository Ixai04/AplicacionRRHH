package com.aplicacionRRHH.modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Candidato implements Serializable{

	private static final long serialVersionUID = -1695989566727688651L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name = "id")
	Long Id;
	
	//---------------- CLAVES FORANEAS
	
		//-----> 1: id_localidad
		

	
	@NotNull
	String nombre;

	@NotNull
	String apellido1;

	@NotNull
	String apellido2;
	
	@NotNull
	String correo;

	@NotNull
	String telefono;

	@NotNull
	String dni;

	@NotNull
	String direccion;

	@NotNull
	String codigo_Postal;

	public Candidato() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidato(@NotNull Long id, @NotNull String nombre, @NotNull String apellido1, @NotNull String apellido2,
			@NotNull String correo, @NotNull String telefono, @NotNull String dni, @NotNull String direccion,
			@NotNull String codigo_Postal) {
		super();
		Id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.telefono = telefono;
		this.dni = dni;
		this.direccion = direccion;
		this.codigo_Postal = codigo_Postal;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigo_Postal() {
		return codigo_Postal;
	}

	public void setCodigo_Postal(String codigo_Postal) {
		this.codigo_Postal = codigo_Postal;
	}
	
	

}