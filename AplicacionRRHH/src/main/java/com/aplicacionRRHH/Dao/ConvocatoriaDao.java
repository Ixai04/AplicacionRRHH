package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Convocatoria;

public interface ConvocatoriaDao {

	public List<Convocatoria> findConvocatoria();
	
	public void save(Convocatoria convocatoria);
}
