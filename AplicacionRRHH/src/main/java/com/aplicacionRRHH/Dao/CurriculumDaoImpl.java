package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Curriculum;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CurriculumDaoImpl implements CurriculumDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Curriculum> findCurriculum() {
		List<Curriculum> listCurriculum = em.createQuery("from Curriculum").getResultList();
		return listCurriculum;
	}

	@Override
	@Transactional
	public void save(Curriculum curriculum) {
		if(curriculum.getId() !=null && curriculum.getId() > 0) {
			em.merge(curriculum);
		}
		else {
		em.persist(curriculum);
		}
	}

	@Override
	public Curriculum findOne(Long id) {
		return em.find(Curriculum.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
