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
@Table(name="t_struktur_organisasi")
public class StrukturOrganisasi {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String judul_organisasi;
	private String gambar_organisasi;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_id")
	private Pengurus pengurus;
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJudul_organisasi() {
		return judul_organisasi;
	}
	public void setJudul_organisasi(String judul_organisasi) {
		this.judul_organisasi = judul_organisasi;
	}
	public String getGambar_organisasi() {
		return gambar_organisasi;
	}
	public void setGambar_organisasi(String gambar_organisasi) {
		this.gambar_organisasi = gambar_organisasi;
	}
	public Pengurus getPengurus() {
		return pengurus;
	}
	public void setPengurus(Pengurus pengurus) {
		this.pengurus = pengurus;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
