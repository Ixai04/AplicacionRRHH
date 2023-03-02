package com.aplicacionRRHH.Dao;

import java.util.List;

import javax.sql.DataSource;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Fichajes;

public interface FichajesDao {
	public List<Fichajes> findFichajes();

	public void save(Fichajes fichajes);

	public Fichajes findOne(Long id);

	public void delete(Long id);
}


