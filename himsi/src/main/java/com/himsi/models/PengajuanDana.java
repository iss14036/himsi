package com.himsi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="t_pengajuan_dana")
public class PengajuanDana {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String subjek_pengajuan_dana;
	private String deskripsi_pengajuan_dana;
	private Integer total_dana;
	public String file_pengajuan;
	private String status;
	private String status_terima;
	private String tanggal_buat;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_program_kerja_id")
	private ProgramKerja programKerja;
	@OneToOne(mappedBy = "pengajuanDana")
	private LaporanKeuangan laporanKeuangan;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_pengaju_id")
	private Pengurus pengurus;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_approved_id")
	private Pengurus pengurusApproved;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_pengurus_receipt_id")
	private Pengurus pengurusTerima;
	@OneToOne(mappedBy = "pengajuanDana")
    private Saldo saldo;
	
	
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubjek_pengajuan_dana() {
		return subjek_pengajuan_dana;
	}

	public void setSubjek_pengajuan_dana(String subjek_pengajuan_dana) {
		this.subjek_pengajuan_dana = subjek_pengajuan_dana;
	}

	public String getDeskripsi_pengajuan_dana() {
		return deskripsi_pengajuan_dana;
	}

	public void setDeskripsi_pengajuan_dana(String deskripsi_pengajuan_dana) {
		this.deskripsi_pengajuan_dana = deskripsi_pengajuan_dana;
	}

	public Integer getTotal_dana() {
		return total_dana;
	}

	public void setTotal_dana(Integer total_dana) {
		this.total_dana = total_dana;
	}

	public ProgramKerja getProgramKerja() {
		return programKerja;
	}

	public void setProgramKerja(ProgramKerja programKerja) {
		this.programKerja = programKerja;
	}

	public LaporanKeuangan getLaporanKeuangan() {
		return laporanKeuangan;
	}
	
	public void setLaporanKeuangan(LaporanKeuangan laporanKeuangan) {
		this.laporanKeuangan = laporanKeuangan;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus_terima() {
		return status_terima;
	}

	public void setStatus_terima(String status_terima) {
		this.status_terima = status_terima;
	}
	
	public String getFile_pengajuan() {
		return file_pengajuan;
	}

	public void setFile_pengajuan(String file_pengajuan) {
		this.file_pengajuan = file_pengajuan;
	}

	public String getTanggal_buat() {
		return tanggal_buat;
	}

	public void setTanggal_buat(String tanggal_buat) {
		this.tanggal_buat = tanggal_buat;
	}

	public Pengurus getPengurus() {
		return pengurus;
	}

	public void setPengurus(Pengurus pengurus) {
		this.pengurus = pengurus;
	}

	public Pengurus getPengurusApproved() {
		return pengurusApproved;
	}

	public void setPengurusApproved(Pengurus pengurusApproved) {
		this.pengurusApproved = pengurusApproved;
	}

	public Pengurus getPengurusTerima() {
		return pengurusTerima;
	}

	public void setPengurusTerima(Pengurus pengurusTerima) {
		this.pengurusTerima = pengurusTerima;
	}
	
	
}
