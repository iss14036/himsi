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
import com.himsi.models.Saldo;
import com.himsi.models.User;
import com.himsi.models.IuranBulanan;
import com.himsi.services.LaporanKeuanganService;
import com.himsi.services.RoleService;
import com.himsi.services.SaldoService;
import com.himsi.services.IuranBulananService;
import com.himsi.services.UserService;

@Controller
public class IuranBulananController {
	private UserService userService;
	private RoleService roleService;
	private LaporanKeuanganService laporanService;
	private IuranBulananService IuranBulananService;
	private SaldoService saldoService;
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
	public SaldoService getSaldoService() {
		return saldoService;
	}
	@Autowired
	public void setSaldoService(SaldoService saldoService) {
		this.saldoService = saldoService;
	}
	@RequestMapping(value = "/pembayaran/{id}", method = RequestMethod.GET)
	public String sudahBayar(@PathVariable Integer id,HttpServletRequest request) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		IuranBulanan IuranBulanan = IuranBulananService.getIuranBulananById(id);
		IuranBulanan.setTanggal_pembayaran(dtf.format(now));
		IuranBulanan.setStatus("Sudah Lunas");
		IuranBulanan = IuranBulananService.saveOrUpdate(IuranBulanan);
		LaporanKeuangan lKeuangan = IuranBulanan.getLaporanKeuangan();
		lKeuangan.setTotal(lKeuangan.getTotal()+IuranBulanan.getTotal_bayar());
		laporanService.saveOrUpdate(lKeuangan);
		try{
			Saldo saldoo = saldoService.getSaldo();
			Saldo saldo = new Saldo();
			saldo.setLast_update(dtf.format(now));
			saldo.setTotal_saldo(saldoo.getTotal_saldo()+3000);
			saldo.setIuranBulanan(IuranBulanan);
			saldoService.saveOrUpdate(saldo);
		}
		catch(NullPointerException ie){
			Saldo saldo = new Saldo();
			saldo.setLast_update(dtf.format(now));
			saldo.setTotal_saldo(3000);
			saldo.setIuranBulanan(IuranBulanan);
			saldoService.saveOrUpdate(saldo);
		}
		return "redirect:/daftar/pembayaran/"+IuranBulanan.getLaporanKeuangan().getId();
	}
	
	@RequestMapping(value = "/daftar/iuranBulanan", method = RequestMethod.GET)
	public String iuranBulanan(Model model,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("daftarPembayaran", IuranBulananService.getAllByIdUser(user.getId()));
		return "iuran_bulanan";
	}
	
}
