package com.aplicacionRRHH.modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class CandidatoParametros implements Serializable{

	private static final long serialVersionUID = -2945673221267191557L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;

	@NotNull
	Integer valoracion;
	
	@ManyToOne
    @JoinColumn(name="id_Candidato", nullable=false)
    private Candidato candidato;
	
	@ManyToOne
    @JoinColumn(name="id_Parametro", nullable=false)
    private Parametro parametro;
	
	public CandidatoParametros() {
		super();
	}

	public CandidatoParametros(Long id, Candidato candidato, Parametro parametro, Integer valoracion) {
		super();
		Id = id;
		this.candidato = candidato;
		this.parametro = parametro;
		this.valoracion = valoracion;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public Candidato getCandidato() {
		return candidato;
	}


	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	
	
	
	public Parametro getParametro() {
		return parametro;
	}


	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	
	public Integer getValoracion() {
		return valoracion;
	}


	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}



	
	

}