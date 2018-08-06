package com.himsi.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.himsi.models.Berita;
import com.himsi.models.LaporanKeuangan;
import com.himsi.models.PengajuanDana;
import com.himsi.models.Saldo;
import com.himsi.models.User;
import com.himsi.services.BeritaService;
import com.himsi.services.LaporanKeuanganService;
import com.himsi.services.PengajuanDanaService;
import com.himsi.services.ProgramKerjaService;
import com.himsi.services.SaldoService;
import com.himsi.services.UserService;

@Controller
public class ValidationController {
	private UserService userService;
	private ProgramKerjaService programKerjaService;
	private PengajuanDanaService proposalService;
	private LaporanKeuanganService lKService;
	private SaldoService saldoService;
	private BeritaService beritaService;
	
	
	public PengajuanDanaService getProposalService() {
		return proposalService;
	}
	@Autowired
	public void setProposalService(PengajuanDanaService proposalService) {
		this.proposalService = proposalService;
	}
	public BeritaService getBeritaService() {
		return beritaService;
	}
	@Autowired
	public void setBeritaService(BeritaService beritaService) {
		this.beritaService = beritaService;
	}
	public LaporanKeuanganService getlKService() {
		return lKService;
	}
	@Autowired
	public void setlKService(LaporanKeuanganService lKService) {
		this.lKService = lKService;
	}
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public ProgramKerjaService getProgramKerjaService() {
		return programKerjaService;
	}
	@Autowired
	public void setProgramKerjaService(ProgramKerjaService programKerjaService) {
		this.programKerjaService = programKerjaService;
	}
	public SaldoService getSaldoService() {
		return saldoService;
	}
	@Autowired
	public void setSaldoService(SaldoService saldoService) {
		this.saldoService = saldoService;
	}
	@RequestMapping(value="/validation/create", method = RequestMethod.GET)
	public String lihatValidasi(Model modal, HttpServletRequest request){
		modal.addAttribute("allProposal",proposalService.getAllPengajuanDana());
		modal.addAttribute("allBerita",beritaService.getAllBerita());		
		return "validation";
	}
	
	@RequestMapping(value="/proposal/accept/{id}", method = RequestMethod.GET)
	public String acceptPengajuanDana(@PathVariable Integer id,Model modal, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		PengajuanDana proposal = proposalService.getById(id);
		proposal.setStatus("accepted");
		proposal.setPengurusApproved(user.getPengurus());
		proposalService.saveOrUpdate(proposal);
		return "redirect:/validation/create";
	}
	
	@RequestMapping(value="/proposal/reject/{id}", method = RequestMethod.GET)
	public String rejectPengajuanDana(@PathVariable Integer id,Model modal, HttpServletRequest request){
		PengajuanDana proposal = proposalService.getById(id);
		proposal.setStatus("rejected");
		proposalService.saveOrUpdate(proposal);
		return "redirect:/validation/create";
	}
	
	@RequestMapping(value="/proposal/terima", method = RequestMethod.GET)
	public String lihatProposal(Model modal){
		modal.addAttribute("allProposal",proposalService.getAllPengajuanDana());	
		return "terima_proposal";
	}
	
	@RequestMapping(value="/proposal/terima/{id}", method = RequestMethod.GET)
	public String terimaProposal(@PathVariable Integer id,Model modal, HttpServletRequest request){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		User user = (User) request.getSession().getAttribute("user");
		PengajuanDana proposal = proposalService.getById(id);
		proposal.setStatus_terima("accepted");
		proposal.setPengurusTerima(user.getPengurus());
		proposal = proposalService.saveOrUpdate(proposal);
		LaporanKeuangan laporanKeuangan = new LaporanKeuangan();
		laporanKeuangan.setDeskripsi("Pengeluaran pada proposal : "+proposal.getSubjek_pengajuan_dana());
		laporanKeuangan.setPengurus(user.getPengurus());
		laporanKeuangan.setTanggal(dtf.format(now));
		laporanKeuangan.setTipe_laporan("pengeluaran");
		laporanKeuangan.setTotal(proposal.getTotal_dana());
		laporanKeuangan.setPengajuanDana(proposal);
		laporanKeuangan = lKService.saveOrUpdate(laporanKeuangan);
		Saldo saldo = saldoService.getSaldo();
		saldo.setTotal_saldo(saldo.getTotal_saldo()-laporanKeuangan.getTotal());
		saldo.setPengajuanDana(proposal);
		saldoService.saveOrUpdate(saldo);
		return "redirect:/proposal/terima";
	}
	
	@RequestMapping(value="/berita/accept/{id}", method = RequestMethod.GET)
	public String acceptBerita(@PathVariable Integer id,Model modal, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		Berita berita = beritaService.getById(id);
		berita.setStatus_berita("accepted");
		berita.setPengurusApproval(user.getPengurus());
		beritaService.saveOrUpdate(berita);
		return "redirect:/validation/create";
	}
	
	@RequestMapping(value="/berita/reject/{id}", method = RequestMethod.GET)
	public String rejectBerita(@PathVariable Integer id,Model modal, HttpServletRequest request){
		Berita berita = beritaService.getById(id);
		berita.setStatus_berita("rejected");
		beritaService.saveOrUpdate(berita);
		return "redirect:/validation/create";
	}
}
