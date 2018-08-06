package com.himsi.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="t_berita")
public class Berita {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_berita;
	@Column(nullable = false)
	private String judul_berita;
	@Column(nullable = false)
	private String isi_berita;
	private String isi_singkat_berita;
	private String waktu_publish_berita;
	private String last_update_berita;
	private String foto;
	private String status_berita;	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_id")
	private Pengurus pengurus;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_divisi_id")
	private Divisi divisi;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_approved_id")
	private Pengurus pengurusApproval;
	
	
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;
	public Integer getId_berita() {
		return id_berita;
	}
	public void setId_berita(Integer id_berita) {
		this.id_berita = id_berita;
	}
	public String getJudul_berita() {
		return judul_berita;
	}
	public void setJudul_berita(String judul_berita) {
		this.judul_berita = judul_berita;
	}
	public String getIsi_berita() {
		return isi_berita;
	}
	public void setIsi_berita(String isi_berita) {
		this.isi_berita = isi_berita;
	}
	
	public String getIsi_singkat_berita() {
		return isi_singkat_berita;
	}
	public void setIsi_singkat_berita(String isi_singkat_berita) {
		this.isi_singkat_berita = isi_singkat_berita;
	}
	public String getWaktu_publish_berita() {
		return waktu_publish_berita;
	}
	public void setWaktu_publish_berita(String waktu_publish_berita) {
		this.waktu_publish_berita = waktu_publish_berita;
	}
	public String getLast_update_berita() {
		return last_update_berita;
	}
	public void setLast_update_berita(String last_update_berita) {
		this.last_update_berita = last_update_berita;
	}
	public Divisi getDivisi() {
		return divisi;
	}
	public void setDivisi(Divisi divisi) {
		this.divisi = divisi;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getStatus_berita() {
		return status_berita;
	}
	public void setStatus_berita(String status_berita) {
		this.status_berita = status_berita;
	}
	public Pengurus getPengurus() {
		return pengurus;
	}
	public void setPengurus(Pengurus pengurus) {
		this.pengurus = pengurus;
	}
	public Pengurus getPengurusApproval() {
		return pengurusApproval;
	}
	public void setPengurusApproval(Pengurus pengurusApproval) {
		this.pengurusApproval = pengurusApproval;
	}
	
	
	
}
