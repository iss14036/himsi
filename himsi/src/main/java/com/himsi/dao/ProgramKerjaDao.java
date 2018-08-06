package com.himsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.ProgramKerja;
import com.himsi.services.ProgramKerjaService;

@Service
public class ProgramKerjaDao implements ProgramKerjaService {
	private EntityManagerFactory emf;
	
	@Override
	public ProgramKerja saveOrUpdate(ProgramKerja programKerja) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		ProgramKerja saved = em.merge(programKerja);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public List<ProgramKerja> getAllProgramKerja() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from ProgramKerja", ProgramKerja.class).getResultList();
	}

	@Override
	public ProgramKerja getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(ProgramKerja.class, id);
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<ProgramKerja> getAllProgramKerjaByDivisi(int divisi) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from ProgramKerja where divisi="+divisi, ProgramKerja.class).getResultList();
	}

	

}
