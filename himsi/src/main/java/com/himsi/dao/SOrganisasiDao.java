package com.himsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.StrukturOrganisasi;
import com.himsi.services.SOrganisasiService;

@Service
public class SOrganisasiDao implements SOrganisasiService {
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	@Override
	public StrukturOrganisasi saveOrUpdate(StrukturOrganisasi sOrganisasi) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		StrukturOrganisasi saved = em.merge(sOrganisasi);
		em.getTransaction().commit();
		return saved;
	}
	@Override
	public List<StrukturOrganisasi> getAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from StrukturOrganisasi", StrukturOrganisasi.class).getResultList();
	}
	@Override
	public StrukturOrganisasi getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(StrukturOrganisasi.class, id);
	}
	
	
	
}
