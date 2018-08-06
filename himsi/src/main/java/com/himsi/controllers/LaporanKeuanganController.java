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

import com.himsi.models.LaporanKeuangan;
import com.himsi.models.IuranBulanan;
import com.himsi.models.User;
import com.himsi.services.LaporanKeuanganService;
import com.himsi.services.RoleService;
import com.himsi.services.SaldoService;
import com.himsi.services.IuranBulananService;
import com.himsi.services.UserService;

@Controller
public class LaporanKeuanganController {
	private UserService userService;
	private RoleService roleService;
	private LaporanKeuanganService laporanService;
	private IuranBulananService IuranBulananService;
	private SaldoService saldoService;
	
	public SaldoService getSaldoService() {
		return saldoService;
	}
	@Autowired
	public void setSaldoService(SaldoService saldoService) {
		this.saldoService = saldoService;
	}
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
	public LaporanKeuanganService getLaporanService() {
		return laporanService;
	}
	@Autowired
	public void setLaporanService(LaporanKeuanganService laporanService) {
		this.laporanService = laporanService;
	}
	public IuranBulananService getIuranBulananService() {
		return IuranBulananService;
	}
	@Autowired
	public void setIuranBulananService(IuranBulananService IuranBulananService) {
		this.IuranBulananService = IuranBulananService;
	}
	
	@RequestMapping("/laporanKeuangan")
	public String laporanKeuangan(Model modal){
		modal.addAttribute("laporanKeuangan", new LaporanKeuangan());
		return "laporan_keuangan";
	}
	
	@RequestMapping(value="/laporan", method = RequestMethod.GET)
	public String createLaporan(Model modal){
		modal.addAttribute("laporanKeuangan",laporanService.getAllLaporanByTipe("pemasukan"));
		modal.addAttribute("laporan",new LaporanKeuangan());
		try{
			modal.addAttribute("saldo", "Total Saldo : Rp."+saldoService.getSaldo().getTotal_saldo());
		}catch(NullPointerException ie){
			modal.addAttribute("saldo", "Total Saldo : Rp.0,-");
				
		}
		return "laporan_keuangan";
	}
	@RequestMapping(value="/laporan", method = RequestMethod.POST)
	public String createIuranBulanan(Model modal,LaporanKeuangan laporan,HttpServletRequest request){
		String bulan = request.getParameter("bulan");
		User user = (User) request.getSession().getAttribute("user");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		laporan.setTanggal(dtf.format(now));
		laporan.setPengurus(user.getPengurus());
		laporan.setTipe_laporan("pemasukan");
		LaporanKeuangan saved = laporanService.saveOrUpdate(laporan);
		IuranBulanan IuranBulanan = new IuranBulanan();
		IuranBulanan.setLaporanKeuangan(saved);
		IuranBulananService.createAllPembayaran(IuranBulanan,bulan);
		return "redirect:/laporan";
	}
	
	@RequestMapping(value="/daftar/pembayaran/{id}", method = RequestMethod.GET)
	public String daftarPembayaran(@PathVariable Integer id,Model modal){
		LaporanKeuangan saved = laporanService.getById(id);
		modal.addAttribute("daftarPembayaran", IuranBulananService.getAllByLaporan(saved));
		modal.addAttribute("mhsSudahBayar", "Yang telah lunas : "+IuranBulananService.getMahasiswaSdhBayar(saved)+" Orang");
		modal.addAttribute("mhsBelumBayar", "Yang belum lunas : "+IuranBulananService.getMahasiswaBlmBayar(saved)+" Orang");
		modal.addAttribute("totalMhs", "Total Mahasiswa : "+IuranBulananService.getAllByLaporan(saved).size()+" Orang");
		return "daftar_pembayaran";
	}
}	
