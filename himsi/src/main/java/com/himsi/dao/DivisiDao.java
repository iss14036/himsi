package com.himsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.Divisi;
import com.himsi.services.DivisiService;

@Service
public class DivisiDao implements DivisiService{
	private EntityManagerFactory emf;
	
	@Override
	public List<Divisi> getAllDivisi() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Divisi",Divisi.class).getResultList();
	}

	@Override
	public Divisi getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Divisi.class, id);
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	

}
