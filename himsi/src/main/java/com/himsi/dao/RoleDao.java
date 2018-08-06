package com.himsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.Role;
import com.himsi.services.RoleService;

@Service
public class RoleDao implements RoleService {
	private EntityManagerFactory emf;
	
	@Override
	public List<Role> getAllRole() {
		EntityManager em = emf.createEntityManager();
		return em.createNamedQuery("from Role", Role.class).getResultList();
	}

	@Override
	public Role getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Role.class, id);
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	

}
