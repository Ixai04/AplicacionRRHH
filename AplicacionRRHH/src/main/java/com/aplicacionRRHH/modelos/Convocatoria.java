package com.aplicacionRRHH.modelos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Convocatoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotEmpty
	@Column(name = "puesto_Trabajo")
	String puestoTrabajo;
	

	@NotNull
	LocalDate fecha;
	
	@NotEmpty
	String lugar;

	public Convocatoria() {
		super();
	}

	public Convocatoria(Long id, @NotEmpty String puestoTrabajo, @NotNull LocalDate fecha,
			@NotEmpty String lugar) {
		super();
		Id = id;
		this.puestoTrabajo = puestoTrabajo;
		this.fecha = fecha;
		this.lugar = lugar;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	
	
}
