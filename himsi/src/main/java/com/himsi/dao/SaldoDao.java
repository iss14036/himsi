package com.himsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.Saldo;
import com.himsi.services.SaldoService;

@Service
public class SaldoDao implements SaldoService {
	private EntityManagerFactory emf;
	
	@Override
	public Saldo saveOrUpdate(Saldo saldo) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Saldo saved = em.merge(saldo);
		em.getTransaction().commit();
		return saved;
	}

	@Override
	public Saldo getSaldo() {
		EntityManager em = emf.createEntityManager();
		List<Saldo> saldos = em.createQuery("from Saldo", Saldo.class).getResultList();
		try{
			Saldo saldo = new Saldo();
			if(saldos.size()==0) return null;
			else saldo = saldos.get(saldos.size()-1);
		return saldo;
		}catch(NullPointerException ie){
			return null;
		}
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	
	
}
