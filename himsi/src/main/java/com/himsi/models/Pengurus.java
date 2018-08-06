package com.himsi.models;

import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name="t_pengurus")
public class Pengurus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String jabatan;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_divisi_id")
    private Divisi divisi;
	
	@OneToOne(mappedBy = "pengurus",cascade = CascadeType.ALL)
    private User user;
	
	@OneToMany(mappedBy = "pengurus")
    private Set<Berita> beritas;
	
	@OneToMany(mappedBy = "pengurus")
    private Set<ProgramKerja> program_kerja;
	
	@OneToMany(mappedBy = "pengurus")
    private Set<Pengumuman> pengumumans;
	
	@OneToMany(mappedBy = "pengurus")
    private Set<LaporanKeuangan> laporan_keuangans;
	
	@OneToMany(mappedBy = "pengurus")
    private Set<StrukturOrganisasi> struktur_organisasi;
	
	@OneToMany(mappedBy = "pengurus")
    private Set<PengajuanDana> pengajuanDanas;
	
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getJabatan() {
		return jabatan;
	}


	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}


	public Divisi getDivisi() {
		return divisi;
	}


	public void setDivisi(Divisi divisi) {
		this.divisi = divisi;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<Berita> getBeritas() {
		return beritas;
	}


	public void setBeritas(Set<Berita> beritas) {
		this.beritas = beritas;
	}


	public Set<Pengumuman> getPengumumans() {
		return pengumumans;
	}


	public void setPengumumans(Set<Pengumuman> pengumumans) {
		this.pengumumans = pengumumans;
	}


	public Set<LaporanKeuangan> getLaporan_keuangans() {
		return laporan_keuangans;
	}


	public void setLaporan_keuangans(Set<LaporanKeuangan> laporan_keuangans) {
		this.laporan_keuangans = laporan_keuangans;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Set<ProgramKerja> getProgram_kerja() {
		return program_kerja;
	}


	public void setProgram_kerja(Set<ProgramKerja> program_kerja) {
		this.program_kerja = program_kerja;
	}


	public Set<StrukturOrganisasi> getStruktur_organisasi() {
		return struktur_organisasi;
	}


	public void setStruktur_organisasi(Set<StrukturOrganisasi> struktur_organisasi) {
		this.struktur_organisasi = struktur_organisasi;
	}


	public Set<PengajuanDana> getPengajuanDanas() {
		return pengajuanDanas;
	}


	public void setPengajuanDanas(Set<PengajuanDana> pengajuanDanas) {
		this.pengajuanDanas = pengajuanDanas;
	}
	
	
	
}
