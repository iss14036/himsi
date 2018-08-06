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
import com.himsi.services.UserService;

@Controller
public class UserController {
	private UserService userService;
	private PasswordDao passwords;
	
	
	public PasswordDao getPassword() {
		return passwords;
	}
	@Autowired
	public void setPassword(PasswordDao password) {
		this.passwords = password;
	}
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/daftar/mahasiswa", method = RequestMethod.GET)
	public String daftarMahasiswa(Model model){
		model.addAttribute("daftarMahasiswa", userService.getAllUser());
		return "daftar_mahasiswa";
	}
	
	@RequestMapping(value="/mahasiswa/lihat/{id}", method = RequestMethod.GET)
	public String lihaMahasiswa(@PathVariable Integer id,Model model){
		model.addAttribute("mahasiswa", userService.getById(id));
		return "mahasiswa";
	}
	
	@RequestMapping(value="/mahasiswa/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Integer id,Model model){
		userService.removeUser(id);
		model.addAttribute("daftarMahasiswa", userService.getAllUser());
		model.addAttribute("alertTrue", true);
		return "daftar_mahasiswa";
	}
	
	@RequestMapping(value="/mahasiswa/profile/{id}", method = RequestMethod.GET)
	public String profilUser(@PathVariable Integer id,Model model,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) return "redirect:/login";
		if(user.getId()!=id) id = user.getId();
		model.addAttribute("mahasiswa", userService.getById(id));
		model.addAttribute("profile",true);
		return "mahasiswa";
	}
	
	@RequestMapping(value="/edit/mahasiswa/{id}", method = RequestMethod.GET)
	public String editToUser(@PathVariable Integer id,Model model,User mahasiswa,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) return "redirect:/login";
		if(user.getId()!=id) id = user.getId();
		model.addAttribute("mahasiswa", userService.getById(id));
		return "edit_mahasiswa";
	}
	
	@RequestMapping(value="/edit/mahasiswa", method = RequestMethod.POST)
	public String editUser(User mahasiswa,HttpServletRequest request){
		User user = userService.getById(mahasiswa.getId());
		String nama = request.getParameter("nama");
		String alamat = request.getParameter("alamat");
		String no_telepon = request.getParameter("no_telepon");
		String jenis_kelamin = request.getParameter("jenis_kelamin");
		user.setNama(nama);
		user.setAlamat(alamat);
		user.setNo_telepon(no_telepon);
		user.setJenis_kelamin(jenis_kelamin);
		userService.saveOrUpdate(user);
		return "redirect:/mahasiswa/profile/"+mahasiswa.getId();
	}
	
	@RequestMapping(value="/edit/password", method = RequestMethod.POST)
	public String editPassword(User mahasiswa,HttpServletRequest request){
		User user = userService.getById(mahasiswa.getId());
		String password = request.getParameter("password");
		String hashed = passwords.encode(password);
		user.setPassword(hashed);
		userService.saveOrUpdate(user);
		return "redirect:/edit/mahasiswa/"+mahasiswa.getId();
	}
}
