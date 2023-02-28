package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.CandidatoParametros;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CandidatoParametrosDaoImpl implements CandidatoParametrosDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<CandidatoParametros> findCandidatoParametros() {
		List<CandidatoParametros> listCandidatoParametros = em.createQuery("from CandidatoParametros").getResultList();
		return listCandidatoParametros;
	}

	@Override
	@Transactional
	public void save(CandidatoParametros CandidatoParametros) {
		if(CandidatoParametros.getId() !=null && CandidatoParametros.getId() > 0) {
			em.merge(CandidatoParametros);
		}
		else {
		em.persist(CandidatoParametros);
		}
	}

	@Override
	public CandidatoParametros findOne(Long id) {
		return em.find(CandidatoParametros.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}
	
	
	/*---------------- MÉTODOS PERSONALIZADOS -------------------*/
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CandidatoParametros> findFromIDCandidato (Long idCandidato) {
		
		//Query: nombre de clases y variables tal y como están en el modelo
		List<CandidatoParametros> listaCandidatoParametros = em.createQuery("from CandidatoParametros where candidato=" + idCandidato).getResultList();
		if (listaCandidatoParametros.size() > 0){
			System.out.println("Lista desde el dao IF: " + listaCandidatoParametros.size());
			return listaCandidatoParametros;
		}
		System.out.println("Lista desde el dao FUERA: " + listaCandidatoParametros.size());
		return null;
	}

}
