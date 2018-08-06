package com.himsi.services;

import java.text.ParseException;
import java.util.List;

import com.himsi.models.User;

public interface UserService {
	public User saveOrUpdate(User user);
	public List<User> getAllUser();
	public User getById(int id);
	public User loginUser(String username, String password);
	public List<User> getAllUltah() throws ParseException;
	public void removeUser(int id);
	public List<User> getAllUserByDivisi(int id);
	
}
