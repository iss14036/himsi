package com.himsi.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="t_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@Column(unique = true, nullable = false)
	private String id_user;
	private String nama;
	private String password;
	private String tanggal_lahir;
	private String jenis_kelamin;
	private String alamat;
	private String email;
	private String no_telepon;
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_id")
    private Pengurus pengurus;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_role_id")
    private Role role;
	@OneToMany(mappedBy = "user")
    private Set<IuranBulanan> iuranBulanan;
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTanggal_lahir() {
		return tanggal_lahir;
	}
	public void setTanggal_lahir(String tanggal_lahir) {
		this.tanggal_lahir = tanggal_lahir;
	}
	public String getJenis_kelamin() {
		return jenis_kelamin;
	}
	public void setJenis_kelamin(String jenis_kelamin) {
		this.jenis_kelamin = jenis_kelamin;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNo_telepon() {
		return no_telepon;
	}
	public void setNo_telepon(String no_telepon) {
		this.no_telepon = no_telepon;
	}
	public Pengurus getPengurus() {
		return pengurus;
	}
	public void setPengurus(Pengurus pengurus) {
		this.pengurus = pengurus;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Set<IuranBulanan> getIuranBulanan() {
		return iuranBulanan;
	}
	public void setIuranBulanan(Set<IuranBulanan> iuranBulanan) {
		this.iuranBulanan = iuranBulanan;
	}
	
	
	
	
}
