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
@Table(name="t_laporan_keuangan")
public class LaporanKeuangan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String deskripsi;
	private String tanggal;
	private String tipe_laporan;
	private int total;
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;
	
	@OneToMany(mappedBy = "laporanKeuangan")
    private Set<IuranBulanan> iuranBulanan;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_id")
    private Pengurus pengurus;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengajuan_dana_id")
	private PengajuanDana pengajuanDana;
	
	
	public String getTipe_laporan() {
		return tipe_laporan;
	}
	public void setTipe_laporan(String tipe_laporan) {
		this.tipe_laporan = tipe_laporan;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeskripsi() {
		return deskripsi;
	}
	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
	public String getTanggal() {
		return tanggal;
	}
	public void setTanggal(String string) {
		this.tanggal = string;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public PengajuanDana getPengajuanDana() {
		return pengajuanDana;
	}
	public void setPengajuanDana(PengajuanDana pengajuanDana) {
		this.pengajuanDana = pengajuanDana;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Pengurus getPengurus() {
		return pengurus;
	}
	public void setPengurus(Pengurus pengurus) {
		this.pengurus = pengurus;
	}
	public Set<IuranBulanan> getIuranBulanan() {
		return iuranBulanan;
	}
	public void setIuranBulanan(Set<IuranBulanan> iuranBulanan) {
		this.iuranBulanan = iuranBulanan;
	}
	
	
}
