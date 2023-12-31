package com.aplicacionRRHH.modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Fichaje implements Serializable{


	private static final long serialVersionUID = -2739489587240481946L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotNull
	LocalDate dia;
	
	@NotNull
	LocalTime hora;

	@NotNull
	@Column(name = "es_entrada")
	Boolean esEntrada;

	@ManyToOne
    @JoinColumn(name="id_usuario", nullable=false)
    private Usuario usuario;

	public Fichaje() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fichaje(Long id, @NotNull LocalDate dia, @NotNull LocalTime hora, @NotNull Boolean esEntrada, Usuario usuario) {
		super();
		Id = id;
		this.dia = dia;
		this.hora = hora;
		this.esEntrada = esEntrada;
		this.usuario = usuario;
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

	public Boolean getEsEntrada() {
		return esEntrada;
	}

	public void setEsEntrada(Boolean esEntrada) {
		this.esEntrada = esEntrada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}