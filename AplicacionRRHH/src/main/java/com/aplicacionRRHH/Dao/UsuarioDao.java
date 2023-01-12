package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Localidad;
import com.aplicacionRRHH.modelos.Provincia;
import com.aplicacionRRHH.modelos.Usuario;

public interface UsuarioDao {

//	public List<Usuario> findUsuario();
//	public List<Candidato> findCandidato();
//	public List<Provincia> findProvincia();
//	public List<Localidad> findLocalidad(); 
//	
//	public void save(Provincia provincia);
//	
//	public Provincia findOne(Long id);
//	public void delete (Long id);
	
	public Usuario login (String username, String password);

}
