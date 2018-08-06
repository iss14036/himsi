package com.himsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.LaporanKeuangan;
import com.himsi.services.LaporanKeuanganService;

@Service
public class LaporanKeuanganDao implements LaporanKeuanganService {
	private EntityManagerFactory emf;
	
	@Override
	public LaporanKeuangan saveOrUpdate(LaporanKeuangan laporanKeuangan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		LaporanKeuangan saved = em.merge(laporanKeuangan);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public List<LaporanKeuangan> getAllLaporanKeuangan() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from LaporanKeuangan", LaporanKeuangan.class).getResultList();
	}

	@Override
	public LaporanKeuangan getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(LaporanKeuangan.class, id);
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<LaporanKeuangan> getAllLaporanByTipe(String tipe) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from LaporanKeuangan", LaporanKeuangan.class).getResultList();
	}
	
}
