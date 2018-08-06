package com.himsi.services;

import java.util.List;

import com.himsi.models.Role;

public interface RoleService {
	public List<Role> getAllRole();
	public Role getById(int id);
}
