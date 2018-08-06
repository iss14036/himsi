package com.himsi.models;

import java.sql.Date;

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
@Table(name="t_pengumuman")
public class Pengumuman {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@Column(nullable = false)
	private String judul_pengumuman;
	@Column(nullable = false)
	private String isi_pengumuman;
	private String waktu_publish_pengumuman;
	private Date last_update_pengumuman;
	private String file_path;
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
	public String getJudul_pengumuman() {
		return judul_pengumuman;
	}
	public void setJudul_pengumuman(String judul_pengumuman) {
		this.judul_pengumuman = judul_pengumuman;
	}
	public String getIsi_pengumuman() {
		return isi_pengumuman;
	}
	public void setIsi_pengumuman(String isi_pengumuman) {
		this.isi_pengumuman = isi_pengumuman;
	}
	public String getWaktu_publish_pengumuman() {
		return waktu_publish_pengumuman;
	}
	public void setWaktu_publish_pengumuman(String string) {
		this.waktu_publish_pengumuman = string;
	}
	public Date getLast_update_pengumuman() {
		return last_update_pengumuman;
	}
	public void setLast_update_pengumuman(Date last_update_pengumuman) {
		this.last_update_pengumuman = last_update_pengumuman;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
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
