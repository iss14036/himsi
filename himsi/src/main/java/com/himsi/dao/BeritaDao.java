package com.himsi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.Berita;
import com.himsi.services.BeritaService;

@Service
public class BeritaDao implements BeritaService {
	private EntityManagerFactory emf;
	
	@Override
	public Berita saveOrUpdate(Berita berita) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Berita saved = em.merge(berita);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public List<Berita> getAllBerita() {
		EntityManager em = emf.createEntityManager();
		List<Berita> beritas = em.createQuery("from Berita",Berita.class).getResultList();
		return beritas;
	}

	@Override
	public Berita getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Berita.class, id);
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Berita> getAllBeritaByIdDivisi(int id) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Berita where id="+id, Berita.class).getResultList();
	}

	@Override
	public List<Berita> getAllBeritaByStatus(String status) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from Berita where status_berita='" + status + "'", Berita.class).getResultList();
	}

	@Override
	public List<Berita> getTop6() {
		List<Berita> beritas = getAllBeritaByStatus("accepted");
		List<Berita> berita = new ArrayList<>();
		int j=0;
		for(int i=beritas.size()-1; i>=0; i--){
			berita.add(beritas.get(i));
			j++;
			if(j==6) break;
		}
		return berita;
	}

	@Override
	public List<Berita> getAllBeritaAccepted() {
		EntityManager em = emf.createEntityManager();
		List<Berita> beritas = em.createQuery("from Berita where status_berita='" + "accepted" + "'",Berita.class).getResultList();
		return beritas;
	}

	@Override
	public List<Berita> getAllBeritaImgTop3() {
		List<Berita> beritaAccepted = getAllBeritaAccepted();
		List<Berita> beritaImg = new ArrayList<>();
		int j=0;
		try{
		for(int i=beritaAccepted.size()-1; i>=0; i--){
			System.out.println("->"+beritaAccepted.get(i).getFoto());
			if(beritaAccepted.get(i).getFoto()!=null) beritaImg.add(beritaAccepted.get(i));
			j++;
			if(j==3) break;
		}
		}
		catch(NullPointerException ie){
			return null;
		}
		return beritaImg;
	}
	

}
