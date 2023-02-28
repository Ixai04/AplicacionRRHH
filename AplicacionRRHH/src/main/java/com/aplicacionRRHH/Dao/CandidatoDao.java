package com.aplicacionRRHH.Dao;

import java.util.List;

import javax.sql.DataSource;

import com.aplicacionRRHH.modelos.Candidato;

public interface CandidatoDao {
	public List<Candidato> findCandidato();

	public void save(Candidato candidato);

	public Candidato findOne(Long id);

	public void delete(Long id);
	
	//----- MÉTODOS PERSONALIZADOS -----
	
	public List<Candidato> buscarCandidatos(Long idConvocatoria, Long idParametroOrden, Long idParametroFiltro);
	
	public List<Candidato> buscarCandidatosPro(Long idConvocatoria);
	
	public DataSource getDataSource();
}


