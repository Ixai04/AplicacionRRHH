package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Localidad;
import com.aplicacionRRHH.modelos.Provincia;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> findUsuario() {
		List<Usuario> listUsuario = em.createQuery("from Usuario").getResultList();
		return listUsuario;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> findCandidato() {
		List<Candidato> listCandidato = em.createQuery("from Candidato").getResultList();
		return listCandidato;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Provincia> findProvincia() {
		List<Provincia> listProvincia = em.createQuery("from Provincia").getResultList();
		return listProvincia;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Localidad> findLocalidad() {
		List<Localidad> listLocalidad = em.createQuery("from Localidad").getResultList();
		return listLocalidad;
	}

	//Arreglar consulta crear
	@Override
	public void save(String nombre) {
		String query = "insert into Provincia(nombre) values('"+ nombre + "')";

		em.createNativeQuery(query).executeUpdate(); 
	}

}
