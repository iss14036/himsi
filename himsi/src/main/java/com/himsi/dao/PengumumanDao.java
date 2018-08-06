package com.himsi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.himsi.models.Pengumuman;
import com.himsi.services.PengumumanService;

@Service
public class PengumumanDao implements PengumumanService {
	private EntityManagerFactory emf;
	
	@Override
	public Pengumuman saveOrUpdate(Pengumuman pengumuman) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Pengumuman saved = em.merge(pengumuman);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public List<Pengumuman> getAllPengumuman() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Pengumuman", Pengumuman.class).getResultList();
	}

	@Override
	public Pengumuman getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Pengumuman.class, id);
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Pengumuman> getAllPengumumanTop15() {
		List<Pengumuman> pengumumans = getAllPengumuman();
		List<Pengumuman> show = new ArrayList<>();
		int count=0;
		for(int i=pengumumans.size()-1; i>=0; i--){
			show.add(pengumumans.get(i));
			count++;
			if(count==15) break;
		}
		return show;
	}
	
	

}
