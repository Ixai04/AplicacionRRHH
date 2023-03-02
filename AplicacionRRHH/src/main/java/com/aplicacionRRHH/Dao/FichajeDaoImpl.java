package com.aplicacionRRHH.Dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.aplicacionRRHH.modelos.Fichaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class FichajeDaoImpl implements FichajeDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Fichaje> findFichajes() {
		List<Fichaje> listFichajes = em.createQuery("from Fichaje").getResultList();
		return listFichajes;
	}

	
	@Override
	@Transactional
	public void save(Fichaje fichaje) {
		if(fichaje.getId() !=null && fichaje.getId() > 0) {
			em.merge(fichaje);
		}
		else {
		em.persist(fichaje);
		}
	}

	@Override
	public Fichaje findOne(Long id) {
		return em.find(Fichaje.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
