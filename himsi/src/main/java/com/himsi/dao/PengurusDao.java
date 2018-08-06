package com.himsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.Pengurus;
import com.himsi.services.PengurusService;

@Service
public class PengurusDao implements PengurusService{
	private EntityManagerFactory emf;
	
	@Override
	public List<Pengurus> getAllPengurus() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Pengurus", Pengurus.class).getResultList();
	}

	@Override
	public List<Pengurus> getAllAnggotaPengurusByid(int id) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Pengurus where divisi="+id, Pengurus.class).getResultList();
	}

	@Override
	public Pengurus getPengurusById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Pengurus.class, id);
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	
}
