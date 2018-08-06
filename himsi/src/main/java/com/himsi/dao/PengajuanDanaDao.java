package com.himsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.LaporanKeuangan;
import com.himsi.models.PengajuanDana;
import com.himsi.models.ProgramKerja;
import com.himsi.services.PengajuanDanaService;

@Service
public class PengajuanDanaDao implements PengajuanDanaService {
	private EntityManagerFactory emf;
	
	@Override
	public PengajuanDana saveOrUpdate(PengajuanDana pengajuanDana) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		PengajuanDana saved = em.merge(pengajuanDana);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public List<PengajuanDana> getAllPengajuanDana() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from PengajuanDana", PengajuanDana.class).getResultList();
	}

	@Override
	public List<PengajuanDana> getAllPengajuanDanaByPK(ProgramKerja programKerja) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from PengajuanDana where programKerja="+programKerja.getId(), PengajuanDana.class).getResultList();
	}

	@Override
	public PengajuanDana getPengajuanDanaByLK(LaporanKeuangan laporanKeuangan) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from PengajuanDana where laporanKeuangan="+laporanKeuangan.getId(), PengajuanDana.class).getSingleResult();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public PengajuanDana getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(PengajuanDana.class, id);
	}

	@Override
	public List<PengajuanDana> getAllPengajuanDanaByStatus(String status) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from PengajuanDana where status='" + status + "'", PengajuanDana.class).getResultList();
	}

	@Override
	public List<PengajuanDana> getAllPendingByBendahara() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from PengajuanDana where status='" + "accepted" + "' and status_terima='" + "pending" + "'", PengajuanDana.class).getResultList();
	}
	
	

}
