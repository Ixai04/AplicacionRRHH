package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Candidato;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CandidatoDaoImpl implements CandidatoDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> buscarCandidatos(Long idConvocatoria, Long idParametroOrden, Long idParametroFiltro) {
		
		
		List<Candidato> listCandidato = em.createQuery("from Candidato").getResultList();
		return listCandidato;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> findCandidato() {
		List<Candidato> listCandidato = em.createQuery("from Candidato").getResultList();
		return listCandidato;
	}

	@Override
	@Transactional
	public void save(Candidato candidato) {
		if(candidato.getId() !=null && candidato.getId() > 0) {
			em.merge(candidato);
		}
		else {
		em.persist(candidato);
		}
	}

	@Override
	public Candidato findOne(Long id) {
		return em.find(Candidato.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
