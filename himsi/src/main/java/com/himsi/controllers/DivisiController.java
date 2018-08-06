package com.himsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.himsi.services.BeritaService;
import com.himsi.services.PengurusService;
import com.himsi.services.ProgramKerjaService;
import com.himsi.services.SOrganisasiService;
import com.himsi.services.UserService;

@Controller
public class DivisiController {
	private UserService userService;
	private PengurusService pengurusService;
	private BeritaService beritaService;
	private SOrganisasiService organisasiService;
	private ProgramKerjaService pService;
	
	
	public ProgramKerjaService getpService() {
		return pService;
	}
	@Autowired
	public void setpService(ProgramKerjaService pService) {
		this.pService = pService;
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

	public BeritaService getBeritaService() {
		return beritaService;
	}
	@Autowired
	public void setBeritaService(BeritaService beritaService) {
		this.beritaService = beritaService;
	}

	public SOrganisasiService getOrganisasiService() {
		return organisasiService;
	}
	@Autowired
	public void setOrganisasiService(SOrganisasiService organisasiService) {
		this.organisasiService = organisasiService;
	}

	@RequestMapping("/pendidikan")
	public String pendidikan(Model model){
		model.addAttribute("programKerja",pService.getAllProgramKerjaByDivisi(1));
		model.addAttribute("daftarPengurus", userService.getAllUserByDivisi(1));
		model.addAttribute("daftarBerita", beritaService.getAllBeritaByIdDivisi(1));
		return "pendidikan";
	}
	
	@RequestMapping("/agama")
	public String agama(Model model){
		model.addAttribute("programKerja",pService.getAllProgramKerjaByDivisi(2));
		model.addAttribute("daftarPengurus", userService.getAllUserByDivisi(2));
		model.addAttribute("daftarBerita", beritaService.getAllBeritaByIdDivisi(2));
		return "agama";
	}
	
	@RequestMapping("/seni")
	public String seni(Model model){
		model.addAttribute("programKerja",pService.getAllProgramKerjaByDivisi(3));
		model.addAttribute("daftarPengurus", userService.getAllUserByDivisi(3));
		model.addAttribute("daftarBerita", beritaService.getAllBeritaByIdDivisi(3));
		return "seni";
	}
	
	@RequestMapping("/pengabdian")
	public String pengabdian(Model model){
		model.addAttribute("programKerja",pService.getAllProgramKerjaByDivisi(4));
		model.addAttribute("daftarPengurus", userService.getAllUserByDivisi(4));
		model.addAttribute("daftarBerita", beritaService.getAllBeritaByIdDivisi(4));
		return "pengabdian";
	}
}
