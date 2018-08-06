package com.himsi.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="t_divisi")	
public class Divisi {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String nama_divisi;
	@OneToMany(mappedBy = "divisi")
	private Set<Pengurus> pengurus;
	@OneToMany(mappedBy = "divisi")
	private Set<Berita> beritas;
	@OneToMany(mappedBy="divisi")
	private Set<ProgramKerja> program_kerja;
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNama_divisi() {
		return nama_divisi;
	}
	public void setNama_divisi(String nama_divisi) {
		this.nama_divisi = nama_divisi;
	}
	public Set<Pengurus> getPengurus() {
		return pengurus;
	}
	public void setPengurus(Set<Pengurus> pengurus) {
		this.pengurus = pengurus;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
