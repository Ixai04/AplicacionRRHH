package com.aplicacionRRHH.modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Entrevista implements Serializable{

	private static final long serialVersionUID = 265347191751328858L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	//---------------- CLAVES FORANEAS
	
		//-----> 1: id_candidato
		

		//-----> 2: id_entrevistador
		

		//-----> 3: id_entrevista
	
	@NotNull
	LocalDate dia;
	
	@NotNull
	LocalTime hora;
	
	@NotNull
	String lugar;

	public Entrevista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entrevista(@NotNull Long id, @NotNull LocalDate dia, @NotNull LocalTime hora, @NotNull String lugar) {
		super();
		Id = id;
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	
	
	

}