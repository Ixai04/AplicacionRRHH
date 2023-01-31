package com.aplicacionRRHH.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Curriculum;
import com.aplicacionRRHH.modelos.CurriculumParametros;
import com.aplicacionRRHH.modelos.Entrevista;

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
		
		//-----> (1) INICIO: OBTENER LISTA CANDIDATOS
		
		List<Candidato> listaCandidatos = em.createQuery("from Candidato").getResultList();
		System.out.println("--1-- Lista Candidatos al inicio: " + listaCandidatos.size());
		
		List<Entrevista> listaEntrevistas = em.createQuery("from Entrevista where convocatoria = " + idConvocatoria).getResultList();
		System.out.println("--2-- Lista Entrevistas: " + listaEntrevistas.size());
        

        List<Candidato> listaUsuariosEntrevista = new ArrayList<Candidato>();
        
        for(Entrevista entrevista : listaEntrevistas) {
        	listaUsuariosEntrevista.add(entrevista.getCandidato());
        }
		System.out.println("--3-- Lista UsuariosEntrevista: " + listaUsuariosEntrevista.size());

        listaCandidatos.removeAll(listaUsuariosEntrevista);
		System.out.println("--4-- Lista Candidatos al final: " + listaCandidatos.size());
		
		//-----> (1) FIN: OBTENER LISTA CANDIDATOS
		
		
		
		//-----> (2) INICIO: FILTRAR POR PARAMETRO
		List<Candidato> listaCandidatosFiltrados = new ArrayList<Candidato>();
		
		for(Candidato candidato : listaCandidatos) {
			List<Curriculum> listaCurriculum = em.createQuery("from Curriculum where candidato = " + candidato.getId()).getResultList();
			Curriculum curriculum = listaCurriculum.get(0);
			
			List<CurriculumParametros> listaCurrPar = em.createQuery("FROM curriculum_parametros AS cp INNER JOIN parametro AS p ON cp.id_Parametro = p.id INNER JOIN curriculum AS cu ON cp.id_Curriculum = cu.id INNER JOIN candidato AS ca ON cu.id_Candidato = ca.id WHERE ca.id = 4 AND cp.id_Parametro = 3 AND valoracion >= 3").getResultList();
		
			if (listaCurrPar.size()>0) {
				listaCandidatosFiltrados.add(candidato);
			}
		}
		
		
		//-----> (1) FIN: OBTENER LISTA CANDIDATOS
		
		return listaCandidatos;
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
