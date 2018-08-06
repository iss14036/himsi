package com.himsi.controllers;

import java.text.ParseException;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.himsi.models.Berita;
import com.himsi.models.User;
import com.himsi.services.BeritaService;
import com.himsi.services.PengajuanDanaService;
import com.himsi.services.PengumumanService;
import com.himsi.services.RoleService;
import com.himsi.services.UserService;

@Controller
public class IndexController {
	private UserService userService;
	private RoleService roleService;
	private PengumumanService pengumumanService;
	private BeritaService beritaService;
	private PengajuanDanaService proposalService;
	public void cekLogin(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			
		}
	}
	
	@RequestMapping("")
	public String index(Model model){
		List<Berita> beritas = beritaService.getAllBeritaImgTop3();
		model.addAttribute("allBerita", beritaService.getTop6());
		for(int i=0; i<beritas.size(); i++){
			model.addAttribute("berita"+(i+1), beritas.get(i));
		}		
		return "index";
	}
	
	@RequestMapping("/berita/guest/{id}")
	public String lihatBeritaGuest(@PathVariable Integer id, Model model){
		model.addAttribute("berita", beritaService.getById(id));
		return "berita_guest";
	}
	
	@RequestMapping("home")
	public String home(HttpServletRequest request,Model model) throws ParseException{
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			model.addAttribute("sessionn", user.getRole().getDeskripsi_role());
			model.addAttribute("allPengumuman", pengumumanService.getAllPengumumanTop15());
			model.addAttribute("userUltah",userService.getAllUltah());
			model.addAttribute("title", "Home");
			model.addAttribute("navigation", "home");
			model.addAttribute("allBerita", beritaService.getTop6());
			model.addAttribute("notification", proposalService.getAllPendingByBendahara().size());
			model.addAttribute("validation", beritaService.getAllBeritaByStatus("pending").size()+proposalService.getAllPengajuanDanaByStatus("pending").size());
			return "home";
		}
		return "login";
	}
	
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/about")
	public String about(){
		return "about";
	}
	
	
	@RequestMapping("/visiMisi")
	public String visiMisi(){
		return "visi_misi";
	}
	
	
	@RequestMapping(value="/user/login", method = RequestMethod.POST)
	public String verifyLogin(HttpServletRequest request,Model model){
		String userId = request.getParameter("username"); 
		String password = request.getParameter("password");
		User user = userService.loginUser(userId, password);
		if(userId.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
			return "redirect:/create/user";
		}
		else if(user==null){
			model.addAttribute("loginError", true);
			return "login";
		}
		else{
			request.getSession().setAttribute("user", user);
			return "redirect:/home";
		}
	}
	
	@RequestMapping(value="/user/login", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest request,Model model){
		return "login";
	}
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "login";
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

	public PengumumanService getPengumumanService() {
		return pengumumanService;
	}
	@Autowired
	public void setPengumumanService(PengumumanService pengumumanService) {
		this.pengumumanService = pengumumanService;
	}

	public BeritaService getBeritaService() {
		return beritaService;
	}
	@Autowired
	public void setBeritaService(BeritaService beritaService) {
		this.beritaService = beritaService;
	}

	public PengajuanDanaService getProposalService() {
		return proposalService;
	}
	@Autowired
	public void setProposalService(PengajuanDanaService proposalService) {
		this.proposalService = proposalService;
	}
	
	
	
	
}
