package com.himsi.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.himsi.models.Pengumuman;
import com.himsi.models.User;
import com.himsi.services.PengumumanService;
import com.himsi.services.RoleService;
import com.himsi.services.UserService;

@Controller
public class HomeController {
	private UserService userService;
	private RoleService roleService;
	private PengumumanService pengumumanService;
	
	
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public PengumumanService getPengumumanService() {
		return pengumumanService;
	}
	@Autowired
	public void setPengumumanService(PengumumanService pengumumanService) {
		this.pengumumanService = pengumumanService;
	}
	
	@RequestMapping(value="/form/pengumuman", method = RequestMethod.GET)
	public String formPengumuman(Model model){
		model.addAttribute("pengumuman", new Pengumuman());
		return "form_pengumuman";
	}
	
	@RequestMapping(value="/form/pengumuman", method = RequestMethod.POST)
	public String createPengumuman(@RequestParam("file") MultipartFile file,Model model, Pengumuman pengumuman,HttpServletRequest request){
		if (!file.isEmpty()) {
			pengumuman.setFile_path(file.getOriginalFilename());
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File("D:/Perkuliahan/Semester 6/PROSI/Projek/himsi/src/main/resources/static/file/"+name)));
				stream.write(bytes);
				stream.close();
				System.out.println(stream);
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		User user = (User) request.getSession().getAttribute("user");
		pengumuman.setPengurus(user.getPengurus());
		pengumuman.setWaktu_publish_pengumuman(dtf.format(now));
		pengumumanService.saveOrUpdate(pengumuman);
		model.addAttribute("alert",true);
		model.addAttribute("pengumuman", new Pengumuman());
		return "form_pengumuman";
	}
	
	@RequestMapping(value="/pengumuman/{id}", method = RequestMethod.GET)
	public String getPengumuman(@PathVariable Integer id,Model modal){
		modal.addAttribute("pengumuman", pengumumanService.getById(id));
		return "pengumuman";
	}
	
	@RequestMapping(value="/all/pengumuman", method = RequestMethod.GET)
	public String allPengumuman(Model modal){
		modal.addAttribute("allPengumuman", pengumumanService.getAllPengumuman());
		return "all_pengumuman";
	}
	
	
	
	@RequestMapping("/divisi_pendidikan")
	public String divisiPendidikanDanPenelitian()
	{
		
		return "divisi_pendidikan&penelitian";
	}
	@RequestMapping("/divisi_agama")
	public String diviiAgamaDanKerohanian()
	{
		
		return "divisi_agama&kerohanian";
	}
	
	@RequestMapping("/divisi_seni")
	public String divisiSeniDanPeralatan()
	{
		
		return "divisi_seni&peralatan";
	}
	
	@RequestMapping("/divisi_pengabdian")
	public String divisiPengabdianMasyarakat()
	{
		
		return "divisi_pengabdian_masyarakat";
	}
	
}
