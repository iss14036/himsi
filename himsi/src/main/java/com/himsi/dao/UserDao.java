package com.himsi.dao;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.himsi.dao.PasswordDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himsi.models.IuranBulanan;
import com.himsi.models.User;
import com.himsi.services.IuranBulananService;
import com.himsi.services.UserService;

@Service
public class UserDao implements UserService {
	private EntityManagerFactory emf;
	private PasswordDao passwords;
	private IuranBulananService tService;
	
	
	public IuranBulananService gettService() {
		return tService;
	}
	@Autowired
	public void settService(IuranBulananService tService) {
		this.tService = tService;
	}
	public PasswordDao getPassword() {
		return passwords;
	}
	@Autowired
	public void setPassword(PasswordDao password) {
		this.passwords = password;
	}

	@Override
	public List<User> getAllUser() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from User", User.class).getResultList();
	}

	@Override
	public User getById(int id) {
		EntityManager em = emf.createEntityManager();
		return em.find(User.class, id);
	}

	@Override
	public User loginUser(String username, String password) {
		List<User> users = getAllUser();
		for(int i=0; i<users.size(); i++){
			if(users.get(i).getId_user().equals(username)){
				if(passwords.matches(password, users.get(i).getPassword())) return users.get(i);
				else return null;
			}
		}
		return null;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}
	@Autowired
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<User> getAllUltah() throws ParseException {
		List<User> users = getAllUser();
		List<User> usersUltah = new ArrayList<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String tanggalSekarang =  dtf.format(now);
		for(User user: users){
			String tes = user.getTanggal_lahir();
			String tanggalUser = tes.substring(5,10);
			if(tanggalSekarang.equalsIgnoreCase(tanggalUser)) usersUltah.add(user);
		}
		return usersUltah;
	}

	@Override
	public void removeUser(int id) {
		List<IuranBulanan> iuranBulanans = tService.getAllByIdUser(id);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for(int i=0; i<iuranBulanans.size(); i++){
			em.remove(em.find(IuranBulanan.class, iuranBulanans.get(i).getId()));
		}
		em.remove(em.find(User.class, id));
		em.getTransaction().commit();
	}

	@Override
	public User saveOrUpdate(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User saved = em.merge(user);
		em.getTransaction().commit();
		return saved;
	}
	@Override
	public List<User> getAllUserByDivisi(int id) {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from User where pengurus.divisi="+id, User.class).getResultList();
	}

	
	
}
