package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll() {
		List<Usuario> listUsuario = em.createQuery("from Usuario").getResultList();
		System.err.println("\n\n\n--------------------------------------" + listUsuario.get(0).getNombre() +"----------------------------------------------------------------\n\n\n");
		return listUsuario;
		
	}

}
