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

import com.himsi.models.PengajuanDana;
import com.himsi.models.ProgramKerja;
import com.himsi.models.User;
import com.himsi.services.PengajuanDanaService;
import com.himsi.services.ProgramKerjaService;
import com.himsi.services.UserService;

@Controller
public class ProgramKerjaController {
	private UserService userService;
	private ProgramKerjaService programKerjaService;
	private PengajuanDanaService proposalService;
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public ProgramKerjaService getProgramKerjaSevice() {
		return programKerjaService;
	}
	@Autowired
	public void setProgramKerjaSevice(ProgramKerjaService programKerjaSevice) {
		this.programKerjaService = programKerjaSevice;
	}
	
	public ProgramKerjaService getProgramKerjaService() {
		return programKerjaService;
	}
	@Autowired	
	public void setProgramKerjaService(ProgramKerjaService programKerjaService) {
		this.programKerjaService = programKerjaService;
	}
	public PengajuanDanaService getProposalService() {
		return proposalService;
	}
	@Autowired
	public void setProposalService(PengajuanDanaService proposalService) {
		this.proposalService = proposalService;
	}
	
	@RequestMapping(value="/programKerja/create", method = RequestMethod.GET)
	public String lihatProgramKerja(Model modal, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		modal.addAttribute("programKerja",new ProgramKerja());
		modal.addAttribute("programKerjas",programKerjaService.getAllProgramKerjaByDivisi(user.getPengurus().getDivisi().getId()));	
		return "form_programkerja";
	}
	
	@RequestMapping(value="/programKerja/create", method = RequestMethod.POST)
	public String createProgramKerja(@RequestParam("file") MultipartFile file,Model modal, HttpServletRequest request,ProgramKerja programKerja){
		if (!file.isEmpty()) {
			programKerja.setFile_program_kerja(file.getOriginalFilename());
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File("D:/Perkuliahan/Semester 6/PROSI/Projek/himsi/src/main/resources/static/file/program_kerja/"+name)));
				stream.write(bytes);
				stream.close();
				System.out.println(stream);
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		programKerja.setDivisi(user.getPengurus().getDivisi());
		programKerja.setPengurus(user.getPengurus());
		programKerjaService.saveOrUpdate(programKerja);
		modal.addAttribute("programKerja",new ProgramKerja());
		modal.addAttribute("programKerjas",programKerjaService.getAllProgramKerjaByDivisi(user.getPengurus().getDivisi().getId()));	
		modal.addAttribute("alertTrue",true);
		return "form_programkerja";
	}
	
	@RequestMapping(value="/proposal/create/{id}", method = RequestMethod.GET)
	public String listPengajuanDana(@PathVariable Integer id,Model modal, HttpServletRequest request){
		ProgramKerja programKerja = programKerjaService.getById(id);
		modal.addAttribute("proposal",new PengajuanDana());
		modal.addAttribute("idProgramKerja",id);
		modal.addAttribute("daftarProposal",proposalService.getAllPengajuanDanaByPK(programKerja));	
		return "daftar_proposal";
	}
	
	@RequestMapping(value="/proposal/create/{id}", method = RequestMethod.POST)
	public String createPengajuanDana(@RequestParam("file") MultipartFile file,@PathVariable Integer id,Model modal, HttpServletRequest request,PengajuanDana proposal){
		if (!file.isEmpty()) {
			proposal.setFile_pengajuan(file.getOriginalFilename());
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File("D:/Perkuliahan/Semester 6/PROSI/Projek/himsi/src/main/resources/static/file/pengajuan_dana/"+name)));
				stream.write(bytes);
				stream.close();
				System.out.println(stream);
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		ProgramKerja programKerja = programKerjaService.getById(id);
		proposal.setProgramKerja(programKerja);
		proposal.setStatus("pending");
		proposal.setStatus_terima("pending");
		proposal.setProgramKerja(programKerja);
		proposal.setPengurus(user.getPengurus());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		proposal.setTanggal_buat(dtf.format(now));
		proposalService.saveOrUpdate(proposal);
		return "redirect:/proposal/create/{id}";
	}
	
	@RequestMapping(value="/proposal/{id}", method = RequestMethod.GET)
	public String liatPengajuanDana(@PathVariable Integer id,Model modal){
		modal.addAttribute("proposal", proposalService.getById(id));
		return "proposal";
	}
	
}
