package com.himsi.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.himsi.models.StrukturOrganisasi;
import com.himsi.models.User;
import com.himsi.services.SOrganisasiService;
import com.himsi.services.UserService;

@Controller
public class StrukturOrganisasiSController {
	private SOrganisasiService organisasiService;
	private UserService userService;
	public SOrganisasiService getOrganisasiService() {
		return organisasiService;
	}
	@Autowired
	public void setOrganisasiService(SOrganisasiService organisasiService) {
		this.organisasiService = organisasiService;
	}
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/organisasi/create", method = RequestMethod.GET)
	public String listOrganisasi(HttpServletRequest request,Model model){
		model.addAttribute("daftarStruktur", organisasiService.getAll());
		model.addAttribute("strukturOrganisasi", new StrukturOrganisasi());
		return "daftar_struktur_organisasi";
	}
	
	@RequestMapping(value="/organisasi/create", method = RequestMethod.POST)
	public String createOrganisasi(@RequestParam("image") MultipartFile file,HttpServletRequest request,Model model,StrukturOrganisasi strukturOrganisasi){
		if (!file.isEmpty()) {
			strukturOrganisasi.setGambar_organisasi(file.getOriginalFilename());
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File("D:/Perkuliahan/Semester 6/PROSI/Projek/himsi/src/main/resources/static/image/struktur_organisasi/"+name)));
				stream.write(bytes);
				stream.close();
				System.out.println(stream);
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		strukturOrganisasi.setPengurus(user.getPengurus());
		organisasiService.saveOrUpdate(strukturOrganisasi);
		model.addAttribute("daftarStruktur", organisasiService.getAll());
		model.addAttribute("strukturOrganisasi", new StrukturOrganisasi());
		model.addAttribute("alertTrue", true);
		return "daftar_struktur_organisasi";
	}
	
	@RequestMapping(value="/organisasi/{id}", method = RequestMethod.GET)
	public String lihatOrganisasi(@PathVariable Integer id, HttpServletRequest request,Model model){
		model.addAttribute("organisasi", organisasiService.getById(id));
		return "organisasi";
	}
}
