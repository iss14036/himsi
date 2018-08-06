package com.himsi.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.himsi.dao.PasswordDao;
import com.himsi.models.User;
import com.himsi.services.DivisiService;
import com.himsi.services.PengurusService;
import com.himsi.services.RoleService;
import com.himsi.services.UserService;

@Controller
public class AdminController {
	private UserService userService;
	private PengurusService pengurusService;
	private DivisiService divisiService;
	private RoleService roleService;
	private PasswordDao password;
	
	public PasswordDao getPassword() {
		return password;
	}
	@Autowired
	public void setPassword(PasswordDao password) {
		this.password = password;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public PengurusService getPengurusService() {
		return pengurusService;
	}
	@Autowired
	public void setPengurusService(PengurusService pengurusService) {
		this.pengurusService = pengurusService;
	}
	public DivisiService getDivisiService() {
		return divisiService;
	}
	@Autowired
	public void setDivisiService(DivisiService divisiService) {
		this.divisiService = divisiService;
	}
	
	@RequestMapping(value="/create/user", method = RequestMethod.GET)
	public String lihatUser(Model model){
		model.addAttribute("allUser", userService.getAllUser());
		model.addAttribute("user", new User());
		return "add_mahasiswa";
	}
	
	@RequestMapping(value="/create/user", method = RequestMethod.POST)
	public String createUser(Model model,User user,HttpServletRequest request){
		String jabatan = ""+request.getParameter("jabatan");
		Integer jab = Integer.parseInt(jabatan);
		if(jab==1) {
			user.setRole(roleService.getById(jab));
			user.setPengurus(pengurusService.getPengurusById(jab));
		}
		else if(jab==2){
			user.setRole(roleService.getById(jab));
			user.setPengurus(pengurusService.getPengurusById(jab));
		}
		else if(jab==3){
			user.setRole(roleService.getById(jab));
			user.setPengurus(pengurusService.getPengurusById(jab));
		}
		else if(jab>=4 && jab<=7){
			if(jab==4) user.setPengurus(pengurusService.getPengurusById(jab));
			else if(jab==5) user.setPengurus(pengurusService.getPengurusById(jab));
			else if(jab==6) user.setPengurus(pengurusService.getPengurusById(jab));
			else if(jab==7) user.setPengurus(pengurusService.getPengurusById(jab));
			user.setRole(roleService.getById(4));	
		}
		else if(jab>=8 && jab<=11){
			if(jab==8) user.setPengurus(pengurusService.getPengurusById(jab));
			else if(jab==9) user.setPengurus(pengurusService.getPengurusById(jab));
			else if(jab==10) user.setPengurus(pengurusService.getPengurusById(jab));
			else if(jab==11) user.setPengurus(pengurusService.getPengurusById(jab));
			user.setRole(roleService.getById(5));
		}
		else if(jab==12){
			user.setPengurus(pengurusService.getPengurusById(jab));
			user.setRole(roleService.getById(5));
		}
		String hashedPassword = password.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userService.saveOrUpdate(user);
		model.addAttribute("allUser", userService.getAllUser());
		model.addAttribute("user", new User());
		model.addAttribute("alertTrue",true);
		return "add_mahasiswa";
	}
	
	@RequestMapping(value="/edit/user/{id}", method = RequestMethod.GET)
	public String lihatUser(@PathVariable Integer id,Model model){
		User user = userService.getById(id);
		model.addAttribute("user",user);
		return "edit_user";
	}
}
