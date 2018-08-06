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
@Table(name="t_iuran_bulanan")
public class IuranBulanan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_user_id")
    private User user;
	private String status;
	private String tanggal_pembayaran;
	private String bulan_iuran;
	public int total_bayar;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_laporan_keuangan_id")
    private LaporanKeuangan laporanKeuangan;
	@OneToOne(mappedBy = "iuranBulanan")
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTanggal_pembayaran() {
		return tanggal_pembayaran;
	}
	public void setTanggal_pembayaran(String tanggal_pembayaran) {
		this.tanggal_pembayaran = tanggal_pembayaran;
	}
	public LaporanKeuangan getLaporanKeuangan() {
		return laporanKeuangan;
	}
	public void setLaporanKeuangan(LaporanKeuangan laporanKeuangan) {
		this.laporanKeuangan = laporanKeuangan;
	}
	public String getBulan_iuran() {
		return bulan_iuran;
	}
	public void setBulan_iuran(String bulan_iuran) {
		this.bulan_iuran = bulan_iuran;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public int getTotal_bayar() {
		return total_bayar;
	}
	public void setTotal_bayar(int total_bayar) {
		this.total_bayar = total_bayar;
	}
	public Saldo getSaldo() {
		return saldo;
	}
	public void setSaldo(Saldo saldo) {
		this.saldo = saldo;
	}
	
	
	
}
