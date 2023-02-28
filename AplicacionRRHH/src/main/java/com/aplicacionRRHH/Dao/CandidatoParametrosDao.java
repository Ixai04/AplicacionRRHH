package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.CandidatoParametros;
import com.aplicacionRRHH.modelos.Usuario;

public interface CandidatoParametrosDao {

	public List<CandidatoParametros> findCandidatoParametros();

	public void save(CandidatoParametros CandidatoParametros);

	public CandidatoParametros findOne(Long id);

	public void delete(Long id);
	
	/*---- MÉTODOS PERSONALIZADOS -----*/
	
	public List<CandidatoParametros> findFromIDCandidato (Long idCandidato);
}
