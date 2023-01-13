package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Candidato;

public interface CandidatoDao {
	public List<Candidato> findCandidato();

	public void save(Candidato candidato);

	public Candidato findOne(Long id);

	public void delete(Long id);
}


