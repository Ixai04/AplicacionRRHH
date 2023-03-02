package com.aplicacionRRHH.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Entrevista;
import com.aplicacionRRHH.modelos.Fichajes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class FichajesDaoImpl implements FichajesDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Fichajes> findFichajes() {
		List<Fichajes> listFichajes = em.createQuery("from Fichajes").getResultList();
		return listFichajes;
	}

	
	@Override
	@Transactional
	public void save(Fichajes fichaje) {
		if(fichaje.getId() !=null && fichaje.getId() > 0) {
			em.merge(fichaje);
		}
		else {
		em.persist(fichaje);
		}
	}

	@Override
	public Fichajes findOne(Long id) {
		return em.find(Fichajes.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
