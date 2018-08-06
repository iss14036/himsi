package com.himsi.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.LaporanKeuangan;
import com.himsi.models.IuranBulanan;
import com.himsi.models.User;
import com.himsi.services.IuranBulananService;
import com.himsi.services.UserService;

@Service
public class IuranBulananDao implements IuranBulananService {
	private EntityManagerFactory emf;
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public IuranBulanan saveOrUpdate(IuranBulanan iuranBulanan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		IuranBulanan saved = em.merge(iuranBulanan);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public List<IuranBulanan> getAllIuranBulanan() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from IuranBulanan", IuranBulanan.class).getResultList();
	}

	@Override
	public List<IuranBulanan> getAllById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from IuranBulanan where id="+id, IuranBulanan.class).getResultList();
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public void createAllPembayaran(IuranBulanan transaki, String bulan) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<User> users = userService.getAllUser();
		for(int i=0; i<users.size(); i++){
			transaki.setStatus("Belum Bayar");
			transaki.setTotal_bayar(3000);
			transaki.setTanggal_pembayaran("none");
			transaki.setUser(users.get(i));
			transaki.setBulan_iuran(bulan);
			em.merge(transaki);
		}
		em.getTransaction().commit();
		
	}

	@Override
	public List<IuranBulanan> getAllByLaporan(LaporanKeuangan lKeuangan) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from IuranBulanan where laporanKeuangan="+lKeuangan.getId(), IuranBulanan.class).getResultList();
	}

	@Override
	public IuranBulanan getIuranBulananById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(IuranBulanan.class, id);
	}

	@Override
	public int getMahasiswaBlmBayar(LaporanKeuangan lKeuangan) {
		List<IuranBulanan> IuranBulanans = getAllByLaporan(lKeuangan);
		int total = 0;
		for(IuranBulanan IuranBulanan : IuranBulanans){
			if(IuranBulanan.getStatus().equalsIgnoreCase("Belum Bayar")) total++;
		}
		return total;
	}

	@Override
	public int getMahasiswaSdhBayar(LaporanKeuangan lKeuangan) {
		List<IuranBulanan> IuranBulanans =  getAllByLaporan(lKeuangan);
		int total = 0;
		for(IuranBulanan IuranBulanan : IuranBulanans){
			if(IuranBulanan.getStatus().equalsIgnoreCase("Sudah Lunas")) total++;
		}
		return total;
	}

	@Override
	public List<IuranBulanan> getAllByIdUser(int id) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from IuranBulanan where user="+id, IuranBulanan.class).getResultList();
	}
	
	

}
