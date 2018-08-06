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
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="t_program_kerja")
public class ProgramKerja {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@Column(nullable = false)
	private String subjek_program_kerja;
	private String file_program_kerja;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_id")
	private Pengurus pengurus;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_divisi_id")
	private Divisi divisi;
	@OneToMany(mappedBy = "programKerja")
    private Set<PengajuanDana> danas;
	
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubjek_program_kerja() {
		return subjek_program_kerja;
	}
	public void setSubjek_program_kerja(String subjek_program_kerja) {
		this.subjek_program_kerja = subjek_program_kerja;
	}
	public String getFile_program_kerja() {
		return file_program_kerja;
	}
	public void setFile_program_kerja(String string) {
		this.file_program_kerja = string;
	}
	public Pengurus getPengurus() {
		return pengurus;
	}
	public void setPengurus(Pengurus pengurus) {
		this.pengurus = pengurus;
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
	
	
	
}
