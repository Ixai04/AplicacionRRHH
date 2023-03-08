package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Fichaje;

public interface FichajeDao {
	public List<Fichaje> findFichajes();

	public void save(Fichaje fichajes);

	public Fichaje findOne(Long id);

	public void delete(Long id);
}


