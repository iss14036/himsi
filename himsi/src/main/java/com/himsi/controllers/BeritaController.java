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

import com.himsi.models.Berita;
import com.himsi.models.User;
import com.himsi.services.BeritaService;
import com.himsi.services.DivisiService;
import com.himsi.services.PengurusService;
import com.himsi.services.UserService;

@Controller
public class BeritaController {
	private BeritaService beritaService;
	private UserService userService;
	private PengurusService pengurusService;
	private DivisiService divisiService;
	public BeritaService getBeritaService() {
		return beritaService;
	}
	@Autowired
	public void setBeritaService(BeritaService beritaService) {
		this.beritaService = beritaService;
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
	
	public String getDeskSingkat(String deskripsi){
		String[] singkat = deskripsi.split(" ");
		String kata= "";
		for(int i=0; i<singkat.length; i++){
			if(i==10) break;
			kata+=singkat[i];
			kata+=" ";
		}
		kata+="...";
		return kata;
		
	}
	
	@RequestMapping(value="/form/berita", method = RequestMethod.GET)
	public String formBerita(Model model){
		model.addAttribute("berita", new Berita());
		return "form_berita";
	}
	

	@RequestMapping(value="/all/berita", method = RequestMethod.GET)
	public String allBerita(Model modal){
		modal.addAttribute("allBerita", beritaService.getAllBeritaAccepted());
		return "all_berita";
	}
	
	@RequestMapping(value="/form/berita", method = RequestMethod.POST)
	public String createBerita(@RequestParam("images") MultipartFile file,Model model,Berita berita,HttpServletRequest request){
		if (!file.isEmpty()) {
			berita.setFoto(file.getOriginalFilename());
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File("D:/Perkuliahan/Semester 6/PROSI/Projek/himsi/src/main/resources/static/image/berita/"+name)));
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
		berita.setWaktu_publish_berita(dtf.format(now));
		berita.setPengurus(user.getPengurus());
		Integer idP = user.getPengurus().getId();
		if(idP>=1 && idP<=2) {
			berita.setStatus_berita("accepted");
		}
		else{
			berita.setDivisi(user.getPengurus().getDivisi());
			berita.setStatus_berita("pending");
		}
		berita.setIsi_singkat_berita(getDeskSingkat(berita.getIsi_berita()));
		beritaService.saveOrUpdate(berita);
		model.addAttribute("berita", new Berita());
		model.addAttribute("alert", true);
		return "form_berita";
	}
	
	@RequestMapping(value="/berita/{id}", method = RequestMethod.GET)
	public String lihatBerita(@PathVariable Integer id,Model model){
		model.addAttribute("berita", beritaService.getById(id));
		return "berita";
	}
}
