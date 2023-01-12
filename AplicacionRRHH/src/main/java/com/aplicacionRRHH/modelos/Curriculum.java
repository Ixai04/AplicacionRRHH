package com.aplicacionRRHH.modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Curriculum implements Serializable{

	private static final long serialVersionUID = -4851170669989674220L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	//---------------- CLAVES FORANEAS
	
		//-----> 1: id_candidato
		
	
	@NotNull
	String nombre;

	public Curriculum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curriculum(@NotNull Long id, @NotNull  String nombre) {
		super();
		Id = id;
		this.nombre = nombre;
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
	
	
	

}