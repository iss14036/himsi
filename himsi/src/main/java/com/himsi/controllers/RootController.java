package com.himsi.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.himsi.SmtpMailSender;
import com.himsi.models.IuranBulanan;
import com.himsi.services.IuranBulananService;

@Controller
public class RootController {
	@Autowired
	private SmtpMailSender smtpMailSender;
	@Autowired
	private IuranBulananService IuranBulananService;
	
	@RequestMapping("/send-mail/{id}")
	public String sendMailPrivate(@PathVariable int id) throws MessagingException{
		IuranBulanan IuranBulanan = IuranBulananService.getIuranBulananById(id);
		String kata = 	"Kepada Saudara "+IuranBulanan.getUser().getNama()+"<br/><br/>"+
						"Waktu untuk pembayaran iuran anda akan berakhir, silahkan melakukan pembayaran anda kebendahara kelas.<br/>"+
						"Jika tidak, maka anda akan kena denda sebesar Rp.2000,-.<br/><br/>"+
						"Terima Kasih.";
		smtpMailSender.send(IuranBulanan.getUser().getEmail(), "Peringatan Keterlambatan Pembayaran Iuran", kata);		
		return "redirect:/daftar/pembayaran/"+IuranBulanan.getLaporanKeuangan().getId();
	}
	
}
