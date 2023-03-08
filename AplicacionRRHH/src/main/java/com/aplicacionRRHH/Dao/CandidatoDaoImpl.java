package com.aplicacionRRHH.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Entrevista;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CandidatoDaoImpl implements CandidatoDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public DataSource getDataSource() {
		EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
	    ConnectionProvider cp = ((SessionFactory) entityManagerFactory).getSessionFactoryOptions()
	            .getServiceRegistry()
	            .getService(ConnectionProvider.class);
	    return cp.unwrap(DataSource.class);
	    }
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> buscarCandidatos(Long idConvocatoria, Long idParametroOrden, Long idParametroFiltro) {
		
		System.out.println("IDConvocatoria (" + idConvocatoria + ") IDParametroOrden (" + idParametroOrden + ") IDParametroFiltro (" + idParametroFiltro + ")");
		//-----> (1) INICIO: OBTENER LISTA CANDIDATOS
		List<Candidato> listaCandidatos = em.createQuery("from Candidato").getResultList();
		System.out.println("--1-- Lista Candidatos al inicio: " + listaCandidatos.size());
		
		//-----> (2) ENTREVISTAS: OBTENER LISTA ENTREVISTAS DENTRO DE LA CONVOCATORIA
		List<Entrevista> listaEntrevistas = em.createQuery("from Entrevista where convocatoria = " + idConvocatoria).getResultList();
		System.out.println("--2-- Lista Entrevistas: " + listaEntrevistas.size());
        
		//-----> (3) ENTREVISTADOS: OBTENER LOS CANDIDATOS DE LAS ENTREVISTAS
        List<Candidato> listaUsuariosEntrevista = new ArrayList<Candidato>();
        
        for(Entrevista entrevista : listaEntrevistas) {
        	listaUsuariosEntrevista.add(entrevista.getCandidato());
        }
		System.out.println("--3-- Lista UsuariosEntrevista: " + listaUsuariosEntrevista.size());
		
		//-----> (4) FILTRO: ELIMINAR LOS CANDIDATOS CON ENTREVISTA DE LA LISTA INICIAL
        listaCandidatos.removeAll(listaUsuariosEntrevista);
		System.out.println("--4-- Lista Candidatos al final: " + listaCandidatos.size());
		
		
		return listaCandidatos;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> buscarCandidatosPro(Long idConvocatoria) {
		
		
		List<Candidato> listaCandidatos = em.createQuery("from Candidato").getResultList();
		System.out.println("--1-- Lista Candidatos al inicio: " + listaCandidatos.size());
		
		return listaCandidatos;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> findCandidato() {
		List<Candidato> listCandidato = em.createQuery("from Candidato").getResultList();
		return listCandidato;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> listacandiodatonombre() {
		List<Candidato> listCandidato = em.createQuery("from Candidato where nombre='Ixai'").getResultList();
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


	@Override
	public void save(Usuario usuario1) {
		// TODO Auto-generated method stub
		
	}

}
